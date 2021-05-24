package it.polimi.ingsw.model.board;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.model.board.faithtrack.Cell;
import it.polimi.ingsw.model.board.faithtrack.PopeFavorTile;
import it.polimi.ingsw.model.board.faithtrack.PopeFavorTileColor;
import it.polimi.ingsw.model.board.faithtrack.VaticanReportSection;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Deposit;
import it.polimi.ingsw.model.board.storage.Strongbox;
import it.polimi.ingsw.model.board.storage.Warehouse;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentCard;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentSpace;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.GameBoardInterface;

import java.io.*;
import java.util.*;

/**
 * Class Board represents the board that each player has
 */
public class Board implements BoardInterface {
    private int numOfDevCards;
    private int faithMarker;
    private int boardVictoryPoint;
    private final Strongbox strongbox;
    private final ArrayList<Deposit> deposits;
    private final ArrayList<DevelopmentSpace> developmentSpaces;
    private ArrayList<Cell> track;
    private final ArrayList<VaticanReportSection> vaticanReportSections;
    private final ArrayList<ProductionPower> specialProductionPowers;
    private final GameBoardInterface game;
    //private final SinglePlayerGameInterface singleGame;


    /**
     * Constructor Board creates a new Board instance
     * @param game is an interface of the class Game in which are inserted the methods for the Board
     */
    public Board(GameBoardInterface game){
        numOfDevCards = 0;
        faithMarker = 0;
        boardVictoryPoint = 0;
        this.game = game;
        //this.singleGame=singleGame;

        specialProductionPowers = new ArrayList<>();
        HashMap<ResourceType,Integer> resourceNeeded = new HashMap<>();
        HashMap<ResourceType,Integer> resourceObtained = new HashMap<>();
        resourceNeeded.put(ResourceType.JOLLY, 2);
        resourceObtained.put(ResourceType.JOLLY,1);
        ProductionPower baseProductionPower = new ProductionPower(resourceNeeded,resourceObtained);
        specialProductionPowers.add(baseProductionPower);


        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try {
            track= mapper.readValue(new File("src/main/resources/json/FaithTrack.json"), new TypeReference<>() {
            });
        } catch (IOException e) {
            System.out.println("FaithTrack.json file was not found");
        }


        strongbox = new Strongbox();
        strongbox.getStrongbox().put(ResourceType.COIN, 0);
        strongbox.getStrongbox().put(ResourceType.STONE, 0);
        strongbox.getStrongbox().put(ResourceType.SERVANT, 0);
        strongbox.getStrongbox().put(ResourceType.SHIELD, 0);


        deposits = new ArrayList<>();
        Deposit deposit1 = new Deposit(1, false);
        Deposit deposit2 = new Deposit(2, false);
        Deposit deposit3 = new Deposit(3, false);
        deposits.add(deposit1);
        deposits.add(deposit2);
        deposits.add(deposit3);


        vaticanReportSections = new ArrayList<>();
        PopeFavorTile yellowPopeFavorTile = new PopeFavorTile(PopeFavorTileColor.YELLOW, 2);
        PopeFavorTile orangePopeFavorTile = new PopeFavorTile(PopeFavorTileColor.ORANGE, 3);
        PopeFavorTile redPopeFavorTile = new PopeFavorTile(PopeFavorTileColor.RED, 4);
        VaticanReportSection vaticanReportSection1 = new VaticanReportSection(yellowPopeFavorTile);
        VaticanReportSection vaticanReportSection2 = new VaticanReportSection(orangePopeFavorTile);
        VaticanReportSection vaticanReportSection3 = new VaticanReportSection(redPopeFavorTile);
        for(Cell cell : track){
            if(cell.getVaticanReportSection() == 1){
                vaticanReportSection1.getSection().add(cell);
            }
            if(cell.getVaticanReportSection() == 2){
                vaticanReportSection2.getSection().add(cell);
            }
            if(cell.getVaticanReportSection() == 3){
                vaticanReportSection3.getSection().add(cell);
            }
        }
        vaticanReportSections.add(vaticanReportSection1);
        vaticanReportSections.add(vaticanReportSection2);
        vaticanReportSections.add(vaticanReportSection3);


        developmentSpaces = new ArrayList<>();
        DevelopmentSpace developmentSpace1 = new DevelopmentSpace();
        DevelopmentSpace developmentSpace2 = new DevelopmentSpace();
        DevelopmentSpace developmentSpace3 = new DevelopmentSpace();
        developmentSpaces.add(developmentSpace1);
        developmentSpaces.add(developmentSpace2);
        developmentSpaces.add(developmentSpace3);
    }


