package it.polimi.ingsw.SinglePlayer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import it.polimi.ingsw.Exceptions.NegativeNumberBlackCross;
import it.polimi.ingsw.Game;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the class used when a player wants to play in Single Player against Lorenzo il Magnifico.
 */

public class SinglePlayerGame extends Game {

    private ArrayList<SoloActionToken> deckSoloActionToken;
    private ArrayList<SoloActionToken> deletedSoloActionToken;
    private SoloActionToken token;
    private int blackCross;

    /**
     * Constructor SinglePlayerGame creates a new SinglePlayerGame instance.
     */
    public SinglePlayerGame() {
        deckSoloActionToken = new ArrayList<>();
        deletedSoloActionToken = new ArrayList<>();
        blackCross = 0;
    }

    /**
     * Method getBlackCross returns the Black Cross of this SinglePlayerGame object.
     */
    public int getBlackCross() {
        return blackCross;
    }

    /**
     * Method getDeckSoloActionToken returns the deck that contains all the token.
     */
    public ArrayList<SoloActionToken> getDeckSoloActionToken() {
        return deckSoloActionToken;
    }

    /**
     * Method getDeletedSoloActionToken returns the deck that contains the token already used.
     */
    public ArrayList<SoloActionToken> getDeletedSoloActionToken() {
        return deletedSoloActionToken;
    }

    /**
     * Method increaseBlackCross moves the black cross forward by an amount of steps.
     *
     * @param amount of type Int - indicates the number of steps.
     */
    public void increaseBlackCross(int amount) throws NegativeNumberBlackCross {
        if(amount < 0) {
            throw new NegativeNumberBlackCross();
        }
        blackCross += amount;
    }

    /**
     * Method setDeckSoloActionToken extracts from the json file Token.json the types of the tokens featured in
     * the Single Player Game and according to their types, it assigns to them the correct strategy
     * @throws IOException if it cannot open the json file
     */
    public void setDeckSoloActionToken() throws IOException {

        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader("src/resources/json/Token.json"));
        deckSoloActionToken = gson.fromJson(br, new TypeToken<List<SoloActionToken>>(){}.getType());

        for (SoloActionToken soloActionToken : deckSoloActionToken) {

            if (soloActionToken.getType().equals(SoloActionTokenType.DISCARD)) {
                if (soloActionToken.getColor().equals(CardColor.BLUE)) {
                    soloActionToken.setStrategy(new ConcreteStrategyDiscard(this, CardColor.BLUE));
                } else if (soloActionToken.getColor().equals(CardColor.GREEN)) {
                    soloActionToken.setStrategy(new ConcreteStrategyDiscard(this, CardColor.GREEN));
                } else if (soloActionToken.getColor().equals(CardColor.YELLOW)) {
                    soloActionToken.setStrategy(new ConcreteStrategyDiscard(this, CardColor.YELLOW));
                } else if (soloActionToken.getColor().equals(CardColor.PURPLE)) {
                    soloActionToken.setStrategy(new ConcreteStrategyDiscard(this, CardColor.PURPLE));
                }

            } else if (soloActionToken.getType().equals(SoloActionTokenType.BLACKCROSS_1)) {
                soloActionToken.setStrategy(new ConcreteStrategyPlusOne(this));
            } else if (soloActionToken.getType().equals(SoloActionTokenType.BLACKCROSS_2)) {
                soloActionToken.setStrategy(new ConcreteStrategyPlusTwo(this));
            }
        }

    }

    //DA TOGLIERE
    public void print(){
        System.out.println(deletedSoloActionToken);
    }


    /**
     * Method shuffleSoloActionToken shuffles the deck containing the tokens.
     *
     */
    public void shuffleSoloActionToken(){
        Collections.shuffle(deckSoloActionToken);
    }

    /**
     * Method useSoloActionToken extracts the last token from the deck, applies its effect and
     * adds it to the deck containing the token already used.
     */
    public void useSoloActionToken() throws NegativeNumberBlackCross {
        SoloActionToken token = deckSoloActionToken.get(deckSoloActionToken.size() - 1);
        token.applyEffect();
        deletedSoloActionToken.add(token);
    }

    /**
     * Override method setup calls the method setDeckSoloActionToken to create the token's deck at the beginning
     * of the Single Player Game
     * @throws IOException if that method cannot open the json file
     */
    @Override
    public void setup() throws IOException {
        setDeckSoloActionToken();
    }
}
