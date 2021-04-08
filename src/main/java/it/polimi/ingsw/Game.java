package it.polimi.ingsw;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentCard;
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
    public ArrayList<ArrayList<DevelopmentCard>> getDevelopmentDeck() {
        return developmentDeck;
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
    public void createDevelopmentDeck() {
        ArrayList<DevelopmentCard> deckToOrder;
        Gson gson = new Gson();

        try{
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/json/DevelopmentCard.json"));
            deckToOrder = gson.fromJson(br, new TypeToken<List<DevelopmentCard>>(){}.getType());
            Collections.shuffle(deckToOrder);

            ArrayList<DevelopmentCard> y1 , y2, y3, b1, b2, b3, g1, g2, g3, p1, p2, p3;

            Map<CardColor, Map<Level, List<DevelopmentCard>>> groupByColorAndLevel =
                    deckToOrder.stream().collect(Collectors.groupingBy(DevelopmentCard::getColor, Collectors.groupingBy(DevelopmentCard::getLevel)));

            y1 = new ArrayList<>(groupByColorAndLevel.get(CardColor.YELLOW).get(Level.ONE));
            y2 = new ArrayList<>(groupByColorAndLevel.get(CardColor.YELLOW).get(Level.TWO));
            y3 = new ArrayList<>(groupByColorAndLevel.get(CardColor.YELLOW).get(Level.THREE));
            g1 = new ArrayList<>(groupByColorAndLevel.get(CardColor.GREEN).get(Level.ONE));
            g2 = new ArrayList<>(groupByColorAndLevel.get(CardColor.GREEN).get(Level.TWO));
            g3 = new ArrayList<>(groupByColorAndLevel.get(CardColor.GREEN).get(Level.THREE));
            b1 = new ArrayList<>(groupByColorAndLevel.get(CardColor.BLUE).get(Level.ONE));
            b2 = new ArrayList<>(groupByColorAndLevel.get(CardColor.BLUE).get(Level.TWO));
            b3 = new ArrayList<>(groupByColorAndLevel.get(CardColor.BLUE).get(Level.THREE));
            p1 = new ArrayList<>(groupByColorAndLevel.get(CardColor.PURPLE).get(Level.ONE));
            p2 = new ArrayList<>(groupByColorAndLevel.get(CardColor.PURPLE).get(Level.TWO));
            p3 = new ArrayList<>(groupByColorAndLevel.get(CardColor.PURPLE).get(Level.THREE));

            /*developmentDeck:
                [y3] [g3] [p3] [b3]
                [y2] [g2] [p2] [b2]
                [y1] [g1] [p1] [b1]

            [ [y1] [g1] [p1] [b1] [y2] [g2] [p2] [b2] [y3] [g3] [p3] [b3] ]
            */
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
     * Method removeCardFromDevelopmentDeck is called by the Discard's token

     */
    // TODO: 05/04/2021 DA FINIRE DI PROGRAMMARE E TESTARE -> metterlo dentro singleplayergame???
    public void removeCardFromDevelopmentDeck(CardColor color) {

        for (ArrayList<DevelopmentCard> developmentCards : developmentDeck) {
            if (developmentCards.size() != 0) {
                if (developmentCards.get(0).getColor().equals(color)) {
                    developmentCards.remove(developmentCards.size() - 1);
                }
            }
        }

    }


    /**
     * Method distributeLeaderCards distributes 4 leader cards to each active player
     */
    public void distributeLeaderCards(){
        IntStream.range(0, 4).flatMap(numDealtCards -> IntStream.range(0, activePlayers.size())).forEach(whichPlayer -> {
            players.get(whichPlayer).addLeaderCard(leaderDeck.get(leaderDeck.size() - 1));
            leaderDeck.remove(leaderDeck.size() - 1);
        });

    }


    @Override
    public void setup(){
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

}
