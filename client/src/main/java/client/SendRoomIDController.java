package client;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;

public class SendRoomIDController {
    SendRoomIDView sendRoomIDView;
    Client client;
    String choice;

    public SendRoomIDController(SendRoomIDView sendRoomIDView, Client client) {
        this.sendRoomIDView = sendRoomIDView;
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
                } else {
                    // error pop
                    sendRoomIDView.error.setVisible(true);
                }
            }
        });
    }
}
