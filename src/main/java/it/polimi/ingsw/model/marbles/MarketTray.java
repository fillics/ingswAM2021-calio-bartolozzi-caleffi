package it.polimi.ingsw.model.marbles;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Color;
import it.polimi.ingsw.constants.Printable;
import it.polimi.ingsw.model.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Represents the market tray
 */

public class MarketTray {
    private final Marble[][] table= new Marble[3][4];
    private Marble remainingMarble;
    private ArrayList<Marble> market;

    /**
     * Constructor MarketTray fills the matrix "table" and remainingMarble with the marbles of the shuffled market.
     * Attribute market is only used at the beginning of the game to shuffle the marbles.
     */
    public MarketTray() {
        int i,j,k;
        k=1;
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        try {
            market = mapper.readValue(new File("src/main/resources/json/Marble.json"), new TypeReference<>() {
            });
            Collections.shuffle(market);
            for(i=0;i<3;i++){
                for(j=0; j<4;j++,k++){
                    table[i][j]= market.get(k);
                }
            }
            remainingMarble= market.get(0);
        } catch (IOException e) {
            e.printStackTrace(); //aggiungere frase di errore
            System.out.println("I'm sorry the file is not found");
        }
    }

    public ArrayList<Marble> getMarket() {
        return market;
    }

    /**
     * Method getRemainingMarble returns the marble that is in the slide of the Market.
     */
    public Marble getRemainingMarble() {
        return remainingMarble;
    }

    /**
     * Method getTable returns the actual market table.
     */
    public Marble[][] getTable() {
        return table;
    }

    /**
     * Method lineSelection calls transform() on every marble of the line selected.
     */
    public void lineSelection(String line, int numline, Player player){
        int i,j;
        if(line.equals("row")){
            for(i=numline-1,j=0;j<4;j++){
                table[i][j].transform(player);
            }
        } else if(line.equals("column")){
            for(i=0,j=numline-1;i<3;i++){
                table[i][j].transform(player);
            }
        }
    }

    /**
     * Method change is used to modify the market when a row or column is chosen
     */
    public void change(String line, int numline){
        Marble temp;
        int i,j;
        if(line.equals("row")){
            temp= remainingMarble;
            remainingMarble= table[numline-1][0];
            for(j=0; j<3;j++){
                table[numline-1][j]=table[numline-1][j+1];
            }
            table[numline-1][3]=temp;
        }else if(line.equals("column")){
            temp= remainingMarble;
            remainingMarble= table[0][numline-1];
            for(i=0;i<2;i++){
                table[i][numline-1]=table[i+1][numline-1];
            }
            table[2][numline-1]=temp;
        }
    }

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
            escape.append(Printable.ARROW_RIGHT.print()).append("\n");
        }
        escape.append("    ").append(Printable.ARROW_BOTTOM.print()).append(" ").append(Printable.ARROW_BOTTOM.print()).append(" ").append(Printable.ARROW_BOTTOM.print()).append(" ").append(Printable.ARROW_BOTTOM.print()).append(" ");
        return escape.toString();
    }
}
