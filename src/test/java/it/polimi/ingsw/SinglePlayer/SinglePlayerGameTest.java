package it.polimi.ingsw.SinglePlayer;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;


/**
 * Class SinglePlayerGameTest tests SinglePlayerGame class.
 *
 * @see SinglePlayerGame
 */

class SinglePlayerGameTest {

    SinglePlayerGame testSingle = new SinglePlayerGame();

    /** Method istantiaton instantiates the SinglePlayerGame classes. */
    @BeforeEach
    void istantiaton() {
        assertEquals(0, testSingle.getBlackCross());
        assertEquals(0, testSingle.getDeckSoloActionToken().size());
        assertEquals(0, testSingle.getDeletedSoloActionToken().size());
    }

    /**
     * Test method checkSetup checks if the setup method works correctly, creating the Token's deck.
     */
    @Test
    void checkSetup(){
        testSingle.setup();
        assertEquals(7, testSingle.getDeckSoloActionToken().size());
    }

    /**
     * Test method checkIncreaseBlackCross checks the correct operation of the increasing of the black cross
     */
    @Test
    void checkIncreaseBlackCross(){
        assertEquals(0, testSingle.getBlackCross());
        testSingle.increaseBlackCross(1);
        assertEquals(1, testSingle.getBlackCross());

    }

    /**
     * Test method checkSetDeckSoloActionToken checks the correct size of the deck that contains the solo
     * action token before and after the shuffle
     */
    @Test
    void checkSetDeckSoloActionToken() {
        testSingle.setDeckSoloActionToken();
        assertEquals(7, testSingle.getDeckSoloActionToken().size());
        testSingle.shuffleSoloActionToken();
        assertEquals(7, testSingle.getDeckSoloActionToken().size());
    }

    /**
     * Test method checkSizeAfterUseToken checks the correct discard of the solo action token
     * and the correct addition of the tokens in the already used token's deck
     */
    @Test
    void checkSizeAfterUseToken() {
        testSingle.setDeckSoloActionToken();
        assertEquals(0, testSingle.getDeletedSoloActionToken().size());
        testSingle.useSoloActionToken();
        assertEquals(6, testSingle.getDeckSoloActionToken().size());
        assertEquals(1, testSingle.getDeletedSoloActionToken().size());
        /*testSingle.useSoloActionToken();
        assertEquals(5, testSingle.getDeckSoloActionToken().size());
        assertEquals(2, testSingle.getDeletedSoloActionToken().size());*/
    }

    /**
     * Test method checkShuffle checks the correct creation of the new shuffled deck
     */
    @Test
    void checkShuffle() {
        testSingle.setDeckSoloActionToken();
        testSingle.shuffleSoloActionToken();
        assertEquals(0, testSingle.getDeletedSoloActionToken().size());
        assertEquals(7, testSingle.getDeckSoloActionToken().size());
        testSingle.useSoloActionToken();
        assertEquals(1, testSingle.getDeletedSoloActionToken().size());
        assertEquals(6, testSingle.getDeckSoloActionToken().size());
        testSingle.shuffleSoloActionToken();
        assertEquals(0, testSingle.getDeletedSoloActionToken().size());
        assertEquals(7, testSingle.getDeckSoloActionToken().size());

    }

    @Test
    void checkTokenPlusOneBlackCross(){
        SoloActionToken token = new SoloActionToken(SoloActionTokenType.BLACKCROSS_1);
        token.setStrategy(new ConcreteStrategyPlusOne(testSingle));
        token.applyEffect();
        assertEquals(1, testSingle.getBlackCross());
    }

    @Test
    void checkTokenPlusTwoBlackCross(){
        SoloActionToken token = new SoloActionToken(SoloActionTokenType.BLACKCROSS_2);
        token.setStrategy(new ConcreteStrategyPlusTwo(testSingle));
        token.applyEffect();
        assertEquals(2, testSingle.getBlackCross());
    }


}