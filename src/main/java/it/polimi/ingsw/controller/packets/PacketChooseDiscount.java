package it.polimi.ingsw.controller.packets;

import it.polimi.ingsw.model.GameInterface;

import java.util.ArrayList;

public class PacketChooseDiscount implements HandlePacket {
    private ArrayList<Integer> leaderCards;

    public PacketChooseDiscount(ArrayList<Integer> leaderCards) {
        this.leaderCards = leaderCards;
    }

    @Override
    public void execute(GameInterface gameInterface) {

    }
}
