package it.polimi.ingsw.client;

import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.cards.developmentcards.Level;

import java.util.Arrays;

public class CLI implements ViewInterface{
    private ClientModelView clientModelView;

    public CLI(ClientModelView clientModelView) {
        this.clientModelView = clientModelView;
    }

    @Override
    public ClientModelView getClientModelView() {
        return clientModelView;
    }

    @Override
    public void printLeaderCards(){
        StringBuilder matrix= new StringBuilder();
        int size= clientModelView.getMyPlayer().getLeaderCards().size();
        System.out.println("Your leader cards:");
        String backspace= "";

        for(int i=0; i<size;i++){
            matrix.append(Printable.UPPER_BOX.print()).append("  ");
        }
        matrix.append("\n");
        for(int i=0; i<size; i++){
            if(clientModelView.getMyPlayer().getLeaderCards().get(i).getId() < 10)
                backspace="   ";
            if(clientModelView.getMyPlayer().getLeaderCards().get(i).getId() > 9)
                backspace="  ";
            matrix.append(Printable.DOUBLE_LINE.print()).append("id: ").append(clientModelView.getMyPlayer().getLeaderCards().get(i).getId()).append(backspace).append(Printable.DOUBLE_LINE.print()).append("  ");
        }
        matrix.append("\n");
        for(int i=0; i<size;i++)
            matrix.append(Printable.DOUBLE_LINE.print()).append(clientModelView.getMyPlayer().getLeaderCards().get(i).getRequirements().toString()).append("  ").append(Printable.DOUBLE_LINE.print()).append("  ");
        matrix.append("\n");
        for(int i=0; i<size;i++)
            matrix.append(Printable.DOUBLE_LINE.print()).append(clientModelView.getMyPlayer().getLeaderCards().get(i).getVictorypoint()).append(Color.ANSI_YELLOW.escape()).append(" VP").append(Color.RESET).append("    ").append(Printable.DOUBLE_LINE.print()).append("  ");
        matrix.append("\n");
        for(int i=0; i<size;i++)
            matrix.append(Printable.DOUBLE_LINE.print()).append(clientModelView.getMyPlayer().getLeaderCards().get(i).getStrategy().toString()).append(Printable.DOUBLE_LINE.print()).append("  ");
        matrix.append("\n");
        for(int i=0; i<size;i++)
            matrix.append(Printable.BOTTOM_BOX.print()).append("  ");
        matrix.append("\n");

        System.out.println(matrix);
    }

    public void printDevGrid(){
        int i;
        StringBuilder matrix= new StringBuilder();

        for(int j=0; j<3;j++){

            for(i=j*4; i<4*(j+1);i++)
                matrix.append(Printable.NORD_OVEST.print()).append(String.valueOf(Printable.MIDDLE.print()).repeat(11)).append(Printable.NORD_EST.print()).append("  ");
            matrix.append("\n");

            for(i=j*4; i<4*(j+1);i++){
                matrix.append(Printable.DOUBLE_LINE.print()).append("   ").append("id:").append(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).getId());
                if(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).getId()<10)
                    matrix.append("    ");
                else
                    matrix.append("   ");
                matrix.append(Printable.DOUBLE_LINE.print()).append("  ");
            }
            matrix.append("\n");

