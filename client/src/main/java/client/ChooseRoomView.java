package client;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class ChooseRoomView {
    AnchorPane chooseRoomPane;
    Label prompt;
    RadioButton r1;
    RadioButton r2;
    RadioButton r3;
    RadioButton r4;
    ToggleGroup tg;
    Button confirm;

    public ChooseRoomView() {
        this.chooseRoomPane = new AnchorPane();
        this.prompt = new Label();
        this.r1 = new RadioButton("2 people");
        this.r2 = new RadioButton("3 people");
        this.r3 = new RadioButton("4 people");
        this.r4 = new RadioButton("5 people");
        this.tg = new ToggleGroup();
        this.confirm = new Button();
    }

    public void init() {
        // prompt label
        prompt.setLayoutX(20);
        prompt.setLayoutY(75);
        prompt.setText("Which game room (2-5 people) do you want to enter?");
        chooseRoomPane.getChildren().add(prompt);

        // input radio button
        r1.setToggleGroup(tg);
        r1.setLayoutX(20);
        r1.setLayoutY(150);
        r2.setToggleGroup(tg);
        r2.setLayoutX(20);
        r2.setLayoutY(200);
        r3.setToggleGroup(tg);
        r3.setLayoutX(20);
        r3.setLayoutY(250);
        r4.setToggleGroup(tg);
        r4.setLayoutX(20);
        r4.setLayoutY(300);
        chooseRoomPane.getChildren().add(r1);
        chooseRoomPane.getChildren().add(r2);
        chooseRoomPane.getChildren().add(r3);
        chooseRoomPane.getChildren().add(r4);

        // confirm button
        confirm.setLayoutX(200);
        confirm.setLayoutY(400);
        confirm.setText("confirm");
        chooseRoomPane.getChildren().add(confirm);

    }
}
