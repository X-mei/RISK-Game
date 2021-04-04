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
    Button continueWatch;
    Button exitGame;

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
        this.continueWatch = new Button();
        this.exitGame = new Button();
    }

    public void init(String playerNum) {
        // show img
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
            playGamePane.getChildren().add(imageView);
        } catch (IOException e) {
            e.printStackTrace();
        }
        input.setLayoutX(600);
        input.setLayoutY(700);
        input.setVisible(false);
        playGamePane.getChildren().add(input);

        confirm.setLayoutX(600);
        confirm.setLayoutY(750);
        confirm.setVisible(false);
        confirm.setText("confirm");
        playGamePane.getChildren().add(confirm);

        continueWatch.setLayoutX(600);
        continueWatch.setLayoutY(450);
        continueWatch.setText("continue");
        continueWatch.setVisible(false);
        playGamePane.getChildren().add(continueWatch);

        exitGame.setLayoutX(600);
        exitGame.setLayoutY(500);
        exitGame.setText("exit");
        continueWatch.setVisible(false);
        playGamePane.getChildren().add(exitGame);
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

    public void exitOrContinue() {

        continueWatch.setVisible(true);
        exitGame.setVisible(true);

        upgrade.setVisible(false);
        move.setVisible(false);
        attack.setVisible(false);
        tech.setVisible(false);
        done.setVisible(false);
    }

    public void deactivateAll() {
        prompt1.setVisible(true);
        prompt1.setText("Wait for other players to perform the action...");

        prompt2.setVisible(false);
        upgrade.setVisible(false);
        move.setVisible(false);
        attack.setVisible(false);
        tech.setVisible(false);
        done.setVisible(false);
        continueWatch.setVisible(false);
        exitGame.setVisible(false);
        input.setVisible(false);
        confirm.setVisible(false);
    }

    public void activateAll() {
        prompt2.setVisible(true);
        upgrade.setVisible(true);
        move.setVisible(true);
        attack.setVisible(true);
        tech.setVisible(true);
        done.setVisible(true);
    }

}
