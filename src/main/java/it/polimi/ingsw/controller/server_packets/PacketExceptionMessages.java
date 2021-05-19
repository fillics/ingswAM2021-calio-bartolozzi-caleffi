package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.ExceptionMessages;

public class PacketExceptionMessages implements ServerPacketHandler{
    ExceptionMessages message;

    @JsonCreator
    public PacketExceptionMessages(@JsonProperty("message")ExceptionMessages message) {
        this.message = message;
    }

    @Override
    public void execute(Client client) {
        Constants.printExceptionMessage(message);
    }

    public ExceptionMessages getMessage() {
        return message;
    }

}
