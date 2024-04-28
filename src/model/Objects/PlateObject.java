package model.Objects;


import model.Shapes;

/**
 *
 * @author ADMIN
 */
public class PlateObject  extends Shapes {
    
   

    public PlateObject(int x, int y, String path){
     super(x,y,path);
     this.setShapeType("plate");
 
    }
   
}
