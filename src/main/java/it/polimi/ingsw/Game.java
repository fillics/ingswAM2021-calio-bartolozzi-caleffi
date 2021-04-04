package it.polimi.ingsw;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentCard;
import it.polimi.ingsw.Cards.DevelopmentCards.Level;
import it.polimi.ingsw.Cards.LeaderCards.LeaderCard;
import it.polimi.ingsw.Marbles.MarketTray;

import java.io.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

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

    /**
     * Constructor Game creates a new Game instance.
     */

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
     * Getter method used to return the active player's list
     *
     */
    public ArrayList<Player> getActivePlayers() {
        return activePlayers;
    }

    /**
     * Setter method used to create the Market Tray.
     */
    public MarketTray setMarketTray(){
        return market;
    }

    /**
     * Setter method used to create the Development Cards' Deck using the JSON file
     */
    public void createDevelopmentDeck() throws FileNotFoundException {
        ArrayList<DevelopmentCard> deckToOrder;
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader("src/resources/json/DevelopmentCard.json"));
        deckToOrder = gson.fromJson(br, new TypeToken<List<DevelopmentCard>>(){}.getType());

        ArrayList<DevelopmentCard> y1, y2, y3, b1, b2, b3, g1, g2, g3, p1, p2, p3;
        y1 = y2 = y3 = b1 = b2 = b3 = g1 = g2 = g3 = p1 = p2 = p3 = new ArrayList<>();

        deckToOrder.forEach(developmentCard -> {
            if (developmentCard.getColor().equals(CardColor.YELLOW)) {
                if (developmentCard.getLevel().equals(Level.ONE)) y1.add(developmentCard);
                else if (developmentCard.getLevel().equals(Level.TWO)) y2.add(developmentCard);
                else if (developmentCard.getLevel().equals(Level.THREE)) y3.add(developmentCard);
            } else if (developmentCard.getColor().equals(CardColor.GREEN)) {
                if (developmentCard.getLevel().equals(Level.ONE)) g1.add(developmentCard);
                else if (developmentCard.getLevel().equals(Level.TWO)) g2.add(developmentCard);
                else if (developmentCard.getLevel().equals(Level.THREE)) g3.add(developmentCard);
            } else if (developmentCard.getColor().equals(CardColor.PURPLE)) {
                if (developmentCard.getLevel().equals(Level.ONE)) p1.add(developmentCard);
                else if (developmentCard.getLevel().equals(Level.TWO)) p2.add(developmentCard);
                else if (developmentCard.getLevel().equals(Level.THREE)) p3.add(developmentCard);
            } else if (developmentCard.getColor().equals(CardColor.BLUE)) {
                if (developmentCard.getLevel().equals(Level.ONE)) b1.add(developmentCard);
                else if (developmentCard.getLevel().equals(Level.TWO)) b2.add(developmentCard);
                else if (developmentCard.getLevel().equals(Level.THREE)) b3.add(developmentCard);
            }
        });

        Collections.shuffle(y1);
        Collections.shuffle(y2);
        Collections.shuffle(y3);
        Collections.shuffle(g1);
        Collections.shuffle(g2);
        Collections.shuffle(g3);
        Collections.shuffle(p1);
        Collections.shuffle(p2);
        Collections.shuffle(p3);
        Collections.shuffle(b1);
        Collections.shuffle(b2);
        Collections.shuffle(b3);

        developmentDeck.add(y1);
        developmentDeck.add(g1);
        developmentDeck.add(p1);
        developmentDeck.add(b1);

        developmentDeck.add(y2);
        developmentDeck.add(g2);
        developmentDeck.add(p2);
        developmentDeck.add(b2);

        developmentDeck.add(y3);
        developmentDeck.add(g3);
        developmentDeck.add(p3);
        developmentDeck.add(b3);

    }

    /**
     * Setter method used to create the Leader Cards' Deck using the JSON file
     */
    public void createLeaderDeck() throws IOException {
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader("src/resources/json/LeaderCard.json"));
        leaderDeck = gson.fromJson(br, new TypeToken<List<LeaderCard>>(){}.getType());
        Collections.shuffle(leaderDeck);
    }

    public void removeCardFromDevelopmentDeck(int amount, CardColor color) {

    }

    public ArrayList<ArrayList<DevelopmentCard>> getDevelopmentDeck() {
        return developmentDeck;
    }

    public ArrayList<LeaderCard> getLeaderDeck() {
        return leaderDeck;
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

    /**
     * It distributes 4 leader cards to each active player
     */
    public void distributeLeaderCards(){
        IntStream.range(0, 4).flatMap(numDealtCards -> IntStream.range(0, activePlayers.size())).forEach(whichPlayer -> {
            players.get(whichPlayer).addLeaderCard(leaderDeck.get(leaderDeck.size() - 1));
            leaderDeck.remove(leaderDeck.size() - 1);
        });

    }


    @Override
    public void setup() throws IOException {
        setMarketTray(); // to shuffle the market
        createDevelopmentDeck(); //to place the cards in the right order
        createLeaderDeck(); //to shuffle the leader card
        distributeLeaderCards(); //to give to the player 4 cards
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
