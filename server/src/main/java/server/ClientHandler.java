package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import shared.BasicAction;
import shared.Board;
import shared.MapFactory;
import shared.Player;
import shared.Territory;
import shared.UnitsFactory;

public class ClientHandler extends Thread {
  final DataInputStream input;
  final DataOutputStream output;
  final Socket socket;
  final MapFactory mapFac;
  final UnitsFactory UnitsFac;
  final LinkedHashSet<BasicAction> moveHashSet;
  final LinkedHashSet<BasicAction> attackHashSet;
  final Board board;
  final String playerName;
  final Boolean readyFlag;
  final Player player;
  final HashSet<Character> actionSet;
  final Lock lock;
  final Condition isReady;

  public ClientHandler(Socket s, DataInputStream in, DataOutputStream out, Board b, String name, Lock lock, Condition isReady){
    this.input = in;
    this.output = out;
    this.socket = s;
    this.mapFac = new MapFactory();
    this.UnitsFac = new UnitsFactory();
    this.moveHashSet = new LinkedHashSet<BasicAction>();
    this.attackHashSet = new LinkedHashSet<BasicAction>();
    this.board = b;
    this.playerName = name;
    this.readyFlag = false;
    this.player = getPlayer();
    this.actionSet = new HashSet<Character>();
    actionSet.add('D');
    actionSet.add('M');
    actionSet.add('A');
    this.lock = lock;
    this.isReady = isReady;
  }

  @Override
  public void run(){
    String playerInfo;
    playerInfo = playerName;
    try{
      // send playerInfo
      output.writeUTF(playerInfo);
      // ask client to assign territory
      assignTerritory();
      // send board message
      // while loop, check if game ends
      while(!board.checkSinglePlayerLose(playerName)) {
        sendBoardPromptAndRecv();
        updateBoard();
      }
      output.writeUTF("You lost all your territories!");
      // this client lost the game, only send msg and don't recv
      while(board.checkGameEnd().equals("")) {
        sendBoardMsg();
      }
      sendGameEndMsg();
    }catch(IOException e){
      e.printStackTrace();
    }finally {
      //close connection when game over
      closeConnection();
    }
  }

  private Player getPlayer() {
    ArrayList<Player> playerList = board.getPlayers();
    for (Player p: playerList) {
      if (p.getName().equals(playerName)) {
        return p;
      }
    }
    return null;
  }

  public boolean getReadyFlag() {
    return readyFlag;
  }

  /**
   * This function tells the client to assign the units
   * to their territories
   * @throws IOException
   */
  public void assignTerritory() throws IOException {
    try {
      int totalUnits = board.getTotalUnits();
      int unitsSetup = 0;
      String[] promptMsg = board.askUnitSetup(playerName);
      int[] unitsAssign = new int[promptMsg.length];
      int i = 0;
      output.writeUTF("You have total " + totalUnits + " to set up in your territories.");
      while(i < promptMsg.length) {
        output.writeUTF(promptMsg[i] + "You have " + (totalUnits - unitsSetup) + " units left.");
        String received = input.readUTF();
        // TODO : check the interger
        int unitsNum;
        try {
          unitsNum = Integer.parseInt(received);
        } catch(NumberFormatException e) {
          continue;
        }
        if (unitsNum + unitsSetup > totalUnits) {
          continue;
        } else {
          unitsSetup += unitsNum;
          unitsAssign[i] = unitsNum;
          i++;
        }
      }
      int j = 0;
      for(Territory t: player.getTerritoryList()) {
        int[] unitsToAdd = new int[1];
        unitsToAdd[0] = unitsAssign[j];
        board.singleTerritoryUnitSetup(t.getTerritoryName(), unitsToAdd);
        j++;
      }
      output.writeUTF("Wait for other players to assign the units...");
      lock.lock();
      isReady.await();
      lock.unlock();
    } catch(IOException e){
      e.printStackTrace();
    } catch(InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * This function sends the prompt and play one turn
   * @throws IOException
   */
  public void sendBoardPromptAndRecv() throws IOException {
    try {
      moveHashSet.clear();
      attackHashSet.clear();
      String boardMsg = board.displayAllPlayerAllBoard();
      output.writeUTF(boardMsg);
      Boolean valid = true;
      String received = null;
      while(true) {
        String prompt = "";
        if (!valid) {
          prompt += "Invalid input!\n";
        }
        prompt += "You are the " + playerName + " player, what would you like to do?\n(M)ove\n(A)ttack\n(D)one";
        valid = true;
        output.writeUTF(prompt);
        received = input.readUTF();
        char chr =  received.charAt(0);
        if(received.length() != 1){
          valid = false;
          continue;
        }
        if(!actionSet.contains(chr)){
          valid = false;
          continue;
        }
        if(chr == 'D') {
          // rule checker of move and attack actions
          if(board.checkIfActionBoolean(moveHashSet, "Move") && board.checkIfActionBoolean(attackHashSet, "Attack")) {
            output.writeUTF("Wait for other players to perform the action...");
            lock.lock();
            isReady.await();
            lock.unlock();
            break;
          } else {
            moveHashSet.clear();
            attackHashSet.clear();
            valid = false;
            continue;
          }
        }
        else{
          output.writeUTF("Please enter the action: src dest count");
          String actionInfo = input.readUTF();
          // TODO: check the action str
          BasicAction act = player.formAction(received, actionInfo);
          if(act.getActionName().equals("M")){
            moveHashSet.add(act);
          }
          else{
            attackHashSet.add(act);
          }  
        }
      }
    }catch(IOException e){
      e.printStackTrace();
    }catch(InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * This function transmits the two action set to the 
   * board and update the board.
   */
  public void updateBoard() {
    try {
      board.processOneTurnMove(moveHashSet);
      lock.lock();
      isReady.await();
      lock.unlock();
      board.processOneTurnAttackPre(attackHashSet);
      lock.lock();
      isReady.await();
      lock.unlock();
      board.processOneTurnAttackNext(attackHashSet);
      lock.lock();
      isReady.await();
      lock.unlock();
    } catch(InterruptedException e) {
      e.printStackTrace();
    }
    
  }
  
  /**
   * This function only sends the message
   */
  void sendBoardMsg() throws IOException {
    String boardMsg = board.displayAllPlayerAllBoard();
    output.writeUTF(boardMsg);
  }

  /**
   * This function sends the game end message
   */
  void sendGameEndMsg() throws IOException {
    String winner = board.checkGameEnd();
    output.writeUTF(winner + " wins the game!");
  }

  void closeConnection(){
    try{
      this.input.close();
      this.output.close();
    } catch(IOException e){
      e.printStackTrace();
    }
  }

  Boolean checkActionStr(String str){
    if(str == null){
      return false;
    }
    int pos1 = str.indexOf(" ");
    if(pos1 == -1){
      return false;
    }
    String substr1 = str.substring(pos1 + 1);
    substr1 = substr1.trim();
    int pos2 = substr1.indexOf(" ");
    if(pos2 == -1){
      return false;
    }
    String substr2 = str.substring(pos2 + 1);
    for(int i = 0; i < substr2.length(); i++){
      if(!Character.isDigit(substr2.charAt(i))){
        return false;
      }
    }
    return true;
  }
}
