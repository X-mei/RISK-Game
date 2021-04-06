package client;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.IOException;

public class PlayGameView {
    String playerNum;
    AnchorPane playGamePane;
    Label prompt0;
    Label prompt1;
    Label prompt2;
    Label promptAM;
    Label promptUpdate;
    Label srcPrompt;
    Label destPrompt;
    Label lvPrompt1;
    Label lvPrompt2;
    Label countPrompt;
    Label terrPrompt;
    Button upgrade;
    Button move;
    Button attack;
    Button tech;
    Button done;
    TextField input;
    Button confirm;
    Button continueWatch;
    Button exitGame;
    ChoiceBox choicesOfLevel1;
    ChoiceBox choicesOfLevel2;
    ChoiceBox choicesOfDest;
    ChoiceBox choicesOfSource;

    Label error;
    Label errorTech;

    public PlayGameView(String userInfo) {
        this.playGamePane = new AnchorPane();
        this.prompt0 = new Label(userInfo);
        this.prompt1 = new Label();
        this.prompt2 = new Label();
        this.promptAM = new Label();
        this.srcPrompt = new Label();
        this.destPrompt = new Label();
        this.lvPrompt1 = new Label();
        this.lvPrompt2 = new Label();
        this.countPrompt = new Label();
        this.terrPrompt = new Label();
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
        this.errorTech = new Label();
        this.choicesOfLevel1 = new ChoiceBox();
        this.choicesOfLevel2 = new ChoiceBox();
        this.choicesOfDest = new ChoiceBox();
        this.choicesOfSource = new ChoiceBox();
    }

    public void init(String playerNum) {
        // show img
        try {
            String path = null;
            if (playerNum.equals("2")) {
                path = "src/main/resources/2P.JPG";
            } else if (playerNum.equals("3")) {
                path = "src/main/resources/3P.JPG";
            } else if (playerNum.equals("4")) {
                path = "src/main/resources/4P.JPG";
            } else {
                path = "src/main/resources/5P.JPG";
            }
            FileInputStream imageStream = new FileInputStream(path);
            Image image = new Image(imageStream);
            ImageView imageView = new ImageView(image);
            imageView.setX(40);
            imageView.setY(25);
            imageView.setFitHeight(382.5);
            imageView.setFitWidth(680);
            this.playerNum = playerNum;
            playGamePane.getChildren().add(imageView);
        } catch (IOException e) {
            e.printStackTrace();
        }
        promptAM.setLayoutX(40);
        promptAM.setLayoutY(495);
        promptAM.setText("Please enter the action: src dest count Level.");
        promptAM.setVisible(false);
        playGamePane.getChildren().add(promptAM);

        srcPrompt.setLayoutX(45);
        srcPrompt.setLayoutY(530);
        srcPrompt.setText("Src");
        srcPrompt.setVisible(false);
        playGamePane.getChildren().add(srcPrompt);

        terrPrompt.setLayoutX(45);
        terrPrompt.setLayoutY(530);
        terrPrompt.setText("Terri");
        terrPrompt.setVisible(false);
        playGamePane.getChildren().add(terrPrompt);

        destPrompt.setLayoutX(215);
        destPrompt.setLayoutY(530);
        destPrompt.setText("Dest");
        destPrompt.setVisible(false);
        playGamePane.getChildren().add(destPrompt);

        lvPrompt1.setLayoutX(215);
        lvPrompt1.setLayoutY(530);
        lvPrompt1.setText("Level");
        lvPrompt1.setVisible(false);
        playGamePane.getChildren().add(lvPrompt1);

        lvPrompt2.setLayoutX(555);
        lvPrompt2.setLayoutY(530);
        lvPrompt2.setText("Level");
        lvPrompt2.setVisible(false);
        playGamePane.getChildren().add(lvPrompt2);

        countPrompt.setLayoutX(375);
        countPrompt.setLayoutY(530);
        countPrompt.setText("Count");
        countPrompt.setVisible(false);
        playGamePane.getChildren().add(countPrompt);

        promptUpdate.setLayoutX(40);
        promptUpdate.setLayoutY(495);
        promptUpdate.setText("Please enter the action: Territory start-level count final-level.");
        promptUpdate.setVisible(false);
        playGamePane.getChildren().add(promptUpdate);

        input.setLayoutX(420);
        input.setLayoutY(525);
        input.setPrefWidth(110);
        input.setVisible(false);
        playGamePane.getChildren().add(input);

        confirm.setLayoutX(40);
        confirm.setLayoutY(565);
        confirm.setVisible(false);
        confirm.setText("confirm");
        playGamePane.getChildren().add(confirm);


        continueWatch.setLayoutX(40);
        continueWatch.setLayoutY(490);
        continueWatch.setText("continue");
        continueWatch.setPrefWidth(80);
        continueWatch.setVisible(false);
        playGamePane.getChildren().add(continueWatch);

        exitGame.setLayoutX(40);
        exitGame.setLayoutY(530);
        exitGame.setText("exit");
        exitGame.setVisible(false);
        exitGame.setPrefWidth(80);
        playGamePane.getChildren().add(exitGame);

        error.setLayoutX(40);
        error.setLayoutY(515);
        error.setText("Error! Please enter again.");
        error.setVisible(false);
        playGamePane.getChildren().add(error);

        errorTech.setLayoutX(40);
        errorTech.setLayoutY(690);
        errorTech.setText("Error! You have reached the highest tech level.");
        errorTech.setVisible(false);
        playGamePane.getChildren().add(errorTech);

    }

