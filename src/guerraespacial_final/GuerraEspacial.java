package guerraespacial_final;

import javaPlay.GameEngine;

public class GuerraEspacial {

  public static void main(String[] args) {
    
    GameEngine.getInstance().addGameStateController(1, new TesteNaves());
    
    GameEngine.getInstance().setStartingGameStateController(1);    
    
    GameEngine.getInstance().setFramesPerSecond(30);
    GameEngine.getInstance().run();
    
  }
}
