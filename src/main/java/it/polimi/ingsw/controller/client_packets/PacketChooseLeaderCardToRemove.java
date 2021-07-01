package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.ClientStates;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.messages.ExceptionMessages;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.PacketConnectionMessages;
import it.polimi.ingsw.controller.server_packets.PacketExceptionMessages;
import it.polimi.ingsw.controller.server_packets.PacketLeaderCards;
import it.polimi.ingsw.exceptions.LeaderCardNotFound;
import it.polimi.ingsw.model.gameinterfaces.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;


public class PacketChooseLeaderCardToRemove implements ClientPacketHandler {
    private final int Id1;
    private final int Id2;

    /**
     * Class' constructor.
     * @param Id1 is the id of the first leader card that the player wants to remove.
     * @param Id2 is the id of the second leader card that the player wants to remove.
     */
    @JsonCreator
    public PacketChooseLeaderCardToRemove(@JsonProperty("id1")int Id1,@JsonProperty("id2") int Id2) {
        this.Id1 = Id1;
        this.Id2 = Id2;
    }

    /**
     * Method execute() calls chooseLeaderCardToRemove method from GameInterface that modifies the model.
     * It also sends packets from server to client in order to update the light model after the changes of the model.
     */
    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler){

        if(gameInterface.getState().equals(GameStates.SETUP) || gameInterface.getState().equals(GameStates.PHASE_ONE) || gameInterface.getState().equals(GameStates.PHASE_TWO)){
            if(gameInterface.getState().equals(GameStates.SETUP)) gameInterface.setState(GameStates.PHASE_ONE);
            try {
                gameInterface.chooseLeaderCardToRemove(Id1, Id2, clientHandler.getUsername());
                System.out.println("[idGame "+clientHandler.getGame().getIdGame()+"]: "+"Player "+clientHandler.getUsername() + " removed the two initial leader cards");
                clientHandler.sendPacketToClient(new PacketLeaderCards(gameInterface.getUsernameClientActivePlayers().get(clientHandler.getUsername()).getLeaderCards()));


            } catch (LeaderCardNotFound leaderCardNotFound) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.LEADERCARDNOTFOUND));
            }

            server.getMapForReconnection().get(clientHandler.getUsername()).setClientStates(ClientStates.RESOURCESETUP);

            server.getMapForReconnection().get(clientHandler.getUsername()).getClientModelView().getMyPlayer().
                    setLeaderCards(gameInterface.getUsernameClientActivePlayers().get(clientHandler.getUsername()).getLeaderCards());
        }
        else{
            clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLEMOVE));
        }
    }

    public int getId1() {
        return Id1;
    }

    public int getId2() {
        return Id2;
    }
}
