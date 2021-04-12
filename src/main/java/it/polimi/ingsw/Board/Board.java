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
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentSpace;
import it.polimi.ingsw.Cards.DevelopmentCards.ProductionPower;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board {
    private int NumOfDevCard;
    private int faithMarker;
    private int boardVictoryPoint;
    private Strongbox strongbox;
    private ArrayList<Deposit> deposits;
    private ArrayList<DevelopmentSpace> developmentSpaces;
    private ArrayList<Cell> track;
    private ArrayList<VaticanReportSection> vaticanReportSections;
    private ArrayList<ProductionPower> specialProductionPowers;

    /**
     * Class's constructor that'll be used in the setup method
     */
    public Board() {
        NumOfDevCard = 0;
        faithMarker = 0;
        boardVictoryPoint = 0;


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
        vaticanReportSection1.getSection().add(track.get(5));
        vaticanReportSection1.getSection().add(track.get(6));
        vaticanReportSection1.getSection().add(track.get(7));
        vaticanReportSection1.getSection().add(track.get(8));
        vaticanReportSection2.getSection().add(track.get(12));
        vaticanReportSection2.getSection().add(track.get(13));
        vaticanReportSection2.getSection().add(track.get(14));
        vaticanReportSection2.getSection().add(track.get(15));
        vaticanReportSection2.getSection().add(track.get(16));
        vaticanReportSection3.getSection().add(track.get(19));
        vaticanReportSection3.getSection().add(track.get(20));
        vaticanReportSection3.getSection().add(track.get(21));
        vaticanReportSection3.getSection().add(track.get(22));
        vaticanReportSection3.getSection().add(track.get(23));
        vaticanReportSection3.getSection().add(track.get(24));
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
    public int getNumOfDevCard() { return NumOfDevCard; }
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
    }

    /**
     * Method getBoardVictoryPoint returns the amount of the victory points of this Board object.
     * @return the amount of the victory points (type int) of this Board object.
     */
    public int getBoardVictoryPoint() {
        for (VaticanReportSection vaticanReportSection : vaticanReportSections) {
            if (vaticanReportSection.getPopefavortile().isVisible()) {
                boardVictoryPoint += vaticanReportSection.getPopefavortile().getVictorypoint();
            }
        }
        for (DevelopmentSpace developmentSpace : developmentSpaces) {
            for (int i = 0; i < developmentSpace.getDevelopmentSpace().size(); i++) {
                boardVictoryPoint += developmentSpace.getDevelopmentSpace().get(i).getVictoryPoint();
            }
        }
        for (int i = 0; i< faithMarker; i++){
            boardVictoryPoint += track.get(i).getVictoryPoint();
        }
        boardVictoryPoint += (getTotalCoins() + getTotalServants() + getTotalStones() + getTotalShields()) / 5;
        return boardVictoryPoint;
    }

    /**
     * Get-methods that return the total amount of resources of a player
     * @return total resources
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

    public int getTotalShields() {
        int total;
        total = strongbox.getTotalShields();
        for (Deposit deposit : deposits) {
            if(deposit.getResourcetype() != null){
                total += deposit.getTotalShields();
            }        }
        return total;
    }
    public int getTotalResources(){
        return getTotalCoins() + getTotalStones() + getTotalServants() + getTotalShields();
    }
}
