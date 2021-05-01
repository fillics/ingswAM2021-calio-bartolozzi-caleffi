package it.polimi.ingsw.controller.packets;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.polimi.ingsw.exceptions.NumMaxPlayersReached;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.model.marbles.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "command")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PacketUsername.class, name = "USERNAME"),
        @JsonSubTypes.Type(value = PacketBuyDevCard.class, name = "BUYDEVCARD"),
        @JsonSubTypes.Type(value = GreyMarble.class, name = "grey"),
        @JsonSubTypes.Type(value = BlueMarble.class, name = "blue"),
        @JsonSubTypes.Type(value = WhiteMarble.class, name = "white"),
        @JsonSubTypes.Type(value = RedMarble.class, name = "red") })

public interface HandlePacket {
    void execute(GameInterface gameInterface) throws NumMaxPlayersReached;
}
