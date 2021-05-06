package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.exceptions.DiscountCannotBeActivated;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.client.SocketClientConnected;

import java.net.Socket;
import java.util.ArrayList;

public class PacketChooseDiscount implements PacketHandler {
    private ArrayList<Integer> leaderCards;
    @JsonCreator
    public PacketChooseDiscount(@JsonProperty("Leader cards: ")ArrayList<Integer> leaderCards) {
        this.leaderCards = leaderCards;
    }



    @Override
    public void execute(GameInterface gameInterface, Socket socketClientConnected) throws DiscountCannotBeActivated {
        gameInterface.chooseDiscountActivation(leaderCards);
    }
}
