package model.Objects;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import eg.edu.alexu.csd.oop.game.GameObject;

public class BarObject implements GameObject {

    public static final int SPRITE_HEIGHT = 5;
    private static final int MAX_MSTATE = 1;
    
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE]; 
    
     private boolean visible;
    private boolean horizontalOnly;
    
    
    private int x;
    private int y;
    private int width;
   

    public BarObject(int x, int y, int w, boolean horizontalOnly, Color c) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.horizontalOnly = horizontalOnly;
        this.visible = true;
        // create a bunch of buffered images and place into an array, to be displayed sequentially
        spriteImages[0] = new BufferedImage(w, SPRITE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D bar = spriteImages[0].createGraphics();
        bar.setColor(c);
        bar.setBackground(c);
        bar.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        bar.setStroke(new BasicStroke(20));
        bar.drawLine(0, 0, getWidth(), 0);
        bar.dispose();
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
        if (horizontalOnly) {
            return;
        }
        this.y = mY;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return SPRITE_HEIGHT;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
