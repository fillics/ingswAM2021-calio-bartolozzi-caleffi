package it.polimi.ingsw.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.model.board.resources.ConcreteStrategyResource;
import it.polimi.ingsw.model.board.resources.ResourceActionStrategy;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Warehouse;
import it.polimi.ingsw.model.cards.developmentcards.*;
import it.polimi.ingsw.model.cards.leadercards.ConcreteStrategyDiscount;
import it.polimi.ingsw.model.cards.leadercards.ConcreteStrategyMarble;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.marbles.MarketTray;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Game class contains the main logic of "Master of Renaissance".
 */

public class Game implements GameInterface, GameBoardInterface, GamePlayerInterface {

    private ArrayList<Player> players;
    private ArrayList<Player> activePlayers;
    private ArrayList<LeaderCard> leaderDeck;
    protected ArrayList<LinkedList<DevelopmentCard>> developmentGrid;
    private MarketTray market;
    private HashMap<ResourceType,Integer> resourcePriceBuffer;
    private ArrayList<LeaderCard> leaderCardsChosen;
    private int currentPlayer = 0;
    private final int NUM_MAXPLAYERS = 4;
    private boolean endgame = false;


    /**
     * Constructor Game creates a new Game instance.
     */
    public Game() {
        players = new ArrayList<>();
        activePlayers = new ArrayList<>();
        leaderDeck = new ArrayList<>();
        developmentGrid = new ArrayList<>();
        market = new MarketTray();
        leaderCardsChosen = new ArrayList<>();
    }

    /**
     * Method setup called when a game starts
     */
    public void setup(){
        changePlayersPosition(); //to define the player's turn
        createDevelopmentGrid(); //to place the cards in the right order
        createLeaderDeck(); //to create and shuffle the leader card
        distributeLeaderCards(); //to give 4 leader cards to the each player
        additionalSetup(); //to distribute faith points and resources
    }