    /**
     * Get-methods in order to obtain the attributes' values
     */
    public int getNumOfDevCards() { return numOfDevCards; }
    /**
     * Get-methods in order to obtain the attributes' values
     */
    public ArrayList<DevelopmentSpace> getDevelopmentSpaces() {
        return developmentSpaces;
    }

    /**
     * Get-methods in order to obtain the attributes' values
     */
    public ArrayList<Cell> getTrack() {
        return track;
    }

    /**
     * Get-methods in order to obtain the attributes' values
     */
    public Strongbox getStrongbox() {
        return strongbox;
    }

    /**
     * Get-methods in order to obtain the attributes' values
     */
    public ArrayList<Deposit> getDeposits() {
        return deposits;
    }

    /**
     * Get-methods in order to obtain the attributes' values
     */
    public ArrayList<VaticanReportSection> getVaticanReportSections() {
        return vaticanReportSections;
    }

    /**
     * Get-methods in order to obtain the attributes' values
     */
    public ArrayList<ProductionPower> getSpecialProductionPowers() {
        return specialProductionPowers;
    }

    /**
     * Method getFaithMarker returns the Faith Marker of this Board object.
     */
    public int getFaithMarker() {
        return faithMarker;
    }

    /**
     * Method increaseFaithMarker adds to the faith marker a specific amount, to move forward it.
     */
    public void increaseFaithMarker(){
        faithMarker += 1;
        if(faithMarker == 24){
            game.endGame();
        }
        if(track.get(faithMarker - 1).getPopeSpace()){
            if(track.get(faithMarker - 1).getVaticanReportSection() > 0){
                if(!vaticanReportSections.get(track.get(faithMarker - 1).getVaticanReportSection()-1).getActivated()){
                    game.checkPlayersFaithMarkers(faithMarker);
                }
            }
        }
    }

    /**
     * Method getBoardVictoryPoint returns the amount of the victory points of this Board object.
     * @return the amount of the victory points (type int) of this Board object.
     */
    public int getBoardVictoryPoint() {
        boardVictoryPoint = 0;
        for (VaticanReportSection vaticanReportSection : vaticanReportSections) {
            if (vaticanReportSection.getPopefavortile().getVisible()) {
                boardVictoryPoint += vaticanReportSection.getPopefavortile().getVictorypoint();
            }
        }
        for (DevelopmentSpace developmentSpace : developmentSpaces) {
            for (int i = 0; i < developmentSpace.getDevelopmentCardsOfDevSpace().size(); i++) {
                boardVictoryPoint += developmentSpace.getDevelopmentCardsOfDevSpace().get(i).getVictorypoint();
            }
        }
        for (int i = 0; i< faithMarker; i++){
            boardVictoryPoint += track.get(i).getVictoryPoint();
        }
        boardVictoryPoint += (getTotalCoins() + getTotalServants() + getTotalStones() + getTotalShields()) / 5;

        return boardVictoryPoint;
    }

    /**
     * Get-methods that return the total amount of coins of a player
     * @return total coins
     */
    public int getTotalCoins() {
        int total;
        total = strongbox.getTotalCoins();
        for (Deposit deposit : deposits) {
            if(deposit.getResourcetype() != null){
                total += deposit.getTotalCoins();
            }
        }
        return total;
    }
    /**
     * Get-methods that return the total amount of stones of a player
     * @return total stones
     */
    public int getTotalStones() {
        int total;
        total = strongbox.getTotalStones();
        for (Deposit deposit : deposits) {
            if(deposit.getResourcetype() != null){
                total += deposit.getTotalStones();
            }
        }
        return total;
    }

    /**
     * Get-methods that return the total amount of servants of a player
     * @return total servants
     */
    public int getTotalServants() {
        int total;
        total = strongbox.getTotalServants();
        for (Deposit deposit : deposits) {
            if(deposit.getResourcetype() != null){
                total += deposit.getTotalServants();
            }
        }
        return total;
    }

    /**
     * Get-methods that return the total amount of shields of a player
     * @return total shields
     */
    public int getTotalShields() {
        int total;
        total = strongbox.getTotalShields();
        for (Deposit deposit : deposits) {
            if(deposit.getResourcetype() != null){
                total += deposit.getTotalShields();
            }        }
        return total;
    }

