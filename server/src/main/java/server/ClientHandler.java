package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;

import shared.BasicAction;
import shared.Board;
import shared.MapFactory;
import shared.Player;
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

  public ClientHandler(Socket s, DataInputStream in, DataOutputStream out, Board b, String name){
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
  }

  @Override
  public void run(){
    String playerInfo;
    playerInfo = playerName;
    try{
      // send playerInfo
      output.writeUTF(playerInfo);
      
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
  
  public void sendBoardPromptAndRecv() throws IOException {
    try {
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
    }
  }


  // void StoreAction(){
  //   while(true){
  //     try{
  //       String prompt = "You are the "+ player.getName() + " player, What would you like to do?" + "(M)ove\n" + "(A)ttack\n" + "(D)one";
  //       output.writeUTF(prompt);
  //       String input_choice = input.readUTF();
  //       String Choice = input_choice.toUpperCase();
  //       char chr = Choice.charAt(0);
  //       if(Choice.length() != 1){
  //         output.writeUTF("Please input one character.");
  //         continue;
  //       }
  //       if(chr != 'D' && chr != 'M' && chr != 'A'){
  //         output.writeUTF("Invalid action choice! please try again.");
  //         continue;
  //       }
  //       else{
  //           String actionInfo = input.readUTF();
  //           BasicAction act = player.formAction(Choice, actionInfo);
  //           if(act == null){
  //             break;
  //           }
  //           if(act.getActionName() == "Move"){
  //             moveHashSet.add(act);
  //           }
  //           else{
  //             attackHashSet.add(act);
  //           }  
  //       }
  //     }catch(Exception e){
  //       e.printStackTrace();
  //     }
  //   }
  // } 



  void CloseConnection(){
    try{
      this.input.close();
      this.output.close();
    } catch(IOException e){
      e.printStackTrace();
    }
  }

}
