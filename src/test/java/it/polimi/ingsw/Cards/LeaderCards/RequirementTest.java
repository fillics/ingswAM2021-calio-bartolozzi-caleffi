package it.polimi.ingsw.Cards.LeaderCards;

import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import it.polimi.ingsw.Cards.DevelopmentCards.Level;
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
    Requirement testRequirement;
    Requirement testRequirement2;
    Requirement testRequirement3;

    /*
     * Method setup setups tests.
     * */
    @BeforeEach
    void setup(){
        level= Level.TWO;
        color= new HashMap<>();
        color.put(CardColor.GREEN,1);
        resourcePrice= new HashMap<>();
        resourcePrice.put(ResourceType.COIN,5);
        color2= new HashMap<>();
        color2.put(CardColor.YELLOW,2);
        color2.put(CardColor.BLUE,1);
        testRequirement= new Requirement(color,level,null);
        testRequirement2= new Requirement(color2,null,null);
        testRequirement3= new Requirement(null, null, resourcePrice);
    }

    /** Method getColorTest tests getColor method. */
    @Test
    @DisplayName("getColor method test")
    void getColorTest(){
        assertEquals(testRequirement2.getColor(),color2);
        assertEquals(testRequirement.getColor(),color);
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
