package it.polimi.ingsw.Board.Storage;

import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
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
    }

    /** Method GetSpecificResource tests EmptyDeposit exception. */
    @Test
    @DisplayName("Deposit get-methods to obtain the total number of a specific resource test")
    void GetSpecificResource() {
        testStrongbox.getStrongbox().put(ResourceType.COIN, 2);
        testStrongbox.getStrongbox().put(ResourceType.STONE, 1);
        testStrongbox.getStrongbox().put(ResourceType.SERVANT, 56);
        testStrongbox.getStrongbox().put(ResourceType.SHIELD, 4);

        testStrongbox.getStrongbox().replaceAll((chiave , valore) -> valore + 1);

        assertEquals(3, testStrongbox.getTotalCoins());
        assertEquals(2, testStrongbox.getTotalStones());
        assertEquals(57, testStrongbox.getTotalServants());
        assertEquals(5, testStrongbox.getTotalShields());

    }
}
