package it.polimi.ingsw.client;

import it.polimi.ingsw.model.marbles.Marble;

public class LiteMarketTray {

    private Marble[][] table;

    public LiteMarketTray(Marble[][] table) {
        this.table = table;
    }

    public Marble[][] getTable() {
        return table;
    }

    public void setTable(Marble[][] table) {
        this.table = table;
    }
}
