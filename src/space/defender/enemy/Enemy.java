package space.defender.enemy;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import space.defender.environment.ImageLoader;
import space.defender.environment.Logic;
import space.defender.environment.Physics;
import space.defender.game.SpaceDefender;
import space.defender.player.IPlayerObject;

/**
 * class that represents enemy
 * @author Jakub Hôrečný
 */
public class Enemy implements IEnemyObject {
    
    private int positionX;
    private int positionY;
    private int velocity;
    private int health;
    private final int maxHealth;
    
    private int hitBoxX;
    private int hitBoxY;
    
    private BufferedImage texture;
    private ImageLoader imageLoader;
    private SpaceDefender game;
    private Logic logic;

    /**
     * @param positionX - x start position
     * @param positionY - y start position
     * @param logic - logic
     * @param game - game
     * @param velocity - enemy velocity
     * @param health - max hp
     */
    public Enemy(int positionX, int positionY, Logic logic, SpaceDefender game, int velocity, int health) {
        this.maxHealth = health;
        this.health = this.maxHealth;
        this.velocity = velocity;
        this.logic = logic;
        this.game = game;
        this.positionX = positionX;
        this.positionY = positionY;
        this.hitBoxX = 0;
        this.hitBoxY = 0;
    }
     /**
     * update enemy position
     */
    @Override
    public void update() {
        this.positionY += this.velocity;
        
        if (this.positionY > SpaceDefender.getCANVAS_HEIGHT() + 100) {
            this.logic.removeEnemy(this);
            this.logic.setCurrentNumberOfEnemies(this.logic.getNumberOfEnemies() - 1);
            this.health = this.maxHealth;
        }
        
        this.hitboxCheck();
        this.shot();
    }
    /**
     * loads texture
     * @param path - file path
     */
    public void loadTexture(String path) {
        this.imageLoader = new ImageLoader();
        this.texture = this.imageLoader.loadImage(path);
    }
    
    /**
     * Check hitboxes
     */
    public void hitboxCheck() {
        IPlayerObject playerObject;
        for (int i = 0; i < this.logic.getPlayerObjectsArray().size(); i++) {
            playerObject = this.logic.getPlayerObjectsArray().get(i);

            if (Physics.collision(this, playerObject) && this.health == 1) {
                this.logic.removeEnemy(this);
                this.logic.removePlayerObject(playerObject);
                this.logic.setCurrentNumberOfEnemies(this.logic.getNumberOfEnemies() - 1);
                this.game.setScore(this.game.getScore() + 10);
                
            } else if (Physics.collision(this, playerObject) && this.health > 0) {
                this.logic.removePlayerObject(playerObject);
                this.health--;
            }
        }
    }

    /**
     * renders texture
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(this.texture, this.positionX, this.positionY, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.positionX, this.positionY, this.hitBoxX, this.hitBoxY);
    }
    
    public void shot() {

    }

    // getters
    public int getPositionX() {
        return this.positionX;
    }
    public int getPositionY() {
        return this.positionY;
    }
    public Logic getLogic() {
        return this.logic;
    }

    // setters
    public void setHitBoxX(int hitBoxX) {
        this.hitBoxX = hitBoxX;
    }
    public void setHitBoxY(int hitBoxY) {
        this.hitBoxY = hitBoxY;
    }
}
