package client;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Platform;
import javafx.stage.Stage;

public class PlayGameController {
    PlayGameView playGameView;
    Client client;
    String promptMsg;
    String promptInstruction;
    String actionType;
    int i = 1;
    boolean press = false;
    HashMap<String, ArrayList<String>> territories;
    HashMap<String, String> units;

    PlayGameController(PlayGameView playGameView, Client client) {
        this.playGameView = playGameView;
        this.client = client;
        actionType = "";
        promptMsg = "";
        promptInstruction = "";
        this.territories = new HashMap<String, ArrayList<String>>();
        this.units = new HashMap<String, String>();
        upgradeAction();
        moveAction();
        attackAction();
        techAction();
        doneAction();
        confirmAction();
        continueAction();
        exitAction();
    }

    public void upgradeAction() {
        playGameView.upgrade.setOnAction(e -> {
            client.sendInstruction(playGameView.upgrade.getText().substring(0, 1));
            String prompt = client.recvInstruction();
            actionType = "U";
            playGameView.promptUpdate.setVisible(true);
            playGameView.input.setVisible(true);
            playGameView.confirm.setVisible(true);
            playGameView.choicesOfSource.setVisible(true);
            playGameView.choicesOfLevel1.setVisible(true);
            playGameView.choicesOfLevel2.setVisible(true);
            playGameView.countPrompt.setVisible(true);
            playGameView.lvPrompt1.setVisible(true);
            playGameView.lvPrompt2.setVisible(true);
            playGameView.terrPrompt.setVisible(true);
            playGameView.error.setVisible(false);
            playGameView.upgrade.setVisible(false);
            playGameView.move.setVisible(false);
            playGameView.attack.setVisible(false);
            playGameView.tech.setVisible(false);
            playGameView.done.setVisible(false);
        });
    }

    public void moveAction() {
        playGameView.move.setOnAction(e -> {
            client.sendInstruction(playGameView.move.getText().substring(0, 1));
            actionType = "M";
            String prompt = client.recvInstruction();
            playGameView.choicesOfLevel2.setVisible(true);
            playGameView.choicesOfDest.setVisible(true);
            playGameView.choicesOfSource.setVisible(true);
            playGameView.promptAM.setVisible(true);
            playGameView.input.setVisible(true);
            playGameView.srcPrompt.setVisible(true);
            playGameView.destPrompt.setVisible(true);
            playGameView.countPrompt.setVisible(true);
            playGameView.lvPrompt2.setVisible(true);
            playGameView.confirm.setVisible(true);
            playGameView.error.setVisible(false);
            playGameView.upgrade.setVisible(false);
            playGameView.move.setVisible(false);
            playGameView.attack.setVisible(false);
            playGameView.tech.setVisible(false);
            playGameView.done.setVisible(false);
        });
    }

    public void attackAction() {
        playGameView.attack.setOnAction(e -> {

            client.sendInstruction(playGameView.attack.getText().substring(0, 1));
            String prompt = client.recvInstruction();
            actionType = "A";
            playGameView.choicesOfLevel2.setVisible(true);
            playGameView.choicesOfDest.setVisible(true);
            playGameView.choicesOfSource.setVisible(true);
            playGameView.promptAM.setVisible(true);
            playGameView.input.setVisible(true);
            playGameView.confirm.setVisible(true);
            playGameView.srcPrompt.setVisible(true);
            playGameView.destPrompt.setVisible(true);
            playGameView.countPrompt.setVisible(true);
            playGameView.lvPrompt2.setVisible(true);
            playGameView.error.setVisible(false);
            playGameView.upgrade.setVisible(false);
            playGameView.move.setVisible(false);
            playGameView.attack.setVisible(false);
            playGameView.tech.setVisible(false);
            playGameView.done.setVisible(false);
        });
    }

    public void techAction() {
        playGameView.tech.setOnAction(e -> {
            press = true;
            if(i > 5){
                playGameView.errorTech.setVisible(true);
                return;
            }
            i++;
            client.sendInstruction(playGameView.tech.getText().substring(0, 1));
            String prompt = client.recvInstruction();
            playGameView.tech.setVisible(false);
            playGameView.errorTech.setVisible(false);
        });
    }

