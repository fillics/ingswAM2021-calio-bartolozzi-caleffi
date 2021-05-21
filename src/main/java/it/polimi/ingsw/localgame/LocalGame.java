package it.polimi.ingsw.localgame;

import it.polimi.ingsw.model.singleplayer.SinglePlayerGame;

/**
 * Class LocalGame used when a person wants to play without making the connection with the server
 */
public class LocalGame {

    private SinglePlayerGame game;
    public LocalGame() {
        game = new SinglePlayerGame();
    }

    public static void main(String[] args) {

    }
}
