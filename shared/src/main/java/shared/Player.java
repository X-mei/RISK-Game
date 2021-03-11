package shared;

import java.io.IOException;
import java.util.HashSet;

/**
 * This class correspond to one player in the game, player have access to its status, name and all the territory that belongs to her.
 * Player can .
 */

public class Player {
  protected final String name;
  protected final Integer code;
  protected HashSet<Territory> ownedTerritory;
  protected HashSet<String> actionSet;
  protected String status;

  /**
   * This constructor initialize all the field in the player class.
   * @param name of the player,  the place to read input from and the place to dump output to.
   */
  public Player(String name, Integer code) {
    this.name = name;
    this.code = code;
    this.status = "In-game";
    this.ownedTerritory = new HashSet<Territory>();
  }
    
  /**
   * This function generate a action type.
   * @param the action and the input to parse.
   * @return the Action after the conversion.
   * @throws IOException if the input readline fails, IllegalArgumentException if the input is invalid.
   */
  public Action formAction(String action, String input) throws IOException, IllegalArgumentException{
    if (action == "D"){
      return null;
    }
    else {
      if (input == null) {
        throw new IOException();
      }
      if (action == "M") {
        return new Move(name, input);
      }
      else {
        return new Attack(name, input);
      }
    }
  }

  public String getName() {
    return name;
  }

  public Integer getInteger() {
    return code;
  }
}                     








