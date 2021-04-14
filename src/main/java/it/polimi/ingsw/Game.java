package it.polimi.ingsw;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Board.Resources.ConcreteStrategyResource;
import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceActionStrategy;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Board.Storage.Warehouse;
import it.polimi.ingsw.Cards.DevelopmentCards.*;
import it.polimi.ingsw.Cards.LeaderCards.ConcreteStrategyDiscount;
import it.polimi.ingsw.Cards.LeaderCards.LeaderCard;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Marbles.MarketTray;

import java.io.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Game class contains the main logic of "Master of Renaissance".
 */

public class Game implements GameInterface{

    private ArrayList<Player> players;
    private ArrayList<Player> activePlayers;
    private ArrayList<LeaderCard> leaderDeck;
    protected ArrayList<LinkedList<DevelopmentCard>> developmentGrid;
    private MarketTray market;
    private ArrayList<DevelopmentSpace> devSpaceBuffer;
    private HashMap<ResourceType,Integer> resourcePriceBuffer;
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
    public void additionalSetup() {

        if(activePlayers.size() >= 2){
            activePlayers.get(1).addResourcesBeginningGame(activePlayers.get(1).getChosenResource()); //second player receives one resource

            if(activePlayers.size() >= 3){
                activePlayers.get(2).getBoard().increaseFaithMarker(); //third player receives one faith point
                activePlayers.get(2).addResourcesBeginningGame(activePlayers.get(1).getChosenResource()); //third player receives one resource

                if(activePlayers.size() == 4){
                    activePlayers.get(3).getBoard().increaseFaithMarker(); //forth player receives one faith point
                    for (int i = 0; i < 2; i++) {
                        activePlayers.get(3).addResourcesBeginningGame(activePlayers.get(1).getChosenResource()); //forth player receives two resources
                    }

                }
            }
        }

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
     * Test method getCurrentPlayer returns the current player who is playing
     */
    public int getCurrentPlayer() {
        return currentPlayer;
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
    public DevelopmentCard chooseCardFromDevelopmentGrid(CardColor color, Level level) throws DevelopmentCardNotFound{
        DevelopmentCard chosenCard = null;
        for (LinkedList<DevelopmentCard> developmentCards : developmentGrid) {
            if (!developmentCards.isEmpty() && developmentCards.get(0).getColor().equals(color)
                    && developmentCards.get(0).getLevel().equals(level)) {
                chosenCard = developmentCards.getLast();
            }
        }
        if(chosenCard==null)
            throw new DevelopmentCardNotFound();
        else
            return chosenCard;
    }


    /**
     * Method removeCardFromDevelopmentDeck is called when a Player buys a development card and it removes
     * the selected card
     */
    public void removeCardFromDevelopmentGrid(DevelopmentCard cardToRemove) throws DevelopmentCardNotFound {
        boolean found = false;
        for (LinkedList<DevelopmentCard> developmentCards : developmentGrid) {
            if (!developmentCards.isEmpty() && developmentCards.getLast().getLevel().equals(cardToRemove.getLevel()) &&
                    developmentCards.getLast().getColor().equals(cardToRemove.getColor())) {
                developmentCards.removeLast();
                found = true;
            }
        }
        if(!found) throw new DevelopmentCardNotFound();
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

    public void placeDevCard(DevelopmentCard developmentCard,DevelopmentSpace developmentSpace, ArrayList<DevelopmentSpace> devSpaceBuffer)throws WrongDevSpace{
        if(devSpaceBuffer.contains(developmentSpace)){
            developmentSpace.addDevelopmentCard(developmentCard);
        }else{
            throw new WrongDevSpace();
        }
    }


    @Override
    public void buyDevCard(CardColor color, Level level, ArrayList<ArrayList<ResourceType>> chosenResourcesDeposits, ArrayList<ResourceType> chosenResourcesStrongbox, DevelopmentSpace developmentSpace){
        int i;
        DevelopmentCard developmentCard = null;
        devSpaceBuffer= new ArrayList<>();
        ArrayList<ResourceType> chosenResourcesBuffer = new ArrayList<>(chosenResourcesStrongbox);

        //controllo che la carta sia "acquistabile"
        try {
            developmentCard= chooseCardFromDevelopmentGrid(color,level);
            resourcePriceBuffer= developmentCard.getResourcePrice();
        } catch (DevelopmentCardNotFound developmentCardNotFound) {
            developmentCardNotFound.printStackTrace();
        }

        //controllo che la carta scelta sia piazzabile in uno dei dev space
        try {
            activePlayers.get(currentPlayer).getBoard().checkDevSpaces(developmentCard,devSpaceBuffer);
        } catch (DevCardNotPlaceable devCardNotPlaceable){
            devCardNotPlaceable.printStackTrace();
        }

        for(i=0; i<chosenResourcesDeposits.size();i++){
            chosenResourcesBuffer.addAll(chosenResourcesDeposits.get(i));
        }

        //modifico il prezzo richiesto se una o più carte discount sono attive e il player sceglie di utilizzarle
        for(i=0; i<activePlayers.get(currentPlayer).getLeaderCards().size();i++){
            if((activePlayers.get(currentPlayer).getLeaderCards().get(i).getStrategy() instanceof ConcreteStrategyDiscount)&&(activePlayers.get(currentPlayer).getLeaderCards().get(i).getStrategy().isActive())){
                //System.out.println("Do you want to use your discount of one " + ((ConcreteStrategyDiscount) activePlayers.get(currentPlayer).getLeaderCards().get(i).getStrategy()).getResourceType() + " ?");
                //activePlayers.get(currentPlayer).getLeaderCards().get(i).setUseDiscountChoice(playerChoice);
                activePlayers.get(currentPlayer).getLeaderCards().get(i).checkDiscount(developmentCard,resourcePriceBuffer);
            }
        }

        //controllo che ci siano abbastanza risorse nella board per comprare la carta e se le risorse selezionate siano esattamente quelle richieste
        //se si, rimuovo le risorse dalla board
        try {
            if(activePlayers.get(currentPlayer).getBoard().checkResources(resourcePriceBuffer, chosenResourcesBuffer)){
                activePlayers.get(currentPlayer).getBoard().removeResources(chosenResourcesDeposits,chosenResourcesStrongbox);
            }
        } catch (NotEnoughResources | WrongChosenResources notEnoughResources) {
            notEnoughResources.printStackTrace();
        }

        //controllo che la carta venga rimossa dal developmentGrid in modo corretto
        try {
            removeCardFromDevelopmentGrid(developmentCard);
        } catch (DevelopmentCardNotFound developmentCardNotFound) {
            developmentCardNotFound.printStackTrace();
        }

        //controllo che il dev space selezionato sia quello corretto o uno di quelli corretti
        try {
            placeDevCard(developmentCard,developmentSpace, devSpaceBuffer);
        } catch (WrongDevSpace wrongDevSpace) {
            wrongDevSpace.printStackTrace();
        }
    }

    @Override
    public void moveResource(int position) {
        activePlayers.get(currentPlayer).fillBuffer(position);
    }

    @Override
    public void takeResourcesFromMarket(String line, int numline) {
        market.lineSelection(line, numline, activePlayers.get(currentPlayer));
        market.change(line, numline);
    }


    @Override
    public void placeResource(int depositPosition, int resourcePosition) {
        ResourceActionStrategy strategy = new ConcreteStrategyResource(depositPosition, activePlayers.get(currentPlayer).getBoard(), activePlayers.get(currentPlayer).getResourceBuffer().get(resourcePosition).getType());
        activePlayers.get(currentPlayer).getResourceBuffer().get(resourcePosition).setStrategy(strategy);
        activePlayers.get(currentPlayer).getResourceBuffer().get(resourcePosition).useResource();
    }

    @Override
    public void useAndChooseProdPower(ProductionPower productionPower, ArrayList<ResourceType> resources, ArrayList<Warehouse> warehouse) throws DifferentDimensionForProdPower, TooManyResourcesRequested{
        if(productionPower.check(resources,warehouse,activePlayers.get(currentPlayer).getBoard())){
            productionPower.removeResources(resources,warehouse);
            productionPower.addResources(activePlayers.get(currentPlayer).getBoard());
        }

    }

    @Override
    public void useAndChooseProdPower(ProductionPower productionPower, ArrayList<ResourceType> resources, ArrayList<Warehouse> warehouse, ArrayList<ResourceType> newResources) throws DifferentDimensionForProdPower, TooManyResourcesRequested {
        if(productionPower.check(resources,warehouse,activePlayers.get(currentPlayer).getBoard())) {
            productionPower.removeResources(resources, warehouse);
            productionPower.addResources(activePlayers.get(currentPlayer).getBoard(), newResources);
        }
    }

    /**
     * Override method activateLeaderCard used when a player wants to activate a leader card using its ability.
     * @param cardToActivate (type LeaderCard) - it is the card that the player wants to activate
     * @throws LeaderCardNotFound if the player has not got the cardToActivate
     */
    @Override
    public void activateLeaderCard(LeaderCard cardToActivate) throws LeaderCardNotFound {
        if(!activePlayers.get(currentPlayer).getLeaderCards().contains(cardToActivate)) throw new LeaderCardNotFound();
        else{
            int indexCard = activePlayers.get(currentPlayer).getLeaderCards().indexOf(cardToActivate);
            activePlayers.get(currentPlayer).getLeaderCards().get(indexCard).useAbility();
        }
    }

    /**
     * Override method discardLeaderCard used when a player wants to discard a leader card. In doing so, the player
     * receives one faith point.
     * @param cardToDiscard (type LeaderCard) - it is the card that the player discards
     * @throws LeaderCardNotFound if the player has not got the cardToDiscard
     */
    @Override
    public void discardLeaderCard(LeaderCard cardToDiscard) throws LeaderCardNotFound {
        activePlayers.get(currentPlayer).removeLeaderCard(cardToDiscard);
        activePlayers.get(currentPlayer).getBoard().increaseFaithMarker();
    }


    /**
     * Override method chooseLeaderCard used when the player has to choose two leader cards at the beginning of the game
     * @param chosenCard1 (type LeaderCard) - it is the card that the player wants to keep
     * @param chosenCard2 (type LeaderCard) - it is the cards that the player wants to keep
     * @throws LeaderCardNotFound if the player has not got the two chosenCards
     */
    @Override
    public void chooseLeaderCard(LeaderCard chosenCard1, LeaderCard chosenCard2) throws LeaderCardNotFound {
        for (int i = 0; i < activePlayers.get(currentPlayer).getLeaderCards().size() ; i++) {
            if(!(activePlayers.get(currentPlayer).getLeaderCards().get(i).equals(chosenCard1) ||
                    activePlayers.get(currentPlayer).getLeaderCards().get(i).equals(chosenCard2))){
                activePlayers.get(currentPlayer).removeLeaderCard(activePlayers.get(currentPlayer).getLeaderCards().get(i));
            }
        }

    }

    /**
     * Method nextPlayer handles which player is playing during his turn
     */
    public void nextPlayer(){
        if(activePlayers.get(currentPlayer).endTurn()){
            if(!endGame()){
                if(currentPlayer==activePlayers.size()-1){
                    currentPlayer=0;
                }
                else currentPlayer+=1;
            }
            else {
                if(currentPlayer!=activePlayers.size()-1){
                    currentPlayer+=1;
                }
                else {
                    winner();
                }
            }
        }
    }


    /**
     *  Method endGame called when endTurn in Player is true.
     *  It controls if the conditions to end the game are satisfied and indicates the winner
     */
    public boolean endGame(){
        for (Player activePlayer : activePlayers) {
            if (activePlayer.getBoard().getFaithMarker() >= 24 ||
                    activePlayer.getBoard().getNumOfDevCard() == 7) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method winner indicates which player has more victory points than other players
     */
    public void winner(){
        ArrayList<Integer> victoryPointsPlayers = new ArrayList<>();
        ArrayList<Integer> resourcesPlayers = new ArrayList<>();
        int maxVictoryPoints;
        int maxResources;
        int victoryPointsLeaderAndBoard;
        String winnerUsername;

        for (Player player : activePlayers) {
            victoryPointsLeaderAndBoard = 0;
            for (int j = 0; j < player.getLeaderCards().size(); j++) {
                if (player.getLeaderCards().get(j).getStrategy().isActive()) {
                    victoryPointsLeaderAndBoard += player.getLeaderCards().get(j).getVictoryPoint();
                }
            }
            victoryPointsLeaderAndBoard += player.getTotalVictoryPoint();
            victoryPointsPlayers.add(victoryPointsLeaderAndBoard);
        }

        maxVictoryPoints = Collections.max(victoryPointsPlayers);

        if(Collections.frequency(victoryPointsPlayers, maxVictoryPoints)==1){
            winnerUsername = activePlayers.get(victoryPointsPlayers.indexOf(maxVictoryPoints)).getUsername();
        }
        else{ //caso di pareggio
            for (Player activePlayer : activePlayers) {
                resourcesPlayers.add(activePlayer.getBoard().getTotalResources());
            }
            maxResources = Collections.max(resourcesPlayers);
            winnerUsername = activePlayers.get(resourcesPlayers.indexOf(maxResources)).getUsername();
        }
    }


}
