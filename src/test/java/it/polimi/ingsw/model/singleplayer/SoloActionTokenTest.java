package it.polimi.ingsw.model.singleplayer;
import static org.junit.jupiter.api.Assertions.*;


import it.polimi.ingsw.model.cards.developmentcards.CardColor;
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
    void initialization(){
        testToken = new SoloActionToken(SoloActionTokenType.DISCARD, CardColor.GREEN,2);
    }

    /**
     * Test method checkGet checks if the getter methods work correctly
     */
    @Test
    void checkGet(){
        assertEquals(CardColor.GREEN, testToken.getColor());
        assertEquals(SoloActionTokenType.DISCARD, testToken.getType());
    }

}