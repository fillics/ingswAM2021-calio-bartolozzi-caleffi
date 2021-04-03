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

    Player testPlayer;
    private static final String username = "player";
    private static final int position = 1;

    /** Method setup setups test. */
    @BeforeEach
    void setup() {
        testPlayer = new Player(username, position);
    }

    /** Method usernameTest tests username's getter. */
    @Test
    @DisplayName("Username/position getter test")
    void usernameTest() {
        assertEquals(username, testPlayer.getUsername());
        assertEquals(position, testPlayer.getPosition());
    }

    /** Method cardAdditionTest test the addition of a single card to the player. */
    @Test
    @DisplayName("Leader Card addition test")
    void cardAdditionTest() {

    }


    @Test
    void increaseFaithMarker() {
    }
}