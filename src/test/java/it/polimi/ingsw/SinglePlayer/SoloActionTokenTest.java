package it.polimi.ingsw.SinglePlayer;
import static org.junit.jupiter.api.Assertions.*;


import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

/**
 * Class SoloActionTokenTest tests SoloActionToken class.
 *
 * @see SoloActionToken
 */


class SoloActionTokenTest {
    SoloActionToken testToken;

    /**
     * Method initialization initializes values.
     */
    @BeforeEach
    void inizialitation(){
        testToken = new SoloActionToken(SoloActionTokenType.DISCARD, CardColor.GREEN);
    }

    /**
     * Test method checkGet checks if the getter methods work correctly.
     */
    @Test
    void checkGet(){
        assertEquals(CardColor.GREEN, testToken.getColor());
        assertEquals(SoloActionTokenType.DISCARD, testToken.getType());
    }

}