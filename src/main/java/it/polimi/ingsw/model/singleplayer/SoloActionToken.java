package it.polimi.ingsw.model.singleplayer;


import it.polimi.ingsw.model.cards.developmentcards.CardColor;

/**
 * Represents the tokens that can be used in Single Mode.
 */

public class SoloActionToken {

    private final SoloActionTokenType type;
    private CardColor color;
    private TokenActionStrategy strategy;
    private int id;

    /**
     * Constructor SoloActionToken creates a new SoloActionToken instance.
     * @param type of type SoloActionTokenType
     * @param color of type CardColor
     */
    public SoloActionToken(SoloActionTokenType type, CardColor color, int id) {
        this.type = type;
        this.color = color;
        this.id = id;
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
     * Method getId returns the id of the token
     */
    public int getId() {
        return id;
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
