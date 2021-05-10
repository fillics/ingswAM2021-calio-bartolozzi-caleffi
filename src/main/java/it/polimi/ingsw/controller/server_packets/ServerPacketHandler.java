package it.polimi.ingsw.controller.server_packets;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.polimi.ingsw.controller.client_packets.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "command")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PacketLiteMarketTray.class, name = "LITE_MARKET_TRAY"),
        @JsonSubTypes.Type(value = PacketLiteDevelopmentGrid.class, name = "LITE_DEV_GRID"),
        @JsonSubTypes.Type(value = PacketLiteBoard.class, name = "LITE_BOARD"),
        @JsonSubTypes.Type(value = PacketLitePlayer.class, name = "LITE_PLAYER")
        })

public interface ServerPacketHandler {
}