    public void addPrompt(String prompt) {
        prompt0.setLayoutX(750);
        prompt0.setLayoutY(60);
        playGamePane.getChildren().add(prompt0);

        prompt1.setLayoutX(750);
        prompt1.setLayoutY(80);
        prompt1.setText(prompt);
        playGamePane.getChildren().add(prompt1);
    }

    public void addPrompt2(String prompt) {
        prompt2.setLayoutX(40);
        prompt2.setLayoutY(450);
        prompt2.setText(prompt);
        playGamePane.getChildren().add(prompt2);

        choicesOfLevel1.setItems(FXCollections.observableArrayList(
                "Lv1", "Lv2",
                "Lv3", "Lv4", "Lv5", "Lv6", "Lv7")
        );
        choicesOfLevel1.setLayoutX(250);
        choicesOfLevel1.setLayoutY(525);
        choicesOfLevel1.setPrefWidth(110);
        playGamePane.getChildren().add(choicesOfLevel1);

        choicesOfLevel2.setItems(FXCollections.observableArrayList(
                "Lv1", "Lv2",
                "Lv3", "Lv4", "Lv5", "Lv6", "Lv7")
        );
        choicesOfLevel2.setLayoutX(590);
        choicesOfLevel2.setLayoutY(525);
        choicesOfLevel2.setPrefWidth(110);
        playGamePane.getChildren().add(choicesOfLevel2);

        if(playerNum.equals("2")){
            choicesOfSource.setItems(FXCollections.observableArrayList(
                    "Hanamura", "Volskaya",
                    "Hollywood", "Junkertown", "Dorado", "Ilios")
            );
            choicesOfDest.setItems(FXCollections.observableArrayList(
                    "Hanamura", "Volskaya",
                    "Hollywood", "Junkertown", "Dorado", "Ilios")
            );
        }
        else if(playerNum.equals("3")){
            choicesOfSource.setItems(FXCollections.observableArrayList(
                    "Hanamura", "Volskaya", "Nepal", "Oasis", "Numbani",
                    "Hollywood", "Junkertown", "Dorado", "Ilios")
            );
            choicesOfDest.setItems(FXCollections.observableArrayList(
                    "Hanamura", "Volskaya", "Nepal", "Oasis", "Numbani",
                    "Hollywood", "Junkertown", "Dorado", "Ilios")
            );
        }
        else if(playerNum.equals("4")){
            choicesOfSource.setItems(FXCollections.observableArrayList(
                    "Hanamura", "Volskaya", "Nepal", "Oasis", "Numbani",
                    "Hollywood", "Junkertown", "Dorado", "Ilios")
            );
            choicesOfDest.setItems(FXCollections.observableArrayList(
                    "Hanamura", "Volskaya", "Nepal", "Oasis", "Numbani",
                    "Hollywood", "Junkertown", "Dorado", "Ilios")
            );
        }
        else if(playerNum.equals("5")){
            choicesOfSource.setItems(FXCollections.observableArrayList(
                    "Hanamura", "Volskaya", "Nepal", "Oasis", "Numbani",
                    "Hollywood", "Junkertown", "Dorado", "Ilios", "Anubis")
            );
            choicesOfDest.setItems(FXCollections.observableArrayList(
                    "Hanamura", "Volskaya", "Nepal", "Oasis", "Numbani",
                    "Hollywood", "Junkertown", "Dorado", "Ilios", "Anubis")
            );
        }

        choicesOfSource.setLayoutX(80);
        choicesOfSource.setLayoutY(525);
        choicesOfSource.setPrefWidth(110);
        playGamePane.getChildren().add(choicesOfSource);

        choicesOfDest.setLayoutX(250);
        choicesOfDest.setLayoutY(525);
        choicesOfDest.setPrefWidth(110);
        playGamePane.getChildren().add(choicesOfDest);

        choicesOfLevel1.setVisible(false);
        choicesOfLevel2.setVisible(false);
        choicesOfDest.setVisible(false);
        choicesOfSource.setVisible(false);

        tech.setLayoutX(40);
        tech.setLayoutY(490);
        tech.setText("Technical Upgrade");
        tech.setPrefWidth(200);
        playGamePane.getChildren().add(tech);

        upgrade.setLayoutX(40);
        upgrade.setLayoutY(530);
        upgrade.setText("Upgrade Soldiers");
        upgrade.setPrefWidth(200);
        playGamePane.getChildren().add(upgrade);

        attack.setLayoutX(40);
        attack.setLayoutY(570);
        attack.setText("Attack");
        attack.setPrefWidth(200);
        playGamePane.getChildren().add(attack);

        move.setLayoutX(40);
        move.setLayoutY(610);
        move.setText("Move");
        move.setPrefWidth(200);
        playGamePane.getChildren().add(move);

        done.setLayoutX(40);
        done.setLayoutY(650);
        done.setText("Done");
        done.setPrefWidth(200);
        playGamePane.getChildren().add(done);

    }

    public void exitOrContinue() {

        continueWatch.setVisible(true);
        exitGame.setVisible(true);
        choicesOfLevel1.setVisible(false);
        choicesOfLevel2.setVisible(false);
        choicesOfDest.setVisible(false);
        choicesOfSource.setVisible(false);
        srcPrompt.setVisible(false);
        destPrompt.setVisible(false);
        upgrade.setVisible(false);
        move.setVisible(false);
        attack.setVisible(false);
        tech.setVisible(false);
        done.setVisible(false);
    }

    public void deactivateAll() {
        prompt1.setVisible(true);
        prompt1.setText("Wait for other players to perform the action...");
        choicesOfLevel1.setVisible(false);
        choicesOfLevel2.setVisible(false);
        choicesOfDest.setVisible(false);
        choicesOfSource.setVisible(false);
        srcPrompt.setVisible(false);
        destPrompt.setVisible(false);
        countPrompt.setVisible(false);
        lvPrompt1.setVisible(false);
        lvPrompt2.setVisible(false);
        terrPrompt.setVisible(false);
        errorTech.setVisible(false);
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
