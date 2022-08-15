package space.defender.environment;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import space.defender.game.SpaceDefender;

/**
 * Keyboard control
 * @author Jakub Hôrečný
 */
public class KeyboardControl extends KeyAdapter {
    
    private SpaceDefender game;

    /**
     * @param game
     */
    public KeyboardControl(SpaceDefender game) {
        this.game = game;
    }

    /**
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        this.game.keyPressed(e);
    }

    /**
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        this.game.keyReleased(e);
    }
}
