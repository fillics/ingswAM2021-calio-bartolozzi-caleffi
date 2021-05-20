package it.polimi.ingsw.client;

import it.polimi.ingsw.model.board.faithtrack.PopeFavorTileColor;
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
        escape.append(Printable.SUD_OVEST.print()).append(String.valueOf(Printable.MIDDLE.print()).repeat(numOfResources*2 +1)).append(Printable.SUD_EST.print());

        System.out.println(escape);
    }

    @Override
    public void printMarketTray() {
        clientModelView.getMarketTray().dump();
    }

    public String printColor(int i){
        StringBuilder escape = new StringBuilder();
        if((clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection())!=0 && clientModelView.getLiteBoard().getTrack().get(i).getPopeSpace()){
            if( clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()== PopeFavorTileColor.YELLOW)
                escape.append(Color.ANSI_PURPLE.escape());
            else if(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()==PopeFavorTileColor.ORANGE)
                escape.append(Color.ANSI_GREEN.escape());
            else if(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()==PopeFavorTileColor.RED)
                escape.append(Color.ANSI_RED.escape());
        }
        else if((clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection())!=0 && !clientModelView.getLiteBoard().getTrack().get(i).getPopeSpace())
            escape.append(Color.ANSI_YELLOW.escape());
        return escape.toString();
    }

    @Override
    public void printFaithTrack() {
        StringBuilder escape = new StringBuilder();
        for(int i=0; i<clientModelView.getLiteBoard().getTrack().size();i++){
                escape.append(printColor(i));
                escape.append(Printable.NORD_OVEST.print());
                if(i+1<10)
                    escape.append(Printable.MIDDLE.print().repeat(2));
                else
                    escape.append(Printable.MIDDLE.print().repeat(1));
                escape.append(i+1).append(Printable.MIDDLE.print().repeat(2)).append(Printable.NORD_EST.print());
                if(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()!=0)
                    escape.append(Color.RESET);
            }
            escape.append("\n");
            for (int i=0; i<clientModelView.getLiteBoard().getTrack().size();i++) {
                escape.append(printColor(i));
                escape.append(Printable.DOUBLE_LINE.print());
                if (clientModelView.getLiteBoard().getTrack().get(i).getVictoryPoint() != 0)
                    escape.append((" ").repeat(1));
                else
                    escape.append((" ").repeat(2));
                if (clientModelView.getLiteBoard().getTrack().get(i).getVictoryPoint() != 0) {
                    escape.append(Color.RESET).append(Color.ANSI_YELLOW.escape()).append(clientModelView.getLiteBoard().getTrack().get(i).getVictoryPoint()).append("VP").append(Color.RESET);
                    escape.append(printColor(i));

                    if (clientModelView.getLiteBoard().getTrack().get(i).getVictoryPoint() < 10)
                        escape.append(" ".repeat(1));
                } else
                    escape.append(" ".repeat(3));
                escape.append(Printable.DOUBLE_LINE.print());

                if(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()!=0)
                    escape.append(Color.RESET);
            }

            escape.append("\n");
            for (int i=0; i<clientModelView.getLiteBoard().getTrack().size();i++) {
                escape.append(printColor(i));
                escape.append(Printable.DOUBLE_LINE.print());
                if(clientModelView.getLiteBoard().getFaithMarker()==i+1){
                    escape.append(Color.RESET).append(" ".repeat(2)).append(Color.ANSI_RED.escape()).append(Printable.CROSS.print()).append(Color.RESET).append(" ".repeat(2));
                    escape.append(printColor(i));
                }
                else
                    escape.append(" ".repeat(5));
                escape.append(Printable.DOUBLE_LINE.print());
                if(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()!=0)
                    escape.append(Color.RESET);
            }
            escape.append("\n");

            for (int i=0; i<clientModelView.getLiteBoard().getTrack().size();i++) {
                escape.append(printColor(i));
                escape.append(Printable.DOUBLE_LINE.print());
                if(clientModelView.getLiteBoard().getBlackCross()!=0 && clientModelView.getLiteBoard().getBlackCross()==i){
                    escape.append(" ".repeat(2)).append(Color.RESET).append(Printable.CROSS.print()).append(" ".repeat(2));
                    escape.append(printColor(i));
                }
                else
                    escape.append(" ".repeat(5));
                escape.append(Printable.DOUBLE_LINE.print());
                if(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()!=0)
                    escape.append(Color.RESET);
            }
            escape.append("\n");

            for (int i=0; i<clientModelView.getLiteBoard().getTrack().size();i++) {
                escape.append(printColor(i));

                escape.append(Printable.SUD_OVEST.print()).append(Printable.MIDDLE.print().repeat(5)).append(Printable.SUD_EST.print());
                if(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()!=0)
                    escape.append(Color.RESET);
            }
            escape.append("\n");

            for (int i=0; i<clientModelView.getLiteBoard().getTrack().size();i++) {
                if(clientModelView.getLiteBoard().getTrack().get(i).getPopeSpace()){
                    if( clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()== PopeFavorTileColor.YELLOW)
                        escape.append(Color.ANSI_PURPLE.escape()).append(Printable.NORD_OVEST.print()).append(Printable.MIDDLE.print().repeat(5)).append(Printable.NORD_EST.print()).append(Color.RESET);
                    else if(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()==PopeFavorTileColor.ORANGE)
                        escape.append(Color.ANSI_GREEN.escape()).append(Printable.NORD_OVEST.print()).append(Printable.MIDDLE.print().repeat(5)).append(Printable.NORD_EST.print()).append(Color.RESET);
                    else if(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()==PopeFavorTileColor.RED)
                        escape.append(Color.ANSI_RED.escape()).append(Printable.NORD_OVEST.print()).append(Printable.MIDDLE.print().repeat(5)).append(Printable.NORD_EST.print()).append(Color.RESET);

                }
                else
                    escape.append(" ".repeat(7));
            }
            escape.append("\n");
            for (int i=0; i<clientModelView.getLiteBoard().getTrack().size();i++) {
                if(clientModelView.getLiteBoard().getTrack().get(i).getPopeSpace()){
                    if( clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()==PopeFavorTileColor.YELLOW)
                        escape.append(Color.ANSI_PURPLE.escape()).append(Printable.DOUBLE_LINE.print()).append(" ".repeat(1)).append(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getVictorypoint()).append("VP").append(" ".repeat(1)).append(Printable.DOUBLE_LINE.print()).append(Color.RESET);
                    else if(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()==PopeFavorTileColor.ORANGE)
                        escape.append(Color.ANSI_GREEN.escape()).append(Printable.DOUBLE_LINE.print()).append(" ".repeat(1)).append(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getVictorypoint()).append("VP").append(" ".repeat(1)).append(Printable.DOUBLE_LINE.print()).append(Color.RESET);
                    else if(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()==PopeFavorTileColor.RED)
                        escape.append(Color.ANSI_RED.escape()).append(Printable.DOUBLE_LINE.print()).append(" ".repeat(1)).append(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getVictorypoint()).append("VP").append(" ".repeat(1)).append(Printable.DOUBLE_LINE.print()).append(Color.RESET);

                }
                else
                    escape.append(" ".repeat(7));
            }
            escape.append("\n");
            for (int i=0; i<clientModelView.getLiteBoard().getTrack().size();i++) {
                if(clientModelView.getLiteBoard().getTrack().get(i).getPopeSpace()){
                    if( clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()==PopeFavorTileColor.YELLOW)
                        escape.append(Color.ANSI_PURPLE.escape()).append(Printable.SUD_OVEST.print()).append(Printable.MIDDLE.print().repeat(5)).append(Printable.SUD_EST.print()).append(Color.RESET);
                    else if(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()==PopeFavorTileColor.ORANGE)
                        escape.append(Color.ANSI_GREEN.escape()).append(Printable.SUD_OVEST.print()).append(Printable.MIDDLE.print().repeat(5)).append(Printable.SUD_EST.print()).append(Color.RESET);
                    else if(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()==PopeFavorTileColor.RED)
                        escape.append(Color.ANSI_RED.escape()).append(Printable.SUD_OVEST.print()).append(Printable.MIDDLE.print().repeat(5)).append(Printable.SUD_EST.print()).append(Color.RESET);

                }
                else
                    escape.append(" ".repeat(7));
            }
            System.out.println(escape);
    }
}
