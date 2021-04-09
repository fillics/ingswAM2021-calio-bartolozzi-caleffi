package it.polimi.ingsw.Cards.LeaderCards;

import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Player;

/**
 * This class represents the white marble strategy for Leader Cards.
 */

public class ConcreteStrategyMarble implements LeaderCardStrategy{
    private ResourceType resourceType;
    private Player player;


    /**
     * Constructor ConcreteStrategyMarble creates a new ConcreteStrategyMarble instance.
     */
    public ConcreteStrategyMarble(ResourceType resourceType, Player player) {
        this.player = player;
        this.resourceType = resourceType;
    }

    @Override
    public void ability() {
        System.out.println("I'm a white marble leader card");
    }
}
