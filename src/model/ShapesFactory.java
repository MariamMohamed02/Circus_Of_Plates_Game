
package model;

import model.Objects.PlateObject;
import model.Objects.OvalObject;
import eg.edu.alexu.csd.oop.game.GameObject;


public class ShapesFactory {
    
    
    // plateSide 1:left   2:right 
    // BarLevel  1:up     2:down
    public static GameObject getShape( int x, int y,String type) {
        if (type == null) {
            return null;
        }
        if (type.equals("plate")) {
            
               // To insert a random color
               PlateObject p=new PlateObject(x, y, "/plate" + ((int) (Math.random() * 1000) % 2 + 1) + ".png")  ;   
               p.setCurrentState(new OnBarState(p));
             
            
            return p;
        }
        
        if (type.equals("oval")) {
            
               // To insert a random color
               OvalObject o=new OvalObject(x, y, "/oval" + ((int) (Math.random() * 1000) % 2 + 1) + ".png")  ;   
               o.setCurrentState(new OnBarState(o));
               
            
            return o;
        }
       
        
        
        
        return null;
    }
    
}
