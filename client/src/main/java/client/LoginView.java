package client;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginView {
    AnchorPane loginPane;
    Label username;
    TextField text;
    Label pwd;
    PasswordField password;
    Button register;
    Button login;
    Label error;

    public LoginView() {
        this.loginPane = new AnchorPane();
        this.username = new Label();
        this.text = new TextField();
        this.pwd = new Label();
        this.password = new PasswordField();
        this.register = new Button();
        this.login = new Button();
        this.error = new Label();
    }

    public void init() {
      try {
        // add image
        String path = "src/main/resources/riscTitle.png";
        FileInputStream imageStream = new FileInputStream(path);
        Image image1 = new Image(imageStream);
        ImageView imageView1 = new ImageView(image1);
        imageView1.setX(300);
        imageView1.setY(25);
        imageView1.setFitHeight(400);
        imageView1.setFitWidth(800);
        loginPane.getChildren().add(imageView1);
      } catch (IOException e) {
        e.printStackTrace();
      }

      try {
        // add image
        String path = "src/main/resources/soldiers.gif";
        FileInputStream imageStream = new FileInputStream(path);
        Image image2 = new Image(imageStream);
        ImageView imageView2 = new ImageView(image2);
        imageView2.setX(300);
        imageView2.setY(500);
        imageView2.setFitHeight(200);
        imageView2.setFitWidth(800);
        loginPane.getChildren().add(imageView2);
      } catch (IOException e) {
        e.printStackTrace();
      }

      
        // username Label
        username.setLayoutX(550);
        username.setLayoutY(400);
        username.setText("username: ");
        // username.setPrefSize(100, 50);
        username.setFont(new Font("Arial", 15));
        username.setTextFill(Color.web("#a0522d", 1.0));
        loginPane.getChildren().add(username);

        // username textField
        text.setLayoutX(650);
        text.setLayoutY(400);
        text.setPromptText("please input username");
        text.setFocusTraversable(false);
        loginPane.getChildren().add(text);

        // password label
        pwd.setLayoutX(550);
        pwd.setLayoutY(450);
        pwd.setText("password: ");
        pwd.setFont(new Font("Arial", 15));
        pwd.setTextFill(Color.web("#a0522d", 1.0));
        loginPane.getChildren().add(pwd);

        // password passwordField
        password.setLayoutX(650);
        password.setLayoutY(450);
        loginPane.getChildren().add(password);

        // button register
        register.setLayoutX(600);
        register.setLayoutY(550);
        register.setText("register");
        register.setId("register");
        register.setStyle("-fx-background-color: #8FBC8F;");
        register.setPrefWidth(80);
        loginPane.getChildren().add(register);
        

        // button login
        login.setLayoutX(700);
        login.setLayoutY(550);
        login.setText("login");
        login.setId("login");
        login.setStyle("-fx-background-color: #CDCDB4;");
        login.setPrefWidth(80);
        loginPane.getChildren().add(login);

        // error
        error.setLayoutX(550);
        error.setLayoutY(700);
        error.setText("Error! Please enter again.");
        error.setFont(new Font("Arial", 20));
        error.setTextFill(Color.web("#ff0000", 1.0));
        error.setVisible(false);
        loginPane.getChildren().add(error);

    }

}
