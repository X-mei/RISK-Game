package shared;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.function.BiFunction;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

/**
 * This class correspond to one player in the game, player have access to its status, name and all the territory that belongs to her.
 * Player can .
 */

public class Player {
  protected final String name;
  protected final Integer code;
  protected int techResource;
  protected int foodResource;
  protected int tempTechResource;
  protected int tempFoodResource;
  protected int techLevel;
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
    this.techResource = 1000;
    this.foodResource = 1000;
    this.tempFoodResource = foodResource;
    this.tempTechResource = techResource;
    this.techLevel = 1;
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

  public UpgradeAction formUpgradeAction(String input) throws IOException, IllegalArgumentException{
    if (input == null) {
      throw new IOException();
    }
    return factory.createUpgrade(name, input);
  }

  public TechAction formTechAction() {
    return factory.createTechUpgrade(name);
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
  
  public int getTechResource(){
    return techResource;
  }

  public void updateTechResource(int credits){
    techResource += credits;
  }

  public void updateTempTechResource(int credits){
    tempTechResource += credits;
  }

  public void refreshTempTechResource() {
    tempTechResource = techResource;
  }

  public int getFoodResource(){
    return foodResource;
  }

  public void updateFoodResource(int credits){
    foodResource += credits;
  }

  public int getTempFoodResource() {
    return tempFoodResource;
  }
  
  public void refreshTempFoodResource() {
    tempFoodResource = foodResource;
  }
  
  public void updateTempFoodResource(int credits) {
    tempFoodResource += credits;
  }
  
  public int getTechLevel(){
    return techLevel;
  }

  public void updateTechLevel(){
    techLevel += 1;
  }

}                     