            for(i=j*4; i<4*(j+1);i++){
                matrix.append(Printable.DOUBLE_LINE.print());
                matrix.append(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).printColor());
                if(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).getLevel()==Level.THREE){
                    if(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).numOfResourcePrice()==1)
                        matrix.append((" ").repeat(4));
                    else if(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).numOfResourcePrice()==2)
                        matrix.append((" ").repeat(3));
                    else if(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).numOfResourcePrice()==3)
                        matrix.append((" ").repeat(2));
                }
                else{
                    if(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).numOfResourcePrice()==1)
                        matrix.append((" ").repeat(3));
                    else if(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).numOfResourcePrice()==2)
                        matrix.append((" ").repeat(2));
                    else if(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).numOfResourcePrice()==3)
                        matrix.append((" ").repeat(1));
                }

                matrix.append(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).printResourcePrice());

                if(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).numOfResourcePrice()==1)
                    matrix.append((" ").repeat(4));
                else if(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).numOfResourcePrice()==2)
                    matrix.append((" ").repeat(3));
                else if(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).numOfResourcePrice()==3)
                    matrix.append((" ").repeat(2));

                matrix.append(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).printColor());
                matrix.append(Printable.DOUBLE_LINE.print()).append("  ");
            }
            matrix.append("\n");

            for(i=j*4; i<4*(j+1);i++){
                matrix.append(Printable.DOUBLE_LINE.print());
                if(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).getProductionPower().numofResources()==2)
                    matrix.append((" ").repeat(3));
                else if (clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).getProductionPower().numofResources()==3)
                    matrix.append((" ").repeat(2));
                else if(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).getProductionPower().numofResources()==4)
                    matrix.append((" ").repeat(1));

                matrix.append(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).getProductionPower().toString());

                if(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).getProductionPower().numofResources()==2)
                    matrix.append((" ").repeat(3));
                else if (clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).getProductionPower().numofResources()==3)
                    matrix.append((" ").repeat(2));
                else if(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).getProductionPower().numofResources()==4)
                    matrix.append((" ").repeat(1));

                matrix.append(Printable.DOUBLE_LINE.print()).append("  ");
            }
            matrix.append("\n");

            for(i=j*4; i<4*(j+1);i++){
                matrix.append(Printable.DOUBLE_LINE.print());
                if(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).getVictorypoint() <10)
                    matrix.append("    ");
                else
                    matrix.append("   ");
                matrix.append(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).getVictorypoint()).append(Color.ANSI_YELLOW.escape()).append("VP").append(Color.RESET).append("    ").append(Printable.DOUBLE_LINE.print()).append("  ");
            }
            matrix.append("\n");

            for(i=j*4; i<4*(j+1);i++)
                matrix.append(Printable.SUD_OVEST.print()).append(String.valueOf(Printable.MIDDLE.print()).repeat(11)).append(Printable.SUD_EST.print()).append("  ");
            matrix.append("\n");
        }

        System.out.println(matrix);
    }

    @Override
    public void printDeposits(){
        System.out.println("Your deposits:");
        for(int i=0; i<clientModelView.getLiteBoard().getDeposits().size(); i++){
            clientModelView.getLiteBoard().getDeposits().get(i).dump();
        }
    }

    @Override
    public void printStrongbox() {
        System.out.println("Your strongbox:");
        clientModelView.getLiteBoard().getStrongbox().dump();
    }

    @Override
    public void printResourceBuffer(){
        System.out.println("These are the resources you can put in deposits:");
        StringBuilder escape = new StringBuilder();
        int numOfResources = clientModelView.getMyPlayer().getResourceBuffer().size();

        escape.append(Printable.NORD_OVEST.print()).append(String.valueOf(Printable.MIDDLE.print()).repeat(numOfResources*2 + 1)).append(Printable.NORD_EST.print()).append("\n");
        escape.append(Printable.DOUBLE_LINE.print()).append(" ");
        for(int i=1; i< numOfResources+1; i++)
            escape.append(i).append(" ");
        escape.append(Printable.DOUBLE_LINE.print()).append("\n");
        escape.append(Printable.DOUBLE_LINE.print()).append(" ");
        for (Resource resource : clientModelView.getMyPlayer().getResourceBuffer()) {
            if (resource.getType() == ResourceType.COIN)
                escape.append(Color.ANSI_YELLOW.escape()).append(Printable.SQUARE.print()).append(Color.RESET).append(" ");
            if (resource.getType() == ResourceType.STONE)
                escape.append(Color.ANSI_GREY.escape()).append(Printable.SQUARE.print()).append(Color.RESET).append(" ");
            if (resource.getType() == ResourceType.SHIELD)
                escape.append(Color.ANSI_BLUE.escape()).append(Printable.SQUARE.print()).append(Color.RESET).append(" ");
            if (resource.getType() == ResourceType.SERVANT)
                escape.append(Color.ANSI_PURPLE.escape()).append(Printable.SQUARE.print()).append(Color.RESET).append(" ");
        }
        escape.append(Printable.DOUBLE_LINE.print()).append("\n");
        escape.append(Printable.SUD_OVEST.print()).append(String.valueOf(Printable.MIDDLE.print()).repeat(numOfResources*2 +1)).append(Printable.SUD_EST.print()).append("\n");

        System.out.println(escape);
    }

    @Override
    public void printMarketTray() {
        clientModelView.getMarketTray().dump();
    }
}
