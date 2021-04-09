package it.polimi.ingsw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * PlayerTest class tests Player class.
 *
 * @see Player
 */

class PlayerTest {

    Game testGame;
    Player testPlayer;
    private static final String username = "fil";
    private static final int position = 1;

    /** Method setup setups test. */
    @BeforeEach
    void setup() {
        testGame = new Game();
        testGame.createNewPlayer(new Player(username, position, testGame));
        testPlayer = new Player(username, position, testGame);

    }

    /** Method usernameTest tests username's getter. */
    @Test
    @DisplayName("Username getter test")
    void usernameTest() {
        assertEquals(username, testPlayer.getUsername());
        assertEquals(position, testPlayer.getPosition());
    }

    /** Method positionTest tests position's getter. */
    @Test
    @DisplayName("Position getter test")
    void positionTest() {
        assertEquals(position, testPlayer.getPosition());
    }

// TODO: 09/04/2021 fix this part 
    /** Method cardAdditionTest test the addition of a single card to the player. */
    /*@Test
    void cardAdditionTest() {
        testGame.setup();
        assertEquals(4, testPlayer.getLeaderCards().size());
    }*/


    @Test
    void increaseFaithMarker() {
    }
}