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
        joinAction();
        createAction();
    }

    public void joinAction() {
      enterGameView.previousGame.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            client.answerInfo("p");
            App.root.getChildren().remove(enterGameView.enterGamePane);
            App.root.getChildren().add(sendRoomIDView.roomIDPane);
            SendRoomIDController sendRoomIDController  = new SendRoomIDController(sendRoomIDView, client);
          }
      });
    }

    public void createAction() {
      enterGameView.newGame.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            client.answerInfo("c");
            App.root.getChildren().remove(enterGameView.enterGamePane);
            App.root.getChildren().add(chooseRoomView.chooseRoomPane);
            ChooseRoomController chooseRoomController = new ChooseRoomController(chooseRoomView, client);
          }
      });
  }
}
