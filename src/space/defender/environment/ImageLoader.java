package space.defender.environment;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * loads image
 * @author Jakub Hôrečný
 */
public class ImageLoader {
    
    
    private BufferedImage texture;

    /**
     * loads image
     * @param path - file path
     * @return - image
     */
    public BufferedImage loadImage(String path) {
        try {                
            this.texture = ImageIO.read(new File(path));
          
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return this.texture;
    }
}
