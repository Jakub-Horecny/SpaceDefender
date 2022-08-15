package space.defender.player;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import space.defender.environment.Physics;
import space.defender.environment.Logic;
import space.defender.environment.ImageLoader;
import space.defender.game.SpaceDefender;
import space.defender.enemy.IEnemyObject;

/**
 *  class that represents player
 * @author Jakub Hôrečný
 */
public class Player implements IPlayerObject {
    
    private int playerHealth;
    private static final int PLAYER_SPEED = 15;
    private static final int HEALTH_LOSE = 40;

    private BufferedImage texture;
    private ImageLoader imageLoader;
    private Logic logic;

    private int startPositionX;
    private int startPositionY;
    
    private int relativePositionX;
    private int relativePositionY;

    /**
     * @param logic - game logic
     */
    public Player(Logic logic) {
        this.playerHealth = 200;
        this.startPositionX = 350;
        this.startPositionY = 750;
        this.logic = logic;
        this.relativePositionY = 0;
        this.relativePositionX = 0;
        this.loadTexture("image/player.png");
    }
    
    /**
     * update players coordinates and checks hit boxes
     */
    @Override
    public void update() {
        this.startPositionX += this.relativePositionX;
        this.startPositionY += this.relativePositionY;
        
        if (this.startPositionX <= 0) {
            this.startPositionX = 0;
        }
        
        if (this.startPositionX >= SpaceDefender.getCANVAS_WIDTH() - 112) {
            this.startPositionX = SpaceDefender.getCANVAS_WIDTH() - 112;
        }
        
        if (this.startPositionY <= 0) {
            this.startPositionY = 0;
        }
        if (this.startPositionY >= SpaceDefender.getCANVAS_HEIGHT() - 150) {
            this.startPositionY = SpaceDefender.getCANVAS_HEIGHT() - 150;
        }
        this.hitboxCheck();
    }
    
    /**
     * loads texture for player
     * @param path - file path
     */
    public final void loadTexture(String path) {
        this.imageLoader = new ImageLoader();
        this.texture = this.imageLoader.loadImage(path);
    }
    
    /**
     * checks for collisions between player and enemy object
     */
    public void hitboxCheck() {
        IEnemyObject enemy;

        for (int i = 0; i < this.logic.getEnemyObjectsArray().size(); i++) {
            enemy = this.logic.getEnemyObjectsArray().get(i);
            
            if (Physics.collision(this, enemy)) {
                this.logic.removeEnemy(enemy);
                this.playerHealth -= Player.HEALTH_LOSE;
                this.logic.setCurrentNumberOfEnemies(this.logic.getNumberOfEnemies() - 1);
            }
        }
    }

    /**
     * renders player texture
     * @param g - texture
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(this.texture, this.startPositionX, this.startPositionY, null);
        
    }
    
    /**
     * getter for hitbox
     * @return - hitbox
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.startPositionX, this.startPositionY, 100, 100);
    }

    // getters
    public int getStartPositionX() {
        return this.startPositionX;
    }
    public int getStartPositionY() {
        return this.startPositionY;
    }
    public int getPlayerHealth() {
        return this.playerHealth;
    }
    public static int getPLAYER_SPEED() {
        return Player.PLAYER_SPEED;
    }

    // setters
    public void setStartPositionX(int startPositionX) {
        this.startPositionX = startPositionX;
    }
    public void setStartPositionY(int startPositionY) {
        this.startPositionY = startPositionY;
    }
    public void setRelativePositionY(int relativePositionY) {
        this.relativePositionY = relativePositionY;
    }
    public void setRelativePositionX(int relativePositionX) {
        this.relativePositionX = relativePositionX;
    }
    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
    }

}