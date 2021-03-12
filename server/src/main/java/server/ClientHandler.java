package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;

import shared.BasicAction;
import shared.Board;
import shared.MapFactory;
import shared.Player;
import shared.UnitsFactory;

public class ClientHandler extends Thread {
  Boolean isFirst;
  final DataInputStream input;
  final DataOutputStream output;
  final Socket socket;
  final Player player;
  final String name;
  final Integer code;
  final MapFactory mapFac;
  final UnitsFactory UnitsFac;
  final HashSet<BasicAction> moveHashSet;
  final HashSet<BasicAction> attackHashSet;

  public ClientHandler(Player ply, Socket s,DataInputStream in, DataOutputStream out ){
    this.input = in;
    this.output = out;
    this.player = ply;
    this.socket = s;
    this.name = player.getName();
    this.code = player.getCode();
    this.mapFac = new MapFactory();
    this.UnitsFac = new UnitsFactory();
    this.moveHashSet = new HashSet<>();
    this.attackHashSet = new HashSet<>();
  }

  @Override
  public void run(){
    String playerInfo;
    playerInfo = name + " " + code;
    try{
      output.writeUTF(playerInfo);
      Board board = MakeChoice();
      StoreAction(board);

      //close connection when game over
      CloseConnection();
    }catch(IOException e){
      e.printStackTrace();
    }
    
  }

  Board MakeChoice(){
    String received;
    while(true){
      try{
        if(code == 1){
          output.writeUTF("How many players do you want to include? (2-5)\n");
          received = input.readUTF();
          char chr =  received.charAt(0);
          if(received.length()>1 || chr <'2' || chr >'5'){
            output.writeUTF("Invalid input! please select between 2-5");
            continue;
           }
           else{
             int playerNum = chr - 48;
             Board board = new Board(playerNum, mapFac, UnitsFac);
             output.writeUTF("ready to start");
             return board;
           }
        }
      }catch(IOException e){
        e.printStackTrace();
      }
    }
  }


  void StoreAction(Board board){
    while(true){
      try{
        String prompt = "You are the "+ player.getName() + " player, What would you like to do?" + "(M)ove\n" + "(A)ttack\n" + "(D)one";
        output.writeUTF(prompt);
        String input_choice = input.readUTF();
        String Choice = input_choice.toUpperCase();
        char chr = Choice.charAt(0);
        if(Choice.length() != 1){
          output.writeUTF("Please input one character.");
          continue;
        }
        if(chr != 'D' && chr != 'M' && chr != 'A'){
          output.writeUTF("Invalid action choice! please try again.");
          continue;
        }
        else{
            String actionInfo = input.readUTF();
            BasicAction act = player.formAction(Choice, actionInfo);
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
      }catch(Exception e){
        e.printStackTrace();
      }
    }
  } 


 void CloseConnection(){
   try{
     this.input.close();
     this.output.close();
    }catch(IOException e){
      e.printStackTrace();
    }
  }

}
