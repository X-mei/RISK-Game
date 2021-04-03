package client;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SendRoomIDView {
    AnchorPane roomIDPane;
    Label prompt;
    TextField roomID;
    Button confirm;
    Label error;

    public SendRoomIDView() {
        this.roomIDPane = new AnchorPane();
        this.prompt = new Label();
        this.roomID = new TextField();
        this.confirm = new Button();
        this.error = new Label();
    }

    public void init() {
        // prompt label
        prompt.setLayoutX(20);
        prompt.setLayoutY(75);
        prompt.setText("Please input room id.");
        roomIDPane.getChildren().add(prompt);

        // roomID textField
        roomID.setLayoutX(20);
        roomID.setLayoutY(110);
        roomID.setPromptText("room id");
        roomID.setFocusTraversable(false);
        roomIDPane.getChildren().add(roomID);

        // confirm button
        confirm.setLayoutX(200);
        confirm.setLayoutY(200);
        confirm.setText("confirm");
        roomIDPane.getChildren().add(confirm);

        // error
        error.setLayoutX(200);
        error.setLayoutY(250);
        error.setText("Wrong room id.");
        error.setVisible(false);
        roomIDPane.getChildren().add(error);
    }
}
