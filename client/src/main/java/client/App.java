/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class App extends Application {

    static AnchorPane root;
    LoginView loginView;
    EnterGameView enterGameView;
    ChooseRoomView chooseRoomView;

    public App() {
        this.root = new AnchorPane();
        this.loginView = new LoginView();
        this.enterGameView = new EnterGameView();
        this.chooseRoomView = new ChooseRoomView();
    }

    public static void main(String[] args) {
//      try {
//        Client client = new Client("127.0.0.1", 12345,
//                new BufferedReader(new InputStreamReader(System.in)), System.out);
//        client.connectToServer();
//        client.login();
//        client.answerInfo();
//        client.recvNameAndSeq();
//        client.recvStartStatus();
//        if (client.getStartStatus() == 1) {
//          client.recvAssignTerritory();
//        }
//        while(true) {
//          if (!client.recvBoardPromptAndSend()) {
//            break;
//          }
//        }
//        if (client.exitOrContinue()) {
//          client.recvMsg();
//        }
//        client.closeConnection();
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // connect to server
        Client client = new Client("127.0.0.1", 12345,
                new BufferedReader(new InputStreamReader(System.in)), System.out);
        client.connectToServer();

        // login
        loginView.init();
        root.getChildren().add(loginView.loginPane);
        LoginController loginController = new LoginController(loginView, enterGameView, client);

        // set scene
        Scene scene = new Scene(root, 1500, 800);
        scene.getStylesheets().add(getClass().getResource("/Game.css").toExternalForm());
        primaryStage.setTitle("Welcome to RISC");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }

}
