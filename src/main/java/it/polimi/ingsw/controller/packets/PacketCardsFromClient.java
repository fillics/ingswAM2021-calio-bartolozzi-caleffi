package it.polimi.ingsw.controller.packets;

import java.io.Serializable;
import java.util.List;

public class PacketCardsFromClient implements Serializable {

    private List<Integer> chosenCards; // il client riceve 4 carte complete e lui dovrà rispondere con gli id

    public PacketCardsFromClient(List<Integer> chosenCards) {
        this.chosenCards = chosenCards;
    }

    public List<Integer> getChosenCards() {
        return chosenCards;
    }
}

/*

choose the leader cards
[LeaderCard1: productionPower] [LeaderCard2: discount, id: 3]

id2, id3


 */