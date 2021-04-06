package it.polimi.ingsw;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Cards.Card;
import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentCard;
import it.polimi.ingsw.Cards.LeaderCards.LeaderCard;
import it.polimi.ingsw.Marbles.Marble;
import it.polimi.ingsw.Marbles.MarketTray;

import java.io.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Game class contains the main logic of "Master of Renaissance".
 *
 * @author Filippo Caliò
 */

public class Game implements GameInterface{

    private static ArrayList<Marble> market;
    private ArrayList<Player> players;
    private ArrayList<Player> activePlayers;
    private ArrayList<LeaderCard> leaderDeck;
    private ArrayList<ArrayList<DevelopmentCard>> developmentDeck;

    /**
     * Constructor Game creates a new Game instance.
     */

    public Game(){
        players = new ArrayList<>();
        activePlayers = new ArrayList<>();
        leaderDeck = new ArrayList<>();
        developmentDeck = new ArrayList<>();
        market = new ArrayList<>();
    }


    /**
     * Method createNewPlayer creates a new player in the match. T
     *
     * @param player of type Player - the player to be added.
     */
    public void createNewPlayer(Player player) {
        players.add(player);
        activePlayers.add(player);
    }

    /**
     * Getter method used to return the player's list
     *
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Setter method used to create the Market Tray.
     */
    public ArrayList<Marble> createMarketTray() throws FileNotFoundException {
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader("src/resources/json/Marble.json"));
        market=gson.fromJson(br, new TypeToken<List<Marble>>(){}.getType());
        Collections.shuffle(market);
        return market;
    }

    public static ArrayList<Marble> getMarket() {
        return market;
    }

    /**
     * Setter method used to create the Development Cards' Deck using the JSON file. It returns the shuffled deck
     */
    public ArrayList<ArrayList<DevelopmentCard>> createDevelopmentDeck() throws FileNotFoundException {
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader("src/resources/json/DevelopmentCard.json"));
        developmentDeck = gson.fromJson(br, new TypeToken<List<DevelopmentCard>>(){}.getType());
        Collections.shuffle(developmentDeck);
        return developmentDeck;
    }

    /**
     * Setter method used to create the Leader Cards' Deck using the JSON file. It returns the shuffled deck
     */
    public ArrayList<LeaderCard> createLeaderDeck() throws IOException {
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader("src/resources/json/LeaderCard.json"));
        leaderDeck = gson.fromJson(br, new TypeToken<List<LeaderCard>>(){}.getType());
        System.out.println(leaderDeck);
        Collections.shuffle(leaderDeck);
        System.out.println(leaderDeck);
        return leaderDeck;
    }

    public ArrayList<ArrayList<DevelopmentCard>> removeCardFromDevelopmentDeck(int amount, CardColor color) {

        return developmentDeck;
    }

    public Player nextPlayer(int i){

        return players.get(i);
    }


    /**
     *  Called when endTurn in Player is true. It controls if the conditions to end the game are satisfied. If so, the method winner is called.
     */
    public boolean endGame(){
        return true;

    }

    public Player winner(int i){
        return getPlayers().get(i);
    }


    public void giveLeaderCards(){


    }


    @Override
    public void setup() throws IOException {
        createMarketTray(); // to shuffle the market
        createDevelopmentDeck(); //to place the cards in the right order
        createLeaderDeck(); //to shuffle the leader card
        giveLeaderCards(); //to give to the player 4 cards
        System.out.println("Setup finito");
    }

    @Override
    public void buyDevCard(DevelopmentCard developmentCard) {

    }

    @Override
    public void moveResource() {

    }

    @Override
    public void takeAndPlaceResource() {

    }

    @Override
    public void useAndChooseProdPower() {

    }

    @Override
    public void activateLeaderCard() {

    }

    @Override
    public void discardLeaderCard() {

    }

    @Override
    public void chooseLeaderCard() {

    }

}
