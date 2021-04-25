package it.polimi.ingsw.Board.Storage;

import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Exceptions.DepositDoesntHaveThisResource;
import it.polimi.ingsw.Exceptions.EmptyDeposit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * StrongboxTest class tests Strongbox class.
 * @see Strongbox
 */
public class StrongboxTest {
    Strongbox testStrongbox;

    /** Method setup setups tests. */
    @BeforeEach
    void setup() {
        testStrongbox = new Strongbox();
        testStrongbox.getStrongbox().put(ResourceType.COIN, 2);
        testStrongbox.getStrongbox().put(ResourceType.STONE, 1);
        testStrongbox.getStrongbox().put(ResourceType.SERVANT, 56);
        testStrongbox.getStrongbox().put(ResourceType.SHIELD, 4);
    }

    /** Method GetSpecificResource tests EmptyDeposit exception. */
    @Test
    @DisplayName("Deposit get-methods to obtain the total number of a specific resource test")
    void GetSpecificResource() {


        testStrongbox.getStrongbox().replaceAll((chiave , valore) -> valore + 1);

        assertEquals(3, testStrongbox.getTotalCoins());
        assertEquals(2, testStrongbox.getTotalStones());
        assertEquals(57, testStrongbox.getTotalServants());
        assertEquals(5, testStrongbox.getTotalShields());

    }

    /** Method removeTest tests remove method. */
    @Test
    @DisplayName("removeTest used to test the correct functioning of the method")
    void removeTest() throws EmptyDeposit {
        testStrongbox.getStrongbox().replaceAll((key, value) -> 10);

        testStrongbox.remove(ResourceType.COIN);
        assertEquals(9,testStrongbox.getTotalCoins());

    }

    /** Method GetSpecificResource tests remove exceptions. */
    @Test
    @DisplayName("removeTestException used to test if the exception are thrown when it's needed")
    void removeTestException() {
        testStrongbox.getStrongbox().replaceAll((key, value) -> 0);

        try {
            testStrongbox.remove(ResourceType.STONE);
            fail();
        } catch (EmptyDeposit ignored) {}

    }
}
