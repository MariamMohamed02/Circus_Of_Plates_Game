
package model;

import model.Objects.ImageObject;
import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public abstract class Shapes implements GameObject

{
    private static final int MAX_MSTATE = 1;
   
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int x;
    private int y;
    private boolean visible;
    private int type;
    private String shapeType;

    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }

    public String getShapeType() {
        return shapeType;
    }
    
     private State currentState;
    private int upperleft;

    public int getUpperleft() {
        return upperleft;
    }

    public void setUpperleft(int upperleft) {
        this.upperleft = upperleft;
    }

    public int getLowerleft() {
        return lowerleft;
    }

    public void setLowerleft(int lowerleft) {
        this.lowerleft = lowerleft;
    }

    public int getUpperright() {
        return upperright;
    }

    public void setUpperright(int upperright) {
        this.upperright = upperright;
    }

    public int getLowerRight() {
        return lowerright;
    }

    public void setLowerRight(int lowerright) {
        this.lowerright = lowerright;
    }
    private int lowerleft;
    private int upperright;
    private int lowerright;
    

   

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }
    

    
    
    /*
    moving on barrr
    3:bomb    
    
    
    
    1:left stack
    2: right stack
    6: second left stack
    7: second right stack
    
    
    
    
    */
   
    private boolean horizontalOnly;
    private ImageObject c;
    private String path;

    public ImageObject getC() {
        return c;
    }

    public void setC(ImageObject c) {
        this.c = c;
    }

    public Shapes(boolean horizontalOnly) {
        this.horizontalOnly = horizontalOnly;
    }

    

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Shapes(int posX, int posY, String path) {
        this.x = posX;
        this.y = posY;
        
        this.visible = true;
        this.path = path;
        
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
        if (type == 1) 
            this.x = c.getX();
         else if (type==2)
                this.x = (c.getX()+132);
        else if (type==6)
                this.x = (c.getX()+215);
        else if (type==7)
                this.x = (c.getX()+350);
        
        
        else
             this.x = mX;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int mY) {
        if (!horizontalOnly) {
            this.y = mY;
        }
    }

    public boolean isHorizontalOnly() {
        return horizontalOnly;
    }

    public void setHorizontalOnly(boolean horizontalOnly) {
        this.horizontalOnly = horizontalOnly;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
