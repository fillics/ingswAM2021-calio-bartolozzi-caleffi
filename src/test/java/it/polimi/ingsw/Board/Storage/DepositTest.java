package it.polimi.ingsw.Board.Storage;

import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Exceptions.EmptyDeposit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DepositTest class tests Deposit class.
 * @see Deposit
 */
public class DepositTest {
    Deposit testDeposit;
    Resource testResource;
    private final int maxLimit = 3;

    /** Method setup setups tests. */
    @BeforeEach
    void setup() {
        testDeposit = new Deposit(maxLimit);
    }

    /** Method DepositGetterTest tests deposit methods getter. */
    @Test
    @DisplayName("Deposit attributes' get-methods test")
    void DepositGetterTest() {
        assertEquals(0, testDeposit.getQuantity());
        assertEquals(3, testDeposit.getMaxLimit());
        assertNull(testDeposit.getResourcetype());
    }

    /** Method DepositModifierTest tests deposit methods setter without throwing any exceptions. */
    @Test
    @DisplayName("Deposit set-methods test without exceptions")
    void DepositModifierTest() throws EmptyDeposit {
        testDeposit.setResourcetype(ResourceType.COIN);
        assertEquals(ResourceType.COIN, testDeposit.getResourcetype());

        assertEquals(1,testDeposit.increaseNumberOfResources());
        assertEquals(2,testDeposit.increaseNumberOfResources());

        testResource = testDeposit.takeResource();
        assertEquals(ResourceType.COIN, testResource.getType());
        assertEquals(1, testDeposit.getQuantity());
    }

    /** Method EmptyDepositTest tests EmptyDeposit exception. */
    @Test
    @DisplayName("Deposit set-methods test without exceptions")
    void EmptyDepositTest() {
        boolean exception = false;
        try {
            testDeposit.takeResource();
        }catch(EmptyDeposit e){
            exception = true;
        }
        assertTrue(exception);
    }

    /** Method GetSpecificResource tests EmptyDeposit exception. */
    @Test
    @DisplayName("Deposit get-methods to obtain the total number of a specific resource test")
    void GetSpecificResource() {
        testDeposit.setResourcetype(ResourceType.COIN);
        testDeposit.increaseNumberOfResources();
        testDeposit.increaseNumberOfResources();

        assertEquals(0, testDeposit.getTotalStones());
        assertEquals(0, testDeposit.getTotalServants());
        assertEquals(0, testDeposit.getTotalShields());
        assertEquals(2, testDeposit.getTotalCoins());
    }





}
