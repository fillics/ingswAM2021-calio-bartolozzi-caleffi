package it.polimi.ingsw.model.board.storage;

import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.exceptions.DepositDoesntHaveThisResource;
import it.polimi.ingsw.exceptions.EmptyDeposit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DepositTest class tests Deposit class.
 * @see Deposit
 */
public class DepositTest {
    Deposit testDeposit1;
    Deposit testDeposit2;
    Deposit testDeposit3;
    Resource testResource;
    private final int maxLimit3 = 3;
    private final int maxLimit2 = 2;
    private final int maxLimit1 = 1;

    /** Method setup setups tests. */
    @BeforeEach
    void setup() {
        testDeposit1 = new Deposit(maxLimit1, false);
        testDeposit2 = new Deposit(maxLimit2, false);
        testDeposit3 = new Deposit(maxLimit3, false);
    }

    /** Method DepositGetterTest tests deposit methods getter. */
    @Test
    @DisplayName("Deposit attributes' get-methods test")
    void DepositGetterTest() {
        assertEquals(0, testDeposit3.getQuantity());
        assertEquals(3, testDeposit3.getMaxLimit());
        assertNull(testDeposit3.getResourcetype());
    }

    /** Method DepositModifierTest tests deposit methods setter without throwing any exceptions. */
    @Test
    @DisplayName("Deposit set-methods test without exceptions")
    void DepositModifierTest() throws EmptyDeposit {
        testDeposit3.setResourcetype(ResourceType.COIN);
        assertEquals(ResourceType.COIN, testDeposit3.getResourcetype());

        assertEquals(1, testDeposit3.increaseNumberOfResources());
        assertEquals(2, testDeposit3.increaseNumberOfResources());

        testResource = testDeposit3.takeResource();
        assertEquals(ResourceType.COIN, testResource.getType());
        assertEquals(1, testDeposit3.getQuantity());
    }

    /** Method EmptyDepositTest tests EmptyDeposit exception. */
    @Test
    @DisplayName("Deposit set-methods test without exceptions")
    void EmptyDepositTest() {
        boolean exception = false;
        try {
            testDeposit3.takeResource();
        }catch(EmptyDeposit e){
            exception = true;
        }
        assertTrue(exception);
    }

    /** Method GetSpecificResource tests the correct execution of the resources getter. */
    @Test
    @DisplayName("Deposit get-methods to obtain the total number of a specific resource test")
    void GetSpecificResource() {
        testDeposit3.setResourcetype(ResourceType.COIN);
        testDeposit3.increaseNumberOfResources();
        testDeposit3.increaseNumberOfResources();

        assertEquals(0, testDeposit3.getTotalStones());
        assertEquals(0, testDeposit3.getTotalServants());
        assertEquals(0, testDeposit3.getTotalShields());
        assertEquals(2, testDeposit3.getTotalCoins());
    }

    /** Method removeTest tests remove method. */
    @Test
    @DisplayName("removeTest used to test the correct functioning of the method")
    void removeTest() throws EmptyDeposit, DepositDoesntHaveThisResource {
        testDeposit3.setResourcetype(ResourceType.COIN);
        testDeposit3.increaseNumberOfResources();
        testDeposit3.increaseNumberOfResources();

        testDeposit3.remove(ResourceType.COIN);
        assertEquals(1, testDeposit3.getQuantity());

    }

    /** Method GetSpecificResource tests remove exceptions. */
    @Test
    @DisplayName("removeTestException used to test if the exception are thrown when it's needed")
    void removeTestException() {
        testDeposit3.setResourcetype(ResourceType.COIN);
        testDeposit3.increaseNumberOfResources();
        testDeposit3.increaseNumberOfResources();
        try {
            testDeposit3.remove(ResourceType.STONE);
            fail();
        } catch (DepositDoesntHaveThisResource | EmptyDeposit ignored) {}

        try {
            testDeposit3.remove(ResourceType.COIN);
            testDeposit3.remove(ResourceType.COIN);
        }
        catch (DepositDoesntHaveThisResource | EmptyDeposit ignored) {}

        try {
            testDeposit3.remove(ResourceType.COIN);
            fail();
        } catch (DepositDoesntHaveThisResource | EmptyDeposit ignored) {}

    }

    @Test
    @DisplayName(" print test")
    void printTest(){
        testDeposit1.setResourcetype(ResourceType.COIN);
        testDeposit2.setResourcetype(ResourceType.SERVANT);
        testDeposit3.setResourcetype(ResourceType.STONE);
        testDeposit1.increaseNumberOfResources();
        testDeposit2.increaseNumberOfResources();
        testDeposit3.increaseNumberOfResources();
        testDeposit3.increaseNumberOfResources();
        assertEquals(testDeposit3.getQuantity(),2);
        testDeposit1.dump();
        testDeposit2.dump();
        testDeposit3.dump();
    }

}
