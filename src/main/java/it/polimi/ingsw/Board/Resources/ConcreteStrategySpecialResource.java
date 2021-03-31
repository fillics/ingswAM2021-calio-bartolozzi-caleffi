package it.polimi.ingsw.Board.Resources;

import it.polimi.ingsw.Player;

public class ConcreteStrategySpecialResource implements ResourceActionStrategy {
    Player player;
    int amount;

    /**
     *Class's Constructor made to define the concrete strategy
     */
    public ConcreteStrategySpecialResource(Player player, int amount) {
        this.player = player;
        this.amount = amount;
    }

    /**
     * Override method linked to the useResource method used to operate on the faith marker resource
     */
    @Override
    public boolean action() {
        player.increaseFaithMarker(amount);
        return false;
    }
}
