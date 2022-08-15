package space.defender.game;

import space.defender.environment.*;
import space.defender.environment.ImageLoader;
import space.defender.player.Player;
import space.defender.player.PlayerShot;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import static space.defender.environment.GameState.*;

/**
 * main class that runs the game
 * @author Jakub Hôrečný
 */
public class SpaceDefender extends Canvas implements Runnable {
    
    private boolean run;
    private boolean isShooting;
    private Thread thread;

    private static final String fontFamily = "Calibri";
    private static final int CANVAS_HEIGHT = 1000;
    private static final int CANVAS_WIDTH = 800;
    private static final double MAX_FPS = 60.0;
    
    private final BufferedImage background;
    private final BufferedImage gameOver;
    private final BufferedImage logoMenu;
    private final BufferedImage help;

    private ImageLoader imageLoader;
    private Player player;
    private Logic logic;
    private GameState gameState;
    private Menu menu;
    
    private int timeToRespam;
    private int FPSNumber;
    private int score;
    
    /**
     * starts the game
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SpaceDefender game = new SpaceDefender();
        
        JFrame frame = new JFrame("SPACE DEFENDER");
        try {
            frame.setIconImage(ImageIO.read(new FileInputStream("image/icon.png")));
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        frame.pack();

        frame.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        frame.setResizable(false);
        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.add(game);

        game.start();
    }

    /**
     * constructor
     */
    public SpaceDefender() {
        this.isShooting = false;
        this.score = 0;
        this.FPSNumber = 0;
        this.menu = new Menu();
        this.gameState = MAIN_MENU;
        this.imageLoader = new ImageLoader();
        this.run = false;
        this.timeToRespam = 0;
        this.background = this.imageLoader.loadImage("image/background.jpg");
        this.gameOver = this.imageLoader.loadImage("image/game_over.png");
        this.logoMenu = this.imageLoader.loadImage("image/logo.png");
        this.help = this.imageLoader.loadImage("image/help_menu.jpg");
    }
    /**
     * creates game loop
     */
    @Override
    public void run() {
        this.initialization();
        long lastTime  = System.nanoTime();
        double ns = 1000000000 / SpaceDefender.MAX_FPS;
        double delta = 0;
        
        int fPS = 0;
        long timer = System.currentTimeMillis();
        long currentTime;

        while (this.run) {
            currentTime = System.nanoTime();
            delta += ( currentTime - lastTime ) / ns;
            lastTime  = currentTime;
           
            if (delta >= 1) {
                this.update();
                fPS++;
                delta--;
            }
            this.render();
            if (System.currentTimeMillis() - timer > 100) {
                timer += 1000;
                this.FPSNumber = fPS;
                fPS = 0;

            }  
        }
        this.stop();
    }
    
    /**
     * renders graphic and game environment
     */
    public void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        
        if (bufferStrategy == null) {
            createBufferStrategy(3);
            return;
        }
        
        Graphics g = bufferStrategy.getDrawGraphics();
        /////
        g.drawImage(this.background, 0, 0, this);// create background
        
        this.checkGameStatus();
        this.renderFPS(g, this.FPSNumber);

