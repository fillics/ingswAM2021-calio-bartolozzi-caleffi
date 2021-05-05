package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.exceptions.LeaderCardNotActivated;
import it.polimi.ingsw.exceptions.LeaderCardNotFound;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.SocketClientConnected;

import java.util.ArrayList;

public class PacketTakeResourceFromMarket implements PacketHandler {
    private String line;
    private int numline;
    private ArrayList<Integer> leaderCardsID;

    @JsonCreator
    public PacketTakeResourceFromMarket(@JsonProperty("line: ")String line,@JsonProperty("number of line: ") int numline,@JsonProperty("Leader Cards ID: ") ArrayList<Integer> leaderCardsID) {
        this.line = line;
        this.numline = numline;
        this.leaderCardsID = leaderCardsID;
    }



    @Override
    public void execute(GameInterface gameInterface, SocketClientConnected socketClientConnected) throws LeaderCardNotActivated, LeaderCardNotFound {
        gameInterface.takeResourceFromMarket(line,numline,leaderCardsID);
    }
}
