package it.polimi.ingsw.Board.Storage;

import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Exceptions.DepositDoesntHaveThisResource;
import it.polimi.ingsw.Exceptions.EmptyDeposit;

public abstract class Warehouse {
    public abstract int getTotalCoins();
    public abstract int getTotalShields();
    public abstract int getTotalServants();
    public abstract int getTotalStones();

    public abstract void remove(ResourceType resourceType) throws DepositDoesntHaveThisResource, EmptyDeposit;
}
