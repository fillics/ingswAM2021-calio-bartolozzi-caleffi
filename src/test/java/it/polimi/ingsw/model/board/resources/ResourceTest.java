package it.polimi.ingsw.model.board.resources;
import it.polimi.ingsw.exceptions.AnotherDepositContainsThisResource;
import it.polimi.ingsw.exceptions.InvalidResource;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.board.storage.Deposit;
import it.polimi.ingsw.exceptions.DepositHasAnotherResource;
import it.polimi.ingsw.exceptions.DepositHasReachedMaxLimit;
import it.polimi.ingsw.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ResourceTest class tests Resource class.
 * @see Resource
 */
public class ResourceTest {
    Game testGame;
    Board testBoard;
    Deposit testDeposit1;
    Deposit testDeposit2;
    Deposit testDeposit3;
    Deposit testDeposit4;
    Resource coin;
    Resource stone;
    Resource servant;
    Resource shield;
    Resource faithmarker;
    ConcreteStrategyResource strategy1;
    ConcreteStrategyResource strategy2;
    ConcreteStrategyResource strategy3;
    ConcreteStrategyResource strategy4;
    ConcreteStrategyResource strategy6;
    ConcreteStrategyResource strategy7;

    ConcreteStrategySpecialResource strategy5;


    /** Method setup setups tests. */
    @BeforeEach
    void setup() {
        testGame = new Game();
        testBoard = new Board(testGame);
        testDeposit1 = new Deposit(1, false);
        testDeposit2 = new Deposit(2, false);
        testDeposit3 = new Deposit(3, false);
        testDeposit4 = new Deposit(2, true);

        testDeposit4.setResourcetype(ResourceType.COIN);

        testBoard.getDeposits().add(testDeposit1);
        testBoard.getDeposits().add(testDeposit2);
        testBoard.getDeposits().add(testDeposit3);
        testBoard.getDeposits().add(testDeposit4);
        coin = new Resource(ResourceType.COIN);
        stone = new Resource(ResourceType.STONE);
        servant =new Resource(ResourceType.SERVANT);
        shield = new Resource(ResourceType.SHIELD);
        faithmarker = new Resource(ResourceType.FAITHMARKER);
        strategy1 = new ConcreteStrategyResource(0, testBoard, ResourceType.COIN);
        strategy2 = new ConcreteStrategyResource(1, testBoard, ResourceType.STONE);
        strategy3 = new ConcreteStrategyResource(2, testBoard, ResourceType.SERVANT);
        strategy4 = new ConcreteStrategyResource(0, testBoard, ResourceType.SHIELD);
        strategy6 = new ConcreteStrategyResource(6, testBoard, ResourceType.COIN);
        strategy7 = new ConcreteStrategyResource(6, testBoard, ResourceType.STONE);
        strategy5 = new ConcreteStrategySpecialResource(testBoard);
    }
    /** Method ResourceTypeTest tests resources' getter. */
    @Test
    @DisplayName("Resource type getter test")
    void ResourceTypeTest() {
        assertEquals(ResourceType.COIN,coin.getType());
        assertEquals(ResourceType.STONE,stone.getType());
        assertEquals(ResourceType.SERVANT,servant.getType());
        assertEquals(ResourceType.SHIELD,shield.getType());
        assertEquals(ResourceType.FAITHMARKER,faithmarker.getType());
    }

    /** Method StrategySetter tests resource strategy getter and setter. */
    @Test
    @DisplayName("Resource strategy getter/setter test")
    void ResourceStrategyTest() {
        coin.setStrategy(strategy1);
        stone.setStrategy(strategy2);
        servant.setStrategy(strategy3);
        shield.setStrategy(strategy4);
        faithmarker.setStrategy(strategy5);

        assertEquals(strategy1, coin.getStrategy());
        assertEquals(strategy2, stone.getStrategy());
        assertEquals(strategy3, servant.getStrategy());
        assertEquals(strategy4, shield.getStrategy());
        assertEquals(strategy5, faithmarker.getStrategy());
    }

