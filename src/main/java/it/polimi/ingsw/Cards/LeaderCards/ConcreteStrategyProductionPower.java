package it.polimi.ingsw.Cards.LeaderCards;

import it.polimi.ingsw.Board.Board;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.DevelopmentCards.ProductionPower;
import java.util.HashMap;

/**
 * This class represents the production power strategy for Leader Cards.
 */

public class ConcreteStrategyProductionPower implements LeaderCardStrategy{
    private HashMap<ResourceType,Integer> resourceNeeded;
    private HashMap<ResourceType,Integer> resourcesObtained;
    private ProductionPower extraProductionPower;
    private Board board;

    /**
     * Constructor ConcreteStrategyProductionPower creates a new ConcreteStrategyProductionPower instance.
     */
    public ConcreteStrategyProductionPower(HashMap<ResourceType, Integer> resourceNeeded, Board board) {
        this.resourceNeeded = resourceNeeded;
        this.board= board;
        resourcesObtained= new HashMap<>();
        resourcesObtained.put(ResourceType.FAITHMARKER,1);
    }

    @Override
    public void ability(){
        extraProductionPower= new ProductionPower(resourceNeeded,resourcesObtained);
        board.getBoardProdPower().add(extraProductionPower);
    }

}
