package it.polimi.ingsw.SinglePlayer;
import static org.junit.jupiter.api.Assertions.*;

import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import it.polimi.ingsw.Exceptions.NegativeNumberBlackCross;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Class SinglePlayerGameTest tests SinglePlayerGame class.
 *
 * @see SinglePlayerGame
 */

class SinglePlayerGameTest {

    SinglePlayerGame single = new SinglePlayerGame();

    /** Method istantiaton instantiates the SinglePlayerGame classes. */
    @BeforeEach
    void istantiaton() {

        assertEquals(0, single.getBlackCross());
        assertEquals(0, single.getDeckSoloActionToken().size());
        assertEquals(0, single.getDeletedSoloActionToken().size());
    }



    /**
     * Test method checkIncreaseBlackCross checks the correct operation of the increasing of the black cross
     */
    @Test
    void checkIncreaseBlackCross() throws NegativeNumberBlackCross {
        single.increaseBlackCross(1);
        assertEquals(1, single.getBlackCross());

    }

    /**
     * Test method checkUsageSoloAction checks the correct discard of the solo action token
     */
    @Test
    void checkDiscardSoloActionToken(){

    }

    /**
     * Test method checkAdditionSoloAction checks the correct addition of the token used in
     * the deck containing the token already used
     */
    @Test
    void checkAdditionSoloActionToken(){

    }

}