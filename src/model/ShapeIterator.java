package model;


import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.List;


public class ShapeIterator implements Iterator {

    
    List<GameObject> shapesList; 
    public int index=0; 
    
    public ShapeIterator(List<GameObject> x)
    {
        this.shapesList=x;
       
    }
    
    @Override
    public boolean hasNext() {
             if(index < shapesList.size()){
            return true;
         }
         return false;
    }

    @Override
    public GameObject next() {
          if(this.hasNext()){
            return shapesList.get(index);
         }
         return null;  
    }
    
}



