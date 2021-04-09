package it.polimi.ingsw.SinglePlayer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;

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
    private int blackCross;
    private boolean noMoreColumnDevCard = false;

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
     * Method isNoMoreColumnDevCard returns the boolean value of the attribute. Getter method used in SinglePlayerGameTest
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
            deckSoloActionToken = gson.fromJson(br, new TypeToken<List<SoloActionToken>>(){}.getType());


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
     * Method drawSoloActionToken extracts the last token from the deck, applies its effect and
     * adds it to the deck containing the token already used.
     */
    public void drawSoloActionToken() {
        SoloActionToken token = deckSoloActionToken.get(deckSoloActionToken.size() - 1);
        deckSoloActionToken.remove(deckSoloActionToken.size()-1);
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
                developmentGrid.get(i).remove(developmentGrid.get(i).size()-1);
                stop = true;
                if(developmentGrid.get(i).isEmpty()){
                    int j = i+4;
                    if(j> developmentGrid.size()) noMoreColumnDevCard = true;
                }
            }
        }

    }
    /**
     * Override method setup calls the method setDeckSoloActionToken to create the token's deck at the beginning
     * of the Single Player Game
     */
    @Override
    public void setup() {
        setDeckSoloActionToken();
        setMarketTray(); // to shuffle the market
        createDevelopmentGrid(); //to place the cards in the right order
        createLeaderDeck(); //to shuffle the leader card
        distributeLeaderCards(); //to give to the player 4 cards
    }

    /**
     *  Method endGame called when endTurn in Player is true. It controls if the conditions to end the game are satisfied.
     *  If so, the method winner is called.
     */
    @Override
    public boolean endGame() {
        if(blackCross>=20){
            System.out.println("YOU LOST BECAUSE THE BLACK CROSS TOKEN REACHES THE FINAL SPACE OF THE FAITH TRACK");
            return true; //in questo caso vince lorenzo
        }
        else if(noMoreColumnDevCard){
                System.out.println("YOU LOST BECAUSE ONE TYPE OF DEV CARDS IS NO LONGER AVAILABLE IN THE GRID");
            return true; //in questo caso vince lorenzo
        }
        // TODO: 09/04/2021 come controllare il player che sta giocando in quel momento 
        else if(getActivePlayers().get(1).getBoard().getFaithMarker() == 20 || 
                getActivePlayers().get(1).getBoard().getNumOfDevCard()==7){
            System.out.println("YOU WON");
            return true;
        }
        else return false;

    }

    /*@Override
    public Player winner(int i) {
        return super.winner(i);
    }*/
}
