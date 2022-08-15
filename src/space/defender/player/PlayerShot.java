package space.defender.player;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import space.defender.environment.ImageLoader;

/**
 * class that represents player shot
 * @author Jakub Hôrečný
 */
public class PlayerShot implements IPlayerObject {

    private final static int VELOCITY = 20;
    
    private final BufferedImage texture;
    private int positionX;
    private int positionY;
    private ImageLoader imageLoader;

    /**
     * @param positionX - x position
     * @param positionY - y position
     */
    public PlayerShot(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.imageLoader = new ImageLoader();
        this.texture = this.imageLoader.loadImage("image/player_shot.png");
    }
    
    /**
     * update position
     */
    @Override
    public void update() {
        this.positionY -= PlayerShot.VELOCITY;
    }
    
    /**
     * renders graphics
     * @param g
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(this.texture, this.positionX, this.positionY, null);
    }

    /**
     * getter for hitbox
     * @return - hitbox
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
