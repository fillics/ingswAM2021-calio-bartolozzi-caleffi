package it.polimi.ingsw.SinglePlayer;


import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;

/**
 * Represents the tokens that can be used in Single Mode.
 */

public class SoloActionToken {

    private SoloActionTokenType type;
    private CardColor color;
    private TokenActionStrategy strategy;

    /**
     * Class's Constructor made to define the token type
     */
    public SoloActionToken(SoloActionTokenType type, CardColor color) {
        this.type = type;
        this.color = color;
    }

    /**
     * get-method created to obtain the token type
     */
    public SoloActionTokenType getType() {
        return type;
    }
    public CardColor getColor() {
        return color;
    }

    public TokenActionStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(TokenActionStrategy strategy) {
        this.strategy = strategy;
    }


    /**
     * method that links to the strategy method in order to apply the token's effect
     */
    public boolean applyEffect(){
        strategy.effect();
        return true;
    }

    @Override
    public String toString() {
        return "SoloActionToken{" +
                "type=" + type +
                ", color=" + color +
                ", strategy=" + strategy +
                '}';
    }
}
