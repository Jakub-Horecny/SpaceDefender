package space.defender.environment;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import space.defender.game.SpaceDefender;

/**
 * GUI for game
 * @author Jakub Hôrečný
 */
public class Menu {

    private static final String fontFamily = "Calibri";
    private static final String mainMenu = "MAIN MENU";

    /**
     * renders pause menu
     * @param g
     */
    public void renderPause(Graphics g) {
        Font pause = new Font(fontFamily, Font.BOLD, 150);
        Font text = new Font(fontFamily, Font.BOLD, 40);
        Font menu = new Font(fontFamily, Font.BOLD, 35);

        g.setFont(pause);
        g.setColor(Color.WHITE);
        g.fillRect(SpaceDefender.getCANVAS_WIDTH() / 2 - 125, 300, 250, 100);
        
        g.setColor(new Color(115, 16, 18));
        g.drawString("PAUSE", SpaceDefender.getCANVAS_WIDTH() / 3 - 80, 200);
        
        g.setColor(Color.WHITE);
        g.setFont(text);
        g.drawString("TO CONTINUE, PRESS ESC", SpaceDefender.getCANVAS_WIDTH() / 3 - 80, 250);
        
        g.setFont(menu);
        g.setColor(Color.BLACK);
        g.drawString(mainMenu, SpaceDefender.getCANVAS_WIDTH() / 2 - 110, 360);
    }
    
    /**
     * renders game over menu
     * @param g
     */
    public void renderGameOver(Graphics g) {
        Font text = new Font(fontFamily, Font.BOLD, 35);

        g.setColor(Color.WHITE);
        g.fillRect(SpaceDefender.getCANVAS_WIDTH() / 2 - 125, 300, 250, 100);
        g.fillRect(SpaceDefender.getCANVAS_WIDTH() / 2 - 125, 450, 250, 100);
        
        g.setColor(Color.BLACK);
        g.setFont(text);
        
        g.drawString("RESTART", SpaceDefender.getCANVAS_WIDTH() / 2 - 65, 360);
        g.drawString(mainMenu, SpaceDefender.getCANVAS_WIDTH() / 2 - 110, 510);
    }
    
    /**
     * renders main menu
     * @param g
     */
    public void renderMainMenu(Graphics g) {
        Font text = new Font(fontFamily, Font.BOLD, 45);

        g.setColor(Color.WHITE);
        
        g.fillRect(SpaceDefender.getCANVAS_WIDTH() / 2 - 125, 300, 250, 100);
        g.fillRect(SpaceDefender.getCANVAS_WIDTH() / 2 - 125, 450, 250, 100);
        g.fillRect(SpaceDefender.getCANVAS_WIDTH() / 2 - 125, 600, 250, 100);
        
        g.setColor(Color.BLACK);
        g.setFont(text);
        
        g.drawString("NEW GAME", SpaceDefender.getCANVAS_WIDTH() / 2 - 103, 365);
        g.drawString("HELP", SpaceDefender.getCANVAS_WIDTH() / 2 - 70, 515);
        g.drawString("EXIT", SpaceDefender.getCANVAS_WIDTH() / 2 - 40, 665);
    }
    
    /**
     * renders help menu
     * @param g
     */
    public void renderHelp(Graphics g) {
        Font text = new Font(fontFamily, Font.BOLD, 35);

        g.setColor(Color.WHITE);
        g.fillRect(SpaceDefender.getCANVAS_WIDTH() / 2 - 125, 850, 250, 100);
        
        g.setColor(Color.BLACK);
        g.setFont(text);
        g.drawString(mainMenu, SpaceDefender.getCANVAS_WIDTH() / 2 - 110, 915);
    }
}
