package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.controller.State;
import it.polimi.ingsw.exceptions.DiscountCannotBeActivated;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import java.util.ArrayList;

public class PacketChooseDiscount implements PacketHandler {
    private ArrayList<Integer> leaderCards;
    @JsonCreator
    public PacketChooseDiscount(@JsonProperty("Leader cards: ")ArrayList<Integer> leaderCards) {
        this.leaderCards = leaderCards;
    }


    //TODO: Decidere se aggiungere l'else all'if che invia il messaggio al client che gli dà errore
    @Override
    public void execute(GameInterface gameInterface, ClientHandler clientHandler) throws DiscountCannotBeActivated {
        if(gameInterface.getState() == State.PHASE_ONE && clientHandler.getIdClient() == gameInterface.getCurrentPlayer()){
            gameInterface.chooseDiscountActivation(leaderCards);
        }
    }
}
