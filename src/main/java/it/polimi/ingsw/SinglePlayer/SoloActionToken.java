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
     * Constructor SoloActionToken creates a new SoloActionToken instance.
     * @param type of type SoloActionTokenType
     * @param color of type CardColor
     */
    public SoloActionToken(SoloActionTokenType type, CardColor color) {
        this.type = type;
        this.color = color;
    }
    /**
     * Constructor SoloActionToken creates a new SoloActionToken instance.
     * @param type of type SoloActionTokenType
     */
    public SoloActionToken(SoloActionTokenType type) {
        this.type = type;
    }

    /**
     * get-method created to obtain the token type
     */
    public SoloActionTokenType getType() {
        return type;
    }

    /**
     * get-method created to obtain the token color
     */
    public CardColor getColor() {
        return color;
    }

    /**
     * get-method created to obtain the token strategy
     */
    public TokenActionStrategy getStrategy() {
        return strategy;
    }

    /**
     * Setter method to assign the strategy to this SoloActionToken instance
     *
     * @param strategy of type TokenActionStrategy - the strategy to be assigned.
     */
    public void setStrategy(TokenActionStrategy strategy) {
        this.strategy = strategy;
    }


    /**
     * Method applyEffect links to the strategy method in order to apply the token's effect
     */
    public void applyEffect(){
        strategy.effect();
    }

}
