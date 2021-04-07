package client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.junit.jupiter.api.Test;

@ExtendWith(ApplicationExtension.class)
public class EnterGameControllerTest {
  private EnterGameView enterGameView;
  private ChooseRoomView chooseRoomView;
  private SendRoomIDView sendRoomIDView;
  private Client client;
  private EnterGameController cont;

  @Test
  public void test_enterGame() {
    enterGameView = new EnterGameView();
    enterGameView.init();
    chooseRoomView = new ChooseRoomView();
    sendRoomIDView = new SendRoomIDView();
    Client client = new Client("127.0.0.1", 1234, new BufferedReader(new InputStreamReader(System.in)), System.out);
    cont = new EnterGameController(enterGameView, client);
  }

}
