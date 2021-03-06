package it.polimi.ingsw.model.cards.leadercards;

import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.cards.developmentcards.CardColor;
import it.polimi.ingsw.model.cards.developmentcards.Level;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RequirementTest class tests Requirement class.
 * @see Requirement
 */
public class RequirementTest {
    HashMap<CardColor,Integer> color;
    Level level;
    HashMap<ResourceType,Integer> resourcePrice;
    HashMap<CardColor,Integer> color2;
    LevelAndColorRequirement testRequirement;
    NumAndColorRequirement testRequirement2;
    ResourcesRequirement testRequirement3;

    /*
     * Method setup setups tests.
     * */
    @BeforeEach
    void setup(){
        level = Level.TWO;
        color = new HashMap<>();
        color.put(CardColor.GREEN,1);
        resourcePrice = new HashMap<>();
        resourcePrice.put(ResourceType.COIN,5);
        color2 = new HashMap<>();
        color2.put(CardColor.YELLOW,2);
        color2.put(CardColor.BLUE,1);
        testRequirement = new LevelAndColorRequirement(color,level);
        testRequirement2 = new NumAndColorRequirement(color2);
        testRequirement3 = new ResourcesRequirement(resourcePrice);
    }

    /** Method getColorTest tests getColor method. */
    @Test
    @DisplayName("getColor method test")
    void getColorTest(){
        assertEquals(testRequirement.getColor(), color);
        assertEquals(testRequirement2.getColor(), color2);

    }

    /** Method getLevelTest tests getLevel method. */
    @Test
    @DisplayName("getLevel method test")
    void getLevelTest(){
        assertEquals(testRequirement.getLevel(),level);
    }

    /** Method getResourcePriceTest tests getResourcePrice method. */
    @Test
    @DisplayName("getResourcePrice method test")
    void getResourcePriceTest(){
        assertEquals(testRequirement3.getResourcePrice(),resourcePrice);
    }

}
