package client;

import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOError;
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
        Image image = new Image(imageStream);
        ImageView imageView = new ImageView(image);
        imageView.setX(300);
        imageView.setY(25);
        imageView.setFitHeight(400);
        imageView.setFitWidth(800);
        loginPane.getChildren().add(imageView);
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


        // animation
        final Rectangle rectPath = new Rectangle (50, 50, 40, 40);
        loginPane.getChildren().add(rectPath);
        rectPath.setArcHeight(10);
        rectPath.setArcWidth(10);
        rectPath.setFill(Color.ORANGE);
        Path path = new Path();
        path.getElements().add(new MoveTo(20,20));
        path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
        path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(4000));
        pathTransition.setPath(path);
        pathTransition.setNode(rectPath);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.play();
    }

}
