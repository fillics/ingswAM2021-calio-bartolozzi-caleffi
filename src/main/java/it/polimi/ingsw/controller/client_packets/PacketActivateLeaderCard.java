package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.messages.ExceptionMessages;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.*;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.gameinterfaces.GameInterface;
import it.polimi.ingsw.model.cards.leadercards.ConcreteStrategyProductionPower;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

public class PacketActivateLeaderCard implements ClientPacketHandler {
    private final int id;

    @JsonCreator
    public PacketActivateLeaderCard(@JsonProperty("Id")int id) {
        this.id = id;
    }

    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) {
        if((gameInterface.getState().equals(GameStates.PHASE_ONE) || gameInterface.getState().equals(GameStates.PHASE_TWO))
                && clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()){
            try {
                gameInterface.activateLeaderCard(id);
                clientHandler.sendPacketToClient(new PacketWarehouse(gameInterface.getUsernameClientActivePlayers().get(clientHandler.getUsername()).getBoard().getStrongbox(),gameInterface.getUsernameClientActivePlayers().get(clientHandler.getUsername()).getBoard().getDeposits()));
                clientHandler.sendPacketToClient(new PacketDevelopmentSpaces(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getDevelopmentSpaces()));
                clientHandler.sendPacketToClient(new PacketLeaderCards(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getLeaderCards()));
                for(int i=0; i<gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getLeaderCards().size(); i++){
                    if(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getLeaderCards().get(i).getId()==id && gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getLeaderCards().get(i).getStrategy() instanceof ConcreteStrategyProductionPower) {
                        clientHandler.sendPacketToClient(new PacketSpecialProdPowers(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getSpecialProductionPowers()));
                    }
                }
            } catch (LeaderCardNotFound leaderCardNotFound) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.LEADERCARDNOTFOUND));
            } catch (NotEnoughRequirements notEnoughRequirements) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.NOTENOUGHREQUIREMENTS));
            }

        }
        else {
            clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLEMOVE));
        }
    }

    public int getId() {
        return id;
    }
}

