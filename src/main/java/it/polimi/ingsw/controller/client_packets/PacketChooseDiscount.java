package it.polimi.ingsw.controller.client_packets;

import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.SocketConnection;

import java.util.ArrayList;

public class PacketChooseDiscount implements PacketHandler {
    private ArrayList<Integer> leaderCards;

    public PacketChooseDiscount(ArrayList<Integer> leaderCards) {
        this.leaderCards = leaderCards;
    }



    @Override
    public void execute(GameInterface gameInterface, SocketConnection socketConnection){

    }
}
