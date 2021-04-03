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
    Image map;
    TextField input;
    Button submit;

    public AssignTerrView() {
        this.assignTerrPane = new AnchorPane();
        this.prompt1 = new Label();
        this.prompt2 = new Label();
        this.input = new TextField();
        this.submit = new Button();
    }

    public void init() {
        // show img
        try {
            FileInputStream imageStream = new FileInputStream("/Users/pudding/Documents/duke/2021spring/ECE651/ece651-spr21-risc-group6/client/src/main/resources/lena.bmp");
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
    }

    public void addPrompt2(String prompt) {
        // add prompt2
        prompt1.setVisible(false);
        prompt2.setLayoutX(600);
        prompt2.setLayoutY(75);
        prompt2.setText(prompt);
        assignTerrPane.getChildren().add(prompt2);
    }
}
