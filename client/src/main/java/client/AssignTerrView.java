package client;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class AssignTerrView {
    AnchorPane assignTerrPane;
    Label prompt1;
    Label prompt2;
    Label prompt3;
    Label promptTerri1;
    Label promptTerri2;
    Label promptTerri3;
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
  
    Label error;
    TextField input1;
    TextField input2;
    TextField input3;
    Button submit;
    String playerNum;
    final Rectangle rectPath;

    public AssignTerrView() {
        this.assignTerrPane = new AnchorPane();
        this.prompt1 = new Label();
        this.prompt2 = new Label();
        this.prompt3 = new Label();
        this.promptTerri1 = new Label();
        this.promptTerri2 = new Label();
        this.promptTerri3 = new Label();
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
        this.input1 = new TextField();
        this.input2 = new TextField();
        this.input3 = new TextField();
        this.submit = new Button();
        this.rectPath = new Rectangle (50, 600, 40, 40);
    }

    public void init(String playerNum) {
        // show img
        this.playerNum = playerNum;
        try {
            String path = null;
            if (playerNum.equals("2")) {
                path = "src/main/resources/2P.JPG";
            } else if (playerNum.equals("3")) {
                path = "src/main/resources/3P.JPG";
            } else if (playerNum.equals("4")) {
                path = "src/main/resources/4P.JPG";
            } else if (playerNum.equals("5")) {
                path = "src/main/resources/5P.JPG";
            } else {
                path = "src/main/resources/2P.JPG";
            }
            FileInputStream imageStream = new FileInputStream(path);
            Image image = new Image(imageStream);
            ImageView imageView = new ImageView(image);
            imageView.setX(40);
            imageView.setY(25);
            imageView.setFitHeight(382.5);
            imageView.setFitWidth(680);
            assignTerrPane.getChildren().add(imageView);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        terr2P_1.setLayoutX(135);
        terr2P_1.setLayoutY(85);
        terr2P_1.setText("Hanamura");
        terr2P_1.setVisible(false);
        terr2P_1.setId("Hanamura");
        assignTerrPane.getChildren().add(terr2P_1);
        BackgroundFill backgroundFill = new BackgroundFill(Paint.valueOf("#8FBC8F"), new CornerRadii(20), Insets.EMPTY);
        Background background = new Background(backgroundFill);
        terr2P_1.setBackground(background);

        terr2P_2.setLayoutX(140);
        terr2P_2.setLayoutY(310);
        terr2P_2.setText("Dorado");
        terr2P_2.setVisible(false);
        terr2P_2.setId("Dorado");
        assignTerrPane.getChildren().add(terr2P_2);

        terr2P_3.setLayoutX(325);
        terr2P_3.setLayoutY(355);
        terr2P_3.setText("Hollywood");
        terr2P_3.setId("Hollywood");
        terr2P_3.setVisible(false);
        assignTerrPane.getChildren().add(terr2P_3);
        
        terr2P_4.setLayoutX(483);
        terr2P_4.setLayoutY(337);
        terr2P_4.setText("Ilios");
        terr2P_4.setId("Ilios");
        terr2P_4.setVisible(false);
        assignTerrPane.getChildren().add(terr2P_4);
       
        terr2P_5.setLayoutX(605);
        terr2P_5.setLayoutY(200);
        terr2P_5.setText("Junkertown");
        terr2P_5.setVisible(false);
        terr2P_5.setId("Junkertown");
        assignTerrPane.getChildren().add(terr2P_5);

        terr2P_6.setLayoutX(438);
        terr2P_6.setLayoutY(65);
        terr2P_6.setText("Volskaya");
        terr2P_6.setVisible(false);
        terr2P_6.setId("Volskaya");
        assignTerrPane.getChildren().add(terr2P_6);

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
        assignTerrPane.getChildren().add(terr3P_1);

                
        terr3P_2.setLayoutX(45);
        terr3P_2.setLayoutY(210);
        terr3P_2.setText("Dorado");
        terr3P_2.setVisible(false);
        terr3P_2.setId("3p2");
        assignTerrPane.getChildren().add(terr3P_2);

                
        terr3P_3.setLayoutX(100);
        terr3P_3.setLayoutY(260);
        terr3P_3.setText("Hollywood");
        terr3P_3.setVisible(false);
        terr3P_3.setId("3p3");
        assignTerrPane.getChildren().add(terr3P_3);
        
                
        terr3P_4.setLayoutX(140);
        terr3P_4.setLayoutY(350);
        terr3P_4.setText("Volskaya");
        terr3P_4.setVisible(false);
        terr3P_4.setId("3p4");
        assignTerrPane.getChildren().add(terr3P_4);

                
        terr3P_5.setLayoutX(270);
        terr3P_5.setLayoutY(355);
        terr3P_5.setText("Junkertown");
        terr3P_5.setVisible(false);
        terr3P_5.setId("3p5");
        assignTerrPane.getChildren().add(terr3P_5);

                
        terr3P_6.setLayoutX(420);
        terr3P_6.setLayoutY(365);
        terr3P_6.setText("Ilios");
        terr3P_6.setVisible(false);
        terr3P_6.setId("3p6");
        assignTerrPane.getChildren().add(terr3P_6);

                
        terr3P_7.setLayoutX(620);
        terr3P_7.setLayoutY(240);
        terr3P_7.setText("Numbani");
        terr3P_7.setVisible(false);
        terr3P_7.setId("3p7");
        assignTerrPane.getChildren().add(terr3P_7);

                
        terr3P_8.setLayoutX(490);
        terr3P_8.setLayoutY(85);
        terr3P_8.setText("Oasis");
        terr3P_8.setVisible(false);
        terr3P_8.setId("3p8");
        assignTerrPane.getChildren().add(terr3P_8);
        
                
        terr3P_9.setLayoutX(345);
        terr3P_9.setLayoutY(85);
        terr3P_9.setText("Nepal");
        terr3P_9.setVisible(false);
        terr3P_9.setId("3p9");
        assignTerrPane.getChildren().add(terr3P_9);

        
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
                                       
        // prompt: wait
        prompt1.setLayoutX(750);
        prompt1.setLayoutY(75);
        prompt1.setText("Wait for other players to join...");
        assignTerrPane.getChildren().add(prompt1);

        // animation
        assignTerrPane.getChildren().add(rectPath);
        rectPath.setArcHeight(10);
        rectPath.setArcWidth(10);
        rectPath.setFill(Color.ORANGE);
        Path path = new Path();
        path.getElements().add(new MoveTo(650,600));
        path.getElements().add(new CubicCurveTo(200, 500, 500, 700, 650, 600));
        path.getElements().add(new CubicCurveTo(500, 500, 200, 700, 50, 600));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(4000));
        pathTransition.setPath(path);
        pathTransition.setNode(rectPath);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.play();

        // error
        error.setLayoutX(750);
        error.setLayoutY(500);
        error.setText("Wrong, please enter again");
        error.setVisible(false);
        assignTerrPane.getChildren().add(error);
    }

    public void addPromptOfTerritory(String[] prompts){
        promptTerri1.setLayoutX(750);
        promptTerri1.setLayoutY(200);
        promptTerri1.setText(prompts[0]);
        assignTerrPane.getChildren().add(promptTerri1);

        promptTerri2.setLayoutX(750);
        promptTerri2.setLayoutY(300);
        promptTerri2.setText(prompts[1]);
        assignTerrPane.getChildren().add(promptTerri2);

        if(prompts.length == 3){
            promptTerri3.setLayoutX(750);
            promptTerri3.setLayoutY(400);
            promptTerri3.setText(prompts[2]);
            assignTerrPane.getChildren().add(promptTerri3);
        }
    }

    public void addPrompt2(String prompt) {
        // add prompt2
        prompt1.setVisible(false);
        prompt2.setLayoutX(750);
        prompt2.setLayoutY(75);
        prompt2.setText(prompt);
        assignTerrPane.getChildren().add(prompt2);
    }

    public void addPrompt3(String prompt) {
        prompt3.setLayoutX(750);
        prompt3.setLayoutY(150);
        prompt3.setText(prompt);
        assignTerrPane.getChildren().add(prompt3);


        input1.setLayoutX(750);
        input1.setLayoutY(250);
        assignTerrPane.getChildren().add(input1);

        input2.setLayoutX(750);
        input2.setLayoutY(350);
        assignTerrPane.getChildren().add(input2);

        input3.setLayoutX(750);
        input3.setLayoutY(450);
        assignTerrPane.getChildren().add(input3);

        if (playerNum.equals("4") || playerNum.equals("5")) {
            input3.setVisible(false);
        }

        submit.setLayoutX(750);
        submit.setLayoutY(550);
        submit.setText("submit");
        assignTerrPane.getChildren().add(submit);
    }

    public void waitPlayer() {
        prompt1.setVisible(true);
        prompt1.setText("Wait for other players to assign units...");
        prompt2.setVisible(false);
        prompt3.setVisible(false);
        promptTerri1.setVisible(false);
        promptTerri2.setVisible(false);
        promptTerri3.setVisible(false);
        input1.setVisible(false);
        input2.setVisible(false);
        input3.setVisible(false);
        submit.setVisible(false);
        error.setVisible(false);
    }
}
