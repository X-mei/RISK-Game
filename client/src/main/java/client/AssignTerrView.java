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
