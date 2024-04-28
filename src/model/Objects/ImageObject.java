package model.Objects;


import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import eg.edu.alexu.csd.oop.game.GameObject;

public class ImageObject implements GameObject {

    private static final int MAX_MSTATE = 1;
   
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE]; // if more thean one element in array therfore animation
    private int x;
    private int y;
    private boolean visible;
    public static ImageObject cl;

    public ImageObject(int x, int y, String path) {
        this.x = x;
        this.y = y;
        this.visible = true;
        
        try {
            spriteImages[0] = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {

        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int mX) {
        this.x = mX;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int mY) {
       
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    @Override
    public int getWidth() {
        return spriteImages[0].getWidth();
    }

    @Override
    public int getHeight() {
        return spriteImages[0].getHeight();
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}
