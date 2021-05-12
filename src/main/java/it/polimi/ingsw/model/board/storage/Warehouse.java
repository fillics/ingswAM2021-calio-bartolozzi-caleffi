package it.polimi.ingsw.model.board.storage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.exceptions.DepositDoesntHaveThisResource;
import it.polimi.ingsw.exceptions.EmptyDeposit;

public abstract class Warehouse {
    @JsonIgnore
    public abstract int getTotalCoins();
    @JsonIgnore
    public abstract int getTotalShields();
    @JsonIgnore
    public abstract int getTotalServants();
    @JsonIgnore
    public abstract int getTotalStones();

    public abstract void remove(ResourceType resourceType) throws DepositDoesntHaveThisResource, EmptyDeposit;
}
