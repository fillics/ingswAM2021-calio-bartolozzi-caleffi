package it.polimi.ingsw.Board.Resources;

import it.polimi.ingsw.Player;

public class ConcreteStrategySpecialResource implements ResourceActionStrategy {
    Player player;
    /**
     *Class's Constructor made to define the concrete strategy
     */
    public ConcreteStrategySpecialResource(Player player) {
        this.player = player;
    }

    /**
     * Override method linked to the useResource method used to operate on the faith marker resource
     */
    @Override
    public boolean action() {
        player.increaseFaithMarker();
        return false;
    }
}
