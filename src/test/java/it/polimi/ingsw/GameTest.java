package it.polimi.ingsw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class GameTest tests Game class.
 *
 * @see Game
 */

class GameTest {

    Game testGame;

    /** Method initialization initializes values. */
    @BeforeEach
    void initialization() {
        testGame = new Game();
        testGame.createNewPlayer(new Player("fil", 1));
        testGame.createNewPlayer(new Player("bea", 2));
        testGame.createNewPlayer(new Player("gio", 3));
    }

}