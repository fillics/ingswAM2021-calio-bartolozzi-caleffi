package it.polimi.ingsw.Board;

import it.polimi.ingsw.Board.FaithTrack.Cell;
import it.polimi.ingsw.Board.FaithTrack.PopeFavorTile;
import it.polimi.ingsw.Board.FaithTrack.PopeFavorTileColor;
import it.polimi.ingsw.Board.FaithTrack.VaticanReportSection;
import it.polimi.ingsw.Board.Resources.ConcreteStrategyResource;
import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Board.Storage.Warehouse;
import it.polimi.ingsw.Cards.DevelopmentCards.*;
import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * BoardTest class tests Board class.
 * @see Board
 */
public class BoardTest {
    Game testGame;
    Board testBoard;
    DevelopmentCard testDevelopmentCard1;
    DevelopmentCard testDevelopmentCard2;
    DevelopmentCard testDevelopmentCard3;
    Cell cell1;
    Cell cell2;
    Cell cell3;
    Cell cell4;
    Cell cell5;
    Cell cell6;
    Cell cell7;
    Cell cell8;
    Cell cell9;
    Cell cell10;
    VaticanReportSection testVaticanReportSection1;
    VaticanReportSection testVaticanReportSection2;
    VaticanReportSection testVaticanReportSection3;
    ProductionPower testProductionPower;
    HashMap<ResourceType,Integer> testHashMap;
    HashMap<ResourceType,Integer> testResourcePrice;
    PopeFavorTile testPopeFavorTile;
    Resource coin;
    Resource servant;

    HashMap<ResourceType,Integer> resourcePriceBuffer;
    DevelopmentCard testDevelopmentCard4;
    ProductionPower testProductionPower4;
    HashMap<ResourceType,Integer> testResourcePrice4;
    HashMap<ResourceType,Integer> testResourcesNeeded4;
    HashMap<ResourceType,Integer> testResourcesObtained4;
    ArrayList<ResourceType> chosenResources;
    ArrayList<Warehouse> chosenWarehouses;

    /** Method setup setups tests. */
    @BeforeEach
    void setup() throws DepositHasReachedMaxLimit, DepositHasAnotherResource {
        testBoard = new Board(testGame);
        coin = new Resource(ResourceType.COIN);
        servant = new Resource(ResourceType.SERVANT);
        servant.setStrategy(new ConcreteStrategyResource(0, testBoard, ResourceType.SERVANT));
        coin.setStrategy(new ConcreteStrategyResource(2, testBoard, ResourceType.COIN));
        testHashMap = new HashMap<>();
        testHashMap.put(ResourceType.COIN, 0);
        testProductionPower = new ProductionPower(testHashMap,testHashMap);
        testResourcePrice= new HashMap<>();
        testResourcePrice.put(ResourceType.SERVANT,2);
        coin.useResource();
        coin.useResource();
        coin.useResource();
        servant.useResource();


        testBoard.getStrongbox().getStrongbox().put(ResourceType.COIN,3);
        testBoard.getStrongbox().getStrongbox().put(ResourceType.STONE,50);
        testBoard.getStrongbox().getStrongbox().put(ResourceType.SERVANT,7);
        testBoard.getStrongbox().getStrongbox().put(ResourceType.SHIELD,1);


        testDevelopmentCard1 = new DevelopmentCard(Level.ONE, CardColor.GREEN, testProductionPower,testResourcePrice, 1);
        testDevelopmentCard2 = new DevelopmentCard(Level.ONE, CardColor.GREEN, testProductionPower,testResourcePrice, 1);
        testDevelopmentCard3 = new DevelopmentCard(Level.ONE, CardColor.GREEN, testProductionPower,testResourcePrice, 1);
        testBoard.getDevelopmentSpaces().get(0).addDevelopmentCard(testDevelopmentCard1);
        testBoard.getDevelopmentSpaces().get(1).addDevelopmentCard(testDevelopmentCard2);
        testBoard.getDevelopmentSpaces().get(2).addDevelopmentCard(testDevelopmentCard3);


        testPopeFavorTile = new PopeFavorTile(PopeFavorTileColor.YELLOW, 1);
        testPopeFavorTile.setVisible();
        cell1 = new Cell(1,true,1);
        cell2 = new Cell(1,true,1);
        cell3 = new Cell(1,true,1);
        cell4 = new Cell(1,true,2);
        cell5 = new Cell(1,true,2);
        cell6 = new Cell(1,true,2);
        cell7 = new Cell(1,true,3);
        cell8 = new Cell(1,true,3);
        cell9 = new Cell(1,true,3);
        cell10 = new Cell(1,true,3);
        testVaticanReportSection1 = new VaticanReportSection(testPopeFavorTile);
        testVaticanReportSection2 = new VaticanReportSection(testPopeFavorTile);
        testVaticanReportSection3 = new VaticanReportSection(testPopeFavorTile);
        testBoard.getVaticanReportSections().add(testVaticanReportSection1);
        testBoard.getVaticanReportSections().add(testVaticanReportSection2);
        testBoard.getVaticanReportSections().add(testVaticanReportSection3);
        testBoard.getVaticanReportSections().get(0).getSection().add(cell1);
        testBoard.getVaticanReportSections().get(0).getSection().add(cell2);
        testBoard.getVaticanReportSections().get(0).getSection().add(cell3);
        testBoard.getVaticanReportSections().get(1).getSection().add(cell4);
        testBoard.getVaticanReportSections().get(1).getSection().add(cell5);
        testBoard.getVaticanReportSections().get(1).getSection().add(cell6);
        testBoard.getVaticanReportSections().get(2).getSection().add(cell7);
        testBoard.getVaticanReportSections().get(2).getSection().add(cell8);
        testBoard.getVaticanReportSections().get(2).getSection().add(cell9);
        testBoard.getVaticanReportSections().get(2).getSection().add(cell10);


        testBoard.getTrack().add(cell1);
        testBoard.getTrack().add(cell2);
        testBoard.getTrack().add(cell3);
        testBoard.getTrack().add(cell4);
        testBoard.getTrack().add(cell5);
        testBoard.getTrack().add(cell6);
        testBoard.getTrack().add(cell7);
        testBoard.getTrack().add(cell8);
        testBoard.getTrack().add(cell9);
        testBoard.getTrack().add(cell10);

        resourcePriceBuffer=new HashMap<>();
        resourcePriceBuffer.put(ResourceType.SERVANT,1);
        resourcePriceBuffer.put(ResourceType.COIN,2);

        testResourcePrice4= new HashMap<>();
        testResourcesNeeded4 = new HashMap<>();
        testResourcesObtained4 = new HashMap<>();
        testResourcePrice4.put(ResourceType.SERVANT,5);
        testResourcesNeeded4.put(ResourceType.STONE,2);
        testResourcesObtained4.put(ResourceType.FAITHMARKER,2);
        testResourcesObtained4.put(ResourceType.COIN,2);
        testProductionPower4= new ProductionPower(testResourcesNeeded4,testResourcesObtained4);
        testDevelopmentCard4= new DevelopmentCard(Level.TWO, CardColor.PURPLE,testProductionPower4,testResourcePrice4, 7);
        chosenResources= new ArrayList<>();
        chosenWarehouses= new ArrayList<>();
        //chosenResources.add(ResourceType.STONE);
        //chosenWarehouses.add(testBoard.getStrongbox());
        //chosenResources.add(ResourceType.SHIELD);
        //chosenWarehouses.add(testBoard.getStrongbox());
        chosenResources.add(ResourceType.SERVANT);
        chosenWarehouses.add(testBoard.getDeposits().get(0));
        chosenResources.add(ResourceType.COIN);
        chosenWarehouses.add(testBoard.getDeposits().get(2));
        chosenResources.add(ResourceType.COIN);
        chosenWarehouses.add(testBoard.getDeposits().get(2));
    }

