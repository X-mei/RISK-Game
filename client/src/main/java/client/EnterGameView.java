package client;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class EnterGameView {
    AnchorPane enterGamePane;
    Label prompt;
    Button previousGame;
    Button newGame;
    Label error;

    public EnterGameView() {
        this.enterGamePane = new AnchorPane();
        this.prompt = new Label();
        this.previousGame = new Button();
        this.newGame = new Button();
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
        prompt.setLayoutX(480);
        prompt.setLayoutY(350);
        prompt.setText("Do you want to enter a previous game or enter a new game?");
        prompt.setFont(new Font("Arial", 15));
        prompt.setTextFill(Color.web("#a0522d", 1.0));
        enterGamePane.getChildren().add(prompt);

        // choice button
        previousGame.setLayoutX(620);
        previousGame.setLayoutY(400);
        previousGame.setText("JOIN BEFORE!");
        previousGame.setStyle("-fx-background-color: #8FBC8F;");
        previousGame.setPrefSize(150, 80);
        enterGamePane.getChildren().add(previousGame);

        newGame.setLayoutX(620);
        newGame.setLayoutY(500);
        newGame.setText("CREATE NEW!");
        newGame.setStyle("-fx-background-color: #8FBC8F;");
        newGame.setPrefSize(150, 80);
        enterGamePane.getChildren().add(newGame);

    }
    
}
