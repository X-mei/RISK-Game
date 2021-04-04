package client;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.IOException;

public class AssignTerrView {
    AnchorPane assignTerrPane;
    Label prompt1;
    Label prompt2;
    Label prompt3;
    Label error;
    TextField input1;
    TextField input2;
    TextField input3;
    Button submit;
    String playerNum;

    public AssignTerrView() {
        this.assignTerrPane = new AnchorPane();
        this.prompt1 = new Label();
        this.prompt2 = new Label();
        this.prompt3 = new Label();
        this.error = new Label();
        this.input1 = new TextField();
        this.input2 = new TextField();
        this.input3 = new TextField();
        this.submit = new Button();
    }

    public void init(String playerNum) {
        // show img
        this.playerNum = playerNum;
        try {
            String path = null;
            if (playerNum.equals("2")) {
                path = "/Users/pudding/Documents/duke/2021spring/ECE651/ece651-spr21-risc-group6/client/src/main/resources/2P.JPG";
            } else if (playerNum.equals("3")) {
                path = "/Users/pudding/Documents/duke/2021spring/ECE651/ece651-spr21-risc-group6/client/src/main/resources/3P.JPG";
            } else if (playerNum.equals("4")) {
                path = "/Users/pudding/Documents/duke/2021spring/ECE651/ece651-spr21-risc-group6/client/src/main/resources/4P.JPG";
            } else {
                path = "/Users/pudding/Documents/duke/2021spring/ECE651/ece651-spr21-risc-group6/client/src/main/resources/5P.JPG";
            }
            FileInputStream imageStream = new FileInputStream(path);
            Image image = new Image(imageStream);
            ImageView imageView = new ImageView(image);
            imageView.setX(50);
            imageView.setY(25);
            imageView.setFitHeight(455);
            imageView.setFitWidth(500);
            assignTerrPane.getChildren().add(imageView);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // prompt: wait
        prompt1.setLayoutX(600);
        prompt1.setLayoutY(75);
        prompt1.setText("Wait for other players to join...");
        assignTerrPane.getChildren().add(prompt1);

        // error
        error.setLayoutX(600);
        error.setLayoutY(400);
        error.setText("Wrong, please enter again");
        error.setVisible(false);
        assignTerrPane.getChildren().add(error);
    }

    public void addPrompt2(String prompt) {
        // add prompt2
        prompt1.setVisible(false);
        prompt2.setLayoutX(600);
        prompt2.setLayoutY(75);
        prompt2.setText(prompt);
        assignTerrPane.getChildren().add(prompt2);
    }

    public void addPrompt3(String prompt) {
        prompt3.setLayoutX(600);
        prompt3.setLayoutY(150);
        prompt3.setText(prompt);
        assignTerrPane.getChildren().add(prompt3);

        input1.setLayoutX(600);
        input1.setLayoutY(200);
        assignTerrPane.getChildren().add(input1);

        input2.setLayoutX(600);
        input2.setLayoutY(250);
        assignTerrPane.getChildren().add(input2);

        input3.setLayoutX(600);
        input3.setLayoutY(300);
        assignTerrPane.getChildren().add(input3);

        submit.setLayoutX(600);
        submit.setLayoutY(350);
        submit.setText("submit");
        assignTerrPane.getChildren().add(submit);
    }

    public void waitPlayer() {
        prompt1.setVisible(true);
        prompt1.setText("Wait for other players to assign units...");

        prompt2.setVisible(false);
        prompt3.setVisible(false);
        input1.setVisible(false);
        input2.setVisible(false);
        input3.setVisible(false);
        submit.setVisible(false);
        error.setVisible(false);
    }
}
