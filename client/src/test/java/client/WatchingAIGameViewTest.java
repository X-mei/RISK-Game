package client;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

@ExtendWith(ApplicationExtension.class)
public class WatchingAIGameViewTest {

  private WatchingAIGameView watchingAIGameView;
  
  @Test
  public void test_watchingGameView() {
    watchingAIGameView = new WatchingAIGameView();
    watchingAIGameView.init();
  }

}








