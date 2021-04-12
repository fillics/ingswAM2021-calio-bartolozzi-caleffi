package it.polimi.ingsw;

import it.polimi.ingsw.Exceptions.NumMaxPlayersReached;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * PlayerTest class tests Player class.
 *
 * @see Player
 */

class PlayerTest {

    Game testGame;
    private static final String username = "fil";


    /** Method setup setups test. */
    @BeforeEach
    void setup() throws NumMaxPlayersReached {
        testGame = new Game();
        testGame.createNewPlayer(username);
        testGame.setup();
    }

    /** Method checkUsername tests username's getter. */
    @Test
    void checkUsername() {
       assertEquals(username, testGame.getActivePlayers().get(0).getUsername());
    }


    /** Method checkTotalVictoryPoints tests totalVictoryPoints's getter. */
    @Test
    void checkTotalVictoryPoints(){
        assertEquals(0, testGame.getActivePlayers().get(0).getTotalVictoryPoint());
    }


    /** Method cardAdditionTest test the addition of a single card to the player. */
    @Test
    void cardAdditionTest() {
        assertEquals(4, testGame.getActivePlayers().get(0).getLeaderCards().size());
    }

    /**
     * Test method increaseFaithMarker checks the correct increase of the player's faith marker
     */
    @Test
    void increaseFaithMarker() {
        assertEquals(0, testGame.getActivePlayers().get(0).getBoard().getFaithMarker());
        testGame.getActivePlayers().get(0).getBoard().increaseFaithMarker();
        assertEquals(1, testGame.getActivePlayers().get(0).getBoard().getFaithMarker());
    }

    @Test
    void checkChooseResourceBeginningGame(){
        //testGame.getActivePlayers().get(2);
    }

}