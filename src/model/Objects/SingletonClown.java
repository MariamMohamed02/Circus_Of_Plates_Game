package model.Objects;


import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class SingletonClown {
    private int posX; 
    private int posY; 
    private String path; 
  
    
    private static SingletonClown clownInstance=null; 
    
    
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
       
        
    }
    
    private SingletonClown()
    {

        
    }
    
    /**
     *
     */
    public static SingletonClown getInstance()
    {
        if (clownInstance==null)
        {
            clownInstance = new SingletonClown();
        }
          return clownInstance; 
    }
    
}
    
    
    

