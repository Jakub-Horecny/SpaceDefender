package space.defender.enemy;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Interface for enemy object - enemy and enemy shoot
 * @author Jakub Hôrečný
 */
public interface IEnemyObject {
    
    /**
     * Updates the position
     */
    void update();

    /**
     * Renders graphics
     * @param g - graphics
     */
    void render(Graphics g);

    /**
     * returns a hitbox represented by a square
     * @return - hitbox
     */
    Rectangle getBounds();

}
