package space.defender.enemy;

import java.util.Random;
import space.defender.environment.Logic;
import space.defender.game.SpaceDefender;

/**
 * class that represents stronger enemy that shot back
 * @author Jakub Hôrečný
 */
public class EnemyShip extends Enemy {
    
    private final Random random;
    
    /**
     * @param positionX - x start position
     * @param positionY - y start position
     * @param logic - logic
     * @param game - game
     * @param velocity - enemy velocity
     * @param health - max hp
     */
    public EnemyShip(int positionX, int positionY, Logic logic, SpaceDefender game, int velocity, int health) {
        super(positionX, positionY, logic, game, velocity, health);
        this.random = new Random();
        this.loadTexture("image/enemy.png");
        this.setHitBoxX(90);
        this.setHitBoxY(156);
    }
     /**
     * shot
     */
    @Override
    public void shot() {
        if (this.getPositionY() >= 0) {
            int number = this.random.nextInt(240);
            if (number == 0) {
                this.getLogic().createEnemy(new EnemyShot(getPositionX() + 38, getPositionY() + 120));
            }
        }

    }
    
}
