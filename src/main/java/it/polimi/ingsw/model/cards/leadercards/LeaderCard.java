package it.polimi.ingsw.model.cards.leadercards;

import com.fasterxml.jackson.annotation.*;
import it.polimi.ingsw.constants.Color;
import it.polimi.ingsw.constants.Printable;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentCard;

import java.util.HashMap;

/**
 * This class represents the Leader Card.
 */

public class LeaderCard extends Card {
    private final int id;
    private final LeaderCardType type;
    private LeaderCardStrategy strategy;
    private final ResourceType resourceType;
    private final Requirement requirements;
    private final int victorypoint;
    private final String path;
    private int depositPosition;
    private int productionPowerPosition;

    /**
     * constructor LeaderCard creates a new LeaderCard instance,
     * @param id is the ID of the card.
     * @param type is the type of the card: EXTRA_DEPOSIT, DISCOUNT, PRODUCTION_POWER, WHITE_MARBLE.
     * @param requirements are the requirements needed to activate the laeder card.
     * @param resourceType is the resource type of the
     * @param victoryPoint is the amount of victory point obtained at the end of the game if the card is activated.
     */
    @JsonCreator
    public LeaderCard(@JsonProperty("id") int id, @JsonProperty("type") LeaderCardType type, @JsonProperty("requirements") Requirement requirements,
                      @JsonProperty("resourceType") ResourceType resourceType, @JsonProperty("victorypoint") int victoryPoint,@JsonProperty("path") String path,
                      @JsonProperty("depositPosition") int depositPosition, @JsonProperty("productionPowerPosition") int productionPowerPosition) {
        this.id = id;
        this.type = type;
        this.requirements = requirements;
        this.resourceType = resourceType;
        this.victorypoint = victoryPoint;
        this.path = path;
        this.depositPosition = depositPosition;
        this.productionPowerPosition = productionPowerPosition;
    }

    /**
     * Method useDiscount modifies the price of a development card only for its purchase.
     */
    public void useDiscount(DevelopmentCard developmentCard, HashMap<ResourceType,Integer> resourcePriceBuffer){
        int oldvalue;
        ResourceType temp;
        temp = strategy.getResourceType();
        if(developmentCard.getResourcePrice().containsKey(temp)){
            oldvalue = developmentCard.getResourcePrice().get(temp);
            resourcePriceBuffer.replace(temp,oldvalue,oldvalue-1);
        }
    }

    /**
     * Method useAbility activates the ability of the strategy.
     */
    public void useAbility() {
        strategy.ability();
    }

    public int getProductionPowerPosition() {
        return productionPowerPosition;
    }

    public void setProductionPowerPosition(int productionPowerPosition) {
        this.productionPowerPosition = productionPowerPosition;
    }

    public int getDepositPosition() {
        return depositPosition;
    }

    public void setDepositPosition(int depositPosition) {
        this.depositPosition = depositPosition;
    }

    public String getPath() {
        return path;
    }

    public int getId() {
        return id;
    }

    public LeaderCardType getType() {
        return type;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setStrategy(LeaderCardStrategy strategy) {
        this.strategy = strategy;
    }

    public LeaderCardStrategy getStrategy() {
        return strategy;
    }

    public Requirement getRequirements() {
        return requirements;
    }

    @Override
    public int getVictorypoint() {
        return victorypoint;
    }

    @Override
    public String toString(){
        String backspace= "";
        if(this.id<10)
            backspace=" ";
        return Printable.UPPER_BOX.print() + "\n"+ Printable.DOUBLE_LINE.print() + "id: " + this.id + "  " + backspace + Printable.DOUBLE_LINE.print() +"\n"+
        Printable.DOUBLE_LINE.print() + this.requirements.toString() + "  "+ Printable.DOUBLE_LINE.print()+ "\n" +
        Printable.DOUBLE_LINE.print() + victorypoint + Color.ANSI_YELLOW.escape() + " VP" + Color.RESET + "    " + Printable.DOUBLE_LINE.print() +"\n" +
        Printable.DOUBLE_LINE.print() + this.strategy.toString() +  Printable.DOUBLE_LINE.print() +"\n" +
        Printable.BOTTOM_BOX.print();
    }

}
