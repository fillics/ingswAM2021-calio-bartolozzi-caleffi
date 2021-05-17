package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.exceptions.DiscountCannotBeActivated;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

import java.util.ArrayList;

public class PacketChooseDiscount implements ClientPacketHandler {
    private final ArrayList<Integer> leaderCards;

    @JsonCreator
    public PacketChooseDiscount(@JsonProperty("LeaderCards")ArrayList<Integer> leaderCards) {
        this.leaderCards = leaderCards;
    }

    //TODO: Aggiungere l'else all'if che invia il messaggio al client che gli dà errore
    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) {
        if(gameInterface.getState().equals(GameStates.PHASE_ONE) && clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()){
            try {
                gameInterface.chooseDiscountActivation(leaderCards);
            } catch (DiscountCannotBeActivated discountCannotBeActivated) {
                discountCannotBeActivated.printStackTrace();
            }
        }
        else {
            System.out.println("I'm sorry, you can't do this action in this moment of the game");
        }
    }

    public ArrayList<Integer> getLeaderCards() {
        return leaderCards;
    }
}
