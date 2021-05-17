package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.PacketMessage;
import it.polimi.ingsw.controller.server_packets.PacketWarehouse;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

public class PacketChooseInitialResources implements ClientPacketHandler{
    private int depositPosition;
    private ResourceType resource;

    @JsonCreator
    public PacketChooseInitialResources(@JsonProperty("DepositPosition")int depositPosition, @JsonProperty("Resource") ResourceType resource) {
        this.depositPosition = depositPosition;
        this.resource = resource;
    }

    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) {
        System.out.println("sono dentro al pacchetto chooseinitialresouces");
        if(gameInterface.getState().equals(GameStates.SETUP) || gameInterface.getState().equals(GameStates.PHASE_ONE)){

            try {
                gameInterface.additionalResourceSetup(resource,depositPosition,clientHandler.getIdClient());
            } catch (DifferentDimension | DepositHasReachedMaxLimit differentDimension) {
                differentDimension.printStackTrace();
            } catch (DepositHasAnotherResource depositHasAnotherResource) {
                depositHasAnotherResource.printStackTrace();
            }
            if(clientHandler.getPosInGame() == 0){
                clientHandler.sendPacketToClient(new PacketMessage(ConnectionMessages.YOUR_TURN));
            }
            clientHandler.sendPacketToClient(new PacketWarehouse(gameInterface.getIdClientActivePlayers().get(clientHandler.getIdClient()).getBoard().getStrongbox(),gameInterface.getIdClientActivePlayers().get(clientHandler.getIdClient()).getBoard().getDeposits()));
            gameInterface.setState(GameStates.PHASE_ONE);
        }
    }

    public int getDepositPosition() {
        return depositPosition;
    }

    public ResourceType getResource() {
        return resource;
    }
}
