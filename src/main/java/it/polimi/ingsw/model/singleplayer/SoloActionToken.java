package it.polimi.ingsw.model.singleplayer;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.model.cards.developmentcards.CardColor;

/**
 * Represents the tokens that can be used in Single Mode.
 */

public class SoloActionToken {

    private final SoloActionTokenType type;
    private CardColor color;
    private TokenActionStrategy strategy;
    private int id;
    private String path;

    /**
     * Constructor SoloActionToken creates a new SoloActionToken instance.
     * @param type of type SoloActionTokenType
     * @param color of type CardColor
     */
    @JsonCreator
    public SoloActionToken(@JsonProperty("type") SoloActionTokenType type,@JsonProperty("color") CardColor color,@JsonProperty("id") int id, @JsonProperty("path") String path, @JsonProperty("strategy") TokenActionStrategy strategy) {
        this.strategy=strategy;
        this.type = type;
        this.color = color;
        this.id = id;
        this.path = path;
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
     * Method getPath returns the path of the token
     */
    public String getPath() {
        return path;
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
