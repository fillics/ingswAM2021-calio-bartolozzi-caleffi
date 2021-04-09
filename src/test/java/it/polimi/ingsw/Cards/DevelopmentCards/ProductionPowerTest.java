package it.polimi.ingsw.Cards.DevelopmentCards;

import it.polimi.ingsw.Board.Resources.ResourceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
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

    /*
     * Method setup setups tests.
     * */
    @BeforeEach
    void setup(){
        testResourcesNeeded = new HashMap<>();
        testResourcesObtained = new HashMap<>();
        testResourcesNeeded.put(ResourceType.COIN,1);
        testResourcesObtained.put(ResourceType.FAITHMARKER,1);
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
}
