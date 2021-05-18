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

import java.util.ArrayList;

public class PacketChooseInitialResources implements ClientPacketHandler{
    private ArrayList<Integer> depositPosition;
    private ArrayList<ResourceType> resource;


    @JsonCreator
    public PacketChooseInitialResources(@JsonProperty("DepositPositions") ArrayList<Integer> depositPosition, @JsonProperty("Resources") ArrayList<ResourceType> resource) {
        this.depositPosition = depositPosition;
        this.resource = resource;
    }

    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) {
        if(gameInterface.getState().equals(GameStates.SETUP) || gameInterface.getState().equals(GameStates.PHASE_ONE)){

            for (int i = 0; i < depositPosition.size(); i++) {
                try {
                    gameInterface.additionalResourceSetup(resource.get(i),depositPosition.get(i)-1,clientHandler.getIdClient());
                } catch (DifferentDimension | DepositHasReachedMaxLimit | DepositHasAnotherResource differentDimension) {
                    differentDimension.printStackTrace();
                }
            }


            if(clientHandler.getPosInGame() == 0){
                clientHandler.sendPacketToClient(new PacketMessage(ConnectionMessages.YOUR_TURN));
            }
            clientHandler.sendPacketToClient(new PacketWarehouse(gameInterface.getIdClientActivePlayers().get(clientHandler.getIdClient()).getBoard().getStrongbox(),gameInterface.getIdClientActivePlayers().get(clientHandler.getIdClient()).getBoard().getDeposits()));
            gameInterface.setState(GameStates.PHASE_ONE);
        }
    }

    public ArrayList<Integer> getDepositPosition() {
        return depositPosition;
    }

    public ArrayList<ResourceType> getResource() {
        return resource;
    }
}
