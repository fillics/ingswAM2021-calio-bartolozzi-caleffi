package it.polimi.ingsw.Cards.LeaderCards;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.polimi.ingsw.Board.Board;

/**
 * This abstract class represents the Requirements needed in order to activate a Leader Card.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "requirementType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ResourcesRequirement.class, name = "resources"),
        @JsonSubTypes.Type(value = LevelAndColorRequirement.class, name = "levelcolor"),
        @JsonSubTypes.Type(value = NumAndColorRequirement.class, name = "numcolor") })

public abstract class Requirement {

    /**
     * Check if the requirement of the leader card is fulfilled
     * @param board is the board of the player where the check will be done .
     * @return true if the requirement is fulfilled, false if not.
     */
    public abstract boolean checkRequirement(Board board);

}
