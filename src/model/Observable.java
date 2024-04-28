package model;

import controller.CircusWorld;


public class Observable extends Observer{
    
    public Observable(CircusWorld circusWorld){
        super(circusWorld);
    }

    @Override
    public void updateScore(int x) {
           c.setScore(c.getScore()+x);
    }
}
