package it.polimi.ingsw.client.liteclasses;

import
        com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.constants.Color;
import it.polimi.ingsw.constants.Printable;
import it.polimi.ingsw.model.marbles.*;

/**
 * LiteMarketTray class contains a light representation of MarketTray class of the model.
 */

public class LiteMarketTray {

    private Marble[][] table;
    private Marble remainingMarble;

    @JsonCreator
    public LiteMarketTray(@JsonProperty("table")Marble[][] table, @JsonProperty("remMarble")Marble remainingMarble) {
        this.table = table;
        this.remainingMarble = remainingMarble;
    }


    @JsonCreator
    public LiteMarketTray() {
        table=null;
        remainingMarble = null;
    }



    public Marble[][] getTable() {
        return table;
    }

    public void setTable(Marble[][] table) {
        this.table = table;
    }

    public Marble getRemainingMarble() {
        return remainingMarble;
    }

    public void setRemainingMarble(Marble remainingMarble) {
        this.remainingMarble = remainingMarble;
    }

    @Override
    public String toString(){
        StringBuilder escape= new StringBuilder();
        escape.append(String.valueOf(Printable.UNDER_LINE.print()).repeat(11)).append("\n").append(Printable.LINE.print()).append(" ").append(String.valueOf(Printable.UNDER_LINE.print()).repeat(9));
        if(this.remainingMarble instanceof YellowMarble)
            escape.append(Color.ANSI_YELLOW.escape()).append(Printable.CIRCLE.print()).append(Color.RESET).append(" ");
        if(this.remainingMarble instanceof PurpleMarble)
            escape.append(Color.ANSI_PURPLE.escape()).append(Printable.CIRCLE.print()).append(Color.RESET).append(" ");
        if(this.remainingMarble instanceof BlueMarble)
            escape.append(Color.ANSI_BLUE.escape()).append(Printable.CIRCLE.print()).append(Color.RESET).append(" ");
        if(this.remainingMarble instanceof GreyMarble)
            escape.append(Color.ANSI_GREY.escape()).append(Printable.CIRCLE.print()).append(Color.RESET).append(" ");
        if(this.remainingMarble instanceof WhiteMarble)
            escape.append(Printable.WHITE_MARBLE.print()).append(" ");
        if(this.remainingMarble instanceof RedMarble)
            escape.append(Color.ANSI_RED.escape()).append(Printable.CIRCLE.print()).append(Color.RESET).append(" ");
        escape.append("\n");

        for(int i=0; i<3;i++){
            escape.append(Printable.LINE.print()).append(" ").append(Printable.LINE.print()).append(" ");
            for(int j=0; j<4; j++){
                if(this.table[i][j] instanceof YellowMarble)
                    escape.append(Color.ANSI_YELLOW.escape()).append(Printable.CIRCLE.print()).append(Color.RESET).append(" ");
                if(this.table[i][j] instanceof PurpleMarble)
                    escape.append(Color.ANSI_PURPLE.escape()).append(Printable.CIRCLE.print()).append(Color.RESET).append(" ");
                if(this.table[i][j] instanceof BlueMarble)
                    escape.append(Color.ANSI_BLUE.escape()).append(Printable.CIRCLE.print()).append(Color.RESET).append(" ");
                if(this.table[i][j] instanceof GreyMarble)
                    escape.append(Color.ANSI_GREY.escape()).append(Printable.CIRCLE.print()).append(Color.RESET).append(" ");
                if(this.table[i][j] instanceof WhiteMarble)
                    escape.append(Printable.WHITE_MARBLE.print()).append(" ");
                if(this.table[i][j] instanceof RedMarble)
                    escape.append(Color.ANSI_RED.escape()).append(Printable.CIRCLE.print()).append(Color.RESET).append(" ");
            }
            escape.append(Printable.ARROW_RIGHT.print()).append(" ").append(i+1).append("\n");
        }
        escape.append("    ").append(Printable.ARROW_BOTTOM.print()).append(" ").append(Printable.ARROW_BOTTOM.print()).append(" ").append(Printable.ARROW_BOTTOM.print()).append(" ").append(Printable.ARROW_BOTTOM.print()).append("\n");
        escape.append("    ").append("1 2 3 4").append("\n");
        return escape.toString();
    }
    public void dump(){
        System.out.println(this);
    }
}
