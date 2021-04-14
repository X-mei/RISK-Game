package client;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SendRoomIDView {
    AnchorPane roomIDPane;
    Label prompt;
    TextField roomID;
    Button confirm;
    Label error;

    public SendRoomIDView() {
        this.roomIDPane = new AnchorPane();
        this.prompt = new Label();
        this.roomID = new TextField();
        this.confirm = new Button();
        this.error = new Label();
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
        roomIDPane.getChildren().add(imageView);
      } catch (IOException e) {
        e.printStackTrace();
      }

        // prompt label
        prompt.setLayoutX(600);
        prompt.setLayoutY(500);
        prompt.setText("Please input room id.");
        prompt.setFont(new Font("Arial", 15));
        prompt.setTextFill(Color.web("#a0522d", 1.0));
        roomIDPane.getChildren().add(prompt);

        // roomID textField
        roomID.setLayoutX(600);
        roomID.setLayoutY(550);
        roomID.setPromptText("room id");
        roomID.setFocusTraversable(false);
        roomIDPane.getChildren().add(roomID);

        // confirm button
        confirm.setLayoutX(700);
        confirm.setLayoutY(650);
        confirm.setText("confirm");
        confirm.setStyle("-fx-background-color: #8FBC8F;");
        roomIDPane.getChildren().add(confirm);

        // error
        error.setLayoutX(600);
        error.setLayoutY(650);
        error.setText("Wrong room id.");
        error.setVisible(false);
        error.setFont(new Font("Arial", 20));
        error.setTextFill(Color.web("#ff0000", 1.0));
        roomIDPane.getChildren().add(error);
    }
}
