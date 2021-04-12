package it.polimi.ingsw.Cards.DevelopmentCards;

import it.polimi.ingsw.Board.Board;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Board.Storage.Warehouse;
import it.polimi.ingsw.Exceptions.TooManyResourcesRequested;
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
        testResourcesNeeded = new HashMap<>();
        testResourcesObtained = new HashMap<>();

        testResourcesNeeded.put(ResourceType.COIN,1);
        testResourcesNeeded.put(ResourceType.STONE,1);
        testResourcesNeeded.put(ResourceType.SERVANT,1);
        testResourcesNeeded.put(ResourceType.SHIELD,1);

        testResourcesObtained.put(ResourceType.COIN,1);
        testResourcesObtained.put(ResourceType.STONE,1);
        testResourcesObtained.put(ResourceType.SERVANT,1);
        testResourcesObtained.put(ResourceType.SHIELD,1);
        testResourcesObtained.put(ResourceType.FAITHMARKER,1);

        testBoard = new Board();
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
        testresources.add(ResourceType.SERVANT);
        testresources.add(ResourceType.SHIELD);
        testwarehouse.add(testBoard.getDeposits().get(0));
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        try {
            assertTrue(testProductionPower.check(testresources,testwarehouse,testBoard));
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
        testresources.add(ResourceType.SERVANT);
        testwarehouse.add(testBoard.getDeposits().get(0));
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        try {
            assertFalse(testProductionPower.check(testresources,testwarehouse,testBoard));
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
        testresources.add(ResourceType.STONE);
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
        testwarehouse.add(testBoard.getStrongbox());

        try {
            assertTrue(testProductionPower.check(testresources,testwarehouse,testBoard));
        } catch (TooManyResourcesRequested tooManyResourcesRequested) {
            tooManyResourcesRequested.printStackTrace();
        }
    }

    @Test
    @DisplayName("checkTest3 to verify that the arraylist of resources must contains almost the same number of resources of resourceNeeded with jolly resources")
    void checkTest4(){
        ArrayList<ResourceType> testresources= new ArrayList<>();
        ArrayList<Warehouse> testwarehouse= new ArrayList<>();
        testResourcesNeeded.put(ResourceType.JOLLY, 3);
        testresources.add(ResourceType.COIN);
        testresources.add(ResourceType.STONE);
        //testresources.add(ResourceType.STONE);
        testresources.add(ResourceType.STONE);
        testresources.add(ResourceType.SERVANT);
        testresources.add(ResourceType.SERVANT);
        testresources.add(ResourceType.SHIELD);
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        //testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());
        testwarehouse.add(testBoard.getStrongbox());

        try {
            assertFalse(testProductionPower.check(testresources,testwarehouse,testBoard));
        } catch (TooManyResourcesRequested tooManyResourcesRequested) {
            tooManyResourcesRequested.printStackTrace();
        }
    }
}
