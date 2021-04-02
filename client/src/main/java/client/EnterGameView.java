package client;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class EnterGameView {
    AnchorPane enterGamePane;
    Label prompt;
    RadioButton r1;
    RadioButton r2;
    ToggleGroup tg;
    Button confirm;

    public EnterGameView() {
        this.enterGamePane = new AnchorPane();
        this.prompt = new Label();
        this.r1 = new RadioButton("enter a previous game");
        this.r2 = new RadioButton("create a new game");
        this.tg = new ToggleGroup();
        this.confirm = new Button();
    }

    public void init() {
        // prompt label
        prompt.setLayoutX(20);
        prompt.setLayoutY(75);
        prompt.setText("Do you want to enter a previous game or enter a new game?");
        enterGamePane.getChildren().add(prompt);

        // choice
        r1.setToggleGroup(tg);
        r1.setLayoutX(20);
        r1.setLayoutY(150);
        r2.setToggleGroup(tg);
        r2.setLayoutX(20);
        r2.setLayoutY(200);
        enterGamePane.getChildren().add(r1);
        enterGamePane.getChildren().add(r2);

        // confirm button
        confirm.setLayoutX(200);
        confirm.setLayoutY(200);
        enterGamePane.getChildren().add(confirm);
    }
}
