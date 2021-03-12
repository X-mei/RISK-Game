package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import shared.Action;
import shared.Attack;
import shared.BasicAction;
import shared.Board;
import shared.Move;
import shared.Player;
import shared.Territory;

public class ClientHandler extends Thread {
  Boolean isFirst;
  final DataInputStream input;
  final DataOutputStream output;
  final Socket socket;
  final Player player;
  final String name;
  final Integer code;
  private RuleChecker checker;

  public ClientHandler(Player ply, Socket s,DataInputStream in, DataOutputStream out ){
    this.input = in;
    this.output = out;
    this.player = ply;
    this.socket = s;
    this.name = ply.getName();
    this.code = ply.getInteger();
  }

  @Override
  public void run(){
    String playerInfo;
    playerInfo = name + " " + code;
    try{
      output.writeUTF(playerInfo);
      Board board = MakeChoice();
      DoAction(board);
      
      
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
             Board board = new Board(chr);
             output.writeUTF("ready to start");
             return board;
           }
        }
      }catch(IOException e){
        e.printStackTrace();
      }
    }
  }


  void DoAction(Board board){
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
          try{
            String actionInfo = input.readUTF();
            Action act = player.formAction(Choice, actionInfo);
            if(act == null){
              break;
            }
            Territory src = getTerritory(board, act.getSrc());
            Territory dest = getTerritory(board, act.getDest());
            if(act.getActionName() == "Move"){
              this.checker = new RouteChecker(new UnitChecker(null,src,dest),src,dest);
              if(Check(act,checker,src,dest)){

              }
            }
            if(act.getActionName() == "Attack"){
              this.checker = new NeighborChecker(new UnitChecker(null,src,dest),src, dest);
              Check(act,checker,src,dest);
            }
  
          }catch(Exception e){
            e.printStackTrace();
          }
          
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

  public Boolean Check(Action act, RuleChecker checker, Territory src, Territory dest) throws IOException {
    String Msg = checker.checkAction(act);
    if(Msg != null){
      output.writeUTF(Msg);
      return false;
    }
    return true;
  }

  Territory getTerritory(Board board,String terrName){
    for(Territory terr: board.getTerrList()){
      if(terr.getTerritoryName() == terrName){
        return terr;
      }
    }
  }

}
