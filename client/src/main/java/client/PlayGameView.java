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
import javafx.scene.paint.Paint;

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
    
    TextField input;
    Button confirm;
    Button continueWatch;
    Button exitGame;
    ChoiceBox choicesOfLevel1;
    ChoiceBox choicesOfLevel2;
    ChoiceBox choicesOfDest;
    ChoiceBox choicesOfSource;
    HashMap<String, ArrayList<String>> territories;
    HashMap<String, String> units;

    Label error;
    Label errorTech;

    ImageView imageView2;

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
        this.choicesOfLevel1 = new ChoiceBox();
        this.choicesOfLevel2 = new ChoiceBox();
        this.choicesOfDest = new ChoiceBox();
        this.choicesOfSource = new ChoiceBox();
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
        
        if(playerNum.equals("2")){
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
        error.setLayoutY(615);
        error.setText("Error! Please enter again.");
        error.setVisible(false);
        playGamePane.getChildren().add(error);

        errorTech.setLayoutX(40);
        errorTech.setLayoutY(690);
        errorTech.setText("Error! You have reached the highest tech level.");
        errorTech.setVisible(false);
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

  public void recvTerrInfo(HashMap<String, ArrayList<String>> territories, HashMap<String, String> units){
    this.territories = territories;
    this.units = units;
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
        String str = "to upgrade";
        Tooltip.install(upgrade, new Tooltip(str));

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

        research.setLayoutX(300);
        research.setLayoutY(570);
        research.setText("Research Cloak");
        research.setPrefWidth(200);
        playGamePane.getChildren().add(research);

        cloak.setLayoutX(300);
        cloak.setLayoutY(610);
        cloak.setText("Cloak");
        cloak.setPrefWidth(200);
        playGamePane.getChildren().add(cloak);

        done.setLayoutX(40);
        done.setLayoutY(650);
        done.setText("Done");
        done.setPrefWidth(200);
        playGamePane.getChildren().add(done);

    }

    public void exitOrContinue() {

        continueWatch.setVisible(true);
        exitGame.setVisible(true);
        imageView2.setVisible(true);

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
        research.setVisible(true);
        cloak.setVisible(true);
        done.setVisible(true);
    }

}
