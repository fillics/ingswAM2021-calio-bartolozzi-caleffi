package it.polimi.ingsw.Cards.DevelopmentCards;
import static org.junit.jupiter.api.Assertions.*;
import it.polimi.ingsw.Board.Resources.ResourceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * DevelopmentCardTest class tests DevelopmentCard class.
 * @see DevelopmentCard
 */
public class DevelopmentCardTest {
    DevelopmentCard testDevelopmentCard;
    ProductionPower testProductionPower;
    HashMap<ResourceType,Integer> testResourcePrice;
    HashMap<ResourceType,Integer> testResourcesNeeded;
    HashMap<ResourceType,Integer> testResourcesObtained;

    /*
    * Method setup setups tests.
    * */
    @BeforeEach
    void setup(){
        testResourcePrice= new HashMap<>();
        testResourcesNeeded = new HashMap<>();
        testResourcesObtained = new HashMap<>();
        testResourcePrice.put(ResourceType.SERVANT,2);
        testResourcesNeeded.put(ResourceType.COIN,1);
        testResourcesObtained.put(ResourceType.FAITHMARKER,1);
        testProductionPower= new ProductionPower(testResourcesNeeded,testResourcesObtained);
        testDevelopmentCard= new DevelopmentCard(1,Level.ONE, CardColor.PURPLE,testProductionPower,testResourcePrice, 3);
    }

    /** Method LevelGetterTest tests DevelopmentCard method getter. */
    @Test
    @DisplayName("Level getter test")
    void LevelGetterTest(){
        assertEquals(Level.ONE,testDevelopmentCard.getLevel());
    }

    /** Method ColorGetterTest tests DevelopmentCard method getter. */
    @Test
    @DisplayName("Color getter test")
    void ColorGetterTest(){
        assertEquals(CardColor.PURPLE,testDevelopmentCard.getColor());
    }

    /** Method ResourcePriceGetterTest tests DevelopmentCard method getter. */
    @Test
    @DisplayName("Resource Price getter test")
    void ResourcePriceGetterTest(){
        assertEquals(testResourcePrice, testDevelopmentCard.getResourcePrice());
    }

    /** Method ProductionPowerTest tests DevelopmentCard method getter. */
    @Test
    @DisplayName("Production Power getter test")
    void ProductionPowerGetterTest(){
        assertEquals(testProductionPower, testDevelopmentCard.getProductionPower());
    }

    /** Method VictoryPointGetterTest tests DevelopmentCard method getter. */
    @Test
    @DisplayName("VictoryPoint getter test")
    void VictoryPointGetterTest() {
        assertEquals(3,testDevelopmentCard.getVictoryPoint());
    }
}
