package it.polimi.ingsw;

import com.google.gson.Gson;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentCard;
import it.polimi.ingsw.Cards.LeaderCards.LeaderCard;
import it.polimi.ingsw.Marbles.MarketTray;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Game class contains the main logic of "Master of Renaissance".
 *
 * @author Filippo Caliò
 */
public class Game implements GameInterface{

    private ArrayList<Player> players;
    private ArrayList<Player> activePlayers;
    private ArrayList<LeaderCard> leaderDeck;
    private ArrayList<ArrayList<DevelopmentCard>> developmentDeck;
    private MarketTray market;


    public Game(){
        players = new ArrayList<>();
        activePlayers = new ArrayList<>();
        leaderDeck = new ArrayList<>();
        developmentDeck = new ArrayList<>();
        market = new MarketTray();
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
    public MarketTray setMarketTray(){

        return market;
    }

    /**
     * Setter method used to create the Development Cards' Deck using the JSON file. It returns the shuffled deck
     */
    public ArrayList<ArrayList<DevelopmentCard>> setDevelopmentDeck() {

        Collections.shuffle(developmentDeck);
        return developmentDeck;
    }

    /**
     * Setter method used to create the Leader Cards' Deck using the JSON file. It returns the shuffled deck
     */
    public ArrayList<LeaderCard> setLeaderDeck() {
        // create Gson instance
        Gson gson = new Gson();

        // create a reader
        try {

            Reader reader = Files.newBufferedReader(Paths.get("src/resources/json/LeaderCard.json"));

            //convert the json to  Java object (Employee)
            LeaderCard employee = gson.fromJson(reader, LeaderCard.class);

            // print map entries
            //for (Map.Entry<?, ?> entry : map.entrySet()) {
            //    System.out.println(entry.getKey() + "=" + entry.getValue());
           // }

            // close reader
            //reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Collections.shuffle(leaderDeck);
        return leaderDeck;
    }

    public ArrayList<ArrayList<DevelopmentCard>> removeCardFromDevelopmentDeck(LeaderCard cardToRemove, ArrayList<ArrayList<DevelopmentCard>> deck) {

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
    public void setup(){
        setMarketTray(); // to shuffle the market
        setDevelopmentDeck(); //to place the cards in the right order
        setLeaderDeck(); //to shuffle the leader card
        giveLeaderCards(); //to give to the player 4 cards
        System.out.println("Setup finito");
    }

    @Override
    public void buyDevCard() {

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