    /** Method BoardGetterTest tests board methods getter. */
    @Test
    @DisplayName("getBoardVictoryPoint test")
    void VictoryPointGetterTest() {
        assertEquals(19,testBoard.getBoardVictoryPoint());
    }

    /** Method BoardTest tests board getter. */
    @Test
    @DisplayName("Board getters test")
    void BoardGetterTest() {
        assertEquals(1,testBoard.getTotalShields());
        assertEquals(8,testBoard.getTotalServants());
        assertEquals(6,testBoard.getTotalCoins());
        assertEquals(50,testBoard.getTotalStones());
        assertEquals(0,testBoard.getNumOfDevCards());
        assertEquals(0,testBoard.getFaithMarker());
        testBoard.increaseFaithMarker();
        assertEquals(1, testBoard.getFaithMarker());
    }
    /*I don't know how to test the other 6 getters that return the structures that contain development cards,
    deposits, strongbox, production power, vatican report sections and track. Anyway, this method were used in
    the setup method to modify the board.*/

    /** Method CheckResourcesTest tests board method checkResources. */
    @Test
    @DisplayName("Check Resources test")
    void CheckResourcesTest() {
        try {
            testBoard.checkResources(resourcePriceBuffer,chosenResources);
        } catch (NotEnoughResources | WrongChosenResources resourcesProblem) {
            resourcesProblem.printStackTrace();
        }
    }

    /** Method CheckDevSpacesTest tests board method checkDevSpaces. */
    @Test
    @DisplayName("Check DevSpace test")
    void CheckDevSpaceTest() {
        try {
            testBoard.checkDevSpace(testDevelopmentCard4,testBoard.getDevelopmentSpaces().get(0));
        } catch (DevCardNotPlaceable devCardNotPlaceable) {
            devCardNotPlaceable.printStackTrace();
        }
    }

    /** Method RemoveResourcesTest tests board method removeResources. */
    @Test
    @DisplayName("Remove Resources test")
    void RemoveResourcesTest() {
        try {
            testBoard.removeResources(chosenResources,chosenWarehouses);
        } catch (DifferentDimension differentDimensionForProdPower) {
            differentDimensionForProdPower.printStackTrace();
        }
        assertEquals(7,testBoard.getTotalServants());
        assertEquals(4,testBoard.getTotalCoins());
        assertEquals(1,testBoard.getTotalShields());
        assertEquals(50,testBoard.getTotalStones());
    }

}

