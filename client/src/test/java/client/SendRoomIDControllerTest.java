// package client;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.testfx.framework.junit5.ApplicationExtension;

// import javafx.scene.control.TextField;

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;

// @ExtendWith(ApplicationExtension.class)
// public class SendRoomIDControllerTest {
//     private SendRoomIDController cont;
//     private SendRoomIDView sendRoomIDView;

//     @Test
//     public void test_sendRoom() {
//         sendRoomIDView = new SendRoomIDView();
//         sendRoomIDView.init();
//         Client client = new Client("127.0.0.1", 1234,  new BufferedReader(new InputStreamReader(System.in)), System.out);
//         cont = new SendRoomIDController(sendRoomIDView, client);
//     }
// }
