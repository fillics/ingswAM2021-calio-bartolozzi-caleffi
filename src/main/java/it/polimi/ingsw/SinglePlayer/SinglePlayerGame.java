package it.polimi.ingsw.SinglePlayer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Game;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the class used when a player wants to play against Lorenzo il Magnifico.
 */

public class SinglePlayerGame extends Game {

    private ArrayList<SoloActionToken> deckSoloActionToken;
    private ArrayList<SoloActionToken> deletedSoloActionToken;
    private SoloActionTokenType token;
    private int blackCross;

    public SinglePlayerGame() {
        deckSoloActionToken = new ArrayList<>();
        deletedSoloActionToken = new ArrayList<>();
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

    public int increaseBlackCross(int amount, int blackCross){
        blackCross += amount;
        return blackCross;
    }

    public ArrayList<SoloActionToken> setDeckSoloActionToken() throws IOException {

        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader("src/resources/json/Token.json"));
        deckSoloActionToken = gson.fromJson(br, new TypeToken<List<SoloActionToken>>(){}.getType());
        System.out.println(deckSoloActionToken);
        return deckSoloActionToken;
    }

    /**
     * To shuffle the tokens when the game starts.
     */
    public ArrayList<SoloActionToken> shuffleSoloActionToken(){
        Collections.shuffle(deckSoloActionToken);
        System.out.println(deckSoloActionToken);
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
