package it.polimi.ingsw.SinglePlayer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;

import it.polimi.ingsw.Game;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * Represents the class used when a player wants to play in Single Player against Lorenzo il Magnifico.
 */

public class SinglePlayerGame extends Game {

    private LinkedList<SoloActionToken> deckSoloActionToken;
    private ArrayList<SoloActionToken> deletedSoloActionToken;
    private int blackCross;
    private boolean noMoreColumnDevCard = false;

    /**
     * Constructor SinglePlayerGame creates a new SinglePlayerGame instance.
     */
    public SinglePlayerGame() {
        deckSoloActionToken = new LinkedList<>();
        deletedSoloActionToken = new ArrayList<>();
        blackCross = 0;
    }

    /**
     * Override method setup is called at the beginning of the Single Player Game
     */
    @Override
    public void setup() {
        setDeckSoloActionToken();
        createDevelopmentGrid(); //to place the cards in the right order
        createLeaderDeck(); //to shuffle the leader card
        distributeLeaderCards(); //to give to the player 4 cards
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
    public LinkedList<SoloActionToken> getDeckSoloActionToken() {
        return deckSoloActionToken;
    }

    /**
     * Method getDeletedSoloActionToken returns the deck that contains the token already used.
     */
    public ArrayList<SoloActionToken> getDeletedSoloActionToken() {
        return deletedSoloActionToken;
    }

    /**
     * Method isNoMoreColumnDevCard returns the boolean value of the attribute isNoMoreColumnDevCard
     * @see SinglePlayerGame - the method removeDevCard
     */
    public boolean isNoMoreColumnDevCard() {
        return noMoreColumnDevCard;
    }

    /**
     * Method increaseBlackCross moves the black cross forward by an amount of steps.
     *
     * @param amount of type Int - indicates the number of steps.
     */
    public void increaseBlackCross(int amount){
        if (amount>=0) blackCross += amount;
    }

    /**
     * Method setDeckSoloActionToken extracts from the json file Token.json the types of the tokens featured in
     * the Single Player Game and according to their types, it assigns to them the correct strategy
     */
    public void setDeckSoloActionToken() {

        Gson gson = new Gson();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/json/Token.json"));
            deckSoloActionToken = gson.fromJson(br, new TypeToken<LinkedList<SoloActionToken>>(){}.getType());

            deckSoloActionToken.forEach(soloActionToken -> {
                if (soloActionToken.getType().equals(SoloActionTokenType.DISCARD)) {
                    if (soloActionToken.getColor().equals(CardColor.BLUE)) soloActionToken.setStrategy(new ConcreteStrategyDiscard(this, CardColor.BLUE));
                    else if (soloActionToken.getColor().equals(CardColor.GREEN)) soloActionToken.setStrategy(new ConcreteStrategyDiscard(this, CardColor.GREEN));
                    else if (soloActionToken.getColor().equals(CardColor.YELLOW)) soloActionToken.setStrategy(new ConcreteStrategyDiscard(this, CardColor.YELLOW));
                    else if (soloActionToken.getColor().equals(CardColor.PURPLE)) soloActionToken.setStrategy(new ConcreteStrategyDiscard(this, CardColor.PURPLE));
                }
                else if (soloActionToken.getType().equals(SoloActionTokenType.BLACKCROSS_1)) soloActionToken.setStrategy(new ConcreteStrategyPlusOne(this));
                else if (soloActionToken.getType().equals(SoloActionTokenType.BLACKCROSS_2)) soloActionToken.setStrategy(new ConcreteStrategyPlusTwo(this));
            });

            Collections.shuffle(deckSoloActionToken);
        }catch (FileNotFoundException ex){
            System.out.println("Token.json file was not found");
        }

    }

    /**
     * Method shuffleSoloActionToken creates a new shuffled deck containing the tokens and remove the tokens
     * from the deletedSoloActionToken deck.
     */
    public void shuffleSoloActionToken(){
        deckSoloActionToken.addAll(deletedSoloActionToken);
        deletedSoloActionToken.clear();
        Collections.shuffle(deckSoloActionToken);
    }

    /**
     * Method drawSoloActionToken used to draw and remove the last token from the deck
     * @return the drawn token (type SoloActionToken) that is the last element of the deck
     */
    public SoloActionToken drawSoloActionToken() {
        SoloActionToken token = deckSoloActionToken.get(deckSoloActionToken.size() - 1);
        deckSoloActionToken.removeLast();
        return token;
    }

    /**
     * Method useSoloActionToken calls the method drawSoloActionToken and applies the drawn token's effect.
     * Then the method adds the token to the deck containing the token already used.
     */
    public void useSoloActionToken(){
        SoloActionToken token = drawSoloActionToken();
        token.applyEffect();
        deletedSoloActionToken.add(token);
    }

    /**
     * Method removeDevCard is called by the Discard's token and removes from the development card's grid
     * the first occurrence of the development card with a specific color
     */
    public void removeDevCard(CardColor color) {
    boolean stop = false;
        for (int i = 0; i < developmentGrid.size(); i++) {
            if(!developmentGrid.get(i).isEmpty() && developmentGrid.get(i).get(0).getColor().equals(color) && !stop){
                developmentGrid.get(i).removeLast();
                stop = true;
                if(developmentGrid.get(i).isEmpty()){
                    int j = i+4;
                    if(j>developmentGrid.size()) noMoreColumnDevCard = true;
                }
            }
        }
    }


    /**
     *  Method endGame called when endTurn in Player is true.
     *  It controls if the conditions to end the game are satisfied and indicates the winner
     *
     */
    @Override
    public boolean endGame() {
        if(blackCross>=24){
            System.out.println("Lorenzo il Magnifico" + " won");
            return true;
        }
        else if(noMoreColumnDevCard){
            System.out.println("Lorenzo il Magnifico" + " won");
            return true;
        }
        else if(getActivePlayers().get(0).getBoard().getFaithMarker() >= 24 ||
                getActivePlayers().get(0).getBoard().getNumOfDevCard()==7){
            System.out.println(getActivePlayers().get(0).getUsername() + " won");
            return true;
        }
        else return false;
    }
}
