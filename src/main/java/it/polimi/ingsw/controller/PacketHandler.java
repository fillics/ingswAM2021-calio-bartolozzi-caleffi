package it.polimi.ingsw.controller;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.polimi.ingsw.controller.packets.*;
import it.polimi.ingsw.exceptions.NumMaxPlayersReached;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.SocketConnection;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "command")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PacketUsername.class, name = "USERNAME"),
        @JsonSubTypes.Type(value = PacketBuyDevCard.class, name = "BUYDEVCARD"),
        @JsonSubTypes.Type(value = PacketDiscardLeaderCard.class, name = "DISCARDLEADERCARD"),
        @JsonSubTypes.Type(value = PacketActivateLeaderCard.class, name = "ACTIVATELEADERCARD"),
        @JsonSubTypes.Type(value = PacketChooseLeaderCardToRemove.class, name = "CHOOSELEADERCARD"),
        @JsonSubTypes.Type(value = PacketPlaceResource.class, name = "PLACERESOURCE"),
        @JsonSubTypes.Type(value = PacketTakeResourceFromMarket.class, name = "TAKERESOURCE"),
        @JsonSubTypes.Type(value = PacketUseAndChooseProdPower.class, name = "USEANDCHOOSEPRODUCTIONPOWER"),
        @JsonSubTypes.Type(value = PacketNumPlayers.class, name = "NUMOFPLAYERS"),

        @JsonSubTypes.Type(value = PacketMoveResource.class, name = "MOVERESOURCE") })

public interface PacketHandler {
    void execute(GameInterface gameInterface, SocketConnection socketConnection) throws NumMaxPlayersReached;
}
