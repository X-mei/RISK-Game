package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {
  Boolean isFirst;
  final DataInputStream input;
  final DataOutputStream output;
  final Socket socket;
  final Player player;
  private RuleChecker checker;

  public ClientHandler(Player ply, Socket s,DataInputStream in, DataOutputStream out ){
    this.input = in;
    this.output = out;
    this.player = ply;
    this.socket = s;
  }
  @Override
  public void run(){
    String received;
    String playerInfo;
    String name = ply.getName();
    Integer code = ply.getCode();
    playerInfo = name + " " + code;
    output.writeUTF(playerInfo);
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
             //use Territory map to create the map and assign
           }
          output.writeUTF("ready to start");
           break;
        }
      }catch(IOException e){
        e.printStackTrace();
      }
    }
    while(true){
      try{
        String prompt = "You are the "+ ply.getName() + " player, What would you like to do?" + "(M)ove\n" + "(A)ttack\n" + "(D)one";
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
        if(chr == 'D'){
          break;
        }
        else if(chr == 'M'){
          this.checker = new RouteChecker(new UnitChecker(null));
          Check(checker,name);
        }
        else if(chr == 'A'){
          this.checker = new NeighborChecker(new UnitChecker(null));
          Check(checker,name);
        }
      }catch(Exception e){
         e.getStackTrace();
      }
    }

    try{
      this.input.close();
      this.output.close();
    }catch(IOException e){
      e.printStackTrace();
    }
  }
  public Boolean Check(RuleChecker checker, String name){
    String InputAct = input.readUTF();
    Action act = new Action(name, InputAct);
    String Msg = checker.checkAction(act);
    if(Msg != null){
      output.writeUTF(Msg);
      return false;
    }
    return true;
  }
}
