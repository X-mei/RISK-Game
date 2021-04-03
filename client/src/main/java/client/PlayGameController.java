package client;

import javafx.stage.Stage;

public class PlayGameController {
    PlayGameView playGameView;
    Client client;

    PlayGameController(PlayGameView playGameView, Client client) {
        this.playGameView = playGameView;
        this.client = client;
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
            playGameView.input.setVisible(true);
            playGameView.confirm.setVisible(true);
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
            playGameView.input.setVisible(true);
            playGameView.confirm.setVisible(true);
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
            playGameView.input.setVisible(true);
            playGameView.confirm.setVisible(true);
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
        });
    }

    public void doneAction() {
        playGameView.done.setOnAction(e -> {
            client.sendInstruction(playGameView.done.getText().substring(0, 1));
            String prompt = client.recvInstruction();
            if (prompt.equals("Wait for other players to perform the action...")) {
                String prompt1 = client.recvBoardPrompt();
                // TODO: continue watching
                if (prompt1.equals("You lost all your territories!")) {
                    String info = client.recvBoardPrompt();
                    playGameView.prompt1.setText(prompt1);
                    playGameView.prompt2.setText(info);
                    playGameView.exitOrContinue();
                } else {
                    String prompt2 = client.recvInstruction();
                    playGameView.prompt1.setText(prompt1);
                    playGameView.prompt2.setText(prompt2);
                }
            } else {
                playGameView.prompt2.setText(prompt);
            }
        });
    }

    public void confirmAction() {
        playGameView.confirm.setOnAction(e -> {
            client.sendInstruction(playGameView.input.getText());
            String prompt2 = client.recvInstruction();
            playGameView.prompt2.setText(prompt2);
            playGameView.input.setVisible(false);
            playGameView.confirm.setVisible(false);
            playGameView.upgrade.setVisible(true);
            playGameView.move.setVisible(true);
            playGameView.attack.setVisible(true);
            playGameView.tech.setVisible(true);
            playGameView.done.setVisible(true);
        });
    }

    public void continueAction() {
        playGameView.continueWatch.setOnAction(e -> {
            client.sendInstruction("c");
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
