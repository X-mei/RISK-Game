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
    Button previousGame;
    Button newGame;
    Label error;

    public EnterGameView() {
        this.enterGamePane = new AnchorPane();
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

        // choice button
        previousGame.setLayoutX(620);
        previousGame.setLayoutY(450);
        previousGame.setId("previousgame");
        previousGame.setText("JOIN BEFORE!");
        previousGame.setPrefSize(150, 80);
        enterGamePane.getChildren().add(previousGame);

        newGame.setLayoutX(620);
        newGame.setLayoutY(600);
        newGame.setId("newgame");
        newGame.setText("CREATE NEW!");
        newGame.setPrefSize(150, 80);
        enterGamePane.getChildren().add(newGame);

    }
    
}
