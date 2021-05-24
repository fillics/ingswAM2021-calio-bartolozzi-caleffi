package it.polimi.ingsw.model.board.resources;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.polimi.ingsw.exceptions.AnotherDepositContainsThisResource;
import it.polimi.ingsw.exceptions.DepositHasAnotherResource;
import it.polimi.ingsw.exceptions.DepositHasReachedMaxLimit;
import it.polimi.ingsw.exceptions.InvalidResource;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ConcreteStrategySpecialResource.class, name = "normaleresource"),
        @JsonSubTypes.Type(value = ConcreteStrategyResource.class, name = "specialresource")})

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
    void action() throws DepositHasAnotherResource, DepositHasReachedMaxLimit, AnotherDepositContainsThisResource, InvalidResource;
}
