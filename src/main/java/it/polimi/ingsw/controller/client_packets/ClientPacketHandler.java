package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.polimi.ingsw.controller.Packet;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.gameinterfaces.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

import java.io.IOException;

/**
 * ClientPacketHandler interface defines an interface for Packets from Client to Server.
 */

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "command")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PacketEndTurn.class, name = "ENDTURN"),
        @JsonSubTypes.Type(value = PacketBuyDevCard.class, name = "BUYDEVCARD"),
        @JsonSubTypes.Type(value = PacketDiscardLeaderCard.class, name = "DISCARDLEADERCARD"),
        @JsonSubTypes.Type(value = PacketActivateLeaderCard.class, name = "ACTIVATELEADERCARD"),
        @JsonSubTypes.Type(value = PacketChooseLeaderCardToRemove.class, name = "CHOOSELEADERCARDTOREMOVE"),
        @JsonSubTypes.Type(value = PacketChooseDiscount.class, name = "CHOOSEDISCOUNT"),
        @JsonSubTypes.Type(value = PacketChooseInitialResources.class, name = "CHOOSE_INITIAL_RESOURCE"),
        @JsonSubTypes.Type(value = PacketPlaceResource.class, name = "PLACERESOURCE"),
        @JsonSubTypes.Type(value = PacketTakeResourceFromMarket.class, name = "TAKERESOURCE"),
        @JsonSubTypes.Type(value = PacketUseAndChooseProdPower.class, name = "USEANDCHOOSEPRODUCTIONPOWER"),
        @JsonSubTypes.Type(value = PacketUsernameOfAnotherPlayer.class, name = "ANOTHER_PLAYER"),
        @JsonSubTypes.Type(value = PacketEndConnection.class, name = "END_CONNECTION"),
        @JsonSubTypes.Type(value = PacketMoveResource.class, name = "MOVERESOURCE") })

public interface ClientPacketHandler extends Packet {

    /**
     * Method execute() is called after the deserialization of the packet in ClientHandler.
     * It calls methods that modify the model throw gameInterface parameter.
     * It calls methods throw clientHandler parameter that send packets of update from server to client.
     */
    void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) throws EmptyDeposit, DepositHasReachedMaxLimit,
            DepositHasAnotherResource, LeaderCardNotActivated, LeaderCardNotFound, DiscountCannotBeActivated, DevelopmentCardNotFound,
            DepositDoesntHaveThisResource, DevCardNotPlaceable, DifferentDimension, NotEnoughResources, WrongChosenResources,
            NotEnoughRequirements, TooManyResourcesRequested, IOException, ClassNotFoundException;
}
