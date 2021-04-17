package client;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Platform;

public class AssignTerrController {
    AssignTerrView assignTerrView;
    PlayGameView playGameView;
    Client client;
    String boardMsg;
    String instructionMsg;
    String playerNum;
    HashMap<String, ArrayList<String>> territories;
    HashMap<String, String> units;
    HashMap<String, String> greyTerrs;
    HashMap<String, String> greyOwners;

    public AssignTerrController(AssignTerrView assignTerrView, Client client, String playerNum) {
        this.assignTerrView = assignTerrView;
        this.playGameView = new PlayGameView(assignTerrView.prompt2.getText());
        playGameView.init(assignTerrView.playerNum);
        this.client = client;
        this.playerNum = playerNum;
        boardMsg = "";
        instructionMsg = "";
        this.territories = new HashMap<String, ArrayList<String>>();
        this.units = new HashMap<String, String>();
        this.greyTerrs = new HashMap<String, String>();
        this.greyOwners = new HashMap<String, String>();
        submitAction();
    }

    public void submitAction() {
        assignTerrView.submit.setOnAction(e -> {

            System.out.println(assignTerrView.input1.getText() + "," +
                    assignTerrView.input2.getText() + "," +
                    assignTerrView.input3.getText());
            String[] inputs;
            if (playerNum.equals("2") || playerNum.equals("3") || playerNum.equals("1") || playerNum.equals("0")) {
                inputs = new String[3];
                inputs[0] = assignTerrView.input1.getText();
                inputs[1] = assignTerrView.input2.getText();
                inputs[2] = assignTerrView.input3.getText();
            } else {
                inputs = new String[2];
                inputs[0] = assignTerrView.input1.getText();
                inputs[1] = assignTerrView.input2.getText();
            }

            if (client.sendAssignTerritory(inputs)) {

                assignTerrView.waitPlayer();
                App.setTimeout(() -> {
                    boardMsg = client.recvBoardPrompt();
                    String info = client.recvBoardPrompt();
                    instructionMsg = client.recvInstruction();

                    parseTerritory(info);

                    Platform.runLater(() -> {
                        playGameView.addPrompt(boardMsg);
                        playGameView.addPrompt2(instructionMsg);
                        playGameView.recvTerrInfo(territories, units);
                        App.root.getChildren().remove(assignTerrView.assignTerrPane);
                        App.root.getChildren().add(playGameView.playGamePane);
                        PlayGameController playGameController = new PlayGameController(playGameView, client);
                    });
                }, 200);

            } else {
                String[] prompts = client.recvPrompts();
                assignTerrView.error.setVisible(true);
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
      while(!strArr[i].equals("")) {
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
      i = i + 2;
      while(i < strArr.length) {
        String[] terrInfo = strArr[i].split(":");
        greyOwners.put(terrInfo[1], terrInfo[0]);
        greyTerrs.put(terrInfo[1], terrInfo[2]);
        i++;
      }
      System.out.println(territories.entrySet());
      System.out.println(units.entrySet());
      System.out.println(greyTerrs.entrySet());
    }
}
