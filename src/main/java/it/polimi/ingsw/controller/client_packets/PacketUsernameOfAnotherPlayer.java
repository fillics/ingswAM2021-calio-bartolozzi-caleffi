package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.messages.ExceptionMessages;
import it.polimi.ingsw.controller.server_packets.PacketBoardOfAnotherPlayer;
import it.polimi.ingsw.controller.server_packets.PacketExceptionMessages;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.gameinterfaces.GameInterface;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

import java.io.IOException;
import java.util.ArrayList;

public class PacketUsernameOfAnotherPlayer implements ClientPacketHandler{

    private final String username;


    @JsonCreator
    public PacketUsernameOfAnotherPlayer(@JsonProperty("username")String username) {
        this.username = username;
    }


    public String getUsername() {
        return username;
    }

    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) throws EmptyDeposit,
            DepositHasReachedMaxLimit, DepositHasAnotherResource, LeaderCardNotActivated, LeaderCardNotFound,
            DiscountCannotBeActivated, DevelopmentCardNotFound, DepositDoesntHaveThisResource, DevCardNotPlaceable,
            DifferentDimension, NotEnoughResources, WrongChosenResources, NotEnoughRequirements, TooManyResourcesRequested,
            IOException, ClassNotFoundException {

        if((gameInterface.getState().equals(GameStates.PHASE_ONE) || gameInterface.getState().equals(GameStates.PHASE_TWO))){
            if(!gameInterface.getUsernameClientActivePlayers().containsKey(username))
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.USERNAMENOTEXISTING));
            else{
                ArrayList<LeaderCard> leaderCardsActivated= new ArrayList<>();
                ArrayList<LeaderCard> leaderCardsPlayer = gameInterface.getUsernameClientActivePlayers().get(username).getLeaderCards();

                for (LeaderCard leaderCard: leaderCardsPlayer){
                    if(leaderCard.getStrategy().isActive()) leaderCardsActivated.add(leaderCard);
                }

                Board board = gameInterface.getUsernameClientActivePlayers().get(username).getBoard();
                clientHandler.sendPacketToClient(new PacketBoardOfAnotherPlayer(board.getFaithMarker(),
                        board.getTrack(),board.getVaticanReportSections(),leaderCardsActivated,
                        board.getStrongbox(), board.getDeposits(), board.getDevelopmentSpaces()));
            }
        }
    }
}