        switch (this.gameState) {
            case MAIN_MENU:
                g.drawImage(this.logoMenu, 0, 50, this);
                this.menu.renderMainMenu(g);
                break;
            case HELP:
                g.drawImage(this.help, 0, 0, this);
                this.menu.renderHelp(g);
                break;
            case GAME_RUNNING:
                this.player.render(g);
                this.logic.render(g);
                this.renderScore(g, this.score);

                g.setColor(new Color(115, 16, 18));
                g.fillRect(5, 5, 200, 50);
                g.setColor(new Color(0, 159, 0));
                g.fillRect(5, 5, this.player.getPlayerHealth(), 50);
                break;
            case PAUSE:
                this.player.render(g);
                this.logic.render(g);
                this.renderScore(g, this.score);

                g.setColor(new Color(115, 16, 18));
                g.fillRect(5, 5, 200, 50);
                g.setColor(new Color(0, 159, 0));
                g.fillRect(5, 5, this.player.getPlayerHealth(), 50);

                this.menu.renderPause(g);
                break;
            case LOSE:
                this.player.render(g);
                this.logic.render(g);
                this.renderScore(g, this.score);
                g.setColor(new Color(115, 16, 18));
                g.fillRect(5, 5, 200, 50);
                g.setColor(new Color(0, 159, 0));
                g.fillRect(5, 5, this.player.getPlayerHealth(), 50);

                this.menu.renderGameOver(g);
                g.drawImage(this.gameOver, 0, 100, this);
                break;
        }
        g.dispose();
        bufferStrategy.show();
    }
    /**
     * checks game status
     */
    private void checkGameStatus() {
        if (this.player.getPlayerHealth() <= 0) {
            this.gameState = LOSE;
        }
    }
    
    /**
     * restarts game
     */
    public void restart() {
        this.score = 0;
        this.player.setPlayerHealth(200);
        this.player.setStartPositionX(350);
        this.player.setStartPositionY(750);

        this.logic.getEnemyObjectsArray().clear();
        this.logic.getPlayerObjectsArray().clear();

        this.logic.addEnemy(5);
        this.logic.setNumberOfEnemies(5);
        this.logic.setCurrentNumberOfEnemies(5);
    }
    
    /**
     * initialization of the game
     */
    public void initialization() {
        requestFocus();
        this.addKeyListener(new KeyboardControl(this));
        this.addMouseListener(new MouseControl(this));
        this.logic = new Logic(this);
        this.player = new Player(this.logic);
        this.logic.addEnemy(this.logic.getNumberOfEnemies());
        
    }
    /**
     * update
     */
    private void update() {
        if (this.gameState == GAME_RUNNING) {
            this.timeToRespam++;
            this.player.update();
            this.logic.update();

            //every 5 second adds new enemy
            if (this.timeToRespam > 300) {
                this.timeToRespam = 0;
                this.logic.addEnemy(1);
                this.logic.setCurrentNumberOfEnemies(this.logic.getNumberOfEnemies() + 1);
            }

            // when enemy is destroyed, adds new one
            if (this.logic.getNumberOfEnemies() != this.logic.getCurrentNumberOfEnemies()) {
                this.logic.addEnemy(
                        this.logic.getNumberOfEnemies() - this.logic.getCurrentNumberOfEnemies());
                this.logic.setCurrentNumberOfEnemies(this.logic.getNumberOfEnemies() + 1);
            }
        }
    }
    /**
     * synchronized
     */
    private synchronized void start() {
        if (this.run) {
            return;
        }
        this.run = true;
        
        this.thread = new Thread(this);
        this.thread.start();
    }
    /**
     * synchronized
     */
    private synchronized void stop() {
        if (!this.run) {
            return;
        }
        this.run = false;
        try {
            this.thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ex.getMessage());
        }
        System.exit(1);
    }
    /**
     * renders FPS
     */
    private void renderFPS(Graphics g, int i) {
        String s = Integer.toString(i);
        g.setColor(Color.RED);
        Font fond = new Font(fontFamily, Font.BOLD, 20);
        g.setFont(fond);
        g.drawString("FPS  " + s, 730, 15);
    }
    /**
     * renders score
     */
    private void renderScore(Graphics g, int i) {
        String s = Integer.toString(i);
        g.setColor(Color.YELLOW);
        Font fond = new Font(fontFamily, Font.BOLD, 30);
        g.setFont(fond);
        g.drawString("SCORE  " + s, 220, 40);
    }
    /**
     * @param e
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            this.player.setRelativePositionX(0);
        }
        if (key == KeyEvent.VK_LEFT) {
            this.player.setRelativePositionX(0);
        }
        if (key == KeyEvent.VK_UP) {
            this.player.setRelativePositionY(0);
        }
        if (key == KeyEvent.VK_DOWN) {
            this.player.setRelativePositionY(0);
        }
        if (key == KeyEvent.VK_SPACE) {
            this.isShooting = false;
        }
    }
    /**
     * @param e
     */
    public void keyPressed(KeyEvent e) { 
        int key = e.getKeyCode();
        
        if (this.gameState == GAME_RUNNING || this.gameState == PAUSE) {
            if (key == KeyEvent.VK_RIGHT) {
                this.player.setRelativePositionX(Player.getPLAYER_SPEED());
            }
            if (key == KeyEvent.VK_LEFT) {
                this.player.setRelativePositionX(-Player.getPLAYER_SPEED());
            }
            if (key == KeyEvent.VK_UP) {
                this.player.setRelativePositionY(-Player.getPLAYER_SPEED());
            }
            if (key == KeyEvent.VK_DOWN) {
                this.player.setRelativePositionY(Player.getPLAYER_SPEED());
            }
            if (key == KeyEvent.VK_SPACE && !this.isShooting) {
                this.isShooting = true;
                this.logic.addPlayerObject(new PlayerShot(this.player.getStartPositionX() + 36,
                        this.player.getStartPositionY()));
            }
            if (key == KeyEvent.VK_ESCAPE) { // pause conditions
                if (this.gameState == GAME_RUNNING) {
                    this.gameState = PAUSE;
                } else if (this.gameState == PAUSE) {
                    this.gameState = GAME_RUNNING;
                }
            
            }
        }
    }
    
    // getters
    public static int getCANVAS_WIDTH() {
        return CANVAS_WIDTH;
    }
    public static int getCANVAS_HEIGHT() { return CANVAS_HEIGHT; }
    public GameState getGameState() {
        return this.gameState;
    }
    public int getScore() {
        return this.score;
    }

    // setters
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    public void setScore(int score) {
        this.score = score;
    }
    
}
