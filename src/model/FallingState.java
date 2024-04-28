
package model;

import eg.edu.alexu.csd.oop.game.GameObject;


public class FallingState extends State {

    public FallingState(GameObject obj) {
        super(obj);
    }
    @Override
    public void move(int x, int y) {
        obj.setX(x);
        obj.setY(y);
    }
    
}
