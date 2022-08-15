package space.defender.environment;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import space.defender.game.SpaceDefender;

/**
 * mouse control
 * @author Jakub Hôrečný
 */
public class MouseControl implements MouseListener {
    
    private SpaceDefender game;
    
    /**
     * @param game - game
     */
    public MouseControl(SpaceDefender game) {
        this.game = game;
    }

    /**
     * not used
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
      
    }
    /**
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        
        int positionX = e.getX();
        int positionY = e.getY();
          
        if (this.game.getGameState() == GameState.MAIN_MENU) {
            if (positionX >= SpaceDefender.getCANVAS_WIDTH() / 2 - 125 && positionX <= SpaceDefender.getCANVAS_WIDTH() / 2 + 125) {
                if (positionY >= 300 && positionY <= 400) {
                    this.game.setGameState(GameState.GAME_RUNNING);
                }
           
                if (positionX >= SpaceDefender.getCANVAS_WIDTH() / 2 - 125 && positionX <= SpaceDefender.getCANVAS_WIDTH() / 2 + 125) {
                    if (positionY >= 450 && positionY <= 550) {
                        this.game.setGameState(GameState.HELP);
                    }
                }
                if (positionX >= SpaceDefender.getCANVAS_WIDTH() / 2 - 125 && positionX <= SpaceDefender.getCANVAS_WIDTH() / 2 + 125) {
                    if (positionY >= 600 && positionY <= 700) {
                        System.exit(1);
                    }
                }
            }
        }
        if (this.game.getGameState() == GameState.HELP) {
            if (positionX >= SpaceDefender.getCANVAS_WIDTH() / 2 - 125 && positionX <= SpaceDefender.getCANVAS_WIDTH() / 2 + 125) {
                if (positionY >= 850 && positionY <= 950) {
                    this.game.setGameState(GameState.MAIN_MENU);
                }
            }
        }
        if (this.game.getGameState() == GameState.PAUSE) {
            if (positionX >= SpaceDefender.getCANVAS_WIDTH() / 2 - 125 && positionX <= SpaceDefender.getCANVAS_WIDTH() / 2 + 125) {
                if (positionY >= 300 && positionY <= 400) {
                    this.game.restart();
                    this.game.setGameState(GameState.MAIN_MENU);
                }
            }
        }
        if (this.game.getGameState() == GameState.LOSE) {
            if (positionX >= SpaceDefender.getCANVAS_WIDTH() / 2 - 125 && positionX <= SpaceDefender.getCANVAS_WIDTH() / 2 + 125) {
                if (positionY >= 300 && positionY <= 400) {
                    this.game.restart();
                    this.game.setGameState(GameState.GAME_RUNNING);
                }
            }
            if (positionX >= SpaceDefender.getCANVAS_WIDTH() / 2 - 125 && positionX <= SpaceDefender.getCANVAS_WIDTH() / 2 + 125) {
                if (positionY >= 450 && positionY <= 550) {
                    this.game.restart();
                    this.game.setGameState(GameState.MAIN_MENU);
                }
            }
        }

    }
    /**
     * not used
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    /**
     * not used
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    /**
     * not used
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
    
}
