package space.defender.player;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Interface for player object - player and player shoot
 * @author Jakub Hôrečný
 */
public interface IPlayerObject {

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
