package it.polimi.ingsw.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.model.board.resources.ConcreteStrategyResource;
import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.board.resources.ResourceActionStrategy;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Warehouse;
import it.polimi.ingsw.model.cards.developmentcards.*;
import it.polimi.ingsw.model.cards.leadercards.*;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.marbles.Marble;
import it.polimi.ingsw.model.marbles.MarketTray;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Game class contains the main logic of "Master of Renaissance".
 */

// TODO: 11/05/2021 mettere synchronized alcune classi di game per gestire le partite multiple
public class Game implements GameInterface, GameBoardInterface, GamePlayerInterface {

    private final ArrayList<Player> players;
    private final ArrayList<Player> activePlayers;
    private final HashMap<Integer,Player> idClientActivePlayers;
    private int idGame;
    private ArrayList<LeaderCard> leaderDeck;
    protected ArrayList<LinkedList<DevelopmentCard>> developmentGrid;
    private final MarketTray market;
    private ArrayList<Integer> leaderCardsChosen;
    private volatile int currentPlayer = 0;
    private boolean endgame = false;
    private GameStates gameStates = GameStates.FILL_LOBBY;
    private int numof_players;
    private final ArrayList<DevelopmentCard> initialDevGrid;
    protected String winner;

    /**
     * Constructor Game creates a new Game instance.
     */
    public Game() {
        players = new ArrayList<>();
        activePlayers = new ArrayList<>();
        idClientActivePlayers = new HashMap<>();
        leaderDeck = new ArrayList<>();
        developmentGrid = new ArrayList<>();
        initialDevGrid = new ArrayList<>();
        market = new MarketTray();
    }

    /**
     * Method setup called when a game starts
     */
    public void setup(){
        changePlayersPosition(); //to define the player's turn
        createDevelopmentGrid(); //to place the cards in the right order
        createLeaderDeck(); //to create and shuffle the leader card
        distributeLeaderCards(); //to give 4 leader cards to the each player
        additionalFaithMarkerSetup();

    }

    /**
     * Method changePlayersPosition chooses who the first player is and according to that, the other player's position
     */
    public void changePlayersPosition(){
        int position=0;
        int random = (int)(Math.random()*(activePlayers.size()));

        Collections.rotate(activePlayers, random);

        for (Player player: activePlayers){
            player.setPosition(position);
            position++;
        }


    }


    /**
     * Method additionalSetup distributes resources to the players according to their position's turn
     */
    public void additionalResourceSetup(ResourceType resourceType, int depositPosition, int idClient) throws DepositHasReachedMaxLimit, DepositHasAnotherResource {
        ConcreteStrategyResource concreteStrategyResource = new ConcreteStrategyResource(depositPosition, idClientActivePlayers.get(idClient).getBoard(), resourceType);
        Resource resource = new Resource(resourceType);
        resource.setStrategy(concreteStrategyResource);
        resource.useResource();
    }

    /**
     * Method additionalFaithMarkerSetup increase the faith points of the players according to their position's turn
     */
    public void additionalFaithMarkerSetup(){
        if(activePlayers.size()>2){
            activePlayers.get(2).getBoard().increaseFaithMarker();
            if(activePlayers.size()>3){
                activePlayers.get(3).getBoard().increaseFaithMarker();
            }
        }
    }

    /**
     * Method createNewPlayer creates a new player in the match.
     * @param username of type String - the player's username
     */
    public void createNewPlayer(String username, Integer idClient) {
        Player player = new Player(username, this, idClient);
        players.add(player);
        activePlayers.add(player);
        idClientActivePlayers.put(idClient,player);

    }

