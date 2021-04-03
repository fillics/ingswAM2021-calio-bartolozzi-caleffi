package it.polimi.ingsw.Board.Resources;

import it.polimi.ingsw.Exceptions.DepositHasAnotherResource;
import it.polimi.ingsw.Exceptions.DepositHasReachedMaxLimit;

public interface ResourceActionStrategy{
    void action() throws DepositHasAnotherResource, DepositHasReachedMaxLimit;
}
