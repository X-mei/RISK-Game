// package client;

// import java.io.BufferedReader;
// import java.io.InputStreamReader;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.testfx.api.FxRobot;
// import org.testfx.framework.junit5.ApplicationExtension;

// import javafx.scene.control.Button;
// import javafx.scene.control.TextField;

// @ExtendWith(ApplicationExtension.class)
// public class LoginControllerTest {

//   private TextField username;
//   private TextField password;
//   private LoginController cont;
//   private LoginView loginView;
//   private EnterGameView enterGameView;

//   @Test
//   public void test_login(FxRobot robot) {
//     loginView = new LoginView();
//     loginView.init();
//     enterGameView = new EnterGameView();
//     Client client = new Client("127.0.0.1", 1234,  new BufferedReader(new InputStreamReader(System.in)), System.out);
//     cont = new LoginController(loginView, enterGameView, client);
//     Button b = new Button("register");
//     cont.registerAction();
//   }

  
// }






