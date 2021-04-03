package client;

public class AssignTerrController {
    AssignTerrView assignTerrView;
    PlayGameView playGameView;
    Client client;

    public AssignTerrController(AssignTerrView assignTerrView, Client client) {
        this.assignTerrView = assignTerrView;
        this.playGameView = new PlayGameView(assignTerrView.prompt2.getText());
        playGameView.init(assignTerrView.playerNum);
        this.client = client;
        submitAction();
    }

    public void submitAction() {
        assignTerrView.submit.setOnAction(e -> {

            System.out.println(assignTerrView.input1.getText() + "," +
                    assignTerrView.input2.getText() + "," +
                    assignTerrView.input3.getText());
            if (client.sendAssignTerritory(assignTerrView.input1.getText(),
                    assignTerrView.input2.getText(),
                    assignTerrView.input3.getText())) {
                String boardMsg = client.recvBoardPrompt();
                String instructionMsg = client.recvInstruction();
                playGameView.addPrompt(boardMsg);
                playGameView.addPrompt2(instructionMsg);
                App.root.getChildren().remove(assignTerrView.assignTerrPane);
                App.root.getChildren().add(playGameView.playGamePane);
                PlayGameController playGameController = new PlayGameController(playGameView, client);
            } else {
                assignTerrView.error.setVisible(true);
            }

        });
    }
}
