package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.PacketLiteMarketTray;
import it.polimi.ingsw.exceptions.LeaderCardNotActivated;
import it.polimi.ingsw.exceptions.LeaderCardNotFound;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

import java.util.ArrayList;

public class PacketTakeResourceFromMarket implements ClientPacketHandler {
    private final String line;
    private final int numline;
    private final ArrayList<Integer> leaderCardsID;

    @JsonCreator
    public PacketTakeResourceFromMarket(@JsonProperty("Line")String line,@JsonProperty("NumLine") int numline,@JsonProperty("LeaderCardsID") ArrayList<Integer> leaderCardsID) {
        this.line = line;
        this.numline = numline;
        this.leaderCardsID = leaderCardsID;
    }


    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) {
        if(gameInterface.getState().equals(GameStates.PHASE_ONE) && clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()){
            try {
                gameInterface.takeResourceFromMarket(line, numline, leaderCardsID);
            } catch (LeaderCardNotFound leaderCardNotFound) {
                leaderCardNotFound.printStackTrace();
            } catch (LeaderCardNotActivated leaderCardNotActivated) {
                leaderCardNotActivated.printStackTrace();
            }
            gameInterface.setState(GameStates.PHASE_TWO);

            server.sendAll(new PacketLiteMarketTray(gameInterface.getTable()), gameInterface);

        }
        else {
            System.out.println("I'm sorry, you can't do this action in this moment of the game");
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
