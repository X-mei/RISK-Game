package client;

import javafx.application.Platform;

public class AssignTerrController {
    AssignTerrView assignTerrView;
    PlayGameView playGameView;
    Client client;
    String boardMsg;
    String instructionMsg;
    String playerNum;

    public AssignTerrController(AssignTerrView assignTerrView, Client client, String playerNum) {
        this.assignTerrView = assignTerrView;
        this.playGameView = new PlayGameView(assignTerrView.prompt2.getText());
        playGameView.init(assignTerrView.playerNum);
        this.client = client;
        this.playerNum = playerNum;
        boardMsg = "";
        instructionMsg = "";
        submitAction();
    }

    public void submitAction() {
        assignTerrView.submit.setOnAction(e -> {

            System.out.println(assignTerrView.input1.getText() + "," +
                    assignTerrView.input2.getText() + "," +
                    assignTerrView.input3.getText());
            String[] inputs;
            if (playerNum.equals("2") || playerNum.equals("3")) {
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

                    Platform.runLater(() -> {
                        playGameView.addPrompt(boardMsg);
                        playGameView.addPrompt2(instructionMsg);
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
}
