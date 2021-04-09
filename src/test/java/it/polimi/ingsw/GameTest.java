package it.polimi.ingsw;

import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void initialization() {
        testGame = new Game();
        testGame.setup();
        testGame.createNewPlayer(new Player("fil", 1, testGame));
        testGame.createNewPlayer(new Player("bea", 2, testGame));
        testGame.createNewPlayer(new Player("gio", 3, testGame));
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
     * Test method checkSizeDevelopmentGrid checks if the method createDevelopmentGrid creates
     * the grid with the correct dimension
     */
    @Test
    void checkSizeDevelopmentGrid() {
        testGame.setup();
        int bound = testGame.getDevelopmentGrid().size();
        IntStream.range(0, bound).forEach(i -> assertEquals(4, testGame.getDevelopmentGrid().get(i).size()));
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
     * Test method checkSizeLeaderDeckAfterDistribute checks the size of the leader deck
     * after the distribution of the leader cards to each player
     */
    @Test
    void checkSizeLeaderDeckAfterDistribute() {
        testGame.setup();
        assertEquals(16-4*testGame.getActivePlayers().size(), testGame.getLeaderDeck().size());
    }

    /**
     * Test method checkRightSortDevelopmentCard checks if the development grid is sorted in the correct way,
     * with the cards in the right position
     */
    @Test
    void checkRightSortDevelopmentCard() {
        testGame.createDevelopmentGrid();
        assertEquals(CardColor.YELLOW, testGame.getDevelopmentGrid().get(0).get(0).getColor()); //first element first array
        assertEquals(CardColor.YELLOW, testGame.getDevelopmentGrid().get(0).get(3).getColor()); //forth element first array
        assertEquals(CardColor.GREEN, testGame.getDevelopmentGrid().get(1).get(0).getColor()); //first element second array
        assertEquals(CardColor.GREEN, testGame.getDevelopmentGrid().get(1).get(2).getColor()); //third element second array
        assertEquals(CardColor.PURPLE, testGame.getDevelopmentGrid().get(2).get(0).getColor()); //first element third array
        assertEquals(CardColor.PURPLE, testGame.getDevelopmentGrid().get(2).get(3).getColor()); //forth element third array
        assertEquals(CardColor.BLUE, testGame.getDevelopmentGrid().get(3).get(0).getColor()); //first element third array
        assertEquals(CardColor.BLUE, testGame.getDevelopmentGrid().get(3).get(1).getColor()); //second element forth array
    }


    /**
     * Test method checkLeaderCardsDistribution checks if each player receives 4 leader cards at the beginning of the game
     */
    @Test
    void checkLeaderCardsDistribution(){
        testGame.setup();
        for (int i = 0; i < testGame.getActivePlayers().size(); i++) {
            assertEquals(4, testGame.getActivePlayers().get(i).getLeaderCards().size());
        }
    }

    // TODO: 09/04/2021 da fare
    @Test
    void checkRemoveFromDevDeck(){
        testGame.setup();

    }
}