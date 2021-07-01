package it.polimi.ingsw.model.cards.developmentcards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.constants.Color;
import it.polimi.ingsw.constants.Printable;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.cards.Card;

import java.util.HashMap;

/**
 * This class represents the Development Card.
 */
public class DevelopmentCard extends Card {
    private final String path;
    private final int id;
    private final Level level;
    private final CardColor color;
    private final HashMap<ResourceType,Integer> resourcePrice;
    private final ProductionPower productionPower;
    private final int victorypoint;

    /**
     * Constructor DevelopmentCard creates a new DevelopmentCard instance.
     */
    @JsonCreator
    public DevelopmentCard(@JsonProperty("id") int id, @JsonProperty("level")Level level, @JsonProperty("color")CardColor color,
                           @JsonProperty("productionPower")ProductionPower productionPower,
                           @JsonProperty("resourcePrice") HashMap<ResourceType,Integer> resourcePrice, @JsonProperty("victorypoint")int victorypoint, @JsonProperty("path") String path) {
        this.id = id;
        this.level = level;
        this.color = color;
        this.resourcePrice= resourcePrice;
        this.productionPower = productionPower;
        this.victorypoint = victorypoint;
        this.path = path;
    }

    public DevelopmentCard() {
        this.id = 0;
        this.level = null;
        this.color = null;
        this.resourcePrice = null;
        this.productionPower = null;
        this.victorypoint = 0;
        this.path = null;
    }

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    /**
     * Method getLevel returns the development card's level.
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Method getColor returns the development card's color.
     */
    public CardColor getColor() {
        return color;
    }

    /**
     * Method getResourcePrice returns the development card's price.
     */
    public HashMap<ResourceType, Integer> getResourcePrice() {
        return resourcePrice;
    }

    /**
     * Method getProductionPower returns the development card's production power.
     */
    public ProductionPower getProductionPower() {
        return productionPower;
    }

    /**
     * Override Method getVictoryPoints returns the development card's victory points.
     */
    @Override
    public int getVictorypoint() {
        return victorypoint;
    }

    @Override
    public String toString(){
        StringBuilder matrix= new StringBuilder();

        matrix.append(Printable.NORD_OVEST.print()).append(String.valueOf(Printable.MIDDLE.print()).repeat(11)).append(Printable.NORD_EST.print()).append("\n");
        matrix.append(Printable.DOUBLE_LINE.print()).append("   ").append("id:").append(this.id);
        if(this.id<10)
            matrix.append((" ").repeat(4));
        else
            matrix.append((" ").repeat(3));
        matrix.append(Printable.DOUBLE_LINE.print()).append("\n");


        matrix.append(Printable.DOUBLE_LINE.print());
        matrix.append(this.printColor());
        if(numOfResourcePrice()==1 && this.level!=Level.THREE)
            matrix.append((" ").repeat(3));
        else if(numOfResourcePrice()==1 && this.level==Level.THREE)
            matrix.append((" ").repeat(4));
        else if(numOfResourcePrice()==2)
            matrix.append((" ").repeat(2));
        else if(numOfResourcePrice()==3)
            matrix.append((" ").repeat(1));

        matrix.append(this.printResourcePrice());

        if(numOfResourcePrice()==1)
            matrix.append((" ").repeat(4));
        else if(numOfResourcePrice()==2)
            matrix.append((" ").repeat(3));
        else if(numOfResourcePrice()==3)
            matrix.append((" ").repeat(2));
        matrix.append(this.printColor());
        matrix.append(Printable.DOUBLE_LINE.print()).append("\n");


        matrix.append(Printable.DOUBLE_LINE.print());
        assert this.productionPower != null;
        if(this.productionPower.numOfResources()==2)
            matrix.append((" ").repeat(3));
        else if (this.productionPower.numOfResources()==3)
            matrix.append((" ").repeat(2));
        else if(this.productionPower.numOfResources()==4)
            matrix.append((" ").repeat(1));

        matrix.append(this.productionPower);

        if(this.productionPower.numOfResources()==2)
            matrix.append((" ").repeat(3));
        else if (this.productionPower.numOfResources()==3)
            matrix.append((" ").repeat(2));
        else if(this.productionPower.numOfResources()==4)
            matrix.append((" ").repeat(1));

        matrix.append(Printable.DOUBLE_LINE.print()).append("\n");

        matrix.append(Printable.DOUBLE_LINE.print());
        if(this.victorypoint <10)
            matrix.append("    ");
        else
            matrix.append("   ");
        matrix.append(this.victorypoint).append(Color.ANSI_YELLOW.escape()).append("VP").append(Color.RESET).append("    ").append(Printable.DOUBLE_LINE.print()).append("\n");
        matrix.append(Printable.SUD_OVEST.print()).append(String.valueOf(Printable.MIDDLE.print()).repeat(11)).append(Printable.SUD_EST.print());

        return matrix.toString();
    }

    public String printLevel(){
        String level = "";
        assert this.level != null;
        if(this.level.equals(Level.ONE))
            level = Printable.ONE_POINT.print();
        else if(this.level.equals(Level.TWO))
            level= Printable.TWO_POINTS.print();
        else if (this.level.equals(Level.THREE))
            level= Printable.THREE_POINTS.print();
        return level;
    }

    public String printColor(){
        StringBuilder matrix= new StringBuilder();
        assert this.color != null;
        if(this.color.equals(CardColor.BLUE))
            matrix.append(Color.BACKGROUND_BLUE.escape()).append(this.printLevel()).append(Color.RESET);
        if(color.equals(CardColor.GREEN))
            matrix.append(Color.BACKGROUND_GREEN.escape()).append(this.printLevel()).append(Color.RESET);
        if(color.equals(CardColor.PURPLE))
            matrix.append(Color.BACKGROUND_PURPLE.escape()).append(this.printLevel()).append(Color.RESET);
        if(color.equals(CardColor.YELLOW))
            matrix.append(Color.BACKGROUND_YELLOW.escape()).append(this.printLevel()).append(Color.RESET);
        return matrix.toString();
    }

    public String printResourcePrice() {
        String escape="";

        assert resourcePrice != null;
        if(resourcePrice.containsKey(ResourceType.SHIELD))
            escape= resourcePrice.get(ResourceType.SHIELD) + Color.ANSI_BLUE.escape() + Printable.SQUARE.print() + Color.RESET;
        if(resourcePrice.containsKey(ResourceType.COIN))
            escape = escape + resourcePrice.get(ResourceType.COIN) + Color.ANSI_YELLOW.escape() + Printable.SQUARE.print() + Color.RESET;
        if(resourcePrice.containsKey(ResourceType.SERVANT))
            escape = escape + resourcePrice.get(ResourceType.SERVANT) + Color.ANSI_PURPLE.escape() + Printable.SQUARE.print() + Color.RESET;
        if(resourcePrice.containsKey(ResourceType.STONE))
            escape = escape + resourcePrice.get(ResourceType.STONE) + Color.ANSI_GREY.escape() + Printable.SQUARE.print() + Color.RESET;

        return escape;
    }

    public int numOfResourcePrice(){
        int num=0;
        assert resourcePrice != null;
        if(resourcePrice.containsKey(ResourceType.SHIELD))
            num++;
        if(resourcePrice.containsKey(ResourceType.COIN))
            num++;
        if(resourcePrice.containsKey(ResourceType.SERVANT))
            num++;
        if(resourcePrice.containsKey(ResourceType.STONE))
            num++;
        return num;
    }
}