    /**
     * Method getPositionPlayer returns the position in the game of a player
     * @param username (type String) - it is the username of the player
     */
    public int getPositionPlayer(String username){
       int position = -1;
        for (Player player: activePlayers){
            if (player.getUsername().equals(username)) position = activePlayers.indexOf(player);
        }
        return position;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public HashMap<Integer, Player> getIdClientActivePlayers() {
        return idClientActivePlayers;
    }


    //TODO: testare
    /**
     * Method getTable return the table representing the position of the marbles.
     */
    public Marble[][] getTable() {
        return market.getTable();
    }


    public int getNumof_players() {
        return numof_players;
    }

    public void setNumof_players(int numof_players) {
        this.numof_players = numof_players;
    }


    /**
     * Override method chooseLeaderCardToRemove used when the player has to choose two leader cards at the beginning of the game
     * @param idCard1 (type int) - it is the card's id that the player wants to discard
     * @param idCard2 (type int) - it is the card's id that the player wants to discard
     * @throws LeaderCardNotFound if the player has not got the two chosenCards
     */
    @Override
    public void chooseLeaderCardToRemove(int idCard1, int idCard2) throws LeaderCardNotFound {

        LeaderCard[] leaderCards = activePlayers.get(currentPlayer).getLeaderCards().stream().filter(card -> (card.getId()==idCard1 ||
                card.getId() == idCard2)).toArray(LeaderCard[]::new);

        for (LeaderCard cardToRemove: leaderCards){
            activePlayers.get(currentPlayer).removeLeaderCard(cardToRemove);
        }

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

    public ArrayList<Integer> getLeaderCardsChosen() {
        return leaderCardsChosen;
    }

    /**
     * Test method getCurrentPlayer returns the current player who is playing
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Method setState sets the state of the model in order to know which command are accepted or discarded
     * @param gameStates is the new state of the model
     */
    public void setState(GameStates gameStates) {
        this.gameStates = gameStates;
    }

    public String getWinner() {
        return winner;
    }

    /**
     *
     */
    @Override
    public GameStates getState() {
        return gameStates;
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

            for(int i=0; i<12;i++){
                initialDevGrid.add(developmentGrid.get(i).getLast());
            }
        }catch (FileNotFoundException ex){
            System.out.println("Token.json file was not found");
        }
    }

    public ArrayList<DevelopmentCard> getInitialDevGrid(){
        return initialDevGrid;
    }

    // TODO: 14/05/2021 togliere getInitialDevGrid e fare una funzione universale che prende le carte visibili nella devGrid
    // TODO: 14/05/2021 da mandare ai client. gestire anche il caso in cui una pila di carte (per esempio quelle di livello 2 colore blu) sia vuota
    public ArrayList<DevelopmentCard> getDevGridLite(){
        ArrayList<DevelopmentCard> liteDevGrid = new ArrayList<>();
        for(int i=0; i<12;i++){
            liteDevGrid.add(developmentGrid.get(i).getLast());
        }
        return liteDevGrid;
    }


    /**
     * Method createLeaderDeck creates and shuffles the Leader Cards' Deck using the JSON file
     */
    public void createLeaderDeck(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        try {
            leaderDeck = mapper.readValue(new File("src/main/resources/json/LeaderCard.json"), new TypeReference<>() {
            });

            leaderDeck.forEach(leaderCard -> {
                if (leaderCard.getType().equals(LeaderCardType.DISCOUNT)) leaderCard.setStrategy(new ConcreteStrategyDiscount(leaderCard.getResourceType()));
                if (leaderCard.getType().equals(LeaderCardType.WHITE_MARBLE)) leaderCard.setStrategy(new ConcreteStrategyMarble(leaderCard.getResourceType()));
            });

            Collections.shuffle(leaderDeck);
        } catch (IOException e) {
            System.out.println("LeaderCard.json file was not found");
        }
    }


    /**
     * Method chooseCardFromDevelopmentGrid returns the selected card that a Player wants to buy
     * @param idCard (type Int) - it is the card's id that the player wants
     * @return the development card with a specific color and level. If there is no card with those characteristics,
     * it returns null
     */
    public DevelopmentCard chooseCardFromDevelopmentGrid(int idCard) throws DevelopmentCardNotFound{
        DevelopmentCard chosenCard = null;


        for (LinkedList<DevelopmentCard> developmentCards : developmentGrid) {
            if (!developmentCards.isEmpty() && developmentCards.getLast().getId()==idCard) {
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
            if (!developmentCards.isEmpty() && developmentCards.getLast().getId() == cardToRemove.getId()) {
                developmentCards.removeLast();
                found = true;
            }
        }
        if(!found) throw new DevelopmentCardNotFound();
    }

    /**
     * Method distributeLeaderCards distributes 4 leader cards to each active player
     */
    public synchronized void distributeLeaderCards(){
        IntStream.range(0, 4).map(numDealtCards -> activePlayers.size()).flatMap(bound -> IntStream.range(0, bound)).forEach(whichPlayer -> {
            players.get(whichPlayer).addLeaderCard(leaderDeck.get(leaderDeck.size() - 1));
            leaderDeck.remove(leaderDeck.size() - 1);
        });
    }

    /**
     * Method useDiscountActivation calls the method useDiscount from LeaderCard class if the arrayList leaderCardChosen contains the id of the Leader Cards chosen by the current player.
     * At the end of the method the array leaderCardsChosen will be null.
     */
    public void useDiscountActivation(HashMap<ResourceType,Integer> resourcePriceBuffer, DevelopmentCard developmentCard){
        int i,j;
        if(leaderCardsChosen!= null) {
            for (i = 0; i < leaderCardsChosen.size(); i++) {
                for(j=0; j<activePlayers.get(currentPlayer).getLeaderCards().size();j++){
                    if (activePlayers.get(currentPlayer).getLeaderCards().get(j).getId()==leaderCardsChosen.get(i)){
                        activePlayers.get(currentPlayer).getLeaderCards().get(j).useDiscount(developmentCard, resourcePriceBuffer);
                        break;
                    }
                }
            }
            leaderCardsChosen = null;
        }
    }

    /**
     * Override method chooseDiscountActivation is used to set the array leaderCardsChosen with the array in input leaderCards.
     * @param leaderCards (type ArrayList<Integer>) - it is the array of Leader Cards'id the player chooses
     * to use to pay the development card cost with a discount.
     */
    @Override
    public void chooseDiscountActivation(ArrayList<Integer> leaderCards) throws DiscountCannotBeActivated {
        int i,j,num;
        num=0;
        leaderCardsChosen= new ArrayList<>();
        for (i=0;i<leaderCards.size();i++) {
            for(j=0; j<activePlayers.get(currentPlayer).getLeaderCards().size();j++){
                if (activePlayers.get(currentPlayer).getLeaderCards().get(j).getId()== leaderCards.get(i) && activePlayers.get(currentPlayer).getLeaderCards().get(j).getStrategy() instanceof ConcreteStrategyDiscount && activePlayers.get(currentPlayer).getLeaderCards().get(j).getStrategy().isActive()){
                    num++;
                    break;
                }
            }
        }
        if(num==leaderCards.size())
            leaderCardsChosen = leaderCards;
        else
            throw new DiscountCannotBeActivated();
    }

    /**
     * Override method buyDevCard is used to buy a development card from the development grid.
     * The method puts in development card the return value of chooseCardFromDevelopmentGrid method and calls checkDevSpace method from board to verify that the player can place the development card chosen in the development space chosen.
     * Then the method calls checkResources from Board to verify that che resources chosen to buy the card are correct; if so, the resources are removed from board with the method removeResources.
     * @param idCard is the id of the card that the player wants to buy.
     * @param chosenResources is the array of Resources chosen by the player to buy the card.
     * @param chosenWarehouses is the array of Warehouse objects that shows where the chosen resources come from.
     * @param developmentSpace is the dev space chosen by the player to place the development card.
     */
    @Override
    public void buyDevCard(int idCard, ArrayList<ResourceType> chosenResources, ArrayList<Warehouse> chosenWarehouses, DevelopmentSpace developmentSpace) throws DevelopmentCardNotFound, DevCardNotPlaceable, NotEnoughResources, WrongChosenResources, DifferentDimension, EmptyDeposit, DepositDoesntHaveThisResource {
        DevelopmentCard developmentCard;
        HashMap<ResourceType, Integer> resourcePriceBuffer = new HashMap<>();

        developmentCard = chooseCardFromDevelopmentGrid(idCard);
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
     * @param whiteMarbleCardChoice is the array list of the leader card's id with white marble ability the player wants to use to transform the white marbles.
     */
    @Override
    public void takeResourceFromMarket(String line, int numline, ArrayList<Integer> whiteMarbleCardChoice) throws LeaderCardNotFound, LeaderCardNotActivated{
        if (whiteMarbleCardChoice != null) {
            int i, j, num;
            for (i = 0; i < whiteMarbleCardChoice.size(); i++) {
                num = 0;
                for (j = 0; j < activePlayers.get(currentPlayer).getLeaderCards().size(); j++) {
                    if (activePlayers.get(currentPlayer).getLeaderCards().get(j).getId() == whiteMarbleCardChoice.get(i) && activePlayers.get(currentPlayer).getLeaderCards().get(j).getStrategy() instanceof ConcreteStrategyMarble) {
                        num++;
                        break;
                    }
                }
                if (num != 1)
                    throw new LeaderCardNotFound();
                else if (!activePlayers.get(currentPlayer).getLeaderCards().get(j).getStrategy().isActive())
                    throw new LeaderCardNotActivated();
            }
            activePlayers.get(currentPlayer).setWhiteMarbleCardChoice(whiteMarbleCardChoice);
        }
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
            if(newResources == null){
                productionPower.addResources(activePlayers.get(currentPlayer).getBoard());
            }
            else{
                productionPower.addResources(activePlayers.get(currentPlayer).getBoard(), newResources);
            }        }
    }

    /**
     * Override method activateLeaderCard used when a player wants to activate a leader card using its ability.
     * @param idCardToActivate (type int) - it is the card's id that the player wants to activate
     * @throws LeaderCardNotFound if the player has not got the cardToActivate
     */
    @Override
    public void activateLeaderCard(int idCardToActivate) throws LeaderCardNotFound, NotEnoughRequirements {
        boolean found = false;

        for (LeaderCard leaderCard: activePlayers.get(currentPlayer).getLeaderCards()){
            if (!found && leaderCard.getId() == idCardToActivate){
                found = true;
                if(leaderCard.getRequirements().checkRequirement(activePlayers.get(currentPlayer).getBoard()))
                    leaderCard.useAbility();
                else throw new NotEnoughRequirements();
            }
        }

        if(!found) throw new LeaderCardNotFound();

    }


    /**
     * Override method discardLeaderCard used when a player wants to discard a leader card. In doing so, the player
     * receives one faith point.
     * @param idCardToDiscard (type int) - it is the card's id that the player discards
     * @throws LeaderCardNotFound if the player has not got the cardToDiscard
     */
    @Override
    public void discardLeaderCard(int idCardToDiscard) throws LeaderCardNotFound {

        LeaderCard[] leaderCards = activePlayers.get(currentPlayer).getLeaderCards().stream().filter(card ->
                (card.getId()==idCardToDiscard)).
                toArray(LeaderCard[]::new);

        for (LeaderCard cardToDiscard: leaderCards){
            activePlayers.get(currentPlayer).removeLeaderCard(cardToDiscard);
            activePlayers.get(currentPlayer).getBoard().increaseFaithMarker();
        }

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
            else currentPlayer++;
        }
        else {
            if(currentPlayer!=activePlayers.size()-1){
                currentPlayer++;
            }
            else {
                winner();
            }
        }
    }


    /**
     *  Method endGame called when the conditions to end the game are satisfied.
     */
    public void endGame(){
        endgame=true;
    }

    /**
     * Method winner indicates which player has more victory points than other players
     */
    public void winner(){
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
        winner = winnerUsername;
    }


}
