package it.polimi.ingsw.Board;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Board.FaithTrack.Cell;
import it.polimi.ingsw.Board.FaithTrack.PopeFavorTile;
import it.polimi.ingsw.Board.FaithTrack.PopeFavorTileColor;
import it.polimi.ingsw.Board.FaithTrack.VaticanReportSection;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Board.Storage.Deposit;
import it.polimi.ingsw.Board.Storage.Strongbox;
import it.polimi.ingsw.Board.Storage.Warehouse;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentCard;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentSpace;
import it.polimi.ingsw.Cards.DevelopmentCards.ProductionPower;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.GameBoardInterface;

import java.io.*;
import java.util.*;

public class Board implements BoardInterface {
    private int numOfDevCards;
    private int faithMarker;
    private int boardVictoryPoint;
    private Strongbox strongbox;
    private ArrayList<Deposit> deposits;
    private ArrayList<DevelopmentSpace> developmentSpaces;
    private ArrayList<Cell> track;
    private ArrayList<VaticanReportSection> vaticanReportSections;
    private ArrayList<ProductionPower> specialProductionPowers;
    private GameBoardInterface game;

    /**
     * Class's constructor that'll be used in the setup method
     */
    public Board(GameBoardInterface game){
        numOfDevCards = 0;
        faithMarker = 0;
        boardVictoryPoint = 0;
        this.game = game;

        specialProductionPowers = new ArrayList<>();
        HashMap<ResourceType,Integer> resourceNeeded = new HashMap<>();
        HashMap<ResourceType,Integer> resourceObtained = new HashMap<>();
        resourceNeeded.put(ResourceType.JOLLY, 2);
        resourceObtained.put(ResourceType.JOLLY,1);
        ProductionPower baseProductionPower = new ProductionPower(resourceNeeded,resourceObtained);
        specialProductionPowers.add(baseProductionPower);


        Gson gson = new Gson();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/json/FaithTrack.json"));
            track = gson.fromJson(br, new TypeToken<List<Cell>>(){}.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        strongbox = new Strongbox();
        strongbox.getStrongbox().put(ResourceType.COIN, 0);
        strongbox.getStrongbox().put(ResourceType.STONE, 0);
        strongbox.getStrongbox().put(ResourceType.SERVANT, 0);
        strongbox.getStrongbox().put(ResourceType.SHIELD, 0);


        deposits = new ArrayList<>();
        Deposit deposit1 = new Deposit(1);
        Deposit deposit2 = new Deposit(2);
        Deposit deposit3 = new Deposit(3);
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
            if(cell.getVaticaReportSection() == 1){
                vaticanReportSection1.getSection().add(cell);
            }
            if(cell.getVaticaReportSection() == 2){
                vaticanReportSection2.getSection().add(cell);
            }
            if(cell.getVaticaReportSection() == 3){
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
        if(track.get(faithMarker - 1).isPopeSpace()){
            if(track.get(faithMarker - 1).getVaticaReportSection() > 0){
                if(!vaticanReportSections.get(track.get(faithMarker - 1).getVaticaReportSection()-1).isActivated()){
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
            if (vaticanReportSection.getPopefavortile().isVisible()) {
                boardVictoryPoint += vaticanReportSection.getPopefavortile().getVictorypoint();
            }
        }
        for (DevelopmentSpace developmentSpace : developmentSpaces) {
            for (int i = 0; i < developmentSpace.getDevelopmentCardsOfDevSpace().size(); i++) {
                boardVictoryPoint += developmentSpace.getDevelopmentCardsOfDevSpace().get(i).getVictoryPoint();
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
     */
    public void removeResources(ArrayList<ResourceType> chosenResources, ArrayList<Warehouse> chosenWarehouses)throws DifferentDimension {
        if(chosenResources.size() != chosenWarehouses.size()){
            throw new DifferentDimension();
        }
        else {
            for(int i = 0; i<chosenResources.size(); i++){
                try {
                    chosenWarehouses.get(i).remove(chosenResources.get(i));
                } catch (DepositDoesntHaveThisResource | EmptyDeposit depositDoesntHaveThisResource) {
                    depositDoesntHaveThisResource.printStackTrace();
                }
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
