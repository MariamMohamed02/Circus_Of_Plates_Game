package model;


import controller.CircusWorld;


public abstract class Observer {
     protected CircusWorld c;

    public Observer(CircusWorld c) {
        this.c = c;
    }

    public abstract void updateScore(int x);
}
