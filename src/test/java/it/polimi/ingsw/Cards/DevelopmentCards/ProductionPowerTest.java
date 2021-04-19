package it.polimi.ingsw.Cards.DevelopmentCards;

import it.polimi.ingsw.Board.Board;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Board.Storage.Warehouse;
import it.polimi.ingsw.Exceptions.DifferentDimension;
import it.polimi.ingsw.Exceptions.TooManyResourcesRequested;
import it.polimi.ingsw.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * ProductionPowerTest class tests ProductionPower class.
 * @see ProductionPower
 */
public class ProductionPowerTest {
    Game testGame;
    HashMap<ResourceType,Integer> testResourcesNeeded;
    HashMap<ResourceType,Integer> testResourcesObtained;
    ProductionPower testProductionPower;
    Board testBoard;
    ArrayList<ResourceType> testresources;
    ArrayList<Warehouse> testwarehouse;
    /*
     * Method setup setups tests.
     * */
    @BeforeEach
    void setup(){
        testGame = new Game();
        testResourcesNeeded = new HashMap<>();
        testResourcesObtained = new HashMap<>();

        testResourcesNeeded.put(ResourceType.COIN,1);
        testResourcesNeeded.put(ResourceType.STONE,3);
        testResourcesNeeded.put(ResourceType.SERVANT,2);
        testResourcesNeeded.put(ResourceType.SHIELD,1);

        testResourcesObtained.put(ResourceType.COIN,1);
        testResourcesObtained.put(ResourceType.STONE,1);
        testResourcesObtained.put(ResourceType.SERVANT,1);
        testResourcesObtained.put(ResourceType.SHIELD,1);
        testResourcesObtained.put(ResourceType.FAITHMARKER,1);

        testBoard = new Board(testGame);
        testBoard.getStrongbox().getStrongbox().replaceAll((key, oldvalue) -> oldvalue + 10);
        testBoard.getDeposits().get(0).setResourcetype(ResourceType.COIN);
        testBoard.getDeposits().get(1).setResourcetype(ResourceType.SERVANT);
        testBoard.getDeposits().get(2).setResourcetype(ResourceType.COIN);
        testBoard.getDeposits().get(0).increaseNumberOfResources();
        testBoard.getDeposits().get(1).increaseNumberOfResources();
        testBoard.getDeposits().get(1).increaseNumberOfResources();
        testBoard.getDeposits().get(2).increaseNumberOfResources();
        testBoard.getDeposits().get(2).increaseNumberOfResources();
        testBoard.getDeposits().get(2).increaseNumberOfResources();



        testProductionPower=new ProductionPower(testResourcesNeeded,testResourcesObtained);
    }

    /** Method ResourceNeededGetterTest tests ProductionPower method getter. */
    @Test
    @DisplayName("Resource Needed getter test")
    void ResourceNeededGetterTest(){
        assertEquals(testResourcesNeeded,testProductionPower.getResourcesNeeded());
    }

    /** Method ResourceObtainedGetterTest tests ProductionPower method getter. */
    @Test
    @DisplayName("Resource Obtained getter test")
    void ResourceObtainedGetterTest(){
        assertEquals(testResourcesObtained,testProductionPower.getResourcesObtained());
    }

    @Test
    @DisplayName("Resource Obtained getter test")
    void checkTest1(){
        ArrayList<ResourceType> testresources= new ArrayList<>();
        ArrayList<Warehouse> testwarehouse= new ArrayList<>();
        testresources.add(ResourceType.COIN);
        testresources.add(ResourceType.STONE);
        testresources.add(ResourceType.STONE);
        testresources.add(ResourceType.STONE);
        testresources.add(ResourceType.SERVANT);
        testresources.add(ResourceType.SERVANT);
        testresources.add(ResourceType.SHIELD);
        testwarehouse.add(testBoard.getDeposits().get(0));
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        try {
            assertTrue(testProductionPower.checkTakenResources(testresources,testwarehouse,testBoard));
        } catch (TooManyResourcesRequested tooManyResourcesRequested) {
            tooManyResourcesRequested.printStackTrace();
        }
    }

