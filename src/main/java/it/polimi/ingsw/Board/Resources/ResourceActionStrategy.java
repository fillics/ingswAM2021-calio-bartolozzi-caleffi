package it.polimi.ingsw.Board.Resources;

import it.polimi.ingsw.Exceptions.DepositHasAnotherResource;
import it.polimi.ingsw.Exceptions.DepositHasReachedMaxLimit;

import java.util.zip.DataFormatException;

public interface ResourceActionStrategy{
    boolean action() throws DepositHasAnotherResource, DepositHasReachedMaxLimit;
}
