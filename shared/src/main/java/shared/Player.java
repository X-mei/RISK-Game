package shared;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;

/**
 * This class correspond to one player in the game, player have access to its status, name and all the territory that belongs to her.
 * Player can .
 */

public class Player {
  protected final String name;
  protected final Integer code;
  protected HashSet<Territory> ownedTerritory;
  protected final BufferedReader inputReader;
  protected final PrintStream out;
  protected String status;

  /**
   * This constructor initialize all the field in the player class.
   * @param name of the player,  the place to read input from and the place to dump output to.
   */
  public Player(String name, Integer code, BufferedReader inputSource, PrintStream out) {
    this.name = name;
    this.inputReader = inputSource;
    this.out = out;
    this.code = code;
    this.status = "In-game";
  }

  /**
   * This function prompt the user for a Action input.
   * @param things to prompt the user for.
   * @return the Action after the conversion.
   * @throws IOException if the input readline fails, IllegalArgumentException if the input is invalid.
   */
  public Action formAction(String prompt) throws IOException, IllegalArgumentException{
    out.println(prompt);
    String s = inputReader.readLine();
    if (s == null) {
      throw new IOException();
    }
    return new (name, s);
  }
  
  public void updateTerritoryList(){
    
  }
}











