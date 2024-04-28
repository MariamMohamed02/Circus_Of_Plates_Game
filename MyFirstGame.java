/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package oop.game.myfirstgame;

import oop.game.world.StarWar;


/**
 *
 * @author michael.said
 */
public class MyFirstGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        GameController gameController = new GameController(() -> new StarWar(800, 600));
        gameController.start();
    }

}
