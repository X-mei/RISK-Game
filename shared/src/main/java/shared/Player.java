package shared;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.function.BiFunction;

/**
 * This class correspond to one player in the game, player have access to its status, name and all the territory that belongs to her.
 * Player can .
 */

public class Player {
  protected final String name;
  protected final Integer code;
  protected LinkedHashSet<Territory> ownedTerritory;
  protected HashSet<String> actionSet;
  protected String status;
  protected ActionFactory factory;
  protected HashMap<String, BiFunction<String, String, BasicAction>> actionCreationFns;

  /**
   * This constructor initialize all the field in the player class.
   * @param name of the player,  the place to read input from and the place to dump output to.
   */
  public Player(String name, Integer code, ActionFactory factory) {
    this.name = name;
    this.code = code;
    this.status = "In-game";
    //TODO: add territory
    this.ownedTerritory = new LinkedHashSet<Territory>();
    this.actionCreationFns = new HashMap<String, BiFunction<String, String, BasicAction>>();
    this.factory = factory;
    setUpActionCreationMap();
  }
    
  /**
   * This function generate a action type.
   * @param the action and the input to parse.
   * @return the Action after the conversion.
   * @throws IOException if the input readline fails, IllegalArgumentException if the input is invalid.
   */
  public BasicAction formAction(String action, String input) throws IOException, IllegalArgumentException{
    if (action.equals("D")){
      return null;
    }
    else {
      if (input == null) {
        throw new IOException();
      }
      if (actionCreationFns.get(action) != null) {
        return actionCreationFns.get(action).apply(name, input);
      }
      else {
        throw new IllegalArgumentException("Not a valid action.");
      }
    }
  }
  
  public void addTerritory(LinkedHashSet<Territory> territory) {
    ownedTerritory = territory;
  }

  public LinkedHashSet<Territory> getTerritoryList() {
    return ownedTerritory;
  }
  
  public void setUpActionCreationMap() {
    actionCreationFns.put("M", (n, s)->factory.createMove(n, s));
    actionCreationFns.put("A", (n, s)->factory.createAttack(n, s));
  }
  
  public String getName() {
    return name;
  }

  public Integer getCode() {
    return code;
  }

  public String getStatus() {
    return status;
  }
}                     









