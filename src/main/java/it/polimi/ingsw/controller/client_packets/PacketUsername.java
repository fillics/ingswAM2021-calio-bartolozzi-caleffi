package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

public class PacketUsername implements PacketHandler {
    private String username;
    @JsonCreator
    public PacketUsername(@JsonProperty("username")String username) {
        this.username = username;
    }


    public String getUsername() {
        return username;
    }

    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) throws EmptyDeposit, DepositHasReachedMaxLimit,
            DepositHasAnotherResource, LeaderCardNotActivated, LeaderCardNotFound, DiscountCannotBeActivated,
            DevelopmentCardNotFound, DepositDoesntHaveThisResource, DevCardNotPlaceable, DifferentDimension, NotEnoughResources,
            WrongChosenResources, NotEnoughRequirements, TooManyResourcesRequested {

        server.addToLobby(clientHandler);

        server.insertUsernameIntoMap(username, clientHandler);
        /*if(gameInterface.getState() == State.FILL_LOBBY){
            gameInterface.createNewPlayer(username);
            if(gameInterface.getNumof_players() == gameInterface.getActivePlayers().size()){
                gameInterface.setState(State.NUMOF_PLAYERS);
            }
        }*/
    }
}
