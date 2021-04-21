package client;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class PlayGameView {
    HashMap<String, HashMap<String, Button> > playerTerrs;
    HashMap<String, Button> terrs2P;
    HashMap<String, Button> terrs3P;
    HashMap<String, Button> terrs4P;
    HashMap<String, Button> terrs5P;
  
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
    Button research;
    Button cloak;
    Button done;
    Button terr2P_1;
    Button terr2P_2;
    Button terr2P_3;
    Button terr2P_4;
    Button terr2P_5;
    Button terr2P_6;
    Button terr3P_1;
    Button terr3P_2;
    Button terr3P_3;
    Button terr3P_4;
    Button terr3P_5;
    Button terr3P_6;
    Button terr3P_7;
    Button terr3P_8;
    Button terr3P_9;
    Button terr4P_1;
    Button terr4P_2;
    Button terr4P_3;
    Button terr4P_4;
    Button terr4P_5;
    Button terr4P_6;
    Button terr4P_7;
    Button terr4P_8;
    Button terr5P_1;
    Button terr5P_2;
    Button terr5P_3;
    Button terr5P_4;
    Button terr5P_5;
    Button terr5P_6;
    Button terr5P_7;
    Button terr5P_8;
    Button terr5P_9;
    Button terr5P_10;
    
    
    TextField input;
    Button confirm;
    Button continueWatch;
    Button exitGame;
    ChoiceBox<String> choicesOfLevel1;
    ChoiceBox<String> choicesOfLevel2;
    ChoiceBox<String> attackLevel;
    ChoiceBox<String> choicesOfDest;
    ChoiceBox<String> choicesOfSource;
    HashMap<String, ArrayList<String>> territories;
    HashMap<String, String> units;

    Label error;
    Label errorTech;

    ImageView imageView2;

    ImageView moveView;
    ImageView attackView;
    ImageView upgradeView;

    public PlayGameView(String userInfo) {
        this.terrs2P = new HashMap<>();
        this.terrs3P = new HashMap<>();
        this.terrs4P = new HashMap<>();
        this.terrs5P = new HashMap<>();
        this.playerTerrs = new HashMap<>();
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
        this.research = new Button();
        this.cloak = new Button();
        this.done = new Button();
        this.input = new TextField();
        this.confirm = new Button();
        this.continueWatch = new Button();
        this.exitGame = new Button();
        this.error = new Label();
        this.terr2P_1 = new Button();
        this.terr2P_2 = new Button();
        this.terr2P_3 = new Button();
        this.terr2P_4 = new Button();
        this.terr2P_5 = new Button();
        this.terr2P_6 = new Button();
        this.terr3P_1 = new Button();
        this.terr3P_2 = new Button();
        this.terr3P_3 = new Button();
        this.terr3P_4 = new Button();
        this.terr3P_5 = new Button();
        this.terr3P_6 = new Button();
        this.terr3P_7 = new Button();
        this.terr3P_8 = new Button();
        this.terr3P_9 = new Button();
        this.terr4P_1 = new Button();
        this.terr4P_2 = new Button();
        this.terr4P_3 = new Button();
        this.terr4P_4 = new Button();
        this.terr4P_5 = new Button();
        this.terr4P_6 = new Button();
        this.terr4P_7 = new Button();
        this.terr4P_8 = new Button();
        this.errorTech = new Label();
        this.terr5P_1 = new Button();
        this.terr5P_2 = new Button();
        this.terr5P_3 = new Button();
        this.terr5P_4 = new Button();
        this.terr5P_5 = new Button();
        this.terr5P_6 = new Button();
        this.terr5P_7 = new Button();
        this.terr5P_8 = new Button();
        this.terr5P_9 = new Button();
        this.terr5P_10 = new Button();
        this.choicesOfLevel1 = new ChoiceBox<String>();
        this.choicesOfLevel2 = new ChoiceBox<String>();
        this.attackLevel = new ChoiceBox<String>();
        this.choicesOfDest = new ChoiceBox<String>();
        this.choicesOfSource = new ChoiceBox<String>();
        this.territories = new HashMap<String, ArrayList<String>>();
        this.units = new HashMap<String, String>();
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

        try {
            String path = "src/main/resources/move.gif";
            FileInputStream imageStream = new FileInputStream(path);
            Image image = new Image(imageStream);
            moveView = new ImageView(image);
            moveView.setX(900);
            moveView.setY(450);
            moveView.setFitHeight(300);
            moveView.setFitWidth(300);
            moveView.setVisible(false);
            playGamePane.getChildren().add(moveView);

            path = "src/main/resources/combat.gif";
            imageStream = new FileInputStream(path);
            image = new Image(imageStream);
            attackView = new ImageView(image);
            attackView.setX(900);
            attackView.setY(550);
            attackView.setFitHeight(150);
            attackView.setFitWidth(300);
            attackView.setVisible(false);
            playGamePane.getChildren().add(attackView);

        } catch (IOException e) {
            e.printStackTrace();
        }

        playerTerrs.put("2", terrs2P);
        playerTerrs.put("3", terrs3P);
        playerTerrs.put("4", terrs4P);
        playerTerrs.put("5", terrs5P);
        

        terr2P_1.setLayoutX(135);
        terr2P_1.setLayoutY(85);
        terr2P_1.setText("Hanamura");
        terr2P_1.setVisible(false);
        terr2P_1.setId("2p1");
        playGamePane.getChildren().add(terr2P_1);
        terrs2P.put("Hanamura", terr2P_1);
    

        terr2P_2.setLayoutX(140);
        terr2P_2.setLayoutY(310);
        terr2P_2.setText("Dorado");
        terr2P_2.setVisible(false);
        terr2P_2.setId("2p2");
        playGamePane.getChildren().add(terr2P_2);
        terrs2P.put("Dorado", terr2P_2);

        terr2P_3.setLayoutX(325);
        terr2P_3.setLayoutY(355);
        terr2P_3.setText("Hollywood");
        terr2P_3.setVisible(false);
        terr2P_3.setId("2p3");
        playGamePane.getChildren().add(terr2P_3);
        terrs2P.put("Hollywood", terr2P_3);

        terr2P_4.setLayoutX(483);
        terr2P_4.setLayoutY(337);
        terr2P_4.setText("Ilios");
        terr2P_4.setVisible(false);
        terr2P_4.setId("2p4");
        playGamePane.getChildren().add(terr2P_4);
        terrs2P.put("Ilios", terr2P_4);

        terr2P_5.setLayoutX(605);
        terr2P_5.setLayoutY(200);
        terr2P_5.setText("Junkertown");
        terr2P_5.setVisible(false);
        terr2P_5.setId("2p5");
        playGamePane.getChildren().add(terr2P_5);
        terrs2P.put("Junkertown", terr2P_5);

        terr2P_6.setLayoutX(438);
        terr2P_6.setLayoutY(65);
        terr2P_6.setText("Volskaya");
        terr2P_6.setVisible(false);
        terr2P_6.setId("2p6");
        playGamePane.getChildren().add(terr2P_6);
        terrs2P.put("Volskaya", terr2P_6);
        
        if(playerNum.equals("2") || playerNum.equals("1")){
            terr2P_1.setVisible(true);
            terr2P_2.setVisible(true);
            terr2P_3.setVisible(true);
            terr2P_4.setVisible(true);
            terr2P_5.setVisible(true);
            terr2P_6.setVisible(true);
        }

        
        terr3P_1.setLayoutX(135);
        terr3P_1.setLayoutY(85);
        terr3P_1.setText("Hanamura");
        terr3P_1.setVisible(false);
        terr3P_1.setId("3p1");
        playGamePane.getChildren().add(terr3P_1);
        terrs3P.put("Hanamura", terr3P_1);

                
        terr3P_2.setLayoutX(45);
        terr3P_2.setLayoutY(210);
        terr3P_2.setText("Dorado");
        terr3P_2.setVisible(false);
        terr3P_2.setId("3p2");
        playGamePane.getChildren().add(terr3P_2);
        terrs3P.put("Dorado", terr3P_2);

                
        terr3P_3.setLayoutX(100);
        terr3P_3.setLayoutY(260);
        terr3P_3.setText("Hollywood");
        terr3P_3.setVisible(false);
        terr3P_3.setId("3p3");
        playGamePane.getChildren().add(terr3P_3);
        terrs3P.put("Hollywood", terr3P_3);
                
        terr3P_4.setLayoutX(140);
        terr3P_4.setLayoutY(350);
        terr3P_4.setText("Volskaya");
        terr3P_4.setVisible(false);
        terr3P_4.setId("3p4");
        playGamePane.getChildren().add(terr3P_4);
        terrs3P.put("Volskaya", terr3P_4);

                
        terr3P_5.setLayoutX(270);
        terr3P_5.setLayoutY(355);
        terr3P_5.setText("Junkertown");
        terr3P_5.setVisible(false);
        terr3P_5.setId("3p5");
        playGamePane.getChildren().add(terr3P_5);
        terrs3P.put("Junkertown", terr3P_5);

                
        terr3P_6.setLayoutX(420);
        terr3P_6.setLayoutY(365);
        terr3P_6.setText("Ilios");
        terr3P_6.setVisible(false);
        terr3P_6.setId("3p6");
        playGamePane.getChildren().add(terr3P_6);
        terrs3P.put("Ilios", terr3P_6);

                
        terr3P_7.setLayoutX(620);
        terr3P_7.setLayoutY(240);
        terr3P_7.setText("Numbani");
        terr3P_7.setVisible(false);
        terr3P_7.setId("3p7");
        playGamePane.getChildren().add(terr3P_7);
        terrs3P.put("Numbani", terr3P_7);

                
        terr3P_8.setLayoutX(490);
        terr3P_8.setLayoutY(85);
        terr3P_8.setText("Oasis");
        terr3P_8.setVisible(false);
        terr3P_8.setId("3p8");
        playGamePane.getChildren().add(terr3P_8);
        terrs3P.put("Oasis", terr3P_8);

                
        terr3P_9.setLayoutX(345);
        terr3P_9.setLayoutY(85);
        terr3P_9.setText("Nepal");
        terr3P_9.setVisible(false);
        terr3P_9.setId("3p9");
        playGamePane.getChildren().add(terr3P_9);
        terrs3P.put("Nepal", terr3P_9);

        
        if(playerNum.equals("3")){
            terr3P_1.setVisible(true);
            terr3P_2.setVisible(true);
            terr3P_3.setVisible(true);
            terr3P_4.setVisible(true);
            terr3P_5.setVisible(true);
            terr3P_6.setVisible(true);
            terr3P_7.setVisible(true);
            terr3P_8.setVisible(true);
            terr3P_9.setVisible(true);
        }

        
        
        terr4P_1.setLayoutX(135);
        terr4P_1.setLayoutY(85);
        terr4P_1.setText("Dorado");
        terr4P_1.setVisible(false);
        terr4P_1.setId("4p1");
        playGamePane.getChildren().add(terr4P_1);
        terrs4P.put("Dorado", terr4P_1);

                
        terr4P_2.setLayoutX(125);
        terr4P_2.setLayoutY(300);
        terr4P_2.setText("Oasis");
        terr4P_2.setVisible(false);
        terr4P_2.setId("4p2");
        playGamePane.getChildren().add(terr4P_2);
        terrs4P.put("Oasis", terr4P_2);

                
        terr4P_3.setLayoutX(305);
        terr4P_3.setLayoutY(350);
        terr4P_3.setText("Nepal");
        terr4P_3.setVisible(false);
        terr4P_3.setId("4p3");
        playGamePane.getChildren().add(terr4P_3);
        terrs4P.put("Nepal", terr4P_3);
                
        terr4P_4.setLayoutX(425);
        terr4P_4.setLayoutY(355);
        terr4P_4.setText("Ilios");
        terr4P_4.setVisible(false);
        terr4P_4.setId("4p4");
        playGamePane.getChildren().add(terr4P_4);
        terrs4P.put("Ilios", terr4P_4);

                
        terr4P_5.setLayoutX(500);
        terr4P_5.setLayoutY(365);
        terr4P_5.setText("Junkertown");
        terr4P_5.setVisible(false);
        terr4P_5.setId("4p5");
        playGamePane.getChildren().add(terr4P_5);
        terrs4P.put("Junkertown", terr4P_5);

                
        terr4P_6.setLayoutX(605);
        terr4P_6.setLayoutY(200);
        terr4P_6.setText("Volskaya");
        terr4P_6.setVisible(false);
        terr4P_6.setId("4p6");
        playGamePane.getChildren().add(terr4P_6);
        terrs4P.put("Volskaya", terr4P_6);

                
        terr4P_7.setLayoutX(445);
        terr4P_7.setLayoutY(75);
        terr4P_7.setText("Hollywood");
        terr4P_7.setVisible(false);
        terr4P_7.setId("4p7");
        playGamePane.getChildren().add(terr4P_7);
        terrs4P.put("Hollywood", terr4P_7);

                
        terr4P_8.setLayoutX(325);
        terr4P_8.setLayoutY(70);
        terr4P_8.setText("Hanamura");
        terr4P_8.setVisible(false);
        terr4P_8.setId("4p8");
        playGamePane.getChildren().add(terr4P_8);
        terrs4P.put("Hanamura", terr4P_8);

        if(playerNum.equals("4")){
          terr4P_1.setVisible(true);
          terr4P_2.setVisible(true);
          terr4P_3.setVisible(true);
          terr4P_4.setVisible(true);
          terr4P_5.setVisible(true);
          terr4P_6.setVisible(true);
          terr4P_7.setVisible(true);
          terr4P_8.setVisible(true);
        }

        terr5P_1.setLayoutX(45);
        terr5P_1.setLayoutY(210);
        terr5P_1.setText("Dorado");
        terr5P_1.setVisible(false);
        terr5P_1.setId("5p1");
        playGamePane.getChildren().add(terr5P_1);
        terrs5P.put("Dorado", terr5P_1);

                
        terr5P_2.setLayoutX(90);
        terr5P_2.setLayoutY(260);
        terr5P_2.setText("Hanamura");
        terr5P_2.setVisible(false);
        terr5P_2.setId("5p2");
        playGamePane.getChildren().add(terr5P_2);
        terrs5P.put("Hanamura", terr5P_2);

                
        terr5P_3.setLayoutX(150);
        terr5P_3.setLayoutY(300);
        terr5P_3.setText("Anubis");
        terr5P_3.setVisible(false);
        terr5P_3.setId("5p3");
        playGamePane.getChildren().add(terr5P_3);
        terrs5P.put("Anubis", terr5P_3);
        
                
        terr5P_4.setLayoutX(280);
        terr5P_4.setLayoutY(350);
        terr5P_4.setText("Numbani");
        terr5P_4.setVisible(false);
        terr5P_4.setId("5p4");
        playGamePane.getChildren().add(terr5P_4);
        terrs5P.put("Numbani", terr5P_4);

                
        terr5P_5.setLayoutX(450);
        terr5P_5.setLayoutY(325);
        terr5P_5.setText("Junkertown");
        terr5P_5.setVisible(false);
        terr5P_5.setId("5p5");
        playGamePane.getChildren().add(terr5P_5);
        terrs5P.put("Junkertown", terr5P_5);

                
        terr5P_6.setLayoutX(550);
        terr5P_6.setLayoutY(365);
        terr5P_6.setText("Oasis");
        terr5P_6.setVisible(false);
        terr5P_6.setId("5p6");
        playGamePane.getChildren().add(terr5P_6);
        terrs5P.put("Oasis", terr5P_6);

                
        terr5P_7.setLayoutX(610);
        terr5P_7.setLayoutY(240);
        terr5P_7.setText("Nepal");
        terr5P_7.setVisible(false);
        terr5P_7.setId("5p7");
        playGamePane.getChildren().add(terr5P_7);
        terrs5P.put("Nepal", terr5P_7);

                
        terr5P_8.setLayoutX(620);
        terr5P_8.setLayoutY(200);
        terr5P_8.setText("Ilios");
        terr5P_8.setVisible(false);
        terr5P_8.setId("5p8");
        playGamePane.getChildren().add(terr5P_8);
        terrs5P.put("Ilios", terr5P_8);
        
                
        terr5P_9.setLayoutX(345);
        terr5P_9.setLayoutY(55);
        terr5P_9.setText("Volskaya");
        terr5P_9.setVisible(false);
        terr5P_9.setId("5p9");
        playGamePane.getChildren().add(terr5P_9);
        terrs5P.put("Volskaya", terr5P_9);

                        
        terr5P_10.setLayoutX(255);
        terr5P_10.setLayoutY(55);
        terr5P_10.setText("Hollywood");
        terr5P_10.setVisible(false);
        terr5P_10.setId("5p10");
        playGamePane.getChildren().add(terr5P_10);
        terrs5P.put("Hollywood", terr5P_10);

        
        if(playerNum.equals("5")){
            terr5P_1.setVisible(true);
            terr5P_2.setVisible(true);
            terr5P_3.setVisible(true);
            terr5P_4.setVisible(true);
            terr5P_5.setVisible(true);
            terr5P_6.setVisible(true);
            terr5P_7.setVisible(true);
            terr5P_8.setVisible(true);
            terr5P_9.setVisible(true);
            terr5P_10.setVisible(true);
        }
                                       
        promptAM.setLayoutX(750);
        promptAM.setLayoutY(150);
        promptAM.setText("Please enter the action: src dest count Level.");
        promptAM.setVisible(false);
        promptAM.setFont(new Font("Arial", 14));
        promptAM.setTextFill(Color.web("#4f1e47", 1.0));
        promptAM.setStyle("-fx-font-weight: bold;");
        playGamePane.getChildren().add(promptAM);

        srcPrompt.setLayoutX(800);
        srcPrompt.setLayoutY(200);
        srcPrompt.setText("Src");
        srcPrompt.setVisible(false);
        srcPrompt.setFont(new Font("Arial", 12));
        srcPrompt.setTextFill(Color.web("#574a78", 1.0));
        srcPrompt.setStyle("-fx-font-weight: bold;");
        playGamePane.getChildren().add(srcPrompt);

        terrPrompt.setLayoutX(800);
        terrPrompt.setLayoutY(200);
        terrPrompt.setText("Terri");
        terrPrompt.setVisible(false);
        terrPrompt.setFont(new Font("Arial", 12));
        terrPrompt.setTextFill(Color.web("#574a78", 1.0));
        terrPrompt.setStyle("-fx-font-weight: bold;");
        playGamePane.getChildren().add(terrPrompt);

        destPrompt.setLayoutX(800);
        destPrompt.setLayoutY(250);
        destPrompt.setText("Dest");
        destPrompt.setVisible(false);
        destPrompt.setFont(new Font("Arial", 12));
        destPrompt.setTextFill(Color.web("#574a78", 1.0));
        destPrompt.setStyle("-fx-font-weight: bold;");
        playGamePane.getChildren().add(destPrompt);

        lvPrompt1.setLayoutX(800);
        lvPrompt1.setLayoutY(250);
        lvPrompt1.setText("Level");
        lvPrompt1.setVisible(false);
        lvPrompt1.setFont(new Font("Arial", 12));
        lvPrompt1.setTextFill(Color.web("#574a78", 1.0));
        lvPrompt1.setStyle("-fx-font-weight: bold;");
        playGamePane.getChildren().add(lvPrompt1);

        lvPrompt2.setLayoutX(800);
        lvPrompt2.setLayoutY(350);
        lvPrompt2.setText("Level");
        lvPrompt2.setVisible(false);
        lvPrompt2.setFont(new Font("Arial", 12));
        lvPrompt2.setTextFill(Color.web("#574a78", 1.0));
        lvPrompt2.setStyle("-fx-font-weight: bold;");
        playGamePane.getChildren().add(lvPrompt2);

        countPrompt.setLayoutX(800);
        countPrompt.setLayoutY(300);
        countPrompt.setText("Count");
        countPrompt.setVisible(false);
        countPrompt.setFont(new Font("Arial", 12));
        countPrompt.setTextFill(Color.web("#574a78", 1.0));
        countPrompt.setStyle("-fx-font-weight: bold;");
        playGamePane.getChildren().add(countPrompt);

        promptUpdate.setLayoutX(750);
        promptUpdate.setLayoutY(150);
        promptUpdate.setText("Please enter the action: Territory start-level count final-level.");
        promptUpdate.setVisible(false);
        promptUpdate.setFont(new Font("Arial", 14));
        promptUpdate.setTextFill(Color.web("#4f1e47", 1.0));
        promptUpdate.setStyle("-fx-font-weight: bold;");
        playGamePane.getChildren().add(promptUpdate);

        input.setLayoutX(940);
        input.setLayoutY(300);
        input.setPrefWidth(110);
        input.setVisible(false);
        input.setStyle("-fx-background-color:#e9edc5; -fx-text-fill:Black; -fx-font-size:16");
        playGamePane.getChildren().add(input);

        confirm.setLayoutX(940);
        confirm.setLayoutY(400);
        confirm.setVisible(false);
        confirm.setId("confirm");
        confirm.setText("confirm");
        confirm.setPrefSize(80, 50);
        playGamePane.getChildren().add(confirm);


        continueWatch.setLayoutX(920);
        continueWatch.setLayoutY(400);
        continueWatch.setText("continue");
        continueWatch.setPrefWidth(80);
        continueWatch.setVisible(false);
        continueWatch.setId("continue");
        continueWatch.setPrefSize(80, 50);
        playGamePane.getChildren().add(continueWatch);

        exitGame.setLayoutX(920);
        exitGame.setLayoutY(600);
        exitGame.setText("exit");
        exitGame.setVisible(false);
        exitGame.setPrefWidth(80);
        exitGame.setId("exit");
        exitGame.setPrefSize(80, 50);
        playGamePane.getChildren().add(exitGame);

        error.setLayoutX(800);
        error.setLayoutY(750);
        error.setText("Error! Please enter again.");
        error.setVisible(false);
        error.setFont(new Font("Arial", 15));
        error.setTextFill(Color.web("#692327", 1.0));
        error.setStyle("-fx-font-weight: bold;");
        playGamePane.getChildren().add(error);

        errorTech.setLayoutX(800);
        errorTech.setLayoutY(750);
        errorTech.setText("Error! You have reached the highest tech level.");
        errorTech.setVisible(false);
        errorTech.setFont(new Font("Arial", 15));
        errorTech.setTextFill(Color.web("#692327", 1.0));
        errorTech.setStyle("-fx-font-weight: bold;");
        playGamePane.getChildren().add(errorTech);

        try {
          // add image
          String path = "src/main/resources/dead.gif";
          FileInputStream imageStream = new FileInputStream(path);
          Image image2 = new Image(imageStream);
          imageView2 = new ImageView(image2);
          imageView2.setX(200);
          imageView2.setY(450);
          imageView2.setFitHeight(200);
          imageView2.setFitWidth(200);
          playGamePane.getChildren().add(imageView2);
          imageView2.setVisible(false);
        } catch (IOException e) {
          e.printStackTrace();
        }

    }
  public void recvGreyInfo(HashMap<String, String> greyTerrs, HashMap<String, String> greyOwners){
    for(String terrName: greyTerrs.keySet()){
      System.out.println(terrName + ":" + greyTerrs.get(terrName) + " " + greyOwners.get(terrName));
    }
    for(String terrName: greyTerrs.keySet()){
      Tooltip.install(playerTerrs.get(playerNum).get(terrName), new Tooltip("Size = 10\n" + "Owner: " + greyOwners.get(terrName) + "\n" + greyTerrs.get(terrName) + "\n"));
    }
  }
  
  public void recvTerrInfo(HashMap<String, ArrayList<String>> territories, HashMap<String, String> units){
    for(String terrName :playerTerrs.get(playerNum).keySet()){
      BackgroundFill backgroundfill = new BackgroundFill(Paint.valueOf("#CDCDB4"), null, null);
      Background background = new Background(backgroundfill);
      playerTerrs.get(playerNum).get(terrName).setBackground(background);
    }
    for(String terrName: playerTerrs.get(playerNum).keySet()){
      Tooltip.install(playerTerrs.get(playerNum).get(terrName), new Tooltip("Size = 10\n"));
    }
    for(String PlayerName: territories.keySet()){
      for(String terrName: territories.get(PlayerName)){
         Tooltip.install(playerTerrs.get(playerNum).get(terrName), new Tooltip("Size = 10\n" + "Owner: " + PlayerName + "\n" + units.get(terrName) + "\n"));
      }
    }
    
    for(String playerName: territories.keySet()){
      if(playerName.equals("King")){
        for(String terrName : territories.get(playerName)){
          BackgroundFill backgroundfill = new BackgroundFill(Paint.valueOf("#FFEC8B"), null, null);
          Background background = new Background(backgroundfill);
          playerTerrs.get(playerNum).get(terrName).setBackground(background);
        }
      }

       if(playerName.equals("Red")){
        for(String terrName : territories.get(playerName)){
          BackgroundFill backgroundfill = new BackgroundFill(Paint.valueOf("#CD5555"), null, null);
          Background background = new Background(backgroundfill);
          playerTerrs.get(playerNum).get(terrName).setBackground(background);
        }
      }

       if(playerName.equals("Pink")){
        for(String terrName : territories.get(playerName)){
          BackgroundFill backgroundfill = new BackgroundFill(Paint.valueOf("#FFE4E1"), null, null);
          Background background = new Background(backgroundfill);
          playerTerrs.get(playerNum).get(terrName).setBackground(background);
        }
      }
         
       if(playerName.equals("Blue")){
        for(String terrName : territories.get(playerName)){
          BackgroundFill backgroundfill = new BackgroundFill(Paint.valueOf("#AFEEEE"), null, null);
          Background background = new Background(backgroundfill);
          playerTerrs.get(playerNum).get(terrName).setBackground(background);
        }
      }
                
       if(playerName.equals("Green")){
        for(String terrName : territories.get(playerName)){
          BackgroundFill backgroundfill = new BackgroundFill(Paint.valueOf("#C1FFC1"), null, null);
          Background background = new Background(backgroundfill);
          playerTerrs.get(playerNum).get(terrName).setBackground(background);
        }
      }

    }  

  }

    public void addPrompt(String prompt) {
        prompt0.setLayoutX(750);
        prompt0.setLayoutY(60);
        prompt0.setFont(new Font("Georgia", 16));
        prompt0.setTextFill(Color.web("#2d135e", 1.0));
        prompt0.setStyle("-fx-font-weight: bold;");
        playGamePane.getChildren().add(prompt0);

        prompt1.setLayoutX(40);
        prompt1.setLayoutY(450);
        prompt1.setText(prompt);
        prompt1.setFont(new Font("Arial", 15));
        prompt1.setTextFill(Color.web("#382d61", 1.0));
        prompt1.setStyle("-fx-font-weight: bold;");
        playGamePane.getChildren().add(prompt1);
    }

    public void addPrompt2(String prompt) {
        prompt2.setLayoutX(750);
        prompt2.setLayoutY(100);
        prompt2.setText(prompt);
        prompt2.setFont(new Font("Arial", 15));
        prompt2.setTextFill(Color.web("#382d61", 1.0));
        prompt2.setStyle("-fx-font-weight: bold;");
        playGamePane.getChildren().add(prompt2);

        choicesOfLevel1.setItems(FXCollections.observableArrayList(
                "Lv1", "Lv2",
                "Lv3", "Lv4", "Lv5", "Lv6", "Lv7")
        );
        choicesOfLevel1.setLayoutX(940);
        choicesOfLevel1.setLayoutY(250);
        choicesOfLevel1.setPrefWidth(110);
        playGamePane.getChildren().add(choicesOfLevel1);

        choicesOfLevel2.setItems(FXCollections.observableArrayList(
                "Lv1", "Lv2",
                "Lv3", "Lv4", "Lv5", "Lv6", "Lv7", "Spy", "Tel")
        );
        choicesOfLevel2.setLayoutX(940);
        choicesOfLevel2.setLayoutY(350);
        choicesOfLevel2.setPrefWidth(110);
        playGamePane.getChildren().add(choicesOfLevel2);

        attackLevel.setItems(FXCollections.observableArrayList(
                "Lv1", "Lv2",
                "Lv3", "Lv4", "Lv5", "Lv6", "Lv7", "Tel")
        );
        attackLevel.setLayoutX(940);
        attackLevel.setLayoutY(350);
        attackLevel.setPrefWidth(110);
        playGamePane.getChildren().add(attackLevel);

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

        choicesOfSource.setLayoutX(940);
        choicesOfSource.setLayoutY(200);
        choicesOfSource.setPrefWidth(110);
        playGamePane.getChildren().add(choicesOfSource);

        choicesOfDest.setLayoutX(940);
        choicesOfDest.setLayoutY(250);
        choicesOfDest.setPrefWidth(110);
        playGamePane.getChildren().add(choicesOfDest);

        choicesOfLevel1.setVisible(false);
        choicesOfLevel2.setVisible(false);
        attackLevel.setVisible(false);
        choicesOfDest.setVisible(false);
        choicesOfSource.setVisible(false);

        tech.setLayoutX(920);
        tech.setLayoutY(200);
        tech.setText("Technical Upgrade");
        tech.setPrefSize(200, 50);
        tech.setId("tech");
        playGamePane.getChildren().add(tech);

        upgrade.setLayoutX(920);
        upgrade.setLayoutY(280);
        upgrade.setText("Upgrade Soldiers");
        upgrade.setPrefSize(200, 50);
        upgrade.setId("upgrade");
        playGamePane.getChildren().add(upgrade);
        // String str = "to upgrade";
        // Tooltip.install(upgrade, new Tooltip(str));

        attack.setLayoutX(920);
        attack.setLayoutY(360);
        attack.setText("Attack");
        attack.setPrefSize(200, 50);
        attack.setId("attack");
        playGamePane.getChildren().add(attack);

        move.setLayoutX(920);
        move.setLayoutY(440);
        move.setText("Move");
        move.setPrefSize(200, 50);
        move.setId("move");
        playGamePane.getChildren().add(move);

        research.setLayoutX(920);
        research.setLayoutY(520);
        research.setText("Research Cloak");
        research.setPrefSize(200, 50);
        research.setVisible(false);
        research.setId("research");
        playGamePane.getChildren().add(research);

        cloak.setLayoutX(920);
        cloak.setLayoutY(600);
        cloak.setText("Cloak");
        cloak.setPrefSize(200, 50);
        cloak.setVisible(false);
        cloak.setId("cloak");
        playGamePane.getChildren().add(cloak);

        done.setLayoutX(920);
        done.setLayoutY(680);
        done.setText("Done");
        done.setPrefSize(200, 50);
        done.setId("done");
        playGamePane.getChildren().add(done);

    }

    public void exitOrContinue() {

        continueWatch.setVisible(true);
        exitGame.setVisible(true);
        imageView2.setVisible(true);

        choicesOfLevel1.setVisible(false);
        choicesOfLevel2.setVisible(false);
        attackLevel.setVisible(false);
        choicesOfDest.setVisible(false);
        choicesOfSource.setVisible(false);
        srcPrompt.setVisible(false);
        destPrompt.setVisible(false);
        upgrade.setVisible(false);
        move.setVisible(false);
        attack.setVisible(false);
        tech.setVisible(false);
        research.setVisible(false);
        cloak.setVisible(false);
        done.setVisible(false);
    }

    public void deactivateAll() {
        prompt1.setVisible(true);
        prompt1.setText("Wait for other players to perform the action...");
        choicesOfLevel1.setVisible(false);
        choicesOfLevel2.setVisible(false);
        attackLevel.setVisible(false);
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
        research.setVisible(false);
        cloak.setVisible(false);
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
        // research.setVisible(true);
        // cloak.setVisible(true);
        done.setVisible(true);
    }

}
