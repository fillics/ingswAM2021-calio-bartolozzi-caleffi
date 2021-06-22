package it.polimi.ingsw.client.cli;

import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.ViewInterface;
import it.polimi.ingsw.constants.Color;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.constants.Printable;
import it.polimi.ingsw.controller.client_packets.PacketNumPlayers;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.model.board.faithtrack.PopeFavorTileColor;
import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.cards.developmentcards.Level;
import it.polimi.ingsw.model.cards.leadercards.ConcreteStrategyProductionPower;

import java.util.InputMismatchException;
import java.util.Scanner;


public class CLI implements ViewInterface {

    private final ClientModelView clientModelView;
    private final Client client;
    private final Scanner input;

    public CLI(Client client, ClientModelView clientModelView) {
        input = new Scanner(System.in);
        this.client = client;
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

    @Override
    public void printActivatedLeaderCards(){
        StringBuilder matrix= new StringBuilder();
        String backspace= "";
        matrix.append("Leader cards activated: \n");

        for(int i=0; i<2;i++){
            matrix.append(Printable.UPPER_BOX.print()).append("  ");
        }
        matrix.append("\n");

        for(int i=0; i<2; i++){
            if(clientModelView.getMyPlayer().getLeaderCards().get(i)!=null){
                if(clientModelView.getMyPlayer().getLeaderCards().get(i).getId() < 10)
                    backspace="   ";
                if(clientModelView.getMyPlayer().getLeaderCards().get(i).getId() > 9)
                    backspace="  ";
                matrix.append(Printable.DOUBLE_LINE.print()).append("id: ").append(clientModelView.getMyPlayer().getLeaderCards().get(i).getId()).append(backspace).append(Printable.DOUBLE_LINE.print()).append("  ");

            }
            else{
                matrix.append(Printable.DOUBLE_LINE.print()).append(" ".repeat(8)).append(Printable.DOUBLE_LINE.print()).append("  ");
            }
        }
        matrix.append("\n");
        for(int i=0; i<2;i++){
            if(clientModelView.getMyPlayer().getLeaderCards().get(i)!=null) {
                matrix.append(Printable.DOUBLE_LINE.print()).append(clientModelView.getMyPlayer().getLeaderCards().get(i).getRequirements().toString()).append("  ").append(Printable.DOUBLE_LINE.print()).append("  ");
            }
            else{
                matrix.append(Printable.DOUBLE_LINE.print()).append(" ".repeat(8)).append(Printable.DOUBLE_LINE.print()).append("  ");
            }
        }
        matrix.append("\n");
        for(int i=0; i<2;i++) {
            if(clientModelView.getMyPlayer().getLeaderCards().get(i)!=null) {
                matrix.append(Printable.DOUBLE_LINE.print()).append(clientModelView.getMyPlayer().getLeaderCards().get(i).getVictorypoint()).append(Color.ANSI_YELLOW.escape()).append(" VP").append(Color.RESET).append("    ").append(Printable.DOUBLE_LINE.print()).append("  ");
            }
            else{
                matrix.append(Printable.DOUBLE_LINE.print()).append(" ".repeat(8)).append(Printable.DOUBLE_LINE.print()).append("  ");
            }
        }
        matrix.append("\n");
        for(int i=0; i<2;i++) {
            if(clientModelView.getMyPlayer().getLeaderCards().get(i)!=null) {
                matrix.append(Printable.DOUBLE_LINE.print()).append(clientModelView.getMyPlayer().getLeaderCards().get(i).getStrategy().toString()).append(Printable.DOUBLE_LINE.print()).append("  ");
            }
            else{
                matrix.append(Printable.DOUBLE_LINE.print()).append(" ".repeat(8)).append(Printable.DOUBLE_LINE.print()).append("  ");
            }
        }
        matrix.append("\n");
        for(int i=0; i<2;i++) {
            matrix.append(Printable.BOTTOM_BOX.print()).append("  ");
        }
        matrix.append("\n");

        System.out.println(matrix);
    }

    @Override
    public void printDevGrid(){
        int i;
        StringBuilder matrix= new StringBuilder();

        for(int j=2; j>=0;j--){

            for(i=((j+1)*4)-1; i>=j*4;i--)
                matrix.append(Printable.NORD_OVEST.print()).append(String.valueOf(Printable.MIDDLE.print()).repeat(11)).append(Printable.NORD_EST.print()).append("  ");
            matrix.append("\n");

            for(i=((j+1)*4)-1; i>=j*4;i--){
                if(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i)==null){
                    matrix.append(Printable.DOUBLE_LINE.print()).append(" ".repeat(11));
                }
                else{
                    matrix.append(Printable.DOUBLE_LINE.print()).append("   ").append("id:").append(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).getId());
                    if(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).getId()<10)
                        matrix.append("    ");
                    else
                        matrix.append("   ");
                }
                matrix.append(Printable.DOUBLE_LINE.print()).append("  ");
            }
            matrix.append("\n");

