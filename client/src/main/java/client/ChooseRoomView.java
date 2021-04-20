package client;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.IOException;

public class ChooseRoomView {
    AnchorPane chooseRoomPane;
    // Label prompt;
    RadioButton r1;
    RadioButton r2;
    RadioButton r3;
    RadioButton r4;
    RadioButton r5;
    RadioButton r6;
    ToggleGroup tg;
    Button confirm;

    public ChooseRoomView() {
        this.chooseRoomPane = new AnchorPane();
        // this.prompt = new Label();
        this.r1 = new RadioButton("2 human players");
        this.r2 = new RadioButton("3 human players");
        this.r3 = new RadioButton("4 human players");
        this.r4 = new RadioButton("5 human players");
        this.r5 = new RadioButton("1 human player vs AI player");
        this.r6 = new RadioButton("0 human player, AI player vs AI player");
        this.tg = new ToggleGroup();
        this.confirm = new Button();
    }

    public void init() {

      try {
        // add image
        String path = "src/main/resources/riscTitle.png";
        FileInputStream imageStream = new FileInputStream(path);
        Image image = new Image(imageStream);
        ImageView imageView = new ImageView(image);
        imageView.setX(300);
        imageView.setY(25);
        imageView.setFitHeight(400);
        imageView.setFitWidth(800);
        chooseRoomPane.getChildren().add(imageView);
      } catch (IOException e) {
        e.printStackTrace();
      }


        // prompt label
        // prompt.setLayoutX(500);
        // prompt.setLayoutY(350);
        // prompt.setText("Which game room do you want to enter?");
        // prompt.setFont(new Font("Arial", 15));
        // prompt.setTextFill(Color.web("#a0522d", 1.0));
        // chooseRoomPane.getChildren().add(prompt);

        // input radio button
        r1.setToggleGroup(tg);
        r1.setLayoutX(550);
        r1.setLayoutY(400);
        r2.setToggleGroup(tg);
        r2.setLayoutX(550);
        r2.setLayoutY(450);
        r3.setToggleGroup(tg);
        r3.setLayoutX(550);
        r3.setLayoutY(500);
        r4.setToggleGroup(tg);
        r4.setLayoutX(550);
        r4.setLayoutY(550);
        r5.setToggleGroup(tg);
        r5.setLayoutX(550);
        r5.setLayoutY(600);
        r6.setToggleGroup(tg);
        r6.setLayoutX(550);
        r6.setLayoutY(650);
        
        r1.setFont(new Font("Arial", 15));
        r1.setTextFill(Color.web("#1d195e", 1.0));
        r1.setStyle("-fx-font-weight: bold;");

        r2.setFont(new Font("Arial", 15));
        r2.setTextFill(Color.web("#1d195e", 1.0));
        r2.setStyle("-fx-font-weight: bold;");

        r3.setFont(new Font("Arial", 15));
        r3.setTextFill(Color.web("#1d195e", 1.0));
        r3.setStyle("-fx-font-weight: bold;");

        r4.setFont(new Font("Arial", 15));
        r4.setTextFill(Color.web("#1d195e", 1.0));
        r4.setStyle("-fx-font-weight: bold;");

        r5.setFont(new Font("Arial", 15));
        r5.setTextFill(Color.web("#1d195e", 1.0));
        r5.setStyle("-fx-font-weight: bold;");

        r6.setFont(new Font("Arial", 15));
        r6.setTextFill(Color.web("#1d195e", 1.0));
        r6.setStyle("-fx-font-weight: bold;");

        chooseRoomPane.getChildren().add(r1);
        chooseRoomPane.getChildren().add(r2);
        chooseRoomPane.getChildren().add(r3);
        chooseRoomPane.getChildren().add(r4);
        chooseRoomPane.getChildren().add(r5);
        chooseRoomPane.getChildren().add(r6);

        // confirm button
        confirm.setLayoutX(650);
        confirm.setLayoutY(700);
        confirm.setId("confirmRoom");
        confirm.setText("confirm");
        confirm.setPrefSize(80, 50);
        confirm.setStyle("-fx-background-color: #8FBC8F;");
        chooseRoomPane.getChildren().add(confirm);

    }
}
