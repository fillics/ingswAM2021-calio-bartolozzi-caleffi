package it.polimi.ingsw.controller.packets;

import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.model.GameInterface;

import java.util.ArrayList;

public class PacketTakeResourceFromMarket implements PacketHandler {
    private String line;
    private int numline;
    private ArrayList<Integer> leaderCardsID;

    public PacketTakeResourceFromMarket(String line, int numline, ArrayList<Integer> leaderCardsID) {
        this.line = line;
        this.numline = numline;
        this.leaderCardsID = leaderCardsID;
    }

    @Override
    public void execute(GameInterface gameInterface) {

    }
}
