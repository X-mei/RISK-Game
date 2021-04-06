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
    Label promptAM;
    Label promptUpdate;
    Button upgrade;
    Button move;
    Button attack;
    Button tech;
    Button done;
    TextField input;
    Button confirm;
    Button continueWatch;
    Button exitGame;
    Label error;

    public PlayGameView(String userInfo) {
        this.playGamePane = new AnchorPane();
        this.prompt0 = new Label(userInfo);
        this.prompt1 = new Label();
        this.prompt2 = new Label();
        this.promptAM = new Label();
        this.promptUpdate = new Label();
        this.upgrade = new Button();
        this.move = new Button();
        this.attack = new Button();
        this.tech = new Button();
        this.done = new Button();
        this.input = new TextField();
        this.confirm = new Button();
        this.continueWatch = new Button();
        this.exitGame = new Button();
        this.error = new Label();
    }

    public void init(String playerNum) {
        // show img
        try {
            String path = null;
            if (playerNum.equals("2")) {
                path = "/client/src/main/resources/2P.JPG";
            } else if (playerNum.equals("3")) {
                path = "client/src/main/resources/3P.JPG";
            } else if (playerNum.equals("4")) {
                path = "client/src/main/resources/4P.JPG";
            } else {
                path = "client/src/main/resources/5P.JPG";
            }
            FileInputStream imageStream = new FileInputStream(path);
            Image image = new Image(imageStream);
            ImageView imageView = new ImageView(image);
            imageView.setX(40);
            imageView.setY(25);
            imageView.setFitHeight(540);
            imageView.setFitWidth(960);
            playGamePane.getChildren().add(imageView);
        } catch (IOException e) {
            e.printStackTrace();
        }
        promptAM.setLayoutX(40);
        promptAM.setLayoutY(655);
        promptAM.setText("Please enter the action: src dest count Level.");
        promptAM.setVisible(false);
        playGamePane.getChildren().add(promptAM);

        promptUpdate.setLayoutX(40);
        promptUpdate.setLayoutY(655);
        promptUpdate.setText("Please enter the action: Territory start-level count final-level.");
        promptUpdate.setVisible(false);
        playGamePane.getChildren().add(promptUpdate);

        input.setLayoutX(40);
        input.setLayoutY(685);
        input.setVisible(false);
        playGamePane.getChildren().add(input);

        confirm.setLayoutX(40);
        confirm.setLayoutY(725);
        confirm.setVisible(false);
        confirm.setText("confirm");
        playGamePane.getChildren().add(confirm);

        continueWatch.setLayoutX(40);
        continueWatch.setLayoutY(485);
        continueWatch.setText("continue");
        continueWatch.setVisible(false);
        playGamePane.getChildren().add(continueWatch);

        exitGame.setLayoutX(40);
        exitGame.setLayoutY(725);
        exitGame.setText("exit");
        exitGame.setVisible(false);
        continueWatch.setVisible(false);
        playGamePane.getChildren().add(exitGame);

        error.setLayoutX(40);
        error.setLayoutY(775);
        error.setText("Error! Please enter again.");
        error.setVisible(false);
        playGamePane.getChildren().add(error);

    }

    public void addPrompt(String prompt) {
        prompt0.setLayoutX(1010);
        prompt0.setLayoutY(60);
        playGamePane.getChildren().add(prompt0);

        prompt1.setLayoutX(1010);
        prompt1.setLayoutY(80);
        prompt1.setText(prompt);
        playGamePane.getChildren().add(prompt1);
    }

    public void addPrompt2(String prompt) {
        prompt2.setLayoutX(40);
        prompt2.setLayoutY(610);
        prompt2.setText(prompt);
        playGamePane.getChildren().add(prompt2);

        upgrade.setLayoutX(40);
        upgrade.setLayoutY(660);
        upgrade.setText("Upgrade Soldiers");
        upgrade.setPrefWidth(200);
        playGamePane.getChildren().add(upgrade);

        tech.setLayoutX(40);
        tech.setLayoutY(700);
        tech.setText("Technical Upgrade");
        tech.setPrefWidth(200);
        playGamePane.getChildren().add(tech);

        attack.setLayoutX(40);
        attack.setLayoutY(740);
        attack.setText("Attack");
        attack.setPrefWidth(200);
        playGamePane.getChildren().add(attack);

        move.setLayoutX(40);
        move.setLayoutY(780);
        move.setText("Move");
        move.setPrefWidth(200);
        playGamePane.getChildren().add(move);

        done.setLayoutX(40);
        done.setLayoutY(820);
        done.setText("Done");
        done.setPrefWidth(200);
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
