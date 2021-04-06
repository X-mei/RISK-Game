package client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import javafx.scene.control.TextField;
import jdk.jfr.Timestamp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@ExtendWith(ApplicationExtension.class)
public class PlayGameControllerTest {
  private PlayGameController playGameController;
  private PlayGameView playGameView;

  @Test
  public void test_playGame() {
    playGameView = new PlayGameView("test");
    playGameView.init("2");
    Client client = new Client("127.0.0.1", 1234,  new BufferedReader(new InputStreamReader(System.in)), System.out);
    playGameController = new PlayGameController(playGameView, client);
  }

  @Test
  public void test_playGame2() {
    playGameView = new PlayGameView("test");
    playGameView.init("3");
    Client client = new Client("127.0.0.1", 1234,  new BufferedReader(new InputStreamReader(System.in)), System.out);
    playGameController = new PlayGameController(playGameView, client);
  }

  @Test
  public void test_playGame3() {
    playGameView = new PlayGameView("test");
    playGameView.init("4");
    Client client = new Client("127.0.0.1", 1234,  new BufferedReader(new InputStreamReader(System.in)), System.out);
    playGameController = new PlayGameController(playGameView, client);
  }

  @Test
  public void test_playGame4() {
    playGameView = new PlayGameView("test");
    playGameView.init("5");
    Client client = new Client("127.0.0.1", 1234,  new BufferedReader(new InputStreamReader(System.in)), System.out);
    playGameController = new PlayGameController(playGameView, client);
    playGameView.addPrompt("hello");
    playGameView.addPrompt2("hello2");
    playGameView.exitOrContinue();
    playGameView.deactivateAll();
    playGameView.activateAll();
  }

}
