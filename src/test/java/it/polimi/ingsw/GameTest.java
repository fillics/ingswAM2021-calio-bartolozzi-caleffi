package it.polimi.ingsw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class GameTest tests Game class.
 *
 * @see Game
 */

class GameTest {

    Game testGame;

    /**
     * Method initialization initializes values.
     */
    @BeforeEach
    void initialization() {
        testGame = new Game();
        testGame.createNewPlayer(new Player("fil", 1));
        testGame.createNewPlayer(new Player("bea", 2));
        testGame.createNewPlayer(new Player("gio", 3));
    }


    @Test
    void checkSizeArrayPlayer() {
        assertEquals(3, testGame.getActivePlayers().size());
        assertEquals(3, testGame.getPlayers().size());
    }

    @Test
    void checkSizeDevelopmentDeck() throws FileNotFoundException {
        testGame.createDevelopmentDeck();
        assertEquals(12, testGame.getDevelopmentDeck().size());
        IntStream.range(0, testGame.getDevelopmentDeck().size()).forEach(i -> assertEquals(48, testGame.getDevelopmentDeck().get(i).size()));
    }

    @Test
    void checkSizeLeaderDeck() throws IOException {
        testGame.createLeaderDeck();
        assertEquals(16, testGame.getLeaderDeck().size());
    }
}