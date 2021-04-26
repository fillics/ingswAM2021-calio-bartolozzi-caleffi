package it.polimi.ingsw.model.marbles;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.polimi.ingsw.model.Player;

/**
 * Represents a generic marble that can be taken from the market tray
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = YellowMarble.class, name = "yellow"),
        @JsonSubTypes.Type(value = PurpleMarble.class, name = "purple"),
        @JsonSubTypes.Type(value = GreyMarble.class, name = "grey"),
        @JsonSubTypes.Type(value = BlueMarble.class, name = "blue"),
        @JsonSubTypes.Type(value = WhiteMarble.class, name = "white"),
        @JsonSubTypes.Type(value = RedMarble.class, name = "red") })

public abstract class Marble {
    /**
     * Method transform is used to transform every single marble in its corresponding resource
     * and to fill the resourceBuffer of Player
     */
    public abstract void transform(Player player);
}
