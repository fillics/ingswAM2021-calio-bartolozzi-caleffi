package it.polimi.ingsw.model.board.storage;

import it.polimi.ingsw.client.Color;
import it.polimi.ingsw.client.Printable;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.exceptions.EmptyDeposit;

import java.util.HashMap;

/**
 * Class Strongbox represents the strongbox of the Board
 */
public class Strongbox extends Warehouse{
    private final HashMap<ResourceType, Integer> strongbox;

    
    /**
     * Constructor Strongbox creates a new Strongbox instance, 
     * used to create the map where all the strongobox resources will be inserted
     */
    public Strongbox() {
        strongbox = new HashMap<>();
    }

    /**
     * get-method
     * @return tha hashmap that contains all the information about the number and type of resources inside the strongbox
     */
    public HashMap<ResourceType, Integer> getStrongbox() {
        return strongbox;
    }

    /**
     * Override method getTotalCoins returns the quantity of coins in the deposit
     */
    @Override
    public int getTotalCoins() {
        return strongbox.get(ResourceType.COIN);
    }

    /**
     * Override method getTotalShields returns the quantity of shields in the deposit
     */
    @Override
    public int getTotalShields() {
        return strongbox.get(ResourceType.SHIELD);
    }

    /**
     * Override method getTotalServants returns the quantity of servants in the deposit
     */
    @Override
    public int getTotalServants() {
        return strongbox.get(ResourceType.SERVANT);
    }

    /**
     * Override method getTotalStones returns the quantity of stones in the deposit
     */
    @Override
    public int getTotalStones() {
        return strongbox.get(ResourceType.STONE);
    }

    /**
     * Override method that removes one resource from the strongbox.
     * @param resourceType is the type of resource to remove from the strongbox.
     * @throws EmptyDeposit exception thrown when the deposit is empty and there's nothing to remove.
     */
    @Override
    public void remove(ResourceType resourceType) throws EmptyDeposit {
        if(strongbox.get(resourceType) == 0){
            throw new EmptyDeposit();
        }
        else {
            strongbox.replace(resourceType, strongbox.get(resourceType)-1);
        }
    }

    @Override
    public String toString() {
        StringBuilder escape = new StringBuilder();
        int numCifre=0,num;
        if(strongbox.containsKey(ResourceType.SHIELD)){
            num=strongbox.get(ResourceType.SHIELD);
            if(num==0)
                numCifre+=1;
            else{
                while(num!=0) {
                    num= num/10;
                    numCifre += 1;
                }
            }
        }
        if(strongbox.containsKey(ResourceType.COIN)){
            num=strongbox.get(ResourceType.COIN);
            if(num==0)
                numCifre+=1;
            else{
                while(num!=0) {
                    num= num/10;
                    numCifre += 1;
                }
            }
        }
        if(strongbox.containsKey(ResourceType.SERVANT)){
            num=strongbox.get(ResourceType.SERVANT);
            if(num==0)
                numCifre+=1;
            else{
                while(num!=0) {
                    num= num/10;
                    numCifre += 1;
                }
            }
        }
        if(strongbox.containsKey(ResourceType.STONE)){
            num=strongbox.get(ResourceType.STONE);
            if(num==0)
                numCifre+=1;
            else{
                while(num!=0) {
                    num= num/10;
                    numCifre += 1;
                }
            }
        }

        escape.append("4: \n");
        escape.append(Printable.NORD_OVEST.print()).append(String.valueOf(Printable.MIDDLE.print()).repeat(15+numCifre)).append(Printable.NORD_EST.print()).append("\n");
        escape.append(Printable.DOUBLE_LINE.print()).append("  ");
        if(strongbox.containsKey(ResourceType.SHIELD)){
            escape.append(Color.ANSI_BLUE.escape()).append(Printable.SQUARE.print()).append(Color.RESET).append(":").append(strongbox.get(ResourceType.SHIELD)).append(" ");
        }
        if(strongbox.containsKey(ResourceType.COIN)){
            escape.append(Color.ANSI_YELLOW.escape()).append(Printable.SQUARE.print()).append(Color.RESET).append(":").append(strongbox.get(ResourceType.COIN)).append(" ");
        }
        if(strongbox.containsKey(ResourceType.SERVANT)){
            escape.append(Color.ANSI_PURPLE.escape()).append(Printable.SQUARE.print()).append(Color.RESET).append(":").append(strongbox.get(ResourceType.SERVANT)).append(" ");
        }
        if(strongbox.containsKey(ResourceType.STONE)){
            escape.append(Color.ANSI_GREY.escape()).append(Printable.SQUARE.print()).append(Color.RESET).append(":").append(strongbox.get(ResourceType.STONE)).append(" ");
        }
        escape.append(" ").append(Printable.DOUBLE_LINE.print()).append("\n");
        escape.append(Printable.SUD_OVEST.print()).append(String.valueOf(Printable.MIDDLE.print()).repeat(15+numCifre)).append(Printable.SUD_EST.print());
        escape.append("\n");

        return escape.toString();
    }

    public void dump(){
        System.out.println(this);
    }
}