    @Test
    @DisplayName("checkTest2 to verify that the arraylist of resources must contains almost the same number of resources of resourceNeeded")
    void checkTest2(){
        ArrayList<ResourceType> testresources= new ArrayList<>();
        ArrayList<Warehouse> testwarehouse= new ArrayList<>();
        testresources.add(ResourceType.COIN);
        testresources.add(ResourceType.STONE);
        testresources.add(ResourceType.STONE);
       testresources.add(ResourceType.SERVANT);
        testresources.add(ResourceType.SERVANT);
        testresources.add(ResourceType.SHIELD);
        testwarehouse.add(testBoard.getDeposits().get(0));
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        try {
            assertFalse(testProductionPower.checkTakenResources(testresources,testwarehouse,testBoard));
        } catch (TooManyResourcesRequested tooManyResourcesRequested) {
            tooManyResourcesRequested.printStackTrace();
        }
    }
    @Test
    @DisplayName("checkTest3 to verify that the arraylist of resources must contains almost the same number of resources of resourceNeeded with jolly resources")
    void checkTest3(){
        ArrayList<ResourceType> testresources= new ArrayList<>();
        ArrayList<Warehouse> testwarehouse= new ArrayList<>();
        testResourcesNeeded.put(ResourceType.JOLLY, 3);
        testresources.add(ResourceType.COIN);
        testresources.add(ResourceType.COIN);
        testresources.add(ResourceType.COIN);
        testresources.add(ResourceType.STONE);
        testresources.add(ResourceType.STONE);
        testresources.add(ResourceType.STONE);
        testresources.add(ResourceType.SERVANT);
        testresources.add(ResourceType.SERVANT);
        testresources.add(ResourceType.SHIELD);
        testresources.add(ResourceType.SHIELD);
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        try {
            assertTrue(testProductionPower.checkTakenResources(testresources,testwarehouse,testBoard));
        } catch (TooManyResourcesRequested tooManyResourcesRequested) {
            tooManyResourcesRequested.printStackTrace();
        }
    }

    @Test
    @DisplayName("checkTest4 to verify that the arraylist of resources must contains almost the same number of resources of resourceNeeded with jolly resources")
    void checkTest4(){
        ArrayList<ResourceType> testresources= new ArrayList<>();
        ArrayList<Warehouse> testwarehouse= new ArrayList<>();
        testResourcesNeeded.put(ResourceType.JOLLY, 3);
        testresources.add(ResourceType.COIN);
        testresources.add(ResourceType.STONE);
        testresources.add(ResourceType.STONE);
        testresources.add(ResourceType.SERVANT);
        testresources.add(ResourceType.SERVANT);
        testresources.add(ResourceType.SHIELD);
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());

        try {
            assertFalse(testProductionPower.checkTakenResources(testresources,testwarehouse,testBoard));
        } catch (TooManyResourcesRequested tooManyResourcesRequested) {
            tooManyResourcesRequested.printStackTrace();
        }
    }


    @Test
    @DisplayName("addResourcesTest1 to verify that the resources obtained from the prod power are inserted in the strongbox, test case without jolly resources")
    void addResourcesTest1(){
        testProductionPower.addResources(testBoard);

        assertEquals(11, testBoard.getStrongbox().getTotalCoins());
        assertEquals(11, testBoard.getStrongbox().getTotalStones());
        assertEquals(11, testBoard.getStrongbox().getTotalServants());
        assertEquals(11, testBoard.getStrongbox().getTotalShields());
        assertEquals(1, testBoard.getFaithMarker());
    }

    @Test
    @DisplayName("addResourcesTest2 to verify that the resources obtained from the prod power are inserted in the strongbox, test case with jolly resources")
    void addResourcesTest2(){
        testProductionPower.getResourcesObtained().put(ResourceType.JOLLY,4);
        ArrayList<ResourceType> testresources= new ArrayList<>();
        testresources.add(ResourceType.COIN);
        testresources.add(ResourceType.STONE);
        testresources.add(ResourceType.SERVANT);
        testresources.add(ResourceType.SHIELD);
        try {
            testProductionPower.addResources(testBoard,testresources);
        } catch (DifferentDimension differentDimensionForProdPower) {
            differentDimensionForProdPower.printStackTrace();
        }
        assertEquals(12, testBoard.getStrongbox().getTotalCoins());
        assertEquals(12, testBoard.getStrongbox().getTotalStones());
        assertEquals(12, testBoard.getStrongbox().getTotalServants());
        assertEquals(12, testBoard.getStrongbox().getTotalShields());
        assertEquals(1, testBoard.getFaithMarker());
    }
}
