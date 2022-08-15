package space.defender.enemy;

import space.defender.environment.Logic;
import space.defender.game.SpaceDefender;

/**
 * class that represents simple enemy
 * @author Jakub Horečný
 */
public class Rocket extends Enemy {
    
    /**
     * @param positionX - x start position
     * @param positionY - y start position
     * @param logic - logic
     * @param game - game
     * @param velocity - enemy velocity
     * @param health - max hp
     */
    public Rocket(int positionX, int positionY, Logic logic, SpaceDefender game, int velocity, int health) {
        super(positionX, positionY, logic, game, velocity, health);
        this.loadTexture("image/rocket.png");
        this.setHitBoxX(55);
        this.setHitBoxY(90);
    }
}
