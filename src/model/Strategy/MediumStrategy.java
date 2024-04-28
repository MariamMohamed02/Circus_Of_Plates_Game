package model.Strategy;


/**
 *
 * @author ADMIN
 */
public class MediumStrategy implements LevelStrategy{
    
   

    @Override
    public int getSpeed() {
        return 2;   
    }

    @Override
    public int getTimeout() {
        return 2;  
    }

    @Override
    public int getControlSpeed() {
        return 10;    
    }

    
    
}
