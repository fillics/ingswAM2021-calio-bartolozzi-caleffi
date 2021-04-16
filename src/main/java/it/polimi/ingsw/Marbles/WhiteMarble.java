package it.polimi.ingsw.Marbles;

import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Cards.LeaderCards.ConcreteStrategyMarble;
import it.polimi.ingsw.Player;

/**
 * Represents a white marble that can be taken from the market tray
 */

public class WhiteMarble extends Marble{

    /**
     * Override method transform is used to do nothing when the marble is white,
     * unless a marble ability of leader cards is activated.
     */
    @Override
    public void transform(Player player){
        System.out.println("I'm nothing");
        int i;
        for(i=0; i<player.getLeaderCards().size();i++){
            if((player.getLeaderCards().get(i).getStrategy() instanceof ConcreteStrategyMarble)&&((player.getLeaderCards().get(i).getStrategy()).isActive())){
                if(player.getLeaderCards().get(i).getUseDiscountChoice()){
                    Resource newResource= new Resource(player.getLeaderCards().get(i).getStrategy().getResourceType());
                    player.getResourceBuffer().add(newResource);
                    System.out.println("But now I've been transformed into "+ player.getLeaderCards().get(i).getStrategy().getResourceType());
                    break;
                }
            }
        }
    }
}
