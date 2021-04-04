package it.polimi.ingsw.Marbles;

import java.util.ArrayList;

public class MarketTray {
    private ArrayList<Marble> table;

    public MarketTray(){
        table=new ArrayList<>();
    }

    public Marble getRemainingMarble(){
        return table.get(12);
    }

    /**
     * This method is used to modify the table when a row or column is chosen
     */
    public void change(){

    }
}
