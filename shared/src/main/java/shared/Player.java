package shared;

import java.io.IOException;
import java.util.ArrayList;
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
  protected HashMap<String, Integer> spyLocation;
  protected boolean canCloak;
  protected HashMap<String, String> previousRoundCanSee;
  //protected HashMap<String, String> lastRoundCanSeeEnemy;



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
    spyLocation = new HashMap<>();
    canCloak = false;
    previousRoundCanSee = new HashMap<>();
    //lastRoundCanSeeEnemy = new HashMap<>();
  }
    
  /**
   * This function generate a action type.
   * @param action and the input to parse.
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

  /**
   * create an upgrade action
   * @param input the upgrade input string
   * @return the upgrade action object
   * @throws IOException
   * @throws IllegalArgumentException
   */
  public UpgradeAction formUpgradeAction(String input) throws IOException, IllegalArgumentException{
    if (input == null) {
      throw new IOException();
    }
    return factory.createUpgrade(name, input);
  }

  /**
   * create an upgrade tech level action
   * @return the tech upgrade object
   */
  public TechAction formTechAction() {
    return factory.createTechUpgrade(name);
  }

  /**
   * distribute one player's territories
   * @param territory  set for one player
   */
  public void addTerritory(LinkedHashSet<Territory> territory) {
    ownedTerritory = territory;
  }

  /**
   * get one player's owned all territories
   * @return one player's all territories
   */
  public LinkedHashSet<Territory> getTerritoryList() {
    return ownedTerritory;
  }

  /**
   * set up move or attack action creater
   */
  public void setUpActionCreationMap() {
    actionCreationFns.put("M", (n, s)->factory.createMove(n, s));
    actionCreationFns.put("A", (n, s)->factory.createAttack(n, s));
  }

  /**
   * get player's name
   * @return the player's name
   */
  public String getName() {
    return name;
  }

  /**
   * get player's code
   * @return
   */
  public Integer getCode() {
    return code;
  }

  /**
   * get this player's status
   * @return
   */
  public String getStatus() {
    return status;
  }

  /**
   * get player's current tech resource
   * @return the amount of its tech resource
   */
  public int getTechResource(){
    return techResource;
  }

  /**
   * increase player's tech resource with specific number
   * @param credits the increasing amount of the tech resource
   */
  public void updateTechResource(int credits){
    techResource += credits;
  }

  public void updateTempTechResource(int credits){
    tempTechResource += credits;
  }

  public void refreshTempTechResource() {
    tempTechResource = techResource;
  }

  /**
   * get player's current food resource
   * @return the amount of its food resource
   */
  public int getFoodResource(){
    return foodResource;
  }

  /**
   * increase player's food resource with specific number
   * @param credits the increasing amount of the food resource
   */
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

  /**
   * get player's current tech level
   * @return
   */
  public int getTechLevel(){
    return techLevel;
  }

  /**
   * update player's tech level with 1
   */
  public void updateTechLevel(){
    techLevel += 1;
  }

  /**
   * directly set player's tech level to a specific number
   * @param level is the level you want to set
   */
  public void setTechLevel(int level){
    techLevel = level;
  }

  /**
   * get the player's spy location
   * @return the territory name where his spy at
   */
  public HashMap<String, Integer> getSpyLocation(){   
    return spyLocation;
  }


  /**
   * for a new created spy, add it to the spyLocation map with its born territory
   * @param name
   */
  public void addnewSpyLocation(String name, int updateNum){
    spyLocation.put(name, spyLocation.getOrDefault(name, 0) + updateNum);
  }

  /**
   * update the spy location
   * @param location is the new spy territory location name
   */
  public void updateSpyLocation(String src, String dest){
    //add dest territory name 
    spyLocation.put(dest, spyLocation.getOrDefault(dest, 0) + 1);
    //delete source name
    int temp = spyLocation.get(src) - 1;
    spyLocation.put(src, temp);
  }

  /**
   * check id the player can cloak
   * @return true if he can 
   */
  public boolean checkPlayerCanCloak(){
    return canCloak;
  }

  /**
   * set player's cloak ability 
   * @param out 
   */
  public void changeCloakState(boolean out){
    canCloak = out;
  }

  /**
   * get what territory this player can see and owned for last round
   * @return
   */
  public HashMap<String, String> getPreviousRoundCanSee(){
    return previousRoundCanSee;
  }

  // /**
  //  * get what territory this player can see and is enemy's for last round
  //  * @return
  //  */
  // public HashMap<String, String> getLastRoundCanSeeEnemy(){
  //   return lastRoundCanSeeEnemy;
  // }

  // /**
  //  * erase all info in lastRoundCanSeeOwn
  //  */
  // public void cleanLastRoundCanSeeOwn(){
  //   lastRoundCanSeeOwn.clear();
  // }

  // /**
  //  * erase all info in lastRoundCanSeeEnemy
  //  */
  // public void cleanLastRoundCanSeeEnemy(){
  //   lastRoundCanSeeEnemy.clear();
  // }

  /**
   * add info to lastRoundCanSeeOwn
   * @param name
   * @param info
   */
  public void addPreviousRoundCanSee(String name, String info){
    previousRoundCanSee.put(name, info);
  }

  // /**
  //  * add info to lastRoundCanSeeEnemy
  //  * @param name
  //  * @param info
  //  */
  // public void addLastRoundCanSeeEnemy(String name, String info){
  //   lastRoundCanSeeEnemy.put(name, info);
  // }


  // /**
  //  * set the lastRoundCanSeeOwn map to a new map 
  //  * @param map
  //  */
  // public void setLastRoundCanSeeOwn(HashMap<String, String> map){
  //   lastRoundCanSeeOwn = map;
  // }

  // /**
  //  * set the lastRoundCanSeeEnemy map to a new map 
  //  * @param map
  //  */
  // public void setLastRoundCanSeeEnemy(HashMap<String, String> map){
  //   lastRoundCanSeeEnemy = map;
  // }
  
}                     