    /**
     * Method changePlayersPosition chooses who the first player is and according to that, the other player's position
     */
    public void changePlayersPosition(){
        int random = (int)(Math.random()*(activePlayers.size()+1));
        Collections.rotate(activePlayers, random);
        for (int i = 0; i < activePlayers.size(); i++) {
            activePlayers.get(i).setPosition(i+1);
        }
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
                activePlayers.get(2).addResourcesBeginningGame(activePlayers.get(2).getChosenResource()); //third player receives one resource

                if(activePlayers.size() == 4){
                    activePlayers.get(3).getBoard().increaseFaithMarker(); //forth player receives one faith point
                    for (int i = 0; i < 2; i++) {
                        activePlayers.get(3).addResourcesBeginningGame(activePlayers.get(3).getChosenResource()); //forth player receives two resources
                    }

                }
            }
        }

    }
    /**
     * Method createNewPlayer creates a new player in the match.
     * @param username of type String - the player's username
     */
    // TODO: 23/04/2021 sistemare il num max player reached: è una cosa del controller? 
    public void createNewPlayer(String username) throws NumMaxPlayersReached {
        if(players.size()<NUM_MAXPLAYERS){
            Player player = new Player(username, this);
            players.add(player);
            activePlayers.add(player);
        }
        else throw new NumMaxPlayersReached();
    }

    /**
     * Method getPlayerByUsername searches the player identified by his username in the list of active
     * player.
     *
     * @param username (type String) - the username of the player.
     * @return Player - the desired player, null if there's no active player with that nickname.
     */
    public Player getPlayerByUsername(String username) {
        for (Player player : activePlayers) {
            if (player.getUsername().equalsIgnoreCase(username)) {
                return player;
            }
        }
        return null;
    }

    /**
     * Override method chooseLeaderCardToRemove used when the player has to choose two leader cards at the beginning of the game
     * @param chosenCard1 (type LeaderCard) - it is the card that the player wants to discard
     * @param chosenCard2 (type LeaderCard) - it is the cards that the player wants to discard
     * @throws LeaderCardNotFound if the player has not got the two chosenCards
     */
    @Override
    public void chooseLeaderCardToRemove(LeaderCard chosenCard1, LeaderCard chosenCard2) throws LeaderCardNotFound {
        activePlayers.get(currentPlayer).removeLeaderCard(chosenCard1);
        activePlayers.get(currentPlayer).removeLeaderCard(chosenCard2);
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
     * Getter method used to check if the game is ended
     */
    public boolean isEndgame() {
        return endgame;
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

    public ArrayList<LeaderCard> getLeaderCardsChosen() {
        return leaderCardsChosen;
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
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        try {
            leaderDeck = mapper.readValue(new File("src/main/resources/json/LeaderCard.json"), new TypeReference<ArrayList<LeaderCard>>() {});
            Collections.shuffle(leaderDeck);
        } catch (IOException e) {
            System.out.println("LeaderCard.json file was not found");
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

    public void useDiscountActivation(HashMap<ResourceType,Integer> resourcePriceBuffer, DevelopmentCard developmentCard){
        int i;
        for(i=0; i<leaderCardsChosen.size();i++){
            leaderCardsChosen.get(i).useDiscount(developmentCard,resourcePriceBuffer);
        }
    }

    @Override
    public void chooseDiscountActivation(ArrayList<LeaderCard> leaderCards) throws DiscountCannotBeActivated {
        int i;
        for(i=0; i<leaderCards.size();i++){
            if(!activePlayers.get(currentPlayer).getLeaderCards().contains(leaderCards.get(i)) || !(leaderCards.get(i).getStrategy() instanceof ConcreteStrategyDiscount) || !leaderCards.get(i).getStrategy().isActive())
                throw new DiscountCannotBeActivated();
            else
                leaderCardsChosen=leaderCards;
        }
    }

    @Override
    public void buyDevCard(CardColor color, Level level, ArrayList<ResourceType> chosenResources, ArrayList<Warehouse> chosenWarehouses, DevelopmentSpace developmentSpace) throws DevelopmentCardNotFound, DevCardNotPlaceable, NotEnoughResources, WrongChosenResources, DifferentDimension, EmptyDeposit, DepositDoesntHaveThisResource {
        DevelopmentCard developmentCard;
        resourcePriceBuffer= new HashMap<>();

        developmentCard= chooseCardFromDevelopmentGrid(color,level);
        resourcePriceBuffer.putAll(developmentCard.getResourcePrice());
        activePlayers.get(currentPlayer).getBoard().checkDevSpace(developmentCard,developmentSpace);
        useDiscountActivation(resourcePriceBuffer,developmentCard);

        if(activePlayers.get(currentPlayer).getBoard().checkResources(resourcePriceBuffer, chosenResources)){
            activePlayers.get(currentPlayer).getBoard().removeResources(chosenResources,chosenWarehouses);
        }
        removeCardFromDevelopmentGrid(developmentCard);
        developmentSpace.addDevelopmentCard(developmentCard);
        activePlayers.get(currentPlayer).getBoard().increaseNumOfDevCards();
    }


    /**
     * Override method moveResource puts a deposit resource in the ResourceBuffer calling the method FillBuffer
     * @param position is the position of the deposit in the board's array "deposits"
     */
    @Override
    public void moveResource(int position) throws EmptyDeposit {
        activePlayers.get(currentPlayer).fillBuffer(position);
    }

    /**
     * Override method takeResourcesFromMarket used to take the resources from the market tray, calling the method lineSelection()
     * and after that it calls the method change() to update the marble's position in the market
     * @param line (type String) - it specifies if the player wants to select a column or a row
     * @param numline (type Int) - it indicates which line the player chose
     */
    @Override
    public void takeResourcesFromMarket(String line, int numline) {
        market.lineSelection(line, numline, activePlayers.get(currentPlayer));
        market.change(line, numline);
    }

    @Override
    public void takeResourceFromMarket(String line, int numline ,ArrayList<LeaderCard> whiteMarbleCardChoice) throws LeaderCardNotFound, LeaderCardNotActivated{
        int i;
        for(i=0; i<whiteMarbleCardChoice.size();i++){
            if(!activePlayers.get(currentPlayer).getLeaderCards().contains(whiteMarbleCardChoice.get(i)) || !(whiteMarbleCardChoice.get(i).getStrategy() instanceof ConcreteStrategyMarble))
                throw new LeaderCardNotFound();
            else if(!whiteMarbleCardChoice.get(i).getStrategy().isActive())
                throw new LeaderCardNotActivated();
        }
        activePlayers.get(currentPlayer).setWhiteMarbleCardChoice(whiteMarbleCardChoice);
        market.lineSelection(line, numline, activePlayers.get(currentPlayer));
        market.change(line, numline);
    }

    /**
     * Override method placeResource used to place a resource in a specific deposit of the board.
     * To do that, we assign the strategy ConcreteStrategyResource() to the player's resources and thanks to it,
     * we are able to place the resource
     * @param depositPosition (type Int) - it specifies in which deposit we want to place the resource
     * @param resourcePosition (type Int) - it indicates which resource we want to place
     */
    @Override
    public void placeResource(int depositPosition, int resourcePosition) throws DepositHasReachedMaxLimit, DepositHasAnotherResource {
        ResourceActionStrategy strategy = new ConcreteStrategyResource(depositPosition, activePlayers.get(currentPlayer).getBoard(), activePlayers.get(currentPlayer).getResourceBuffer().get(resourcePosition).getType());
        activePlayers.get(currentPlayer).getResourceBuffer().get(resourcePosition).setStrategy(strategy);
        activePlayers.get(currentPlayer).getResourceBuffer().get(resourcePosition).useResource();
        activePlayers.get(currentPlayer).getResourceBuffer().remove(resourcePosition);
    }

    /**
     * Override method useAndChooseProdPower calls the method to check if the production power is usable, if yes the resources obtained
     * are transferred to the warehouse and the resources needed are removed from the deposits. Method without resourceType.JOLLY
     * in the resource obtained
     * @param productionPower is the production power to use
     * @param resources is the array of resources chosen by the player to activate the production power
     * @param warehouse is the array of Warehouse objects that shows where the chosen resources come from
     * @throws DifferentDimension exception thrown because the number of resources is different from the number of deposits chosen
     * @throws TooManyResourcesRequested exception thrown because there are too many resources requested
     */
    @Override
    public void useAndChooseProdPower(ProductionPower productionPower, ArrayList<ResourceType> resources, ArrayList<Warehouse> warehouse) throws DifferentDimension, TooManyResourcesRequested, EmptyDeposit, DepositDoesntHaveThisResource {
        if(productionPower.checkTakenResources(resources,warehouse,activePlayers.get(currentPlayer).getBoard())){
            activePlayers.get(currentPlayer).getBoard().removeResources(resources,warehouse);
            productionPower.addResources(activePlayers.get(currentPlayer).getBoard());
        }
    }

    /**
     * Override method useAndChooseProdPower calls the method to check if the production power is usable, if yes the resources obtained
     * are transferred to the warehouse and the resources needed are removed from the deposits. Method with resourceType.JOLLY
     * in the resource obtained
     * @param productionPower is the production power to use
     * @param resources is the array of resources chosen by the player to activate the production power
     * @param warehouse is the array of Warehouse objects that shows where the chosen resources come from
     * @throws DifferentDimension exception thrown because the number of resources is different from the number of deposits chosen
     * @throws TooManyResourcesRequested exception thrown because there are too many resources requested
     */
    @Override
    public void useAndChooseProdPower(ProductionPower productionPower, ArrayList<ResourceType> resources, ArrayList<Warehouse> warehouse, ArrayList<ResourceType> newResources) throws DifferentDimension, TooManyResourcesRequested, EmptyDeposit, DepositDoesntHaveThisResource {
        if(productionPower.checkTakenResources(resources,warehouse,activePlayers.get(currentPlayer).getBoard())) {
            activePlayers.get(currentPlayer).getBoard().removeResources(resources,warehouse);
            productionPower.addResources(activePlayers.get(currentPlayer).getBoard(), newResources);
        }
    }

    /**
     * Override method activateLeaderCard used when a player wants to activate a leader card using its ability.
     * @param cardToActivate (type LeaderCard) - it is the card that the player wants to activate
     * @throws LeaderCardNotFound if the player has not got the cardToActivate
     */
    @Override
    public void activateLeaderCard(LeaderCard cardToActivate) throws LeaderCardNotFound, NotEnoughRequirements {
        if(!activePlayers.get(currentPlayer).getLeaderCards().contains(cardToActivate)) throw new LeaderCardNotFound();
        else{
            int indexCard = activePlayers.get(currentPlayer).getLeaderCards().indexOf(cardToActivate);

            if(cardToActivate.getRequirements().checkRequirement(activePlayers.get(currentPlayer).getBoard()))
                activePlayers.get(currentPlayer).getLeaderCards().get(indexCard).useAbility();
            else throw new NotEnoughRequirements();
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
     * Method checkPlayersFaithMarkers checks the position of all players faithmarker and sets visibile the pope favor tile
     * if necessary when someone arrives in a Pope Space. It also activates the vatican report section in which is contained
     * the pope space so that the next person that cross that cell doesn't call this method.
     * @param faithmarker is the position of the faithmarker of the player that stepped on the pope space.
     */
    @Override
    public void checkPlayersFaithMarkers(int faithmarker){
        int x;
        for (Player player : activePlayers){
            if(player.getBoard().getFaithMarker() > 0){
                x = player.getBoard().getTrack().get(player.getBoard().getFaithMarker() - 1).getVaticaReportSection() - 1;
                if(player.getBoard().getTrack().get(player.getBoard().getFaithMarker() - 1).getVaticaReportSection() == activePlayers.get(currentPlayer).getBoard().getTrack().get(faithmarker - 1).getVaticaReportSection()){
                    player.getBoard().getVaticanReportSections().get(x).getPopefavortile().setVisible();
                }
            }
            player.getBoard().getVaticanReportSections().get(player.getBoard().getTrack().get(faithmarker - 1).getVaticaReportSection()-1).setActivated();
        }
    }

    /**
     * Method increaseFaithMarkerOfOtherPlayers increases the faithmarker of the players when someone hasn't put all
     * the resources obtained from the market in the deposits.
     */
    @Override
    public void increaseFaithMarkerOfOtherPlayers() {
        for (Player player : activePlayers){
            if(!player.equals(activePlayers.get(currentPlayer))){
                player.getBoard().increaseFaithMarker();
            }
        }
    }

    /**
     * Method nextPlayer handles which player is playing during his turn
     */
    public void nextPlayer(){
        activePlayers.get(currentPlayer).endTurn();
        if(!endgame){
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


    /**
     *  Method endGame called when the conditions to end the game are satisfied.
     */
    @Override
    public void endGame(){
        endgame=true;
    }

    /**
     * Method winner indicates which player has more victory points than other players
     */
    public String winner(){
        ArrayList<Integer> victoryPointsPlayers = new ArrayList<>();
        ArrayList<Integer> resourcesPlayers = new ArrayList<>();
        int maxVictoryPoints;
        int maxResources;
        String winnerUsername;

        for (Player player : activePlayers) {
            victoryPointsPlayers.add(player.getTotalVictoryPoints());
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
        return winnerUsername;
    }


}