package client;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.IOException;

public class PlayGameView {
    AnchorPane playGamePane;
    Label prompt0;
    Label prompt1;
    Label prompt2;
    Button upgrade;
    Button move;
    Button attack;
    Button tech;
    Button done;
    TextField input;
    Button confirm;

    public PlayGameView(String userInfo) {
        this.playGamePane = new AnchorPane();
        this.prompt0 = new Label(userInfo);
        this.prompt1 = new Label();
        this.prompt2 = new Label();
        this.upgrade = new Button();
        this.move = new Button();
        this.attack = new Button();
        this.tech = new Button();
        this.done = new Button();
        this.input = new TextField();
        this.confirm = new Button();
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
            playGamePane.getChildren().add(imageView);
        } catch (IOException e) {
            e.printStackTrace();
        }
        input.setLayoutX(600);
        input.setLayoutY(750);
        input.setVisible(false);
        playGamePane.getChildren().add(input);

        confirm.setLayoutX(600);
        confirm.setLayoutY(780);
        confirm.setVisible(false);
        confirm.setText("confrim");
        playGamePane.getChildren().add(confirm);
    }

    public void addPrompt(String prompt) {
        prompt0.setLayoutX(600);
        prompt0.setLayoutY(50);
        playGamePane.getChildren().add(prompt0);

        prompt1.setLayoutX(600);
        prompt1.setLayoutY(75);
        prompt1.setText(prompt);
        playGamePane.getChildren().add(prompt1);
    }

    public void addPrompt2(String prompt) {
        prompt2.setLayoutX(600);
        prompt2.setLayoutY(400);
        prompt2.setText(prompt);
        playGamePane.getChildren().add(prompt2);

        upgrade.setLayoutX(600);
        upgrade.setLayoutY(450);
        upgrade.setText("Upgrade Soldiers");
        playGamePane.getChildren().add(upgrade);

        move.setLayoutX(600);
        move.setLayoutY(500);
        move.setText("Move");
        playGamePane.getChildren().add(move);

        attack.setLayoutX(600);
        attack.setLayoutY(550);
        attack.setText("Attack");
        playGamePane.getChildren().add(attack);

        tech.setLayoutX(600);
        tech.setLayoutY(600);
        tech.setText("Technical Upgrade");
        playGamePane.getChildren().add(tech);

        done.setLayoutX(600);
        done.setLayoutY(650);
        done.setText("Done");
        playGamePane.getChildren().add(done);

    }

}
