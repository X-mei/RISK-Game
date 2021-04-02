package client;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;

public class ChooseRoomController {
    ChooseRoomView chooseRoomView;
    Client client;
    String choice;

    public ChooseRoomController(ChooseRoomView chooseRoomView, Client client) {
        this.chooseRoomView = chooseRoomView;
        this.client = client;
        chooseAction();
        confirmAction();
    }

    public void chooseAction() {
        chooseRoomView.tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob,
                                Toggle o, Toggle n) {
                RadioButton rb = (RadioButton)chooseRoomView.tg.getSelectedToggle();
                if (rb != null) {
                    choice = rb.getText();
                }
            }
        });
    }

    public void confirmAction() {
        chooseRoomView.confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                client.sendGameRoom(choice.substring(0, 1));
            }
        });
    }

}
