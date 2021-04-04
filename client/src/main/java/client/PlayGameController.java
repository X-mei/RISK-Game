package client;

import javafx.application.Platform;
import javafx.stage.Stage;

public class PlayGameController {
    PlayGameView playGameView;
    Client client;
    String promptMsg;
    String promptInstruction;

    PlayGameController(PlayGameView playGameView, Client client) {
        this.playGameView = playGameView;
        this.client = client;
        promptMsg = "";
        promptInstruction = "";
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
            playGameView.promptUpdate.setVisible(true);
            playGameView.input.setVisible(true);
            playGameView.confirm.setVisible(true);
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
            String prompt = client.recvInstruction();
            playGameView.promptAM.setVisible(true);
            playGameView.input.setVisible(true);
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
            playGameView.promptAM.setVisible(true);
            playGameView.input.setVisible(true);
            playGameView.confirm.setVisible(true);
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
            client.sendInstruction(playGameView.tech.getText().substring(0, 1));
            String prompt = client.recvInstruction();
            playGameView.error.setVisible(false);
        });
    }

    public void doneAction() {
        playGameView.done.setOnAction(e -> {
            client.sendInstruction(playGameView.done.getText().substring(0, 1));
            String prompt = client.recvInstruction();
            if (prompt.equals("Wait for other players to perform the action...")) {

                // set wait scene, deactivate all buttons
                playGameView.deactivateAll();

                App.setTimeout(() -> {
                    promptMsg = client.recvBoardPrompt();
                    promptInstruction = client.recvBoardPrompt();
                    if (promptMsg.equals("You lost all your territories!")) {
                        Platform.runLater(() -> {
                            playGameView.prompt1.setText(promptMsg);
                            playGameView.prompt2.setVisible(true);
                            playGameView.prompt2.setText(promptInstruction);
                            playGameView.exitOrContinue();
                        });
                    } else if (promptMsg.equals("The game ends.")) {
                        Platform.runLater(() -> {
                            playGameView.prompt1.setText(promptMsg);
                            playGameView.prompt2.setVisible(true);
                            playGameView.prompt2.setText(promptInstruction);
                            playGameView.exitOrContinue();
                            playGameView.continueWatch.setVisible(false);
                        });
                    } else {
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
            String str = playGameView.input.getText();
            if(client.checkActionStr(str)){
                client.sendInstruction(str);
                String prompt2 = client.recvInstruction();
                playGameView.prompt2.setText(prompt2);
                playGameView.input.setVisible(false);
                playGameView.confirm.setVisible(false);
                playGameView.promptAM.setVisible(false);
                playGameView.error.setVisible(false);
                playGameView.promptUpdate.setVisible(false);
                playGameView.upgrade.setVisible(true);
                playGameView.move.setVisible(true);
                playGameView.attack.setVisible(true);
                playGameView.tech.setVisible(true);
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
}
