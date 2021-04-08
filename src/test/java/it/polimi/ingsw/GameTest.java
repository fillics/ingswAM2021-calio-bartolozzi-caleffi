package it.polimi.ingsw;

import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
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
    Player testPlayer;

    /**
     * Method initialization initializes values.
     */
    @BeforeEach
    void initialization() throws FileNotFoundException {
        testGame = new Game();
        testGame.createNewPlayer(new Player("fil", 1));
        testGame.createNewPlayer(new Player("bea", 2));
        testGame.createNewPlayer(new Player("gio", 3));
    }

    /**
     * Test method checkSizeArrayPlayer checks if the arrays that contain the players and
     * the active players have the right dimension, after the inizialitation
     */
    @Test
    void checkSizeArrayPlayer() {
        assertEquals(3, testGame.getActivePlayers().size());
        assertEquals(3, testGame.getPlayers().size());
    }

    /**
     * Test method checkSizeDevelopmentDeck checks if the method createDevelopmentDeck creates
     * the deck with the correct dimension
     */
    @Test
    void checkSizeDevelopmentDeck() {
        testGame.createDevelopmentDeck();
        int bound = testGame.getDevelopmentDeck().size();
        IntStream.range(0, bound).forEach(i -> assertEquals(4, testGame.getDevelopmentDeck().get(i).size()));
    }

    /**
     * Test method checkSizeLeaderDeck checks if the method createLeaderDeck creates
     * the deck with the correct dimension
     */
    @Test
    void checkSizeLeaderDeck() {
        testGame.createLeaderDeck();
        assertEquals(16, testGame.getLeaderDeck().size());
    }

    /**
     * Test method checkRightSortDevelopmentCard checks if the development deck is sorted in the correct way, with the cards in the
     * right position
     */
    @Test
    void checkRightSortDevelopmentCard() {
        testGame.createDevelopmentDeck();
        assertEquals(CardColor.YELLOW, testGame.getDevelopmentDeck().get(0).get(0).getColor()); //first element first array
        assertEquals(CardColor.YELLOW, testGame.getDevelopmentDeck().get(0).get(3).getColor()); //forth element first array
        assertEquals(CardColor.GREEN, testGame.getDevelopmentDeck().get(1).get(0).getColor()); //first element second array
        assertEquals(CardColor.GREEN, testGame.getDevelopmentDeck().get(1).get(2).getColor()); //third element second array
        assertEquals(CardColor.PURPLE, testGame.getDevelopmentDeck().get(2).get(0).getColor()); //first element third array
        assertEquals(CardColor.PURPLE, testGame.getDevelopmentDeck().get(2).get(3).getColor()); //forth element third array
        assertEquals(CardColor.BLUE, testGame.getDevelopmentDeck().get(3).get(0).getColor()); //first element third array
        assertEquals(CardColor.BLUE, testGame.getDevelopmentDeck().get(3).get(1).getColor()); //second element forth array
    }


    /**
     * Test method checkLeaderCardsDistribution checks if each player receives 4 leader cards at the beginning of the game
     */
    @Test
    void checkLeaderCardsDistribution(){
        testGame.setup();
        assertEquals(4, testGame.getActivePlayers().get(0).getLeaderCards().size());
    }

    @Test
    void checkRemoveFromDevDeck(){
        testGame.createDevelopmentDeck();

    }
}