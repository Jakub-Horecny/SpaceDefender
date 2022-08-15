package space.defender.enemy;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import space.defender.environment.ImageLoader;

/**
 * class that represents enemy shot
 * @author Jakub Hôrečný
 */
public class EnemyShot implements IEnemyObject {

    private static final int VELOCITY = 10;
    
    private BufferedImage texture;
    private int positionX;
    private int positionY;
    private ImageLoader imageLoader;
    
    /**
     * @param positionX - x position
     * @param positionY - y position
     */
    public EnemyShot(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.imageLoader = new ImageLoader();
        this.texture = this.imageLoader.loadImage("image/enemy_shot.png");
    }

    /**
     * update position
     */
    @Override
    public void update() {
        this.positionY += EnemyShot.VELOCITY;
    }

    /**
     * renders texture
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(this.texture, this.positionX, this.positionY, null);
    }

    /**
     * returns hitbox
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.positionX, this.positionY, 30, 30);
    }
    
    // getters
    public int getPositionX() {
        return this.positionX;
    }
    public int getPositionY() {
        return this.positionY;
    }  
}
