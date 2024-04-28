package model.Objects;



import model.Shapes;


public class BombObject extends Shapes{
    
    public BombObject(int x,int y, String path,int type) {
        super(x,y,path);
        this.setType(type);
    }
    
}
   
