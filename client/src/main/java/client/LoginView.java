package client;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
        // username Label
        username.setLayoutX(20);
        username.setLayoutY(75);
        username.setText("username: ");
        // username.setPrefSize(100, 50);
        loginPane.getChildren().add(username);

        // username textField
        text.setLayoutX(100);
        text.setLayoutY(70);
        text.setPromptText("please input username");
        text.setFocusTraversable(false);
        loginPane.getChildren().add(text);

        // password label
        pwd.setLayoutX(20);
        pwd.setLayoutY(135);
        pwd.setText("password: ");
        loginPane.getChildren().add(pwd);

        // password passwordField
        password.setLayoutX(100);
        password.setLayoutY(130);
        loginPane.getChildren().add(password);

        // button register
        register.setLayoutX(100);
        register.setLayoutY(200);
        register.setText("register");
        loginPane.getChildren().add(register);

        // button login
        login.setLayoutX(200);
        login.setLayoutY(200);
        login.setText("login");
        loginPane.getChildren().add(login);

        // error
        error.setLayoutX(200);
        error.setLayoutY(250);
        error.setText("Error! Please enter again.");
        error.setVisible(false);
        loginPane.getChildren().add(error);
    }

}
