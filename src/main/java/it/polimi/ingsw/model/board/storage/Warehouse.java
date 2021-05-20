package it.polimi.ingsw.model.board.storage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.exceptions.DepositDoesntHaveThisResource;
import it.polimi.ingsw.exceptions.EmptyDeposit;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Strongbox.class, name = "strongbox"),
        @JsonSubTypes.Type(value = Deposit.class, name = "deposit")})

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
