package it.polimi.ingsw.controller.packets;

import it.polimi.ingsw.model.cards.leadercards.LeaderCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PacketCardsFromServer implements Serializable {

    // TODO: 26/04/2021 aggiungere id serializable
    private final String to; //persona alla quale mandare le carte
    private final int numberToChoose;
    private final List<LeaderCard> availableCards;

    //TODO aggiungere costruttori
    public PacketCardsFromServer(String to, List<LeaderCard> availableCards) {
        this.to = to;
        this.numberToChoose = 2;
        this.availableCards = new ArrayList<>(availableCards);
    }

    public String getTo() {
        return to;
    }

    public List<LeaderCard> getAvailableCards() {
        return availableCards;
    }
}
