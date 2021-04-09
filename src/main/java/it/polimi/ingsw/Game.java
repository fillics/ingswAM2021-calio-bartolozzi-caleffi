package it.polimi.ingsw;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentCard;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentSpace;
import it.polimi.ingsw.Cards.DevelopmentCards.Level;
import it.polimi.ingsw.Cards.LeaderCards.LeaderCard;
import it.polimi.ingsw.Marbles.MarketTray;

import java.io.*;

import java.util.*;
import java.util.stream.Collectors;
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
    protected ArrayList<ArrayList<DevelopmentCard>> developmentGrid;
    private MarketTray market;
    private int currentPlayer;


    /**
     * Constructor Game creates a new Game instance.
     */
    public Game(){
        players = new ArrayList<>();
        activePlayers = new ArrayList<>();
        leaderDeck = new ArrayList<>();
        developmentGrid = new ArrayList<>();
        market = new MarketTray();
        currentPlayer = 1;
    }

    @Override
    public void setup(){
        setMarketTray(); // to shuffle the market
        createDevelopmentGrid(); //to place the cards in the right order
        createLeaderDeck(); //to shuffle the leader card
        distributeLeaderCards(); //to give to the player 4 cards
    }

    /**
     * Method createNewPlayer creates a new player in the match. T
     * @param player of type Player - the player to be added.
     */
    public void createNewPlayer(Player player) {
        players.add(player);
        activePlayers.add(player);
    }

    /**
     * Getter method used to return the player's list
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Getter method used to return the active player's list
     */
    public ArrayList<Player> getActivePlayers() {
        return activePlayers;
    }

    /**
     * Getter method used to return the development deck
     */
    public ArrayList<ArrayList<DevelopmentCard>> getDevelopmentGrid() {
        return developmentGrid;
    }

    /**
     * Getter method used to return the leader deck
     */
    public ArrayList<LeaderCard> getLeaderDeck() {
        return leaderDeck;
    }
    /**
     * Setter method used to create the Market Tray.
     */
    public MarketTray setMarketTray(){
        return market;
    }


    /**
     * Method createDevelopmentDeck creates the Development Cards' Deck using the JSON file
     */
    public void createDevelopmentGrid() {
        ArrayList<DevelopmentCard> deckToOrder;
        Gson gson = new Gson();

        try{
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/json/DevelopmentCard.json"));
            deckToOrder = gson.fromJson(br, new TypeToken<List<DevelopmentCard>>(){}.getType());
            Collections.shuffle(deckToOrder);

            Map<CardColor, Map<Level, List<DevelopmentCard>>> groupByColorAndLevel =
                    deckToOrder.stream().collect(Collectors.groupingBy(DevelopmentCard::getColor, Collectors.groupingBy(DevelopmentCard::getLevel)));

            /*developmentDeck:
                [y3] [g3] [p3] [b3]
                [y2] [g2] [p2] [b2]
                [y1] [g1] [p1] [b1]

            [ [y1] [g1] [p1] [b1] [y2] [g2] [p2] [b2] [y3] [g3] [p3] [b3] ]
            */
            developmentGrid.add(new ArrayList<>(groupByColorAndLevel.get(CardColor.YELLOW).get(Level.ONE)));
            developmentGrid.add(new ArrayList<>(groupByColorAndLevel.get(CardColor.GREEN).get(Level.ONE)));
            developmentGrid.add(new ArrayList<>(groupByColorAndLevel.get(CardColor.PURPLE).get(Level.ONE)));
            developmentGrid.add(new ArrayList<>(groupByColorAndLevel.get(CardColor.BLUE).get(Level.ONE)));

            developmentGrid.add(new ArrayList<>(groupByColorAndLevel.get(CardColor.YELLOW).get(Level.TWO)));
            developmentGrid.add(new ArrayList<>(groupByColorAndLevel.get(CardColor.GREEN).get(Level.TWO)));
            developmentGrid.add(new ArrayList<>(groupByColorAndLevel.get(CardColor.PURPLE).get(Level.TWO)));
            developmentGrid.add(new ArrayList<>(groupByColorAndLevel.get(CardColor.BLUE).get(Level.TWO)));

            developmentGrid.add(new ArrayList<>(groupByColorAndLevel.get(CardColor.YELLOW).get(Level.THREE)));
            developmentGrid.add(new ArrayList<>(groupByColorAndLevel.get(CardColor.GREEN).get(Level.THREE)));
            developmentGrid.add(new ArrayList<>(groupByColorAndLevel.get(CardColor.PURPLE).get(Level.THREE)));
            developmentGrid.add(new ArrayList<>(groupByColorAndLevel.get(CardColor.BLUE).get(Level.THREE)));

        }catch (FileNotFoundException ex){
            System.out.println("Token.json file was not found");
        }
    }

    /**
     * Method createLeaderDeck creates the Leader Cards' Deck using the JSON file
     */
    public void createLeaderDeck(){
        Gson gson = new Gson();

        try{
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/json/LeaderCard.json"));
            leaderDeck = gson.fromJson(br, new TypeToken<List<LeaderCard>>(){}.getType());
            Collections.shuffle(leaderDeck);
        }catch (FileNotFoundException ex){
            System.out.println("Token.json file was not found");
        }

    }

    /**
     * Method removeCardFromDevelopmentDeck is called when a Player buys a development card
     */
    public void removeCardFromDevelopmentGrid(CardColor color, Level level) {


    }


    /**
     * Method distributeLeaderCards distributes 4 leader cards to each active player
     */
    public void distributeLeaderCards(){
        IntStream.range(0, 4).map(numDealtCards -> activePlayers.size()).flatMap(bound -> IntStream.range(0, bound)).forEach(whichPlayer -> {
            players.get(whichPlayer).addLeaderCard(leaderDeck.get(leaderDeck.size() - 1));
            leaderDeck.remove(leaderDeck.size() - 1);
        });

    }



    @Override
    public void buyDevCard(DevelopmentCard developmentCard, DevelopmentSpace developmentSpace) {

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


    // TODO: 08/04/2021 gestire il prossimo turno e il prossimo player
    public Player nextPlayer(int whichPlayer){

        currentPlayer += 1;
        return players.get(1);
    }


    /**
     *  Method endGame called when endTurn in Player is true. It controls if the conditions to end the game are satisfied. If so, the method winner is called.
     */
    public boolean endGame(){
        return true;

    }

    /*public String winner(){
        return getPlayers().get();
    }*/

}
