package client;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SendRoomIDController {
    SendRoomIDView sendRoomIDView;
    AssignTerrView assignTerrView;
    Client client;
    String choice;
    HashMap<String, ArrayList<String>> territories;
    HashMap<String, String> units;

    public SendRoomIDController(SendRoomIDView sendRoomIDView, Client client) {
        this.sendRoomIDView = sendRoomIDView;
        this.assignTerrView = new AssignTerrView();
        this.client = client;
        this.territories = new HashMap<String, ArrayList<String>>();
        this.units = new HashMap<String, String>();
        confirmAction();
    }

    public void confirmAction() {
        sendRoomIDView.confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                choice = sendRoomIDView.roomID.getText();
                if (client.sendRoomID(choice)) {
                    // enter the game
                    String playerNum = client.recvNameAndNum();
                    String prompt = client.recvPrompt();
                    int status = client.recvStartStatus();
                    if (status == 1) {
                        String promptAssign = client.recvAssignPrompt();
                        String[] prompts = client.recvPrompts();
                        assignTerrView.init(playerNum);
                        assignTerrView.rectPath.setVisible(false);
                        assignTerrView.addPrompt2(prompt);
                        assignTerrView.addPrompt3(promptAssign);
                        assignTerrView.addPromptOfTerritory(prompts);
                        App.root.getChildren().remove(sendRoomIDView.roomIDPane);
                        App.root.getChildren().add(assignTerrView.assignTerrPane);
                        AssignTerrController assignTerrController = new AssignTerrController(assignTerrView, client, playerNum);
                    } else {
                        PlayGameView playGameView = new PlayGameView(prompt);
                        playGameView.init(playerNum);
                        String boardMsg = client.recvBoardPrompt();
                        String info = client.recvBoardPrompt();
                        String instructionMsg = client.recvInstruction();

                        parseTerritory(info);

                        playGameView.addPrompt(boardMsg);
                        playGameView.addPrompt2(instructionMsg);
                        App.root.getChildren().remove(sendRoomIDView.roomIDPane);
                        App.root.getChildren().add(playGameView.playGamePane);
                        PlayGameController playGameController = new PlayGameController(playGameView, client);
                    }
                } else {
                    // error pop
                    sendRoomIDView.error.setVisible(true);
                }
            }
        });
    }

    /**
     * parse the string into hashmap
     */
    public void parseTerritory(String info) {
      String[] strArr = info.split("\n");

      // add own territory
      String pName = strArr[0];
      ArrayList<String> terrs= new ArrayList<String>();
      int i = 1;
      while(!strArr[i].equals("")) {
        String[] terrInfo = strArr[i].split(":");
        terrs.add(terrInfo[0]);
        i++;
        units.put(terrInfo[0], terrInfo[1]);
      }
      territories.put(pName, terrs);
      i = i + 2;
      // add enemy's territory
      while(i < strArr.length) {
        String[] terrInfo = strArr[i].split(":");
        if (territories.containsKey(terrInfo[0])) {
          ArrayList<String> enemyTerrs = territories.get(terrInfo[0]);
          enemyTerrs.add(terrInfo[1]);
          territories.put(terrInfo[0], enemyTerrs);
        } else {
          ArrayList<String> enemyTerrs = new ArrayList<String>();
          enemyTerrs.add(terrInfo[1]);
          territories.put(terrInfo[0], enemyTerrs);
        }
        units.put(terrInfo[1], terrInfo[2]);
        i++;
      }
      System.out.println(territories.entrySet());
      System.out.println(units.entrySet());
    }
}
