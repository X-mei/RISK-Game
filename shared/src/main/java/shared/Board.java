package shared;

import java.util.HashSet;
import java.util.HashMap;

public class Board {
  final int playerNum;
  final HashMap<String, HashSet<Territory>> gameBoard;
  final MapFactory MapF;
  
  public Board(int num){
    this.playerNum = num;
    this.MapF = new MapFactory();
    this.gameBoard = MapF.getMap(num);
  }

  public HashMap<String, HashSet<Territory>> getBoard(){
    return gameBoard;
  }

  public int getPlayerNum(){
    return playerNum;
  }

  
}









