package client;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;

public class EnterGameController {
    EnterGameView enterGameView;
    ChooseRoomView chooseRoomView;
    SendRoomIDView sendRoomIDView;
    Client client;
    String choice;

    public EnterGameController(EnterGameView enterGameView, Client client) {
        this.enterGameView = enterGameView;
        this.client = client;
        this.choice = null;
        this.chooseRoomView = new ChooseRoomView();
        chooseRoomView.init();
        this.sendRoomIDView = new SendRoomIDView();
        sendRoomIDView.init();
        chooseAction();
        confirmAction();
    }

    public void chooseAction() {
        enterGameView.tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob,
                                Toggle o, Toggle n) {
                RadioButton rb = (RadioButton)enterGameView.tg.getSelectedToggle();
                if (rb != null) {
                    choice = rb.getText();
                }
            }
        });
    }

    public void confirmAction() {
        enterGameView.confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (choice.equals("enter a previous game")) {
                    client.answerInfo("p");
                    App.root.getChildren().remove(enterGameView.enterGamePane);
                    App.root.getChildren().add(sendRoomIDView.roomIDPane);
                    SendRoomIDController sendRoomIDController  = new SendRoomIDController(sendRoomIDView, client);
                } else {
                    client.answerInfo("c");
                    App.root.getChildren().remove(enterGameView.enterGamePane);
                    App.root.getChildren().add(chooseRoomView.chooseRoomPane);
                    ChooseRoomController chooseRoomController = new ChooseRoomController(chooseRoomView, client);
                }
            }
        });
    }
}