    /**
     * Method getTotalResources
     * @return the number of resources contained in the warehouse
     */
    public int getTotalResources(){
        return getTotalCoins() + getTotalStones() + getTotalServants() + getTotalShields();
    }


    public boolean checkResources(HashMap<ResourceType,Integer> resourcePriceBuffer, ArrayList<ResourceType> chosenResources) throws NotEnoughResources, WrongChosenResources {

        if((resourcePriceBuffer.containsKey(ResourceType.COIN)&&(resourcePriceBuffer.get(ResourceType.COIN)>getTotalCoins()))||
                (resourcePriceBuffer.containsKey(ResourceType.STONE)&&(resourcePriceBuffer.get(ResourceType.STONE)>getTotalStones()))||
                (resourcePriceBuffer.containsKey(ResourceType.SERVANT)&&(resourcePriceBuffer.get(ResourceType.SERVANT)>getTotalServants()))||
                (resourcePriceBuffer.containsKey(ResourceType.SHIELD)&&(resourcePriceBuffer.get(ResourceType.SHIELD)>getTotalShields()))){
            throw new NotEnoughResources();
        } else if((resourcePriceBuffer.containsKey(ResourceType.COIN)&&(Collections.frequency(chosenResources,ResourceType.COIN)!=resourcePriceBuffer.get(ResourceType.COIN)))||
                (resourcePriceBuffer.containsKey(ResourceType.SHIELD)&&(Collections.frequency(chosenResources,ResourceType.SHIELD)!=resourcePriceBuffer.get(ResourceType.SHIELD)))||
                (resourcePriceBuffer.containsKey(ResourceType.SERVANT)&&(Collections.frequency(chosenResources,ResourceType.SERVANT)!=resourcePriceBuffer.get(ResourceType.SERVANT)))||
                (resourcePriceBuffer.containsKey(ResourceType.STONE)&&(Collections.frequency(chosenResources,ResourceType.STONE)!=resourcePriceBuffer.get(ResourceType.STONE)))||
                (chosenResources.contains(ResourceType.COIN)&&!resourcePriceBuffer.containsKey(ResourceType.COIN))||
                (chosenResources.contains(ResourceType.STONE)&&!resourcePriceBuffer.containsKey(ResourceType.STONE))||
                (chosenResources.contains(ResourceType.SERVANT)&&!resourcePriceBuffer.containsKey(ResourceType.SERVANT))||
                (chosenResources.contains(ResourceType.SHIELD)&&!resourcePriceBuffer.containsKey(ResourceType.SHIELD))){
            throw  new WrongChosenResources();
        }
        else
            return true;
    }


    /**
     * Method checkDevSpace verifies that the development card the player wants to buy is placeable in the development space chosen. If not, the method throws an Exception.
     */
    public void checkDevSpace(DevelopmentCard developmentCard, DevelopmentSpace developmentSpace) throws DevCardNotPlaceable {
        if (!developmentSpace.isPlaceableCard(developmentCard))
            throw new DevCardNotPlaceable();
    }

    /**
     * Method removeResources removes a certain amount resources from the warehouse
     * @param chosenResources are the resources chosen to remove
     * @param chosenWarehouses are the positions of the resources chosen (the first element of chosenResources is removed from the
     *                         first element of chosenWarehouses
     * @throws DifferentDimension is the exception thrown when the size of the 2 arrays is different.
     * @throws EmptyDeposit exception thrown when the deposit is empty
     * @throws DepositDoesntHaveThisResource exception thrown when the deposit has a different type of resource instead of the resource to remove.
     */
    public void removeResources(ArrayList<ResourceType> chosenResources, ArrayList<Warehouse> chosenWarehouses) throws DifferentDimension, EmptyDeposit, DepositDoesntHaveThisResource {
        if(chosenResources.size() != chosenWarehouses.size()){
            throw new DifferentDimension();
        }
        else {
            for(int i = 0; i<chosenResources.size(); i++){
                    chosenWarehouses.get(i).remove(chosenResources.get(i));
            }
        }
    }

    /**
     * Method increaseNumOfDevCards increases the number of development cards that are present in the player's board.
     * It is called when a player buys a new development card.
     */
    public void increaseNumOfDevCards(){
        numOfDevCards+=1;
        if(numOfDevCards==7) game.endGame();
    }


}
