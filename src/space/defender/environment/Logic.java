package space.defender.environment;

import space.defender.enemy.IEnemyObject;
import space.defender.player.IPlayerObject;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import space.defender.game.SpaceDefender;
import space.defender.enemy.EnemyShip;
import space.defender.enemy.Rocket;

/**
 *
 * class that represents enemy
 * @author Jakub Hôrečný
 */
public class Logic {
    private final ArrayList<IPlayerObject> playerObjects;
    private final ArrayList<IEnemyObject> enemyObjects;
    private Random random;
     
    private SpaceDefender game;
    
    private int currentNumberOfEnemies; //changing
    private int NumberOfEnemies;
    private static final int ROCKET_SPEED = 7;
    private static final int SHIP_SPEED = 4;
    
    /**
     * @param game - game
     */
    public Logic(SpaceDefender game) {
        this.NumberOfEnemies = 5;
        this.currentNumberOfEnemies = this.NumberOfEnemies;
        this.random = new Random();

        this.playerObjects = new ArrayList<>();
        this.enemyObjects = new ArrayList<>();
        this.game = game;
        
    }
    
    /**
     * updates players and enemy objects
     */
    public void update() {
        // player
        for (int i = 0; i < this.playerObjects.size(); i++) {
            //this.playerObjects = this.playerObjects.get(i);
            this.playerObjects.get(i).update();
        }
        // enemy
        for (int i = 0; i < this.enemyObjects.size(); i++) {
            this.enemyObjects.get(i).update();
        }
    }
    
    /**
     * creates new enemy
     * @param n - number of enemy
     */
    public void addEnemy(int n) {
        this.currentNumberOfEnemies = this.NumberOfEnemies;
        int tempNumber;
        for (int i = 0; i < n; i++) {
            tempNumber = this.random.nextInt(5);
            if (tempNumber == 0) {
                this.createEnemy(new EnemyShip(this.random.nextInt(650) + 50,
                        this.random.nextInt(1000) * (-1) - 166,
                        this, this.game, Logic.SHIP_SPEED, 3));
            } else {
                this.createEnemy(new Rocket(this.random.nextInt(700) + 50,
                        this.random.nextInt(1000) * (-1) - 100,
                        this, this.game, Logic.ROCKET_SPEED, 1));
            }
        }
    }
    
    /**
     * renders graphics for enemy and player object
     * @param g
     */
    public void render(Graphics g) {
        // must be for i
        //player
        for (int i = 0; i < this.playerObjects.size(); i++) {
            this.playerObjects.get(i).render(g);
        }
        // enemy
        for (int i = 0; i < this.enemyObjects.size(); i++) {
            this.enemyObjects.get(i).render(g);
        }
    }
    
    /**
     * @param a
     * adds players shot
     */
    public void addPlayerObject(IPlayerObject a) {
        this.playerObjects.add(a);
    }
    
    /**
     * @param a
     * remove players shot
     */
    public void removePlayerObject(IPlayerObject a) {
        this.playerObjects.remove(a);
    }

    // enemy stuff

    /**
     * @param b
     * create new enemy
     */
    public void createEnemy(IEnemyObject b) {
        this.enemyObjects.add(b);
    }
    
    /**
     * @param b
     * remove enemy object
     */
    public void removeEnemy(IEnemyObject b) {
        this.enemyObjects.remove(b);
    }

    //getters
    public ArrayList<IEnemyObject> getEnemyObjectsArray() {
        return this.enemyObjects;
    }
    public ArrayList<IPlayerObject> getPlayerObjectsArray() {
        return this.playerObjects;
    }
    public int getCurrentNumberOfEnemies() {
        return this.currentNumberOfEnemies;
    }
    public int getNumberOfEnemies() {
        return this.NumberOfEnemies;
    }

    public void setCurrentNumberOfEnemies(int currentNumberOfEnemies) {
        this.currentNumberOfEnemies = currentNumberOfEnemies;
    }
    public void setNumberOfEnemies(int numberOfEnemies) { this.NumberOfEnemies = numberOfEnemies; }
}
