package it.polimi.ingsw.Board.Resources;

import it.polimi.ingsw.Exceptions.DepositHasAnotherResource;
import it.polimi.ingsw.Exceptions.DepositHasReachedMaxLimit;

/**
 * Common interface for all resources' strategies.
 */
public interface ResourceActionStrategy{
    /**
     *
     * @throws DepositHasAnotherResource
     * @throws DepositHasReachedMaxLimit
     */
    // TODO: 23/04/2021 JAVADOC
    void action() throws DepositHasAnotherResource, DepositHasReachedMaxLimit;
}
