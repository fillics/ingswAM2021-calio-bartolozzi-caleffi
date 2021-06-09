package it.polimi.ingsw.controller.server_packets;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.polimi.ingsw.client.Client;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "command")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PacketLiteMarketTray.class, name = "LITE_MARKET_TRAY"),
        @JsonSubTypes.Type(value = PacketFaithTrack.class, name = "FAITH_TRACK"),
        @JsonSubTypes.Type(value = PacketBoardOfAnotherPlayer.class, name = "BOARD_ANOTHER_PLAYER"),
        @JsonSubTypes.Type(value = PacketBlackCross.class, name = "BLACK_CROSS"),
        @JsonSubTypes.Type(value = PacketToken.class, name = "TOKEN"),
        @JsonSubTypes.Type(value = PacketConnectionMessages.class, name = "MESSAGE"),
        @JsonSubTypes.Type(value = PacketPingFromServer.class, name = "PING"),
        @JsonSubTypes.Type(value = PacketReconnection.class, name = "RECONNECTION"),
        @JsonSubTypes.Type(value = PacketNewPositionInGame.class, name = "NEW_POS"),
        @JsonSubTypes.Type(value = PacketLiteDevelopmentGrid.class, name = "LITE_DEV_GRID"),
        @JsonSubTypes.Type(value = PacketWarehouse.class, name = "WAREHOUSES"),
        @JsonSubTypes.Type(value = PacketLitePlayer.class, name = "LITE_PLAYER"),
        @JsonSubTypes.Type(value = PacketDevelopmentSpaces.class, name = "DEVELOPMENT_SPACES"),
        @JsonSubTypes.Type(value = PacketLeaderCards.class, name = "LEADER_CARDS"),
        @JsonSubTypes.Type(value = PacketResourceBuffer.class, name = "RESOURCE_BUFFER"),
        @JsonSubTypes.Type(value = PacketSetup.class, name = "SETUP"),
        @JsonSubTypes.Type(value = PacketSpecialProdPowers.class, name = "SPECIAL_PROD_POWERS"),
        @JsonSubTypes.Type(value = PacketWhiteMarbleChoice.class, name = "WHITE_MARBLE_CHOICE"),
        @JsonSubTypes.Type(value = PacketEndGameStarted.class, name = "ENDGAME_STARTED"),
        @JsonSubTypes.Type(value = PacketWinner.class, name = "WINNER"),
        @JsonSubTypes.Type(value = PacketExceptionMessages.class, name = "EXCEPTIONS")

})

public interface ServerPacketHandler {
        void execute(Client client);
}
