package client;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class EnterGameView {
    AnchorPane enterGamePane;
    Label prompt;
    RadioButton r1;
    RadioButton r2;
    ToggleGroup tg;
    Button confirm;
    Label error;

    public EnterGameView() {
        this.enterGamePane = new AnchorPane();
        this.prompt = new Label();
        this.r1 = new RadioButton("Enter a Previous Game");
        this.r2 = new RadioButton("Create a New Game");
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
        enterGamePane.getChildren().add(imageView);
      } catch (IOException e) {
        e.printStackTrace();
      }

        // prompt label
        prompt.setLayoutX(500);
        prompt.setLayoutY(400);
        prompt.setText("Do you want to enter a previous game or enter a new game?");
        prompt.setFont(new Font("Arial", 15));
        prompt.setTextFill(Color.web("#a0522d", 1.0));
        enterGamePane.getChildren().add(prompt);

        // choice
        r1.setToggleGroup(tg);
        r1.setLayoutX(600);
        r1.setLayoutY(500);
        r2.setToggleGroup(tg);
        r2.setLayoutX(600);
        r2.setLayoutY(550);
        enterGamePane.getChildren().add(r1);
        enterGamePane.getChildren().add(r2);

        // confirm button
        confirm.setLayoutX(650);
        confirm.setLayoutY(600);
        confirm.setText("confirm");
        confirm.setStyle("-fx-background-color: #8FBC8F;");
        enterGamePane.getChildren().add(confirm);

    }
    
}
