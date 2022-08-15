package space.defender.environment;

import space.defender.enemy.IEnemyObject;
import space.defender.player.IPlayerObject;

/**
 * class that deals with collisions
 * @author Jakub Hôrečný
 */
public class Physics {

    /**
     *
     * @param object - enemy object
     * @param n - player object
     * @return - true if collision
     */
    public static boolean collision(IPlayerObject object, IEnemyObject n) {
        return object.getBounds().intersects(n.getBounds());
    }
    
    /**
     *
     * @param object - enemy object
     * @param h - player object
     * @return - true if collision
     */
    public static boolean collision(IEnemyObject object, IPlayerObject h) {
        return object.getBounds().intersects(h.getBounds());
    }
}
