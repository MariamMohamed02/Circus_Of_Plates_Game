
package model;

import eg.edu.alexu.csd.oop.game.GameObject;


public class OnBarState extends State{

    public OnBarState(GameObject obj) {
        super(obj);
    }
    @Override
    public void move(int x, int y) {
        obj.setX(x);
       
    }
    
}
