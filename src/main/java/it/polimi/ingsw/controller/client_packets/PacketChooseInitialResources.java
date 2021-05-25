package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.ClientStates;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.messages.ExceptionMessages;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.PacketConnectionMessages;
import it.polimi.ingsw.controller.server_packets.PacketExceptionMessages;
import it.polimi.ingsw.controller.server_packets.PacketWarehouse;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

import java.util.ArrayList;

public class PacketChooseInitialResources implements ClientPacketHandler{
    private final ArrayList<Integer> depositPosition;
    private final ArrayList<ResourceType> resource;


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
                    gameInterface.additionalResourceSetup(resource.get(i),depositPosition.get(i)-1,clientHandler.getUsername());
                    System.out.println("[idGame "+clientHandler.getGame().getIdGame()+"]: "+"Player "+clientHandler.getUsername() + " choose the initial resource");
                } catch (DifferentDimension differentDimension) {
                    clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.DIFFERENTDIMENSION));
                } catch (DepositHasReachedMaxLimit depositHasReachedMaxLimit) {
                    clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.DEPOSITHASREACHEDMAXLIMIT));
                } catch (DepositHasAnotherResource depositHasAnotherResource) {
                    clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.DEPOSITHASANOTHERRSOURCE));
                } catch (AnotherDepositContainsThisResource anotherDepositContainsThisResource){
                    clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.ANOTHERDEPOSITCONTAINSTHISRESOURCE));
                } catch (InvalidResource invalidResource) {
                    clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.INVALIDRESOURCE));
                }

            }

            if(clientHandler.getPosInGame() == 0){
                clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.YOUR_TURN));
            }
            clientHandler.sendPacketToClient(new PacketWarehouse(gameInterface.getUsernameClientActivePlayers().get(clientHandler.getUsername()).getBoard().getStrongbox(),gameInterface.getUsernameClientActivePlayers().get(clientHandler.getUsername()).getBoard().getDeposits()));
            gameInterface.setState(GameStates.PHASE_ONE);



            //SALVATAGGIO SU PROXY
            server.getMapForReconnection().get(clientHandler.getUsername()).setClientStates(ClientStates.GAMESTARTED);



        }
        else{
            clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLEMOVE));
        }
    }

    public ArrayList<Integer> getDepositPosition() {
        return depositPosition;
    }

    public ArrayList<ResourceType> getResource() {
        return resource;
    }
}
