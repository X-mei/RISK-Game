package client;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;

public class SendRoomIDController {
    SendRoomIDView sendRoomIDView;
    AssignTerrView assignTerrView;
    Client client;
    String choice;

    public SendRoomIDController(SendRoomIDView sendRoomIDView, Client client) {
        this.sendRoomIDView = sendRoomIDView;
        this.assignTerrView = new AssignTerrView();
        // TODO: playerNUM
        assignTerrView.init("2");

        this.client = client;
        confirmAction();
    }

    public void confirmAction() {
        sendRoomIDView.confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                choice = sendRoomIDView.roomID.getText();
                if (client.sendRoomID(choice)) {
                    // enter the game
                    String prompt = client.recvNameAndSeq();
                    int status = client.recvStartStatus();
                    if (status == 1) {
                        String promptAssign = client.recvAssignPrompt();
                        assignTerrView.addPrompt2(prompt);
                        assignTerrView.addPrompt3(promptAssign);
                        App.root.getChildren().remove(sendRoomIDView.roomIDPane);
                        App.root.getChildren().add(assignTerrView.assignTerrPane);
                        AssignTerrController assignTerrController = new AssignTerrController(assignTerrView, client);
                    } else {
                        PlayGameView playGameView = new PlayGameView(prompt);
                        playGameView.init();
                        String boardMsg = client.recvBoardPrompt();
                        String instructionMsg = client.recvInstruction();
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
}
