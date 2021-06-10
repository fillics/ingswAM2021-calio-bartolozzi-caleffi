package it.polimi.ingsw.model.singleplayer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.model.cards.developmentcards.CardColor;

import it.polimi.ingsw.model.Game;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;


/**
 * Represents the class used when a player wants to play in Single Player against Lorenzo il Magnifico.
 */

public class SinglePlayerGame extends Game implements SinglePlayerGameInterface{

    private LinkedList<SoloActionToken> deckSoloActionToken;
    private final ArrayList<SoloActionToken> deletedSoloActionToken;
    private int blackCross;
    private boolean noMoreColumnDevCard = false;
    private boolean endgame = false;

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
     *Getter method used to check if the game is ended
     */
    @Override
    public boolean isEndgame() {
        return endgame;
    }
    /**
     * Method increaseBlackCross moves the black cross forward by an amount of steps.
     *
     * @param amount of type Int - indicates the number of steps.
     */
    public void increaseBlackCross(int amount){
        if (amount > 0) {
            for(int i = 0; i < amount; i++){
                blackCross++;
                if(getActivePlayers().get(0).getBoard().getTrack().get(blackCross - 1).isPopeSpace()){
                    if(getActivePlayers().get(0).getBoard().getTrack().get(blackCross - 1).getVaticanReportSection() > 0){
                        if(!getActivePlayers().get(0).getBoard().getVaticanReportSections().get(getActivePlayers().get(0).getBoard().getTrack().get(blackCross - 1).getVaticanReportSection()-1).isActivated()){
                            checkPlayersFaithMarkers(blackCross);
                        }
                    }
                }
                if (blackCross >= 24){
                    endGame();
                    blackCross = 24;
                    break;
                }
            }
        }
    }

    /**
     * Method setDeckSoloActionToken extracts from the json file Token.json the types of the tokens featured in
     * the Single Player Game and according to their types, it assigns to them the correct strategy
     */
    public void setDeckSoloActionToken() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        try {
            deckSoloActionToken = mapper.readValue(Objects.requireNonNull(getClass().getResourceAsStream("/json/Token.json")).readAllBytes(), new TypeReference<>() {});
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
        } catch (IOException e) {
            e.printStackTrace();
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
        SoloActionToken token = deckSoloActionToken.getLast();
        deckSoloActionToken.removeLast();
        deletedSoloActionToken.add(token);
        return token;
    }

    /**
     * Method useSoloActionToken calls the method drawSoloActionToken and applies the drawn token's effect.
     * Then the method adds the token to the deck containing the token already used.
     */
    public void useSoloActionToken(SoloActionToken token){
        token.applyEffect();
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
                    if(j>developmentGrid.size()){
                        noMoreColumnDevCard = true;
                        endGame();
                    }
                }
            }
        }
    }

    @Override
    public void increaseFaithMarkerOfOtherPlayers() {
        increaseBlackCross(1);
    }
    
    

    /**
     *  Method endGame called when the conditions to end the game are satisfied.
     */
    @Override
    public void endGame() {
        endgame=true;
    }


    /**
     * Override method winner indicates who wins the match between the player and Lorenzo il Magnifico
     */
    @Override
    public void winner() {
        String winnerUsername = null;
        if(blackCross>=24 || noMoreColumnDevCard){
            winnerUsername = "Lorenzo Il Magnifico";
        }
        else if(getActivePlayers().get(0).getBoard().getFaithMarker() >= 24 ||
                getActivePlayers().get(0).getBoard().getNumOfDevCards()==7){
            winnerUsername = getActivePlayers().get(0).getUsername();
        }
        setWinner(winnerUsername);
    }


}
