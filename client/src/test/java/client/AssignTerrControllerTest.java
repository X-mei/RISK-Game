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
public class AssignTerrControllerTest {

  private AssignTerrView assignTerrView;
  private AssignTerrController cont;

  @Test
  public void test_assignTerr() {
    assignTerrView = new AssignTerrView();
    assignTerrView.init("2");
    String[] prompts = new String[3];
    prompts[0] = "ddd";
    prompts[1] = "ddd";
    prompts[2] = "ddd";
    assignTerrView.addPromptOfTerritory(prompts);
    assignTerrView.addPrompt2("aa");
    assignTerrView.addPrompt3("bb");
    assignTerrView.waitPlayer();
    Client client = new Client("127.0.0.1", 1234,  new BufferedReader(new InputStreamReader(System.in)), System.out);
    cont = new AssignTerrController(assignTerrView, client, "2");
  }

  @Test
  public void test_assignTerr1() {
    assignTerrView = new AssignTerrView();
    assignTerrView.init("3");
    String[] prompts = new String[3];
    prompts[0] = "ddd";
    prompts[1] = "ddd";
    prompts[2] = "ddd";
    assignTerrView.addPromptOfTerritory(prompts);
    assignTerrView.addPrompt2("aa");
    assignTerrView.addPrompt3("bb");
    assignTerrView.waitPlayer();
    Client client = new Client("127.0.0.1", 1234,  new BufferedReader(new InputStreamReader(System.in)), System.out);
    cont = new AssignTerrController(assignTerrView, client, "3");
  }

  @Test
  public void test_assignTerr2() {
    assignTerrView = new AssignTerrView();
    assignTerrView.init("4");
    String[] prompts = new String[2];
    prompts[0] = "ddd";
    prompts[1] = "ddd";
    assignTerrView.addPromptOfTerritory(prompts);
    assignTerrView.addPrompt2("aa");
    assignTerrView.addPrompt3("bb");
    assignTerrView.waitPlayer();
    Client client = new Client("127.0.0.1", 1234,  new BufferedReader(new InputStreamReader(System.in)), System.out);
    cont = new AssignTerrController(assignTerrView, client, "4");
  }

  @Test
  public void test_assignTerr3() {
    assignTerrView = new AssignTerrView();
    assignTerrView.init("5");
    String[] prompts = new String[2];
    prompts[0] = "ddd";
    prompts[1] = "ddd";
    assignTerrView.addPromptOfTerritory(prompts);
    assignTerrView.addPrompt2("aa");
    assignTerrView.addPrompt3("bb");
    assignTerrView.waitPlayer();
    Client client = new Client("127.0.0.1", 1234,  new BufferedReader(new InputStreamReader(System.in)), System.out);
    cont = new AssignTerrController(assignTerrView, client, "5");
  }

}
