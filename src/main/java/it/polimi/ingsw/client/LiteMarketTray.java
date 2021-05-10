package it.polimi.ingsw.client;

import it.polimi.ingsw.model.marbles.Marble;

public class LiteMarketTray {

    private Marble[][] table= new Marble[3][4];

    public Marble[][] getTable() {
        return table;
    }

    public void setTable(Marble[][] table) {
        this.table = table;
    }
}
