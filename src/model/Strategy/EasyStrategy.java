package model.Strategy;



public class EasyStrategy implements LevelStrategy {
   
   
   @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public int getTimeout() {
        return 3;
    }

    @Override
    public int getControlSpeed() {
       return 10;     
    }

    

}
