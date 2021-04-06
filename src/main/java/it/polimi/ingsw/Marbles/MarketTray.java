package it.polimi.ingsw.Marbles;
import it.polimi.ingsw.Game;

/**
 * Represents the market tray
 */

public class MarketTray {
    private Marble[][] table= new Marble[3][4];
    private Marble remainingMarble;

    /**
     * Constructor MarketTray fills table with the marbles of the shuffled market in game.
     */
    public MarketTray(){
        int i,j,k;
        k=1;
        for(i=0;i<3;i++){
            for(j=0; j<4;j++,k++){
                table[i][j]= Game.getMarket().get(k);
            }
        }
        remainingMarble= Game.getMarket().get(0);
    }

    /**
     * Method getRemainingMarble returns the marble that is in the slide of the Market.
     */
    public Marble getRemainingMarble() {
        return remainingMarble;
    }

    /**
     * Method lineSelection calls transform() on every marble of the line selected.
     */
    public void lineSelection(String line, int numline){
        int i,j;
        if(line.equals("Row")){
            for(i=numline-1,j=0;j<4;j++){
                table[i][j].transform();
            }
        } else if(line.equals("Column")){
            for(i=0,j=numline-1;i<3;i++){
                table[i][j].transform();
            }
        }
    }

    /**
     * Method change is used to modify the market when a row or column is chosen
     */
    public void change(String line, int numline){
        Marble temp;
        int i,j;
        if(line.equals("Row")){
            temp= remainingMarble;
            remainingMarble= table[numline-1][0];
            for(j=0; j<3;j++){
                table[numline-1][j]=table[numline-1][j+1];
            }
            table[numline-1][3]=temp;
        }else if(line.equals("Column")){
            temp= remainingMarble;
            remainingMarble= table[0][numline-1];
            for(i=0;i<2;i++){
                table[i][numline-1]=table[i+1][numline-1];
            }
            table[2][numline-1]=temp;
        }
    }
}
