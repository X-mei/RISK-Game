package client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class LoginController {
    LoginView loginView;
    EnterGameView enterGameView;
    Client client;

    public LoginController(LoginView loginView, EnterGameView enterGameView, Client client) {
        this.loginView = loginView;
        this.enterGameView = enterGameView;
        enterGameView.init();
        this.client = client;
        registerAction();
        loginAction();
    }

    public void registerAction() {
        loginView.register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                client.login("r", loginView.text.getText(), loginView.password.getText());
                App.root.getChildren().remove(loginView.loginPane);
                App.root.getChildren().add(enterGameView.enterGamePane);
                EnterGameController enterGameController = new EnterGameController(enterGameView, client);
            }
        });
    }

    public void loginAction() {
        loginView.login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                client.login("l", loginView.text.getText(), loginView.password.getText());
                App.root.getChildren().remove(loginView.loginPane);
                App.root.getChildren().add(enterGameView.enterGamePane);
                EnterGameController enterGameController = new EnterGameController(enterGameView, client);
            }
        });
    }
}
