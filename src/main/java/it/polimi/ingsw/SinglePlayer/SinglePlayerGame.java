package it.polimi.ingsw.SinglePlayer;

import it.polimi.ingsw.Game;


import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the class used when a player wants to play against Lorenzo il Magnifico.
 */

public class SinglePlayerGame extends Game {

    private ArrayList<SoloActionToken> deckSoloActionToken;
    private ArrayList<SoloActionToken> deletedSoloActionToken;
    private SoloActionTokenType token;
    private int blackCross;

    public SinglePlayerGame() {
        blackCross = 0;
    }

    public int getBlackCross() {
        return blackCross;
    }

    public ArrayList<SoloActionToken> getDeckSoloActionToken() {
        return deckSoloActionToken;
    }

    public ArrayList<SoloActionToken> getDeletedSoloActionToken() {
        return deletedSoloActionToken;
    }

    public int addBlackCross(int amount, int blackCross){
        blackCross += amount;
        return blackCross;
    }

    /**
     * To shuffle the tokens when the game starts.
     */
    public ArrayList<SoloActionToken> shuffleSoloActionToken(){
        Collections.shuffle(deckSoloActionToken);
        return deckSoloActionToken;
    }

    /**
     * To reveal and apply the effect of the token.
     */
    public void useSoloActionToken(){
        SoloActionToken token = deckSoloActionToken.get(deckSoloActionToken.size() - 1);
        token.applyEffect();
        deletedSoloActionToken.add(token);

    }

    @Override
    public void setup(){
        System.out.println("Setup Single Player finito");
    }
}