    public void doneAction() {
        playGameView.done.setOnAction(e -> {
            press = false;
            playGameView.tech.setVisible(true);
            client.sendInstruction(playGameView.done.getText().substring(0, 1));
            String prompt = client.recvInstruction();
            if (prompt.equals("Wait for other players to perform the action...")) {

                // set wait scene, deactivate all buttons
                playGameView.deactivateAll();

                App.setTimeout(() -> {
                    promptMsg = client.recvBoardPrompt();
                    String info = client.recvBoardPrompt();

                    if (promptMsg.equals("You lost all your territories!")) {
                        Platform.runLater(() -> {
                            playGameView.prompt1.setText(promptMsg);
                            playGameView.prompt2.setVisible(true);
                            playGameView.prompt2.setText(info);
                            playGameView.exitOrContinue();
                        });
                    } else if (promptMsg.equals("The game ends.")) {
                        Platform.runLater(() -> {
                            playGameView.prompt1.setText(promptMsg);
                            playGameView.prompt2.setVisible(true);
                            playGameView.prompt2.setText(info);
                            playGameView.exitOrContinue();
                            playGameView.continueWatch.setVisible(false);
                        });
                    } else {
                        // clear the hashmap
                        territories.clear();
                        units.clear();
                        parseTerritory(info);
                        promptInstruction = client.recvBoardPrompt();
                        Platform.runLater(() -> {
                            playGameView.activateAll();
                            playGameView.prompt1.setText(promptMsg);
                            playGameView.prompt2.setText(promptInstruction);
                        });
                    }
                }, 200);

            } else {
                playGameView.prompt2.setText(prompt);
            }
        });
    }

    public void confirmAction() {
        playGameView.confirm.setOnAction(e -> {
            String str = "";
            if(actionType.equals("A") || actionType.equals("M")){
                if(playGameView.choicesOfSource.getValue() == null ||
                        playGameView.choicesOfDest.getValue() == null ||
                        playGameView.choicesOfLevel2.getValue() == null){
                    playGameView.error.setVisible(true);
                    return;
                }
                String Source = playGameView.choicesOfSource.getValue().toString();
                System.out.println(Source);
                String Dest  = playGameView.choicesOfDest.getValue().toString();
                System.out.println(Dest);
                String Level = playGameView.choicesOfLevel2.getValue().toString();
                System.out.println(Level);
                str = Source + " " + Dest + " "+ playGameView.input.getText() + " " + Level;
                System.out.println(str);
            }
            else if(actionType.equals("U")){
                if(playGameView.choicesOfSource.getValue() == null ||
                        playGameView.choicesOfLevel1.getValue() == null ||
                        playGameView.choicesOfLevel2.getValue() == null){
                    playGameView.error.setVisible(true);
                    return;
                }
                String Terri = playGameView.choicesOfSource.getValue().toString();
                String Level1  = playGameView.choicesOfLevel1.getValue().toString();
                String Level2 = playGameView.choicesOfLevel2.getValue().toString();
                str = Terri + " " + Level1 + " " + playGameView.input.getText() + " " + Level2;
            }
            if(client.checkActionStr(str)){
                client.sendInstruction(str);
                String prompt2 = client.recvInstruction();
                playGameView.choicesOfLevel1.setVisible(false);
                playGameView.choicesOfLevel2.setVisible(false);
                playGameView.choicesOfDest.setVisible(false);
                playGameView.choicesOfSource.setVisible(false);
                playGameView.prompt2.setText(prompt2);
                playGameView.srcPrompt.setVisible(false);
                playGameView.destPrompt.setVisible(false);
                playGameView.countPrompt.setVisible(false);
                playGameView.lvPrompt1.setVisible(false);
                playGameView.lvPrompt2.setVisible(false);
                playGameView.terrPrompt.setVisible(false);
                playGameView.input.setVisible(false);
                playGameView.confirm.setVisible(false);
                playGameView.promptAM.setVisible(false);
                playGameView.error.setVisible(false);
                playGameView.promptUpdate.setVisible(false);
                if(press == true){
                    playGameView.tech.setVisible(false);
                }
                else{
                    playGameView.tech.setVisible(true);
                }
                playGameView.upgrade.setVisible(true);
                playGameView.move.setVisible(true);
                playGameView.attack.setVisible(true);

                playGameView.done.setVisible(true);
            }
            else{
                playGameView.error.setVisible(true);
            }
        });
    }

    public void continueAction() {
        playGameView.continueWatch.setOnAction(e -> {
            client.sendInstruction("c");
            promptMsg = client.recvBoardPrompt();
            playGameView.continueWatch.setVisible(false);
            playGameView.prompt1.setText(promptMsg);
            playGameView.prompt2.setVisible(false);

            App.setTimeout(() -> {
                while (true) {
                    promptMsg = client.recvBoardPrompt();
                    if (promptMsg.equals("The game ends.")) {
                        Platform.runLater(() -> {
                            promptInstruction = client.recvBoardPrompt();
                            playGameView.prompt1.setText(promptMsg);
                            playGameView.prompt2.setText(promptInstruction);
                            playGameView.prompt2.setVisible(true);
                            playGameView.exitOrContinue();
                            playGameView.continueWatch.setVisible(false);
                        });
                        break;
                    } else {
                        Platform.runLater(() -> {
                            playGameView.prompt1.setText(promptMsg);
                        });
                    }
                }

            }, 200);
        });
    }

    public void exitAction() {
        playGameView.exitGame.setOnAction(e -> {
            client.sendInstruction("e");
            // close the window
            Stage stage = (Stage) playGameView.exitGame.getScene().getWindow();
            stage.close();
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
