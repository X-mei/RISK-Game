package client;

import java.io.FileInputStream;
import java.io.IOException;

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
import javafx.scene.text.Font;

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
    
    
    Label error;
    TextField input1;
    TextField input2;
    TextField input3;
    Button submit;
    String playerNum;
    ImageView imageView1;

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
        this.terr4P_1 = new Button();
        this.terr4P_2 = new Button();
        this.terr4P_3 = new Button();
        this.terr4P_4 = new Button();
        this.terr4P_5 = new Button();
        this.terr4P_6 = new Button();
        this.terr4P_7 = new Button();
        this.terr4P_8 = new Button();
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
        this.input1 = new TextField();
        this.input2 = new TextField();
        this.input3 = new TextField();
        this.submit = new Button();
    }

    public void init(String playerNum) {
        // show img
        this.playerNum = playerNum;
        if (playerNum.equals("1")) {
          this.playerNum = "2";
        }
        
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

            
        terr4P_1.setLayoutX(135);
        terr4P_1.setLayoutY(85);
        terr4P_1.setText("Dorado");
        terr4P_1.setVisible(false);
        terr4P_1.setId("4p1");
        assignTerrPane.getChildren().add(terr4P_1);
        
                
        terr4P_2.setLayoutX(125);
        terr4P_2.setLayoutY(300);
        terr4P_2.setText("Oasis");
        terr4P_2.setVisible(false);
        terr4P_2.setId("4p2");
        assignTerrPane.getChildren().add(terr4P_2);

                
        terr4P_3.setLayoutX(305);
        terr4P_3.setLayoutY(350);
        terr4P_3.setText("Nepal");
        terr4P_3.setVisible(false);
        terr4P_3.setId("4p3");
        assignTerrPane.getChildren().add(terr4P_3);
                
        terr4P_4.setLayoutX(425);
        terr4P_4.setLayoutY(355);
        terr4P_4.setText("Ilios");
        terr4P_4.setVisible(false);
        terr4P_4.setId("4p4");
        assignTerrPane.getChildren().add(terr4P_4);

                
        terr4P_5.setLayoutX(500);
        terr4P_5.setLayoutY(365);
        terr4P_5.setText("Junkertown");
        terr4P_5.setVisible(false);
        terr4P_5.setId("4p5");
        assignTerrPane.getChildren().add(terr4P_5);
   
                
        terr4P_6.setLayoutX(605);
        terr4P_6.setLayoutY(200);
        terr4P_6.setText("Volskaya");
        terr4P_6.setVisible(false);
        terr4P_6.setId("4p6");
        assignTerrPane.getChildren().add(terr4P_6);
        
                
        terr4P_7.setLayoutX(445);
        terr4P_7.setLayoutY(75);
        terr4P_7.setText("Hollywood");
        terr4P_7.setVisible(false);
        terr4P_7.setId("4p7");
        assignTerrPane.getChildren().add(terr4P_7);
                
        terr4P_8.setLayoutX(325);
        terr4P_8.setLayoutY(70);
        terr4P_8.setText("Hanamura");
        terr4P_8.setVisible(false);
        terr4P_8.setId("4p8");
        assignTerrPane.getChildren().add(terr4P_8);

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
        assignTerrPane.getChildren().add(terr5P_1);

                
        terr5P_2.setLayoutX(90);
        terr5P_2.setLayoutY(260);
        terr5P_2.setText("Hanamura");
        terr5P_2.setVisible(false);
        terr5P_2.setId("5p2");
        assignTerrPane.getChildren().add(terr5P_2);

                
        terr5P_3.setLayoutX(150);
        terr5P_3.setLayoutY(300);
        terr5P_3.setText("Anubis");
        terr5P_3.setVisible(false);
        terr5P_3.setId("5p3");
        assignTerrPane.getChildren().add(terr5P_3);
        
                
        terr5P_4.setLayoutX(280);
        terr5P_4.setLayoutY(350);
        terr5P_4.setText("Numbani");
        terr5P_4.setVisible(false);
        terr5P_4.setId("5p4");
        assignTerrPane.getChildren().add(terr5P_4);

                
        terr5P_5.setLayoutX(450);
        terr5P_5.setLayoutY(325);
        terr5P_5.setText("Junkertown");
        terr5P_5.setVisible(false);
        terr5P_5.setId("5p5");
        assignTerrPane.getChildren().add(terr5P_5);

                
        terr5P_6.setLayoutX(550);
        terr5P_6.setLayoutY(365);
        terr5P_6.setText("Oasis");
        terr5P_6.setVisible(false);
        terr5P_6.setId("5p6");
        assignTerrPane.getChildren().add(terr5P_6);

                
        terr5P_7.setLayoutX(610);
        terr5P_7.setLayoutY(240);
        terr5P_7.setText("Nepal");
        terr5P_7.setVisible(false);
        terr5P_7.setId("5p7");
        assignTerrPane.getChildren().add(terr5P_7);

                
        terr5P_8.setLayoutX(620);
        terr5P_8.setLayoutY(200);
        terr5P_8.setText("Ilios");
        terr5P_8.setVisible(false);
        terr5P_8.setId("5p8");
        assignTerrPane.getChildren().add(terr5P_8);
        
                
        terr5P_9.setLayoutX(345);
        terr5P_9.setLayoutY(55);
        terr5P_9.setText("Volskaya");
        terr5P_9.setVisible(false);
        terr5P_9.setId("5p9");
        assignTerrPane.getChildren().add(terr5P_9);

                        
        terr5P_10.setLayoutX(255);
        terr5P_10.setLayoutY(55);
        terr5P_10.setText("Hollywood");
        terr5P_10.setVisible(false);
        terr5P_10.setId("5p10");
        assignTerrPane.getChildren().add(terr5P_10);

        
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
                                       
        // prompt: wait
        prompt1.setLayoutX(120);
        prompt1.setLayoutY(500);
        prompt1.setText("Wait for other players to join...");
        prompt1.setFont(new Font("Arial", 20));
        prompt1.setTextFill(Color.web("#a0522d", 1.0));
        assignTerrPane.getChildren().add(prompt1);

        // animation
        try {
          // add image
          String path = "src/main/resources/loading.gif";
          FileInputStream imageStream = new FileInputStream(path);
          Image image1 = new Image(imageStream);
          imageView1 = new ImageView(image1);
          imageView1.setX(100);
          imageView1.setY(600);
          imageView1.setFitHeight(200);
          imageView1.setFitWidth(350);
          assignTerrPane.getChildren().add(imageView1);
        } catch (IOException e) {
          e.printStackTrace();
        }

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
