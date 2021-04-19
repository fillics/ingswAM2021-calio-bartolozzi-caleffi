package it.polimi.ingsw.SinglePlayer;
import static org.junit.jupiter.api.Assertions.*;

import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import it.polimi.ingsw.Exceptions.NumMaxPlayersReached;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Class SinglePlayerGameTest tests SinglePlayerGame class.
 *
 * @see SinglePlayerGame
 */

class SinglePlayerGameTest {

    SinglePlayerGame testSingle = new SinglePlayerGame();


    /** Method instantiation instantiates the SinglePlayerGame classes. */
    @BeforeEach
    void instantiation() throws NumMaxPlayersReached {
        assertEquals(0, testSingle.getBlackCross());
        assertEquals(0, testSingle.getDeckSoloActionToken().size());
        assertEquals(0, testSingle.getDeletedSoloActionToken().size());
        testSingle.setup();
        testSingle.createNewPlayer("fil");
    }

    /**
     * Test method checkSetup checks if the setup method works correctly, creating the Token's deck.
     */
    @Test
    void checkSetup(){
        assertEquals(7, testSingle.getDeckSoloActionToken().size());
        assertEquals(0, testSingle.getDeletedSoloActionToken().size());
        assertEquals(0, testSingle.getBlackCross());
        assertEquals("fil", testSingle.getActivePlayers().get(0).getUsername());
    }

    /**
     * Test method checkIncreaseBlackCross checks the correct operation of the increasing of the black cross
     * and the correct use of the method endGame: game ends even when the black cross reaches the final space
     * of the faith track
     */
    @Test
    void checkIncreaseBlackCross(){
        testSingle.increaseBlackCross(1);
        assertEquals(1, testSingle.getBlackCross());
        testSingle.increaseBlackCross(-1);
        assertEquals(1, testSingle.getBlackCross());
        testSingle.increaseBlackCross(10);
        assertEquals(11, testSingle.getBlackCross());
        testSingle.increaseBlackCross(15);
        assertEquals(26, testSingle.getBlackCross());
        assertTrue(testSingle.endGame());
    }

    /**
     * Test method checkDeckSoloActionToken checks the correct size of the deck that contains the solo
     * action token before and after the shuffle
     */
    @Test
    void checkDeckSoloActionToken() {
        assertEquals(7, testSingle.getDeckSoloActionToken().size());
        testSingle.shuffleSoloActionToken();
        assertEquals(7, testSingle.getDeckSoloActionToken().size());
    }


    @Test
    void checkDrawSoloActionToken(){
        assertEquals(7, testSingle.getDeckSoloActionToken().size());
        testSingle.drawSoloActionToken();
        assertEquals(6, testSingle.getDeckSoloActionToken().size());
        testSingle.drawSoloActionToken();
        testSingle.drawSoloActionToken();
        assertEquals(4, testSingle.getDeckSoloActionToken().size());
    }



    /**
     * Test method checkTokenPlusOneBlackCross checks the correct use of the token that moves forward by one
     * space the black cross
     */
    @Test
    void checkTokenPlusOneBlackCross(){
        SoloActionToken token = new SoloActionToken(SoloActionTokenType.BLACKCROSS_1);
        token.setStrategy(new ConcreteStrategyPlusOne(testSingle));
        token.applyEffect();
        assertEquals(1, testSingle.getBlackCross());
        token.applyEffect();
        assertEquals(2, testSingle.getBlackCross());
    }

    /**
     * Test method checkTokenPlusTwoBlackCross checks the correct use of the token that moves forward by two
     * spaces the black cross
     */
    @Test
    void checkTokenPlusTwoBlackCross(){
        SoloActionToken token = new SoloActionToken(SoloActionTokenType.BLACKCROSS_2);
        token.setStrategy(new ConcreteStrategyPlusTwo(testSingle));
        token.applyEffect();
        assertEquals(2, testSingle.getBlackCross());
        token.applyEffect();
        assertEquals(4, testSingle.getBlackCross());
    }

    /**
     * Test method checkTokenDiscard checks the correct use of the token that discards two cards with the same
     * color from the development card's grid
     */
    @Test
    void checkTokenDiscard(){
        SoloActionToken blueToken = new SoloActionToken(SoloActionTokenType.DISCARD, CardColor.BLUE);
        blueToken.setStrategy(new ConcreteStrategyDiscard(testSingle, CardColor.BLUE));
        assertEquals(4, testSingle.getDevelopmentGrid().get(3).size());
        blueToken.applyEffect();
        assertEquals(2, testSingle.getDevelopmentGrid().get(3).size());
        blueToken.applyEffect();
        assertEquals(0, testSingle.getDevelopmentGrid().get(3).size());
        assertEquals(4, testSingle.getDevelopmentGrid().get(7).size());
        blueToken.applyEffect();
        assertEquals(2, testSingle.getDevelopmentGrid().get(7).size());
    }

    /**
     * Test method checkRemoveEntireColumnOfDevGrid checks the behaviour when we remove an entire
     * column of development cards from the grid
     */
    @Test
    void checkRemoveEntireColumnOfDevGrid(){
        testSingle.removeDevCard(CardColor.GREEN);
        testSingle.removeDevCard(CardColor.GREEN);
        testSingle.removeDevCard(CardColor.GREEN);
        testSingle.removeDevCard(CardColor.GREEN);
        assertEquals(0, testSingle.getDevelopmentGrid().get(1).size());
        testSingle.removeDevCard(CardColor.GREEN);
        testSingle.removeDevCard(CardColor.GREEN);
        testSingle.removeDevCard(CardColor.GREEN);
        assertEquals(1, testSingle.getDevelopmentGrid().get(5).size());
        testSingle.removeDevCard(CardColor.GREEN);
        assertEquals(4, testSingle.getDevelopmentGrid().get(9).size());
        testSingle.removeDevCard(CardColor.GREEN);
        assertEquals(0, testSingle.getDevelopmentGrid().get(5).size());
        testSingle.removeDevCard(CardColor.GREEN);
        testSingle.removeDevCard(CardColor.GREEN);
        testSingle.removeDevCard(CardColor.GREEN);
        assertTrue(testSingle.isNoMoreColumnDevCard());
        assertTrue(testSingle.endGame());

    }

    /**
     * Test method checkIncreaseFaithMarker checks the correct increase of the player's faith marker
     */
    @Test
    void checkIncreaseFaithMarker(){
        assertEquals(0, testSingle.getActivePlayers().get(0).getBoard().getFaithMarker());
        testSingle.getActivePlayers().get(0).getBoard().increaseFaithMarker();
        assertEquals(1, testSingle.getActivePlayers().get(0).getBoard().getFaithMarker());
        testSingle.getActivePlayers().get(0).getBoard().increaseFaithMarker();
        testSingle.getActivePlayers().get(0).getBoard().increaseFaithMarker();
        assertEquals(3, testSingle.getActivePlayers().get(0).getBoard().getFaithMarker());
    }

}