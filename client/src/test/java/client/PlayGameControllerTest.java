package client;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

@ExtendWith(ApplicationExtension.class)
public class PlayGameControllerTest {
  private PlayGameController playGameController;
  private PlayGameView playGameView;

  @Test
  public void test_playGame() {
    playGameView = new PlayGameView("test");
    playGameView.init("2");
    playGameView.addPrompt2("hello");
    Client client = new Client("127.0.0.1", 1234,  new BufferedReader(new InputStreamReader(System.in)), System.out);
    playGameController = new PlayGameController(playGameView, client);
  }

  @Test
  public void test_playGame2() {
    playGameView = new PlayGameView("test");
    playGameView.init("3");
    playGameView.addPrompt2("hello");
    Client client = new Client("127.0.0.1", 1234,  new BufferedReader(new InputStreamReader(System.in)), System.out);
    playGameController = new PlayGameController(playGameView, client);
  }

  @Test
  public void test_playGame3() {
    playGameView = new PlayGameView("test");
    playGameView.init("4");
    playGameView.addPrompt2("hello");
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

  @Test
  public void test_recvInfo(){
    playGameView = new PlayGameView("test");
    playGameView.init("2");
    HashMap<String, String> greyTerrs = new HashMap<String, String>();
    greyTerrs.put("Dorado", "1Lv1");
    HashMap<String, String> greyOwner = new HashMap<String, String>();
    greyOwner.put("Dorado", "King");
    playGameView.recvGreyInfo(greyTerrs, greyOwner);
    
    HashMap<String, ArrayList<String>> territories = new HashMap<String, ArrayList<String>>();
    ArrayList<String> terrs = new ArrayList<String>();
    terrs.add("Dorado");
    terrs.add("Hanamura");
    territories.put("King", terrs);
    territories.put("Red", terrs);
    territories.put("Blue", terrs);
    territories.put("Green", terrs);
    territories.put("Pink", terrs);
    HashMap<String, String> units = new HashMap<String, String>();
    units.put("Dorado", "3 Lv1");
    playGameView.recvTerrInfo(territories, units);
  }

  @Test
  public void test_parseTerriInfo() {
    playGameView = new PlayGameView("test");
    playGameView.init("2");
    Client client = new Client("127.0.0.1", 1234,  new BufferedReader(new InputStreamReader(System.in)), System.out);
    playGameController = new PlayGameController(playGameView, client);
    String info = "King\nDorado:10 Lv1\n\nenemy can see:\nRed:Hanamura:6 Lv7\nRed:Hollywood:5:Lv7\n\n";
    info += "previous can see territory info:\nRed:Volskaya:27 Lv7\nKing:Junkertown:35 Lv7\nRed:Ilios:10 Lv1\n";
   playGameController.parseTerritory(info);
  }

}












