package it.polimi.ingsw.model.cards.developmentcards;
import static org.junit.jupiter.api.Assertions.*;
import it.polimi.ingsw.model.board.resources.ResourceType;
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
        testResourcePrice.put(ResourceType.SHIELD,1);
        //testResourcePrice.put(ResourceType.SERVANT,2);
        //testResourcePrice.put(ResourceType.SHIELD,5);

        testResourcesNeeded.put(ResourceType.SERVANT,2);
        //testResourcesNeeded.put(ResourceType.COIN,1);

        //testResourcesObtained.put(ResourceType.STONE,1);
        testResourcesObtained.put(ResourceType.SHIELD,3);
        testResourcesObtained.put(ResourceType.FAITHMARKER,1);

        testProductionPower= new ProductionPower(testResourcesNeeded,testResourcesObtained);
        testDevelopmentCard= new DevelopmentCard(38, Level.THREE, CardColor.PURPLE,testProductionPower,testResourcePrice, 3);
    }

    /** Method LevelGetterTest tests DevelopmentCard method getter. */
    @Test
    @DisplayName("Level getter test")
    void LevelGetterTest(){
        assertEquals(Level.THREE,testDevelopmentCard.getLevel());
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
        assertEquals(3,testDevelopmentCard.getVictorypoint());
    }

    @Test
    @DisplayName("print test")
    void print(){
        System.out.println(testDevelopmentCard.getProductionPower().toString().length());
        testDevelopmentCard.dump();
    }
}
