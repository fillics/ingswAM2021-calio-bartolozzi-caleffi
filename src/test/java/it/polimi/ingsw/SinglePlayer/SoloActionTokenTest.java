package it.polimi.ingsw.SinglePlayer;
import static org.junit.jupiter.api.Assertions.*;


import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Class SoloActionTokenTest tests SoloActionToken class.
 *
 * @see SoloActionToken
 */


class SoloActionTokenTest {
    SoloActionToken token;

    @Test
    @DisplayName("")
    void inizialitation(){
        token = new SoloActionToken(SoloActionTokenType.DISCARD, CardColor.GREEN);
        assertEquals(CardColor.GREEN, token.getColor());
        assertEquals(SoloActionTokenType.DISCARD, token.getType());
    }

}