    /** Method useResourceTest1, useResourceTest2, useResource3 tests resource's useResource method that puts a resource in a specific deposit
     * defined by the strategy assigned OR increase the position of the player's faith marker. To verify that the method
     * has correctly done his work, we'll verify that the number of resources of a certain deposit has changed or
     * that the position of the player's faith marker has increased*/
    @Test
    @DisplayName("Test that fill all the deposit with different resources without calling any exceptions")
    void useResourceTest1() throws DepositHasReachedMaxLimit, DepositHasAnotherResource, AnotherDepositContainsThisResource, InvalidResource {
        coin.setStrategy(strategy1);
        stone.setStrategy(strategy2);
        servant.setStrategy(strategy3);

        coin.useResource();
        stone.useResource();
        stone.useResource();
        servant.useResource();
        servant.useResource();
        servant.useResource();
        assertEquals(1,testBoard.getDeposits().get(0).getQuantity());
        assertEquals(2,testBoard.getDeposits().get(1).getQuantity());
        assertEquals(3,testBoard.getDeposits().get(2).getQuantity());
    }
    @Test
    @DisplayName("Test that calls the DepositHasReachedMaxLimit exception")
    void useResourceTest2() throws DepositHasReachedMaxLimit, DepositHasAnotherResource, AnotherDepositContainsThisResource, InvalidResource {
        boolean exception = false;
        coin.setStrategy(strategy1);
        stone.setStrategy(strategy2);
        servant.setStrategy(strategy3);
        faithmarker.setStrategy(strategy5);

        coin.useResource();
        stone.useResource();
        stone.useResource();
        servant.useResource();
        servant.useResource();
        servant.useResource();
        try{
            servant.getStrategy().action();
        }catch(DepositHasReachedMaxLimit | DepositHasAnotherResource | InvalidResource e){
            exception = true;
        }
        assertTrue(exception);
    }
    @Test
    @DisplayName("Test that calls the DepositHasAnotherResource exception")
    void useResourceTest3() throws DepositHasReachedMaxLimit, DepositHasAnotherResource, AnotherDepositContainsThisResource, InvalidResource {
        boolean exception = false;
        coin.setStrategy(strategy1);
        stone.setStrategy(strategy2);
        servant.setStrategy(strategy3);
        shield.setStrategy(strategy4);

        coin.useResource();
        stone.useResource();
        stone.useResource();
        servant.useResource();
        servant.useResource();
        servant.useResource();
        try{
            shield.getStrategy().action();
        }catch(DepositHasReachedMaxLimit | DepositHasAnotherResource | InvalidResource e){
            exception = true;
        }
        assertTrue(exception);
    }
    @Test
    @DisplayName("Test that verify that the position of the player changes")
    void useResourceTest4() throws DepositHasReachedMaxLimit, DepositHasAnotherResource, AnotherDepositContainsThisResource, InvalidResource {
        faithmarker.setStrategy(strategy5);

        faithmarker.useResource();
        faithmarker.useResource();
        faithmarker.useResource();

        assertEquals(3, testBoard.getFaithMarker());
    }

    @Test
    @DisplayName("Test that verify the correct execution of the special deposit")
    void useResourceTest5() throws DepositHasReachedMaxLimit, DepositHasAnotherResource, AnotherDepositContainsThisResource, InvalidResource {
        assertTrue(testDeposit4.isSpecial());
        assertTrue(testBoard.getDeposits().get(6).isSpecial());

        coin.setStrategy(strategy6);
        coin.useResource();

        assertEquals(1, testBoard.getDeposits().get(6).getQuantity());

        stone.setStrategy(strategy7);
        try {
            stone.useResource();
            fail();
        } catch (InvalidResource ignored){}

        assertEquals(1, testBoard.getDeposits().get(6).getQuantity());
        assertEquals(ResourceType.COIN, testBoard.getDeposits().get(6).getResourcetype());

        coin.useResource();
        assertEquals(2, testBoard.getDeposits().get(6).getQuantity());

        try {
            coin.useResource();
            fail();
        } catch (DepositHasReachedMaxLimit ignored){}


        assertEquals(2, testBoard.getDeposits().get(6).getQuantity());

        coin.setStrategy(strategy1);
        coin.useResource();
        assertEquals(1, testBoard.getDeposits().get(0).getQuantity());
    }
}
