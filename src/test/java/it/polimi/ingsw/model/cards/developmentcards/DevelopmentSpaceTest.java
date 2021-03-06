package it.polimi.ingsw.model.cards.developmentcards;

import static org.junit.jupiter.api.Assertions.*;

import it.polimi.ingsw.constants.Color;
import it.polimi.ingsw.constants.Printable;
import it.polimi.ingsw.model.board.resources.ResourceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * DevelopmentSpaceTest class tests DevelopmentSpace class.
 * @see DevelopmentSpace
 */
public class DevelopmentSpaceTest {
    DevelopmentCard testDevelopmentCard;
    DevelopmentSpace testDevelopmentSpace;
    DevelopmentSpace testDevelopmentSpace2;
    DevelopmentSpace testDevelopmentSpace3;
    ProductionPower testProductionPower;
    HashMap<ResourceType,Integer> testResourcePrice;
    HashMap<ResourceType,Integer> testResourcesNeeded;
    HashMap<ResourceType,Integer> testResourcesObtained;
    ArrayList<DevelopmentCard> testArray;
    DevelopmentCard testDevelopmentCard2;
    DevelopmentCard testDevelopmentCard3;
    DevelopmentCard testDevelopmentCard4;
    ArrayList<DevelopmentSpace> developmentSpaces;

    /*
     * Method setup setups tests.
     * */
    @BeforeEach
    void setup(){
        testResourcePrice= new HashMap<>();
        testResourcesNeeded = new HashMap<>();
        testResourcesObtained = new HashMap<>();
        testResourcePrice.put(ResourceType.SHIELD,1);
        testResourcesNeeded.put(ResourceType.COIN,2);
        testResourcesObtained.put(ResourceType.FAITHMARKER,2);
        testProductionPower= new ProductionPower(testResourcesNeeded,testResourcesObtained);
        testDevelopmentCard= new DevelopmentCard(1, Level.ONE, CardColor.GREEN,testProductionPower,testResourcePrice, 4,"");
        testDevelopmentCard2= new DevelopmentCard(2,Level.TWO, CardColor.PURPLE,testProductionPower,testResourcePrice,1," ");
        testDevelopmentCard3= new DevelopmentCard(3,Level.THREE, CardColor.YELLOW,testProductionPower,testResourcePrice,12, "");
        testDevelopmentCard4= new DevelopmentCard(4,Level.ONE, CardColor.BLUE,testProductionPower,testResourcePrice,3, "");
        testDevelopmentSpace= new DevelopmentSpace();
        testDevelopmentSpace2= new DevelopmentSpace();
        testDevelopmentSpace3 = new DevelopmentSpace();
        testArray= new ArrayList<>();
        developmentSpaces = new ArrayList<>();
    }

    /** Method isPlaceableCardTest tests isPlaceableCard method. */
    @Test
    @DisplayName("isPlaceableCard method test")
    void isPlaceableCardTest(){
        testDevelopmentSpace.addDevelopmentCard(testDevelopmentCard);
        testDevelopmentSpace.addDevelopmentCard(testDevelopmentCard2);
        assertTrue(testDevelopmentSpace.isPlaceableCard(testDevelopmentCard3));
        testDevelopmentSpace.addDevelopmentCard(testDevelopmentCard3);
        assertFalse(testDevelopmentSpace.isPlaceableCard(testDevelopmentCard4));
    }

    /** Method addDevCardTest tests addDevelopmentCard method. */
    @Test
    @DisplayName("addDevelopmentCard and DevelopmentSpace getter method test")
    void addDevCardTest(){
        testDevelopmentSpace.addDevelopmentCard(testDevelopmentCard);
        testDevelopmentSpace.addDevelopmentCard(testDevelopmentCard2);
        testArray.add(testDevelopmentCard);
        testArray.add(testDevelopmentCard2);
        assertEquals(testArray,testDevelopmentSpace.getDevelopmentCardsOfDevSpace());
    }

    /** Method getTopCardProductionPowerTest tests getTopCardProductionPower method. */
    @Test
    @DisplayName("getTopCardProductionPower method test")
    void getTopCardProductionPowerTest(){
        testDevelopmentSpace.addDevelopmentCard(testDevelopmentCard);
        assertEquals(testDevelopmentSpace.getTopCardProductionPower(),testProductionPower);
    }

}
