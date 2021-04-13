package it.polimi.ingsw;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Board.Resources.ConcreteStrategyResource;
import it.polimi.ingsw.Board.Resources.ResourceActionStrategy;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Board.Storage.Warehouse;
import it.polimi.ingsw.Cards.DevelopmentCards.*;
import it.polimi.ingsw.Cards.LeaderCards.LeaderCard;
import it.polimi.ingsw.Exceptions.DifferentDimensionForProdPower;
import it.polimi.ingsw.Exceptions.NumMaxPlayersReached;
import it.polimi.ingsw.Exceptions.TooManyResourcesRequested;
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
    protected ArrayList<LinkedList<DevelopmentCard>> developmentGrid;
    private MarketTray market;
    private int currentPlayer = 0;
    final int NUM_MAXPLAYERS = 4;


    /**
     * Constructor Game creates a new Game instance.
     */
    public Game(){
        players = new ArrayList<>();
        activePlayers = new ArrayList<>();
        leaderDeck = new ArrayList<>();
        developmentGrid = new ArrayList<>();
        market = new MarketTray();

    }

    /**
     * Override method setup called when a game starts
     */
    @Override
    public void setup(){
        createDevelopmentGrid(); //to place the cards in the right order
        createLeaderDeck(); //to create and shuffle the leader card
        distributeLeaderCards(); //to give 4 leader cards to the each player
    }

    /**
     * Method additionalSetup distributes resources and faith points to the players
     * according to their position's turn
     */
    public void additionalSetup(){
        activePlayers.get(1).chooseResourcesBeginningGame(1); //second player receives one resource
        activePlayers.get(2).getBoard().increaseFaithMarker(); //third player receives one faith point
        activePlayers.get(2).chooseResourcesBeginningGame(1); //third player receives one resource
        activePlayers.get(3).getBoard().increaseFaithMarker(); //forth player receives one faith point
        activePlayers.get(3).chooseResourcesBeginningGame(2); //forth player receives two resources

    }
    /**
     * Method createNewPlayer creates a new player in the match.
     * @param username of type String - the player's username
     */
    public void createNewPlayer(String username) throws NumMaxPlayersReached {
        if(players.size()<NUM_MAXPLAYERS){
            Player player = new Player(username, players.size()+1, this);
            players.add(player);
            activePlayers.add(player);
        }
        else throw new NumMaxPlayersReached();
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
    public ArrayList<LinkedList<DevelopmentCard>> getDevelopmentGrid() {
        return developmentGrid;
    }

    /**
     * Getter method used to return the leader deck
     */
    public ArrayList<LeaderCard> getLeaderDeck() {
        return leaderDeck;
    }


    /**
     * Method createDevelopmentDeck creates and shuffles the Development Cards' Deck using the JSON file
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
            developmentGrid.add(new LinkedList<>(groupByColorAndLevel.get(CardColor.YELLOW).get(Level.ONE)));
            developmentGrid.add(new LinkedList<>(groupByColorAndLevel.get(CardColor.GREEN).get(Level.ONE)));
            developmentGrid.add(new LinkedList<>(groupByColorAndLevel.get(CardColor.PURPLE).get(Level.ONE)));
            developmentGrid.add(new LinkedList<>(groupByColorAndLevel.get(CardColor.BLUE).get(Level.ONE)));

            developmentGrid.add(new LinkedList<>(groupByColorAndLevel.get(CardColor.YELLOW).get(Level.TWO)));
            developmentGrid.add(new LinkedList<>(groupByColorAndLevel.get(CardColor.GREEN).get(Level.TWO)));
            developmentGrid.add(new LinkedList<>(groupByColorAndLevel.get(CardColor.PURPLE).get(Level.TWO)));
            developmentGrid.add(new LinkedList<>(groupByColorAndLevel.get(CardColor.BLUE).get(Level.TWO)));

            developmentGrid.add(new LinkedList<>(groupByColorAndLevel.get(CardColor.YELLOW).get(Level.THREE)));
            developmentGrid.add(new LinkedList<>(groupByColorAndLevel.get(CardColor.GREEN).get(Level.THREE)));
            developmentGrid.add(new LinkedList<>(groupByColorAndLevel.get(CardColor.PURPLE).get(Level.THREE)));
            developmentGrid.add(new LinkedList<>(groupByColorAndLevel.get(CardColor.BLUE).get(Level.THREE)));

        }catch (FileNotFoundException ex){
            System.out.println("Token.json file was not found");
        }
    }

    /**
     * Method createLeaderDeck creates and shuffles the Leader Cards' Deck using the JSON file
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
     * Method chooseCardFromDevelopmentGrid returns the selected card that a Player wants to buy
     * @param color (type CardColor) - it indicates the color of the wanted card
     * @param level (type Level) - it indicates the level of the wanted card
     * @return the development card with a specific color and level. If there is no card with those characteristics,
     * it returns null
     */
    public DevelopmentCard chooseCardFromDevelopmentGrid(CardColor color, Level level){
        DevelopmentCard chosenCard = null;
        for (LinkedList<DevelopmentCard> developmentCards : developmentGrid) {
            if (!developmentCards.isEmpty() && developmentCards.get(0).getColor().equals(color)
                    && developmentCards.get(0).getLevel().equals(level)) {
                chosenCard = developmentCards.getLast();
            }
        }
        return chosenCard;
    }

    // TODO: 11/04/2021 da testare
    /**
     * Method removeCardFromDevelopmentDeck is called when a Player buys a development card and it removes
     * the selected card
     */
    public void removeCardFromDevelopmentGrid(DevelopmentCard cardToRemove) {
        for (LinkedList<DevelopmentCard> developmentCards : developmentGrid) {
            if (!developmentCards.isEmpty() && developmentCards.getLast().equals(cardToRemove)) {
                developmentCards.remove(cardToRemove);
            }
        }
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
    public void moveResource(int position) {
        activePlayers.get(currentPlayer).fillBuffer(position);
    }

    @Override
    public void takeResourcesFromMarket(String line, int numline) {
        market.lineSelection(line, numline, activePlayers.get(currentPlayer));
    }

    @Override
    public void placeResource(int depositPosition, int resourcePosition) {
        ResourceActionStrategy strategy = new ConcreteStrategyResource(depositPosition, activePlayers.get(currentPlayer).getBoard(), activePlayers.get(currentPlayer).getResourceBuffer().get(resourcePosition).getType());
        activePlayers.get(currentPlayer).getResourceBuffer().get(resourcePosition).setStrategy(strategy);
        activePlayers.get(currentPlayer).getResourceBuffer().get(resourcePosition).useResource();
    }

    @Override
    public void useAndChooseProdPower(ProductionPower productionPower, ArrayList<ResourceType> resources, ArrayList<Warehouse> warehouse) {
        try {
            if(productionPower.check(resources,warehouse,activePlayers.get(currentPlayer).getBoard())){
                try {
                    productionPower.removeResources(resources,warehouse);
                    productionPower.addResources(activePlayers.get(currentPlayer).getBoard());
                } catch (DifferentDimensionForProdPower differentDimensionForProdPower) {
                    differentDimensionForProdPower.printStackTrace();
                }
            }
        } catch (TooManyResourcesRequested tooManyResourcesRequested) {
            tooManyResourcesRequested.printStackTrace();
        }
    }

    @Override
    public void useAndChooseProdPower(ProductionPower productionPower, ArrayList<ResourceType> resources, ArrayList<Warehouse> warehouse, ArrayList<ResourceType> newResources) {
        try {
            if(productionPower.check(resources,warehouse,activePlayers.get(currentPlayer).getBoard())){
                try {
                    productionPower.removeResources(resources,warehouse);
                    productionPower.addResources(activePlayers.get(currentPlayer).getBoard(), newResources);
                } catch (DifferentDimensionForProdPower differentDimensionForProdPower) {
                    differentDimensionForProdPower.printStackTrace();
                }
            }
        } catch (TooManyResourcesRequested tooManyResourcesRequested) {
            tooManyResourcesRequested.printStackTrace();
        }
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

    /**
     * Method nextPlayer handles which player is playing during his turn
     */
    // TODO: 12/04/2021 da testare
    public void nextPlayer(){
        while(getActivePlayers().get(currentPlayer).endTurn()){
            if(currentPlayer==getActivePlayers().size()-1){
                currentPlayer=0;
            }
                currentPlayer+=1;
        }
    }


    /**
     *  Method endGame called when endTurn in Player is true.
     *  It controls if the conditions to end the game are satisfied and indicates the winner
     */
    public boolean endGame(){
        return true;
    }




}
