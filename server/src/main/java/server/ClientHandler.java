package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
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
  final HashSet<BasicAction> moveHashSet;
  final HashSet<BasicAction> attackHashSet;
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
    this.moveHashSet = new HashSet<>();
    this.attackHashSet = new HashSet<>();
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
      sendBoardPromptAndRecv();
      //close connection when game over
      CloseConnection();
    }catch(IOException e){
      e.printStackTrace();
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
        int unitsNum = Integer.parseInt(received);
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
  
  public void sendBoardPromptAndRecv() throws IOException {
    try {
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
          output.writeUTF("Wait for other players to perform the action...");
          lock.lock();
          isReady.await();
          lock.unlock();
          break;
        }
        else{
          output.writeUTF("Please enter the action: src dest count");
          String actionInfo = input.readUTF();
          BasicAction act = player.formAction(received, actionInfo);
          if(act == null){
            break;
          }
          if(act.getActionName() == "Move"){
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

  void CloseConnection(){
    try{
      this.input.close();
      this.output.close();
    } catch(IOException e){
      e.printStackTrace();
    }
  }

}
