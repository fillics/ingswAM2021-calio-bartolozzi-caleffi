package it.polimi.ingsw.Board.Resources;

import it.polimi.ingsw.Exceptions.DepositHasAnotherResource;
import it.polimi.ingsw.Exceptions.DepositHasReachedMaxLimit;

/**
 * Common interface for all resources' strategies.
 */
public interface ResourceActionStrategy{
    /**
     * The action method is a common method for the resources' strategies that use the resource linked to the strategy.
     * The action may vary according to the type of strategy.
     * @throws DepositHasAnotherResource exception thrown when the deposit chosen to place the resource has already another type of resource in it.
     * @throws DepositHasReachedMaxLimit exception thrown when the deposit is full.
     */
    void action() throws DepositHasAnotherResource, DepositHasReachedMaxLimit;
}
