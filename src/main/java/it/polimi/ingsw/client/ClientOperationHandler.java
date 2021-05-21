package it.polimi.ingsw.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.client_packets.*;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Warehouse;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentCard;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ClientOperationHandler {
    private SocketClientConnection socketClientConnection;
    private ObjectMapper mapper;
    private final Scanner input;
    private ClientModelView clientModelView;
    private ViewInterface viewInterface;

    public ClientOperationHandler(SocketClientConnection socketClientConnection, ClientModelView clientModelView) {
        this.socketClientConnection = socketClientConnection;
        this.clientModelView = clientModelView;
        input = new Scanner(System.in);
    }

    public void setViewInterface(ViewInterface viewInterface) {
        this.viewInterface = viewInterface;
    }

    public void handleCLIOperation(int input) throws IOException {
        switch (input) {
            case 1 -> {
                System.out.println("You have chosen to activate a Leader Card\n");
                activateLeaderCard();
            }
            case 2 -> {
                System.out.println("You have chosen to buy a Development Card\n");
                buyDevCard();
            }
            case 3 -> {
                System.out.println("You have chosen to choose a Discount\n");
                chooseDiscount();
            }
            case 4 -> {
                System.out.println("You have chosen to use your production powers\n");
                useAndChooseProductionPower();
            }
            case 5 -> {
                System.out.println("You have chosen to discard one of your Leader Card\n");
                discardLeaderCard();
            }
            case 6 -> {
                System.out.println("You have chosen to move one of your resources\n");
                moveResource();
            }
            case 7 -> {
                System.out.println("You have chosen to place one of your resource\n");
                placeResource();
            }
            case 8 -> {
                System.out.println("You have chosen to take some resources from the market\n");
                takeResourceFromMarket();
            }
            case 9 -> {
                System.out.println("Ending turn");
                endTurn();
            }
            default -> System.out.println("invalid choice, retry\n");
        }
    }

    public void sendPacket(ClientPacketHandler packet) throws JsonProcessingException {
        mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(packet);
        socketClientConnection.sendToServer(jsonResult);

    }


    public void activateLeaderCard() throws IOException {
        System.out.println("Choose the ID of the leader card to activate: ");
        viewInterface.printLeaderCards();
        int id;
        boolean LeaderCardcheck = false;
        do {
            id = input.nextInt();
            for(LeaderCard leaderCard : clientModelView.getMyPlayer().getLeaderCards()){
                if (id == leaderCard.getId()) {
                    LeaderCardcheck = true;
                    break;
                }
            }
            if(!LeaderCardcheck) System.err.println("You don't have this card, retry");
        } while (!LeaderCardcheck );
        PacketActivateLeaderCard packet = new PacketActivateLeaderCard(id);
        sendPacket(packet);
    }

    public void buyDevCard(){
        System.out.println("Select the card ID you want to buy");
        viewInterface.printDevGrid();

        Scanner input1 = new Scanner(System.in);
        int id;
        boolean devCardcheck = false;
        do {
            id = input.nextInt();
            for(DevelopmentCard developmentCard : clientModelView.getDevelopmentGrid().getDevelopmentCards()){
                if(id == developmentCard.getId()){
                    devCardcheck = true;
                    break;
                }
            }
            if(!devCardcheck) System.err.println("Development card id not found");
        } while (!devCardcheck);

        System.out.println("Choose the resource and the place in which you want to take it\n" +
                "write 0 when you have finished");

        int resource;
        int position;

        viewInterface.printStrongbox();
        viewInterface.printDeposits();

        ArrayList<ResourceType> resources = new ArrayList<>();
        ArrayList<Warehouse> warehouse = new ArrayList<>();
        do {
            do{
                resource = input.nextInt();
                switch (resource) {
                    case 1 -> resources.add(ResourceType.COIN);
                    case 2 -> resources.add(ResourceType.STONE);
                    case 3 -> resources.add(ResourceType.SERVANT);
                    case 4 -> resources.add(ResourceType.SHIELD);
                    case 0 -> {}
                    default -> System.err.println("invalid resource\n");
                }
            }while(resource < 0 || resource > 4);

            if (resource != 0) {
                do{
                    position = input.nextInt();
                    switch (position) {
                        case 1 -> warehouse.add(clientModelView.getLiteBoard().getDeposits().get(0));
                        case 2 -> warehouse.add(clientModelView.getLiteBoard().getDeposits().get(1));
                        case 3 -> warehouse.add(clientModelView.getLiteBoard().getDeposits().get(2));
                        case 4 -> warehouse.add(clientModelView.getLiteBoard().getStrongbox());
                        default -> System.err.println("invalid position\n");
                    }
                }while(position < 1 || position > 4);

            }
        }while (resource != 0);

        System.out.println("Choose in which development space you want to put your new card");

        PacketBuyDevCard packet;

        boolean devSpacecheck = false;
        do {
            String devSpace = input1.nextLine();
            switch (devSpace) {
                case "1" -> {
                    packet = new PacketBuyDevCard(id, resources, warehouse, clientModelView.getLiteBoard().getDevelopmentSpaces().get(0));
                    try {
                        sendPacket(packet);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    devSpacecheck = true;
                }
                case "2" -> {
                    packet = new PacketBuyDevCard(id, resources, warehouse, clientModelView.getLiteBoard().getDevelopmentSpaces().get(1));
                    try {
                        sendPacket(packet);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    devSpacecheck = true;
                }
                case "3" -> {
                    packet = new PacketBuyDevCard(id, resources, warehouse, clientModelView.getLiteBoard().getDevelopmentSpaces().get(2));
                    try {
                        sendPacket(packet);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    devSpacecheck = true;
                }
                case "0" -> {}
                default -> System.err.println("invalid development space\n");
            }
        } while (!devSpacecheck);
    }

    public void chooseDiscount() throws IOException {
        System.out.println("Choose the IDs of the leader cards to activate, press 0 to finish");
        viewInterface.printLeaderCards();
        boolean check = false;
        int id;
        ArrayList<Integer> leaderCards = new ArrayList<>();
        do {
            id = input.nextInt();
            for(LeaderCard leaderCard : clientModelView.getMyPlayer().getLeaderCards()){
                if (id == leaderCard.getId()) {
                    leaderCards.add(id);
                    check = true;
                }
            }
            if(!check && id != 0) System.err.println("You don't have this leader card");
        }while (id != 0);
        PacketChooseDiscount packet = new PacketChooseDiscount(leaderCards);
        sendPacket(packet);
    }

    public void chooseLeaderCardToRemove() {
        int Id1 = 0;
        int Id2 = 0;
        boolean checkid1 = false;
        boolean checkid2 = false;
        boolean inputString = false;

        viewInterface.printLeaderCards();

        do {
            try{
                Id1 = input.nextInt();
            }catch(InputMismatchException e){
                System.err.println("insert a number!");
                inputString=true;
            }
            for(LeaderCard leaderCard : clientModelView.getMyPlayer().getLeaderCards()){
                if (Id1 == leaderCard.getId()) {
                    checkid1 = true;
                    break;
                }
            }
            if(!checkid1) {
                if(!inputString) System.err.println("chosen id not present. please reinsert the id:");
                input.nextLine();
            }
        } while (!checkid1);
        inputString=false;
        do {
            try{
                Id2 = input.nextInt();
            }catch(InputMismatchException e){
                System.err.println("insert a number!");
                inputString=true;
            }
            for(LeaderCard leaderCard : clientModelView.getMyPlayer().getLeaderCards()){
                if (Id2 == leaderCard.getId() && Id2 != Id1) {
                    checkid2 = true;
                    break;
                }
            }
            if(!checkid2) {
                if(Id2==Id1) System.err.println("card already discarded");
                else if(!inputString) System.err.println("chosen id not present. please reinsert the id:");
                input.nextLine();
            }
        } while (!checkid2);

        PacketChooseLeaderCardToRemove packet = new PacketChooseLeaderCardToRemove(Id1, Id2);

        try {
            sendPacket(packet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }



    public void discardLeaderCard(){
        System.out.println("Write the ID of the leader card to discard");
        viewInterface.printLeaderCards();
        boolean check = false;
        int id;

        do{
            id = input.nextInt();
            for(LeaderCard leaderCard : clientModelView.getMyPlayer().getLeaderCards()){
                if (id == leaderCard.getId()) {
                    check = true;
                    break;
                }
            }
            if(!check) System.err.println("You don't have this Leader Card");
        }while(!check);

        PacketDiscardLeaderCard packet = new PacketDiscardLeaderCard(id);
        try {
            sendPacket(packet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    public void moveResource(){
        System.out.println("Choose the deposit in which you want to take the resource");
        viewInterface.printDeposits();
        int position = 0;

        do{
            try{
                position = input.nextInt();
            }catch(InputMismatchException e){
                System.err.println("Don't write strings");
            }
            if(position < 1 || position > 3) System.err.println("Invalid position, retry");
        }while(position < 1 || position > 3);

        PacketMoveResource packet = new PacketMoveResource(position - 1);
        try {
            sendPacket(packet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    public void
    placeResource() {
        if(clientModelView.getMyPlayer().getResourceBuffer().size() == 0){
            System.out.println("I'm sorry, you don't have any resource to place");
        }
        else{
            System.out.println("Choose the resource and the deposit in which you want to place the resource");
            viewInterface.printResourceBuffer();
            int position;
            int resource;

            do{
                resource = input.nextInt();
                if(resource > clientModelView.getMyPlayer().getResourceBuffer().size()) System.err.println("Invalid resource position, retry");
            }while(resource > clientModelView.getMyPlayer().getResourceBuffer().size());

            do{
                position = input.nextInt();
                if(position < 1 || position > 3) System.err.println("Invalid position, retry");
            }while(position < 1 || position > 3);

            PacketPlaceResource packet = new PacketPlaceResource(position - 1, resource - 1);
            try {
                sendPacket(packet);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }


    }

    public void takeResourceFromMarket(){
        System.out.println("Select Row or Column and which of the lines you choose");

        viewInterface.printMarketTray();

        Scanner input1 = new Scanner(System.in);
        boolean check = false;
        String line;
        int numLine = 0;
        do {
            line = input1.nextLine();
            if(!line.equals("Row") && !line.equals("Column")) System.err.println("invalid choice");
        } while (!line.equals("Row") && !line.equals("Column"));



        if (line.equals("Row")) {
            do {
                numLine = input.nextInt();
                if(numLine < 1 || numLine > 3) System.err.println("invalid row");
            } while (numLine < 1 || numLine > 3);
        }

        if (line.equals("Column")) {
            do {
                numLine = input.nextInt();
                if(numLine < 1 || numLine > 4) System.err.println("invalid column");
            } while (numLine < 1 || numLine > 4);
        }

        for(LeaderCard leaderCard : clientModelView.getMyPlayer().getLeaderCards()){
            if(leaderCard.getStrategy().isActive()) check = true;
        }

        ArrayList<Integer> leaderCards = new ArrayList<>();

        if(check){
            System.out.println("You have already activated one or more leader cards that transforms the white marble in some resource,\n" +
                    "select the id of the leader card to use if you want, otherwise press 0 to finish");

            int id;
            do {
                id = input.nextInt();
                if (id == clientModelView.getMyPlayer().getLeaderCards().get(0).getId() || id == clientModelView.getMyPlayer().getLeaderCards().get(1).getId()) {
                    leaderCards.add(id);
                }
                else if( id == 0){}
                else{
                    System.err.println("invalid id");
                }
            } while (id != 0);
        }


        PacketTakeResourceFromMarket packet = new PacketTakeResourceFromMarket(line, numLine, leaderCards);
        try {
            sendPacket(packet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    public void useAndChooseProductionPower(){
        //TODO: rivedere un attimo questo metodo con tutti i possibili errori, uno trovato: il server si disconnette se il potere di produzione passato è vuoto

        System.out.println("Select the IDs of the development space to use for the production. \n" +
                "Press 0 when you have finished");
        viewInterface.printDevSpaces();
        boolean checkProd = false;
        int id;

        ArrayList<ProductionPower> productionPowers = new ArrayList<>();
        do {
            id = input.nextInt();
            switch (id) {
                case 1:
                    if (clientModelView.getLiteBoard().getDevelopmentSpaces().get(0).getTopCard() != null) {
                        if(!productionPowers.contains(clientModelView.getLiteBoard().getDevelopmentSpaces().get(0).getTopCardProductionPower())){
                            productionPowers.add(clientModelView.getLiteBoard().getDevelopmentSpaces().get(0).getTopCardProductionPower());
                        }
                    }
                    break;
                case 2:
                    if (clientModelView.getLiteBoard().getDevelopmentSpaces().get(1).getTopCard() != null) {
                        if(!productionPowers.contains(clientModelView.getLiteBoard().getDevelopmentSpaces().get(1).getTopCardProductionPower())){
                            productionPowers.add(clientModelView.getLiteBoard().getDevelopmentSpaces().get(1).getTopCardProductionPower());
                        }
                    }
                    break;
                case 3:
                    if (clientModelView.getLiteBoard().getDevelopmentSpaces().get(2).getTopCard() != null) {
                        if(!productionPowers.contains(clientModelView.getLiteBoard().getDevelopmentSpaces().get(2).getTopCardProductionPower())){
                            productionPowers.add(clientModelView.getLiteBoard().getDevelopmentSpaces().get(2).getTopCardProductionPower());
                        }
                    }
                    break;
                case 0:
                    break;
                default:
                    System.err.println("Invalid development space");
            }
        }while (id != 0);

        if(clientModelView.getLiteBoard().getSpecialProductionPower().size() == 1) {
            System.out.println("Press 1 to use the special production power of the board, press 0 to continue the production");
            viewInterface.printBaseProdPower();
        }
        if(clientModelView.getLiteBoard().getSpecialProductionPower().size() > 1) {
            System.out.println("You have other production powers thanks to the leader cards you have selected, also you can use the board production power, select one or more" +
                    "of them, press 0 to continue the production");
            viewInterface.printBaseProdPower();
        }
            int prodPositions;

            do{
               prodPositions = input.nextInt();
               if(prodPositions > 0 && prodPositions <= clientModelView.getLiteBoard().getSpecialProductionPower().size()){
                   if(!productionPowers.contains(clientModelView.getLiteBoard().getSpecialProductionPower().get(prodPositions - 1))){
                       productionPowers.add(clientModelView.getLiteBoard().getSpecialProductionPower().get(prodPositions - 1));
                   }
               }
               else if(prodPositions == 0){}
               else{
                   System.err.println("invalid special production power positions, retry");
               }
            }while(prodPositions != 0);


        System.out.println("Choose the resource and the place in which you want to take it\n" +
                        "press 0 once you have finished");

        viewInterface.printDeposits();
        viewInterface.printStrongbox();

            int resource;
            int position;

            ArrayList<ResourceType> resources = new ArrayList<>();
            ArrayList<Warehouse> warehouse = new ArrayList<>();

        do {
            do{
                resource = input.nextInt();
                switch (resource) {
                    case 1 -> resources.add(ResourceType.COIN);
                    case 2 -> resources.add(ResourceType.STONE);
                    case 3 -> resources.add(ResourceType.SERVANT);
                    case 4 -> resources.add(ResourceType.SHIELD);
                    case 0 -> {}
                    default -> System.err.println("invalid resource\n");
                }
            }while(resource < 0 || resource > 4);

            if (resource != 0) {
                do{
                    position = input.nextInt();
                    switch (position) {
                        case 1 -> warehouse.add(clientModelView.getLiteBoard().getDeposits().get(0));
                        case 2 -> warehouse.add(clientModelView.getLiteBoard().getDeposits().get(1));
                        case 3 -> warehouse.add(clientModelView.getLiteBoard().getDeposits().get(2));
                        case 4 -> warehouse.add(clientModelView.getLiteBoard().getStrongbox());
                        default -> System.out.println("Invalid prodPositions\n");
                    }
                }while(position < 1 || position > 4);
            }
        }while (resource != 0);



        for(ProductionPower productionPower : productionPowers){
            if (productionPower.getResourceObtained().get(ResourceType.JOLLY) > 0) {
                checkProd = true;
                break;
            }
        }
        ArrayList<ResourceType> newResources = new ArrayList<>();
        if(checkProd){
            System.out.println("The production powers you selected have some jolly resource, choose the resources instead of the jolly\n" +
                    "press 0 to finish");

            int newResource;

            do {
                newResource = input.nextInt();
                switch (newResource) {
                    case 1 -> newResources.add(ResourceType.COIN);
                    case 2 -> newResources.add(ResourceType.STONE);
                    case 3 -> newResources.add(ResourceType.SERVANT);
                    case 4 -> newResources.add(ResourceType.SHIELD);
                    case 0 -> {}
                    default -> System.out.println("invalid resource\n");
                }
            } while (newResource < 0 || newResource > 4);
        }

        PacketUseAndChooseProdPower packet = new PacketUseAndChooseProdPower(productionPowers, resources, warehouse, newResources);
        try {
            sendPacket(packet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }



    public synchronized void chooseInitialResources(int howManyResources){


        ArrayList<Integer> deposits = new ArrayList<>();
        ArrayList<ResourceType> resources = new ArrayList<>();

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        if (howManyResources==1) System.out.println(Constants.ANSI_YELLOW+"You can choose one resource"+Constants.ANSI_RESET);
        if (howManyResources==2) System.out.println("You can choose two resources");
        for (int i = 0; i < howManyResources; i++) {
            if(i==0) Constants.printConnectionMessage(ConnectionMessages.CHOOSE_FIRST_RESOURCE);
            if(i==1) Constants.printConnectionMessage(ConnectionMessages.CHOOSE_SECOND_RESOURCE);

            resources.add(scannerChooseResources(bufferRead));
            deposits.add(scannerChooseDeposit(bufferRead));

            System.out.println("you have chosen "+resources.get(i).toString()+" in the deposit "+deposits.get(i));

        }
        PacketChooseInitialResources packet = new PacketChooseInitialResources(deposits, resources);
        try {
            sendPacket(packet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public ResourceType scannerChooseResources(BufferedReader bufferRead){

        Constants.printConnectionMessage(ConnectionMessages.RESOURCE_CHOICES);
        int whichResource=0;

        ResourceType resourcetype = null;
        do{

            try {
                whichResource = Integer.parseInt(bufferRead.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (whichResource) {
                case 1 -> resourcetype = ResourceType.COIN;
                case 2 -> resourcetype = ResourceType.STONE;
                case 3 -> resourcetype = ResourceType.SERVANT;
                case 4 -> resourcetype = ResourceType.SHIELD;
                default -> Constants.printConnectionMessage(ConnectionMessages.INVALID_CHOICE);
            }
        }while(whichResource < 1 || whichResource > 4);
        return resourcetype;
    }

    public int scannerChooseDeposit(BufferedReader bufferRead){
        int position=0;
        Constants.printConnectionMessage(ConnectionMessages.CHOOSE_DEPOSIT);
        viewInterface.printDeposits();
        do{
            try{
                try {
                    position = Integer.parseInt(bufferRead.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }catch (NumberFormatException e){
                System.err.println("insert integer");
            }
            if(position < 1|| position > 3) Constants.printConnectionMessage(ConnectionMessages.INVALID_CHOICE);

        }while(position< 1|| position>3);
        return position;
    }



    public void endTurn() throws JsonProcessingException {
        PacketEndTurn packet = new PacketEndTurn();
        sendPacket(packet);
    }

    public ViewInterface getViewInterface() {
        return viewInterface;
    }
}

