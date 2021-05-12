package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.State;
import it.polimi.ingsw.exceptions.LeaderCardNotActivated;
import it.polimi.ingsw.exceptions.LeaderCardNotFound;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

import java.util.ArrayList;

public class PacketTakeResourceFromMarket implements ClientPacketHandler {
    private String line;
    private int numline;
    private ArrayList<Integer> leaderCardsID;

    @JsonCreator
    public PacketTakeResourceFromMarket(@JsonProperty("Line")String line,@JsonProperty("NumLine") int numline,@JsonProperty("LeaderCardsID") ArrayList<Integer> leaderCardsID) {
        this.line = line;
        this.numline = numline;
        this.leaderCardsID = leaderCardsID;
    }


    //TODO: Decidere se aggiungere l'else all'if che invia il messaggio al client che gli dà errore
    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) throws LeaderCardNotActivated, LeaderCardNotFound {
        if(gameInterface.getState() == State.PHASE_ONE && clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()){
            gameInterface.takeResourceFromMarket(line, numline, leaderCardsID);
            gameInterface.setState(State.PHASE_TWO);

        }
    }

    public String getLine() {
        return line;
    }

    public int getNumline() {
        return numline;
    }

    public ArrayList<Integer> getLeaderCardsID() {
        return leaderCardsID;
    }
}
