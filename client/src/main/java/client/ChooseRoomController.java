package client;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;

public class ChooseRoomController {
    ChooseRoomView chooseRoomView;
    AssignTerrView assignTerrView;
    Client client;
    String choice;
    String prompt;
    String promptAssign;

    public ChooseRoomController(ChooseRoomView chooseRoomView, Client client) {
        this.chooseRoomView = chooseRoomView;
        this.assignTerrView = new AssignTerrView();
        this.client = client;
        prompt = "";
        prompt = "";
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
        chooseRoomView.confirm.setOnAction(e -> {

            System.out.println(choice.substring(0, 1));
            client.sendGameRoom(choice.substring(0, 1));
            assignTerrView.init(choice.substring(0, 1));
            App.root.getChildren().remove(chooseRoomView.chooseRoomPane);
            App.root.getChildren().add(assignTerrView.assignTerrPane);

            App.setTimeout(() -> {
                String playerNum = client.recvNameAndNum();
                prompt = client.recvPrompt();
                int status = client.recvStartStatus();
                promptAssign = client.recvAssignPrompt();
                //TODO: add prompts
                String[] prompts = client.recvPrompts();
                Platform.runLater(() -> {
                    assignTerrView.addPrompt2(prompt);
                    assignTerrView.addPrompt3(promptAssign);
                    assignTerrView.addPromptOfTerritory(prompts);
                    AssignTerrController assignTerrController = new AssignTerrController(assignTerrView, client, choice.substring(0,1));
                });
            }, 200);


        });
    }

}
