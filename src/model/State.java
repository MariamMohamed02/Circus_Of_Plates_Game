
package model;

import eg.edu.alexu.csd.oop.game.GameObject;


public abstract class State {
     protected GameObject obj;

    public State(GameObject obj) {
        this.obj = obj;
    }

    public abstract void move(int x, int y);
}
