package client;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class WatchingAIGameView {
  AnchorPane watchAIGamePane;
  Label prompt;

  public WatchingAIGameView() {
    this.watchAIGamePane = new AnchorPane();
    this.prompt = new Label();
  }

  public void init() {
    prompt.setLayoutX(20);
    prompt.setLayoutY(75);
    prompt.setText("Waiting for the end of the AI game.");
    watchAIGamePane.getChildren().add(prompt);
  }
}
