package it.polimi.ingsw.model.board.storage;

import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.exceptions.DepositDoesntHaveThisResource;
import it.polimi.ingsw.exceptions.EmptyDeposit;

public abstract class Warehouse {
    public abstract int getTotalCoins();
    public abstract int getTotalShields();
    public abstract int getTotalServants();
    public abstract int getTotalStones();

    public abstract void remove(ResourceType resourceType) throws DepositDoesntHaveThisResource, EmptyDeposit;
}
