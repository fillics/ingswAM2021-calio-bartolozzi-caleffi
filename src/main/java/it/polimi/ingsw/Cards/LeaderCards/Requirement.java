package it.polimi.ingsw.Cards.LeaderCards;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.polimi.ingsw.Board.Board;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.DevelopmentCards.*;
import it.polimi.ingsw.Marbles.*;

import java.util.HashMap;

/**
 * This class represents the Requirements needed in order to activate a Leader Card.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "requirementType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ResourcesRequirements.class, name = "resources"),
        @JsonSubTypes.Type(value = LevelAndColorRequirements.class, name = "levelcolor"),
        @JsonSubTypes.Type(value = NumAndColorRequirements.class, name = "numcolor") })

public abstract class Requirement {

    public abstract boolean checkRequirements(Board board);

}