            for(i=((j+1)*4)-1; i>=j*4;i--){
                if(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i)==null){
                    matrix.append(Printable.DOUBLE_LINE.print()).append(" ".repeat(11));
                }
                else{
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
                }

                matrix.append(Printable.DOUBLE_LINE.print()).append("  ");
            }
            matrix.append("\n");

            for(i=((j+1)*4)-1; i>=j*4;i--){
                if(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i)==null){
                    matrix.append(Printable.DOUBLE_LINE.print()).append(" ".repeat(11));
                }
                else{
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
                }
                matrix.append(Printable.DOUBLE_LINE.print()).append("  ");
            }
            matrix.append("\n");

            for(i=((j+1)*4)-1; i>=j*4;i--){
                if(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i)==null){
                    matrix.append(Printable.DOUBLE_LINE.print()).append(" ".repeat(11));
                }
                else{
                    matrix.append(Printable.DOUBLE_LINE.print());
                    if(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).getVictorypoint() <10)
                        matrix.append("    ");
                    else
                        matrix.append("   ");
                    matrix.append(clientModelView.getDevelopmentGrid().getDevelopmentCards().get(i).getVictorypoint()).append(Color.ANSI_YELLOW.escape()).append("VP").append(Color.RESET).append("    ");
                }
                matrix.append(Printable.DOUBLE_LINE.print()).append("  ");
            }
            matrix.append("\n");

            for(i=((j+1)*4)-1; i>=j*4;i--)
                matrix.append(Printable.SUD_OVEST.print()).append(String.valueOf(Printable.MIDDLE.print()).repeat(11)).append(Printable.SUD_EST.print()).append("  ");
            matrix.append("\n");
        }

        System.out.println(matrix);
    }

    @Override
    public void printDeposits(){
        StringBuilder escape= new StringBuilder();
        for(int i=0; i<clientModelView.getLiteBoard().getDeposits().size(); i++){

            if(clientModelView.getLiteBoard().getDeposits().get(i).getMaxLimit()==1){
                escape.append("     ").append(Printable.NORD_OVEST.print()).append(Printable.MIDDLE.print().repeat(3)).append(Printable.NORD_EST.print()).append("\n");
                escape.append(i+1).append(":").append("   ").append(Printable.DOUBLE_LINE.print()).append(" ");
            }
            if(clientModelView.getLiteBoard().getDeposits().get(i).getMaxLimit()==2){
                escape.append("    ").append(Printable.NORD_OVEST.print()).append(Printable.MIDDLE.print().repeat(5)).append(Printable.NORD_EST.print()).append("\n");
                escape.append(i+1).append(":").append("  ").append(Printable.DOUBLE_LINE.print()).append(" ");
            }
            if(clientModelView.getLiteBoard().getDeposits().get(i).getMaxLimit()==3){
                escape.append("   ").append(Printable.NORD_OVEST.print()).append(Printable.MIDDLE.print().repeat(7)).append(Printable.NORD_EST.print()).append("\n");
                escape.append(i+1).append(":").append(" ").append(Printable.DOUBLE_LINE.print()).append(" ");
            }

            for(int j=0; j<clientModelView.getLiteBoard().getDeposits().get(i).getQuantity(); j++){
                if(clientModelView.getLiteBoard().getDeposits().get(i).getResourcetype().equals(ResourceType.COIN))
                    escape.append(Color.ANSI_YELLOW.escape()).append(Printable.SQUARE.print()).append(Color.RESET).append(" ");
                if(clientModelView.getLiteBoard().getDeposits().get(i).getResourcetype().equals(ResourceType.SERVANT))
                    escape.append(Color.ANSI_PURPLE.escape()).append(Printable.SQUARE.print()).append(Color.RESET).append(" ");
                if(clientModelView.getLiteBoard().getDeposits().get(i).getResourcetype().equals(ResourceType.SHIELD))
                    escape.append(Color.ANSI_BLUE.escape()).append(Printable.SQUARE.print()).append(Color.RESET).append(" ");
                if(clientModelView.getLiteBoard().getDeposits().get(i).getResourcetype().equals(ResourceType.STONE))
                    escape.append(Color.ANSI_GREY.escape()).append(Printable.SQUARE.print()).append(Color.RESET).append(" ");
            }
            for(int k=clientModelView.getLiteBoard().getDeposits().get(i).getQuantity(); k<clientModelView.getLiteBoard().getDeposits().get(i).getMaxLimit() ; k++)
                escape.append(Printable.WHITE_SQUARE.print()).append(" ");

            escape.append(Printable.DOUBLE_LINE.print()).append("\n");

            if(clientModelView.getLiteBoard().getDeposits().get(i).getMaxLimit()==1){
                escape.append("     ").append(Printable.SUD_OVEST.print()).append(Printable.MIDDLE.print().repeat(3)).append(Printable.SUD_EST.print());
            }
            if(clientModelView.getLiteBoard().getDeposits().get(i).getMaxLimit()==2){
                escape.append("    ").append(Printable.SUD_OVEST.print()).append(Printable.MIDDLE.print().repeat(5)).append(Printable.SUD_EST.print());
            }
            if(clientModelView.getLiteBoard().getDeposits().get(i).getMaxLimit()==3){
                escape.append("   ").append(Printable.SUD_OVEST.print()).append(Printable.MIDDLE.print().repeat(7)).append(Printable.SUD_EST.print());
            }
            if(i!=clientModelView.getLiteBoard().getDeposits().size()-1)
                escape.append("\n");
        }
        System.out.println(escape);
    }

    @Override
    public void printStrongbox() {
        StringBuilder escape = new StringBuilder();
        int numCifre = 0, num;
        if(clientModelView.getLiteBoard().getStrongbox().getStrongbox().containsKey(ResourceType.SHIELD)){
            num=clientModelView.getLiteBoard().getStrongbox().getStrongbox().get(ResourceType.SHIELD);
            if(num==0)
                numCifre+=1;
            else{
                while(num!=0) {
                    num= num/10;
                    numCifre += 1;
                }
            }
        }
        if(clientModelView.getLiteBoard().getStrongbox().getStrongbox().containsKey(ResourceType.COIN)){
            num=clientModelView.getLiteBoard().getStrongbox().getStrongbox().get(ResourceType.COIN);
            if(num==0)
                numCifre+=1;
            else{
                while(num!=0) {
                    num= num/10;
                    numCifre += 1;
                }
            }
        }
        if(clientModelView.getLiteBoard().getStrongbox().getStrongbox().containsKey(ResourceType.SERVANT)){
            num=clientModelView.getLiteBoard().getStrongbox().getStrongbox().get(ResourceType.SERVANT);
            if(num==0)
                numCifre+=1;
            else{
                while(num!=0) {
                    num= num/10;
                    numCifre += 1;
                }
            }
        }
        if(clientModelView.getLiteBoard().getStrongbox().getStrongbox().containsKey(ResourceType.STONE)){
            num=clientModelView.getLiteBoard().getStrongbox().getStrongbox().get(ResourceType.STONE);
            if(num==0)
                numCifre+=1;
            else{
                while(num!=0) {
                    num= num/10;
                    numCifre += 1;
                }
            }
        }

        escape.append("   ").append(Printable.NORD_OVEST.print()).append(String.valueOf(Printable.MIDDLE.print()).repeat(15+numCifre)).append(Printable.NORD_EST.print()).append("\n");
        escape.append(clientModelView.getLiteBoard().getDeposits().size()+1).append(":").append(" ").append(Printable.DOUBLE_LINE.print()).append("  ");
        if(clientModelView.getLiteBoard().getStrongbox().getStrongbox().containsKey(ResourceType.SHIELD)){
            escape.append(Color.ANSI_BLUE.escape()).append(Printable.SQUARE.print()).append(Color.RESET).append(":").append(clientModelView.getLiteBoard().getStrongbox().getStrongbox().get(ResourceType.SHIELD)).append(" ");
        }
        if(clientModelView.getLiteBoard().getStrongbox().getStrongbox().containsKey(ResourceType.COIN)){
            escape.append(Color.ANSI_YELLOW.escape()).append(Printable.SQUARE.print()).append(Color.RESET).append(":").append(clientModelView.getLiteBoard().getStrongbox().getStrongbox().get(ResourceType.COIN)).append(" ");
        }
        if(clientModelView.getLiteBoard().getStrongbox().getStrongbox().containsKey(ResourceType.SERVANT)){
            escape.append(Color.ANSI_PURPLE.escape()).append(Printable.SQUARE.print()).append(Color.RESET).append(":").append(clientModelView.getLiteBoard().getStrongbox().getStrongbox().get(ResourceType.SERVANT)).append(" ");
        }
        if(clientModelView.getLiteBoard().getStrongbox().getStrongbox().containsKey(ResourceType.STONE)){
            escape.append(Color.ANSI_GREY.escape()).append(Printable.SQUARE.print()).append(Color.RESET).append(":").append(clientModelView.getLiteBoard().getStrongbox().getStrongbox().get(ResourceType.STONE)).append(" ");
        }
        escape.append(" ").append(Printable.DOUBLE_LINE.print()).append("\n");
        escape.append("   ").append(Printable.SUD_OVEST.print()).append(String.valueOf(Printable.MIDDLE.print()).repeat(15+numCifre)).append(Printable.SUD_EST.print());
        escape.append("\n");

        System.out.println(escape);
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
        if((clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection())!=0 && clientModelView.getLiteBoard().getTrack().get(i).isPopeSpace()){
            if( clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()== PopeFavorTileColor.YELLOW)
                escape.append(Color.ANSI_PURPLE.escape());
            else if(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()== PopeFavorTileColor.ORANGE)
                escape.append(Color.ANSI_BLUE.escape());
            else if(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()==PopeFavorTileColor.RED)
                escape.append(Color.ANSI_RED.escape());
        }
        else if((clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection())!=0 && !clientModelView.getLiteBoard().getTrack().get(i).isPopeSpace())
            escape.append(Color.ANSI_YELLOW.escape());
        return escape.toString();
    }

    @Override
    public void printFaithTrack() {
        StringBuilder escape = new StringBuilder();

        escape.append(Color.ANSI_GREEN.escape()).append(Printable.CHECK.print()).append(Color.RESET).append(" = ACTIVE TILE \n");
        escape.append(Color.ANSI_RED.escape()).append(Printable.RED_CROSS.print()).append(Color.RESET).append(" = NON ACTIVE TILE \n");

        escape.append(Printable.NORD_OVEST.print()).append(Printable.MIDDLE.print().repeat(2)).append("0").append(Printable.MIDDLE.print().repeat(2)).append(Printable.NORD_EST.print());

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

        escape.append(Printable.DOUBLE_LINE.print()).append((" ").repeat(5)).append(Printable.DOUBLE_LINE.print());

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

        if(clientModelView.getLiteBoard().getFaithMarker()==0)
            escape.append(Printable.DOUBLE_LINE.print()).append(" ".repeat(2)).append(Color.ANSI_RED.escape()).append(Printable.CROSS.print()).append(Color.RESET).append(" ".repeat(2)).append(Printable.DOUBLE_LINE.print());
        else
            escape.append(Printable.DOUBLE_LINE.print()).append(" ".repeat(2)).append(" ".repeat(3)).append(Printable.DOUBLE_LINE.print());

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

        if(clientModelView.isSingleGame() && clientModelView.getLiteBoard().getBlackCross()==0){
            escape.append(Printable.DOUBLE_LINE.print()).append((" ").repeat(2)).append(Printable.CROSS.print()).append(" ".repeat(2)).append(Printable.DOUBLE_LINE.print());
        }
        else
            escape.append(Printable.DOUBLE_LINE.print()).append(" ".repeat(5)).append(Printable.DOUBLE_LINE.print());

        for (int i=0; i<clientModelView.getLiteBoard().getTrack().size();i++) {
            escape.append(printColor(i));
            escape.append(Printable.DOUBLE_LINE.print());
            if(clientModelView.getLiteBoard().getBlackCross()!=0 && clientModelView.getLiteBoard().getBlackCross()==i+1){
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

        escape.append(Printable.SUD_OVEST.print()).append(Printable.MIDDLE.print().repeat(5)).append(Printable.SUD_EST.print());

        for (int i=0; i<clientModelView.getLiteBoard().getTrack().size();i++) {
            escape.append(printColor(i));
            escape.append(Printable.SUD_OVEST.print()).append(Printable.MIDDLE.print().repeat(5)).append(Printable.SUD_EST.print());
            if(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()!=0)
                escape.append(Color.RESET);
        }

        escape.append("\n");
        escape.append(" ".repeat(7));
        for (int i=0; i<clientModelView.getLiteBoard().getTrack().size();i++) {
            if(clientModelView.getLiteBoard().getTrack().get(i).isPopeSpace()){
                if( clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()== PopeFavorTileColor.YELLOW)
                    escape.append(Color.ANSI_PURPLE.escape()).append(Printable.NORD_OVEST.print()).append(Printable.MIDDLE.print().repeat(5)).append(Printable.NORD_EST.print()).append(Color.RESET);
                else if(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()==PopeFavorTileColor.ORANGE)
                    escape.append(Color.ANSI_BLUE.escape()).append(Printable.NORD_OVEST.print()).append(Printable.MIDDLE.print().repeat(5)).append(Printable.NORD_EST.print()).append(Color.RESET);
                else if(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()==PopeFavorTileColor.RED)
                    escape.append(Color.ANSI_RED.escape()).append(Printable.NORD_OVEST.print()).append(Printable.MIDDLE.print().repeat(5)).append(Printable.NORD_EST.print()).append(Color.RESET);
            }
            else
                escape.append(" ".repeat(7));
        }

        escape.append("\n");
        escape.append(" ".repeat(7));
        for (int i=0; i<clientModelView.getLiteBoard().getTrack().size();i++) {
            if(clientModelView.getLiteBoard().getTrack().get(i).isPopeSpace()){
                if( clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()==PopeFavorTileColor.YELLOW){
                    if(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().isVisible())
                        escape.append(Color.ANSI_PURPLE.escape()).append(Printable.DOUBLE_LINE.print()).append(" ".repeat(2)).append(Color.RESET).append(Color.ANSI_GREEN.escape()).append(Printable.CHECK.print()).append(Color.RESET).append(" ".repeat(2)).append(Color.ANSI_PURPLE.escape()).append(Printable.DOUBLE_LINE.print()).append(Color.RESET);
                    else
                        escape.append(Color.ANSI_PURPLE.escape()).append(Printable.DOUBLE_LINE.print()).append(" ".repeat(2)).append(Color.RESET).append(Color.ANSI_RED.escape()).append(Printable.RED_CROSS.print()).append(Color.RESET).append(" ".repeat(2)).append(Color.ANSI_PURPLE.escape()).append(Printable.DOUBLE_LINE.print()).append(Color.RESET);
                }
                else if(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()==PopeFavorTileColor.ORANGE){
                    if(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().isVisible())
                        escape.append(Color.ANSI_BLUE.escape()).append(Printable.DOUBLE_LINE.print()).append(" ".repeat(2)).append(Color.RESET).append(Color.ANSI_GREEN.escape()).append(Printable.CHECK.print()).append(Color.RESET).append(" ".repeat(2)).append(Color.ANSI_BLUE.escape()).append(Printable.DOUBLE_LINE.print()).append(Color.RESET);
                    else
                        escape.append(Color.ANSI_BLUE.escape()).append(Printable.DOUBLE_LINE.print()).append(" ".repeat(2)).append(Color.RESET).append(Color.ANSI_RED.escape()).append(Printable.RED_CROSS.print()).append(Color.RESET).append(" ".repeat(2)).append(Color.ANSI_BLUE.escape()).append(Printable.DOUBLE_LINE.print()).append(Color.RESET);
                }
                else if(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()==PopeFavorTileColor.RED){
                    if(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().isVisible())
                        escape.append(Color.ANSI_RED.escape()).append(Printable.DOUBLE_LINE.print()).append(" ".repeat(2)).append(Color.RESET).append(Color.ANSI_GREEN.escape()).append(Printable.CHECK.print()).append(Color.RESET).append(" ".repeat(2)).append(Color.ANSI_RED.escape()).append(Printable.DOUBLE_LINE.print()).append(Color.RESET);
                    else
                        escape.append(Color.ANSI_RED.escape()).append(Printable.DOUBLE_LINE.print()).append(" ".repeat(2)).append(Color.RESET).append(Color.ANSI_RED.escape()).append(Printable.RED_CROSS.print()).append(Color.RESET).append(" ".repeat(2)).append(Color.ANSI_RED.escape()).append(Printable.DOUBLE_LINE.print()).append(Color.RESET);
                }
            }
            else
                escape.append(" ".repeat(7));
        }

        escape.append("\n");
        escape.append(" ".repeat(7));
        for (int i=0; i<clientModelView.getLiteBoard().getTrack().size();i++) {
            if(clientModelView.getLiteBoard().getTrack().get(i).isPopeSpace()){
                if( clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()==PopeFavorTileColor.YELLOW)
                    escape.append(Color.ANSI_PURPLE.escape()).append(Printable.DOUBLE_LINE.print()).append(" ".repeat(1)).append(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getVictorypoint()).append("VP").append(" ".repeat(1)).append(Printable.DOUBLE_LINE.print()).append(Color.RESET);
                else if(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()==PopeFavorTileColor.ORANGE)
                    escape.append(Color.ANSI_BLUE.escape()).append(Printable.DOUBLE_LINE.print()).append(" ".repeat(1)).append(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getVictorypoint()).append("VP").append(" ".repeat(1)).append(Printable.DOUBLE_LINE.print()).append(Color.RESET);
                else if(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()==PopeFavorTileColor.RED)
                    escape.append(Color.ANSI_RED.escape()).append(Printable.DOUBLE_LINE.print()).append(" ".repeat(1)).append(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getVictorypoint()).append("VP").append(" ".repeat(1)).append(Printable.DOUBLE_LINE.print()).append(Color.RESET);
            }
            else
                escape.append(" ".repeat(7));
        }
        escape.append("\n");
        escape.append(" ".repeat(7));
        for (int i=0; i<clientModelView.getLiteBoard().getTrack().size();i++) {
            if(clientModelView.getLiteBoard().getTrack().get(i).isPopeSpace()){
                if( clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()==PopeFavorTileColor.YELLOW)
                    escape.append(Color.ANSI_PURPLE.escape()).append(Printable.SUD_OVEST.print()).append(Printable.MIDDLE.print().repeat(5)).append(Printable.SUD_EST.print()).append(Color.RESET);
                else if(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()==PopeFavorTileColor.ORANGE)
                    escape.append(Color.ANSI_BLUE.escape()).append(Printable.SUD_OVEST.print()).append(Printable.MIDDLE.print().repeat(5)).append(Printable.SUD_EST.print()).append(Color.RESET);
                else if(clientModelView.getLiteBoard().getVaticanReportSections().get(clientModelView.getLiteBoard().getTrack().get(i).getVaticanReportSection()-1).getPopefavortile().getColor()==PopeFavorTileColor.RED)
                    escape.append(Color.ANSI_RED.escape()).append(Printable.SUD_OVEST.print()).append(Printable.MIDDLE.print().repeat(5)).append(Printable.SUD_EST.print()).append(Color.RESET);
            }
            else
                escape.append(" ".repeat(7));
        }
        System.out.println(escape);
    }

    @Override
    public void printDevSpaces() {
        StringBuilder escape = new StringBuilder();
        int size;
        escape.append("Development spaces: \n");

        for(int k = 0; k <clientModelView.getLiteBoard().getDevelopmentSpaces().size(); k++) {
            escape.append(k+1).append(": ").append("\n");
            size = clientModelView.getLiteBoard().getDevelopmentSpaces().get(k).getDevelopmentCardsOfDevSpace().size();
            if(size!=0){
                escape.append(clientModelView.getLiteBoard().getDevelopmentSpaces().get(k).getTopCard()).append("\n");
                if(size>1){
                    for(int i=size-2;i>=0;i--){
                        escape.append(Printable.DOUBLE_LINE.print());
                        escape.append(clientModelView.getLiteBoard().getDevelopmentSpaces().get(i).getDevelopmentCardsOfDevSpace().get(i).printColor());
                        escape.append("   ");
                        escape.append(clientModelView.getLiteBoard().getDevelopmentSpaces().get(i).getDevelopmentCardsOfDevSpace().get(i).getVictorypoint()).append(Color.ANSI_YELLOW.escape()).append("VP").append(Color.RESET);
                        if(clientModelView.getLiteBoard().getDevelopmentSpaces().get(i).getDevelopmentCardsOfDevSpace().get(i).getLevel()==Level.THREE)
                            escape.append(" ".repeat(3));
                        else if((clientModelView.getLiteBoard().getDevelopmentSpaces().get(i).getDevelopmentCardsOfDevSpace().get(i).getLevel()==Level.ONE || clientModelView.getLiteBoard().getDevelopmentSpaces().get(i).getDevelopmentCardsOfDevSpace().get(i).getLevel()==Level.TWO) && clientModelView.getLiteBoard().getDevelopmentSpaces().get(i).getDevelopmentCardsOfDevSpace().get(i).getVictorypoint()>9)
                            escape.append(" ".repeat(2));
                        else if ((clientModelView.getLiteBoard().getDevelopmentSpaces().get(i).getDevelopmentCardsOfDevSpace().get(i).getLevel()==Level.ONE || clientModelView.getLiteBoard().getDevelopmentSpaces().get(i).getDevelopmentCardsOfDevSpace().get(i).getLevel()==Level.TWO) && clientModelView.getLiteBoard().getDevelopmentSpaces().get(i).getDevelopmentCardsOfDevSpace().get(i).getVictorypoint()<10)
                            escape.append(" ".repeat(3));
                        escape.append(clientModelView.getLiteBoard().getDevelopmentSpaces().get(i).getDevelopmentCardsOfDevSpace().get(i).printColor()).append(Printable.DOUBLE_LINE.print()).append("\n");
                        escape.append(Printable.SUD_OVEST.print()).append(String.valueOf(Printable.MIDDLE.print()).repeat(11)).append(Printable.SUD_EST.print());
                        escape.append("\n");
                    }
                }
            } else{
                escape.append(Printable.NORD_OVEST.print()).append(String.valueOf(Printable.MIDDLE.print()).repeat(11)).append(Printable.NORD_EST.print()).append("  ");
                escape.append("\n");
                for(int i=0; i<4;i++)
                    escape.append(Printable.DOUBLE_LINE.print()).append(" ".repeat(11)).append(Printable.DOUBLE_LINE.print()).append("\n");
                escape.append(Printable.SUD_OVEST.print()).append(String.valueOf(Printable.MIDDLE.print()).repeat(11)).append(Printable.SUD_EST.print()).append("  ");
                escape.append("\n");
            }
            if(clientModelView.getLiteBoard().getDevelopmentSpaces().get(k).getTopCard()!=null)
                escape.append("\n".repeat(2));
        }

        System.out.println(escape);
    }

    @Override
    public void printBaseProdPower() {
        StringBuilder escape = new StringBuilder();
        escape.append("1: \n");
        escape.append(1).append(Printable.QUESTION_MARK.print()).append(" 1").append(Printable.QUESTION_MARK.print()).append("}");
        escape.append(1).append(Printable.QUESTION_MARK.print()).append(" (NO").append(Color.ANSI_RED.escape()).append(Printable.CROSS.print()).append(Color.RESET).append(")").append("\n");

        for(int i=0; i<clientModelView.getMyPlayer().getLeaderCards().size(); i++){
            if(clientModelView.getMyPlayer().getLeaderCards().get(i).getStrategy() instanceof ConcreteStrategyProductionPower && clientModelView.getMyPlayer().getLeaderCards().get(i).getStrategy().isActive()){
                escape.append(i+2).append(" :\n");
                escape.append(clientModelView.getMyPlayer().getLeaderCards().get(i)).append("\n".repeat(2));
            }
        }
        System.out.println(escape);
    }

    @Override
    public void printResourcesLegend() {
        String escape = Color.ANSI_BLUE.escape() + Printable.SQUARE.print() + Color.RESET + " = SHIELD\n" +
                Color.ANSI_GREY.escape() + Printable.SQUARE.print() + Color.RESET + " = STONE\n" +
                Color.ANSI_PURPLE.escape() + Printable.SQUARE.print() + Color.RESET + " = SERVANT\n" +
                Color.ANSI_YELLOW.escape() + Printable.SQUARE.print() + Color.RESET + " = COIN\n";
        System.out.println(escape);
    }

    public void serverMatch() {
        System.out.println(">Insert the server IP address");
        System.out.print(">");
        String ip = input.nextLine();
        Constants.setAddressServer(ip);
        System.out.println(">Insert the server port");
        System.out.print(">");
        int port = 0;
        try{
            port = input.nextInt();
        }catch(InputMismatchException e){
            System.err.println("insert only numbers");
        }
        Constants.setPort(port);
    }


    public void choosePlayerNumber(int numPlayers) {
        do {
            try {
                if(numPlayers < Constants.getNumMinPlayers() || numPlayers > Constants.getNumMaxPlayers()){
                    Constants.printConnectionMessage(ConnectionMessages.INVALID_NUM_PLAYERS);
                    numPlayers = input.nextInt();
                }
            }catch (InputMismatchException e) {
                System.err.println("Invalid parameter: insert a numeric value.");
            }
        }while(numPlayers < Constants.getNumMinPlayers() || numPlayers > Constants.getNumMaxPlayers());
        client.serializeAndSend(new PacketNumPlayers(numPlayers));
    }

}
