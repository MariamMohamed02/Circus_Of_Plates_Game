package model.Strategy;



/**
 *
 * @author ADMIN
 */
public class DifficultStrategy implements LevelStrategy{
    

    @Override
    public int getSpeed() {
        return 3;    
    }

    @Override
    public int getTimeout() {
         return 1;  
    }

    @Override
    public int getControlSpeed() {
         return 8;    
    }
    
}
