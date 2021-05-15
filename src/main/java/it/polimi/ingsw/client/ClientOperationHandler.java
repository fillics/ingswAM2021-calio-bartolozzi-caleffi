package it.polimi.ingsw.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.controller.client_packets.*;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Warehouse;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentCard;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientOperationHandler {
    private SocketClientConnection socketClientConnection;
    private ObjectMapper mapper;
    private final Scanner input;
    private ClientModelView clientModelView;

    public ClientOperationHandler(SocketClientConnection socketClientConnection, ClientModelView clientModelView) {
        this.socketClientConnection = socketClientConnection;
        this.clientModelView = clientModelView;
        input = new Scanner(System.in);
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
            case 9 -> System.out.println("Ending turn");
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
        } while (!LeaderCardcheck );
        PacketActivateLeaderCard packet = new PacketActivateLeaderCard(id);
        sendPacket(packet);
    }

    public void buyDevCard() throws IOException {
        System.out.println("Select the card ID you want to buy");

        int id;
        boolean DevCardcheck = false;
        do {
            id = input.nextInt();
            for(DevelopmentCard developmentCard : clientModelView.getDevelopmentGrid().getDevelopmentCards()){
                if(id == developmentCard.getId()){
                    DevCardcheck = true;
                    break;
                }
            }
        } while (!DevCardcheck);

        System.out.println("Choose the resource and the place in which you want to take it\n" +
                "write 0 when you have finished");

        int resource;
        int position;

        ArrayList<ResourceType> resources = new ArrayList<>();
        ArrayList<Warehouse> warehouse = new ArrayList<>();
        do {
            do{
                resource = input.nextInt();
                switch (resource) {
                    case 0:
                        break;
                    case 1:
                        resources.add(ResourceType.COIN);
                        break;
                    case 2:
                        resources.add(ResourceType.STONE);
                        break;
                    case 3:
                        resources.add(ResourceType.SERVANT);
                        break;
                    case 4:
                        resources.add(ResourceType.SHIELD);
                        break;
                    default:
                        System.out.println("invalid resource\n");
                        break;
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
                        default -> System.out.println("invalid position\n");
                    }
                }while(position < 1 || position > 4);

            }
        }while (resource != 0);

        System.out.println("Choose in which development space you want to put your new card");

        PacketBuyDevCard packet;

        boolean DevSpacecheck = false;
        do {
            String devSpace = input.nextLine();
            switch (devSpace) {
                case "1" -> {
                    packet = new PacketBuyDevCard(id, resources, warehouse, clientModelView.getLiteBoard().getDevelopmentSpaces().get(0));
                    sendPacket(packet);
                    DevSpacecheck = true;
                }
                case "2" -> {
                    packet = new PacketBuyDevCard(id, resources, warehouse, clientModelView.getLiteBoard().getDevelopmentSpaces().get(1));
                    sendPacket(packet);
                    DevSpacecheck = true;
                }
                case "3" -> {
                    packet = new PacketBuyDevCard(id, resources, warehouse, clientModelView.getLiteBoard().getDevelopmentSpaces().get(2));
                    sendPacket(packet);
                    DevSpacecheck = true;
                }
                default -> System.out.println("invalid development space\n");
            }
        } while (!DevSpacecheck);
    }

    public void chooseDiscount() throws IOException {
        System.out.println("Choose the IDs of the leader cards to activate, when you have finished write 0");

        int id;
        ArrayList<Integer> leaderCards = new ArrayList<>();
        do {
            id = input.nextInt();
            for(LeaderCard leaderCard : clientModelView.getMyPlayer().getLeaderCards()){
                if (id == leaderCard.getId()) {
                    leaderCards.add(id);
                }
            }
        }while (id != 0);
        PacketChooseDiscount packet = new PacketChooseDiscount(leaderCards);
        sendPacket(packet);
    }

    public void chooseLeaderCardToRemove() throws IOException {
        int Id1;
        int Id2;
        boolean checkid1 = false;
        boolean checkid2 = false;

        System.out.println("Choose 2 cards: ");

        // TODO: 13/05/2021 mettere frase di errore se il tizio sbaglia a inserire
        do {
            Id1 = input.nextInt();
            for(LeaderCard leaderCard : clientModelView.getMyPlayer().getLeaderCards()){
                if (Id1 == leaderCard.getId()) {
                    checkid1 = true;
                    break;
                }
            }
        } while (!checkid1);


        do {
            Id2 = input.nextInt();
            for(LeaderCard leaderCard : clientModelView.getMyPlayer().getLeaderCards()){
                if (Id2 == leaderCard.getId() && Id2 != Id1) {
                    checkid2 = true;
                    break;
                }
            }
        } while (!checkid2);

        PacketChooseLeaderCardToRemove packet = new PacketChooseLeaderCardToRemove(Id1, Id2);
        //System.out.println(packet);
        sendPacket(packet);
    }

    public void discardLeaderCard() throws IOException {
        System.out.println("Write the ID of the leader card to discard");
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
        }while(!check);

        PacketDiscardLeaderCard packet = new PacketDiscardLeaderCard(id);
        sendPacket(packet);

    }

    public void moveResource() throws IOException {
        System.out.println("Choose the deposit in which you want to take the resource");
        int position;

        do{
            position = input.nextInt();
        }while(position < 1 || position > 3);

        PacketMoveResource packet = new PacketMoveResource(position - 1);
        sendPacket(packet);

    }

    public void placeResource() throws IOException {
        if(clientModelView.getMyPlayer().getResourceBuffer().size() == 0){
            System.out.println("I'm sorry, you don't have any resource to place");
        }
        else{
            System.out.println("Choose the resource and the deposit in which you want to place the resource");
            int position;
            int resource;

            do{
                resource = input.nextInt();
            }while(resource > clientModelView.getMyPlayer().getResourceBuffer().size());

            do{
                position = input.nextInt();
            }while(position < 1 || position > 3);

            PacketPlaceResource packet = new PacketPlaceResource(resource - 1, position - 1);
            sendPacket(packet);
        }


    }

    public void takeResourceFromMarket() throws IOException {
        System.out.println("Select Row or Column and which of the lines you choose");

        String line;
        int numLine = 0;

        do {
            line = input.nextLine();
        } while (!line.equals("Row") && !line.equals("Column"));



        if (line.equals("Row")) {
            do {
                numLine = input.nextInt();
            } while (numLine < 1 || numLine > 3);
        }

        if (line.equals("Column")) {
            do {
                numLine = input.nextInt();
            } while (numLine < 1 || numLine > 4);
        }

        System.out.println("If you have already activated one or more leader cards that transforms the white marble in some resource,\n" +
                "select the id of the leader card to use, otherwise write 0\n"+
                "write 0 when you have finished");

        int id;
        ArrayList<Integer> leaderCards = new ArrayList<>();
        do {
            id = input.nextInt();
            if (id == clientModelView.getMyPlayer().getLeaderCards().get(0).getId() || id == clientModelView.getMyPlayer().getLeaderCards().get(1).getId()) {
                leaderCards.add(id);
            }
        } while (id != 0);

        PacketTakeResourceFromMarket packet = new PacketTakeResourceFromMarket(line, numLine, leaderCards);
        sendPacket(packet);
    }


    public void useAndChooseProductionPower() throws IOException {
        System.out.println("Select the IDs of the development space to use for the production. \n" +
                "Press 0 when you have finished");

        int id;

        ArrayList<ProductionPower> productionPowers = new ArrayList<>();
        do {
            id = input.nextInt();
            switch (id) {
                case 1:
                    if (!productionPowers.contains(clientModelView.getLiteBoard().getDevelopmentSpaces().get(0).getTopCardProductionPower()) && clientModelView.getLiteBoard().getDevelopmentSpaces().get(0) != null) {
                        productionPowers.add(clientModelView.getLiteBoard().getDevelopmentSpaces().get(0).getTopCardProductionPower());
                    }
                    break;
                case 2:
                    if (!productionPowers.contains(clientModelView.getLiteBoard().getDevelopmentSpaces().get(1).getTopCardProductionPower()) && clientModelView.getLiteBoard().getDevelopmentSpaces().get(0) != null) {
                        productionPowers.add(clientModelView.getLiteBoard().getDevelopmentSpaces().get(1).getTopCardProductionPower());
                    }
                    break;
                case 3:
                    if (!productionPowers.contains(clientModelView.getLiteBoard().getDevelopmentSpaces().get(2).getTopCardProductionPower()) && clientModelView.getLiteBoard().getDevelopmentSpaces().get(0) != null) {
                        productionPowers.add(clientModelView.getLiteBoard().getDevelopmentSpaces().get(2).getTopCardProductionPower());
                    }
                    break;
            }
        }while (id != 0);

        if(clientModelView.getLiteBoard().getSpecialProductionPower().size() > 1){
            System.out.println("You have other production powers thanks to the leader cards you have selected, also you can use the board production power, select one or more" +
                    "of them, otherwise press 0 to finish");
            int position;

            do{
               position = input.nextInt();
               if(position > 0 && position < clientModelView.getLiteBoard().getSpecialProductionPower().size()){
                   if(!productionPowers.contains(clientModelView.getLiteBoard().getSpecialProductionPower().get(position - 1))){
                       productionPowers.add(clientModelView.getLiteBoard().getSpecialProductionPower().get(position - 1));
                   }
               }
            }while(position != 0);
        }

        System.out.println("Choose the resource and the place in which you want to take it\n" +
                        "write 0 when you have finished");

            int resource;
            int position;

            ArrayList<ResourceType> resources = new ArrayList<>();
            ArrayList<Warehouse> warehouse = new ArrayList<>();

        do {
            do{
                resource = input.nextInt();
                switch (resource) {
                    case 0:
                        break;
                    case 1:
                        resources.add(ResourceType.COIN);
                        break;
                    case 2:
                        resources.add(ResourceType.STONE);
                        break;
                    case 3:
                        resources.add(ResourceType.SERVANT);
                        break;
                    case 4:
                        resources.add(ResourceType.SHIELD);
                        break;
                    default:
                        System.out.println("invalid resource\n");
                        break;
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
                        default -> System.out.println("invalid position\n");
                    }
                }while(position < 1 || position > 4);
            }
        }while (resource != 0);

            System.out.println("If the production powers you selected have some jolly resource, choose the resource instead of the jolly\n" +
                    "if there's no jolly resource obtainable or in case you have finished write 0");

            ArrayList<ResourceType> newResources = new ArrayList<>();
            int newResource;

        do{
            newResource = input.nextInt();
            switch (newResource) {
                case 0:
                    break;
                case 1:
                    newResources.add(ResourceType.COIN);
                    break;
                case 2:
                    newResources.add(ResourceType.STONE);
                    break;
                case 3:
                    newResources.add(ResourceType.SERVANT);
                    break;
                case 4:
                    newResources.add(ResourceType.SHIELD);
                    break;
                default:
                    System.out.println("invalid resource\n");
                    break;
            }
        }while(newResource < 0 || newResource > 4);

        PacketUseAndChooseProdPower packet = new PacketUseAndChooseProdPower(productionPowers, resources, warehouse, newResources);
        sendPacket(packet);
    }

    public synchronized int chooseInitialResources() throws JsonProcessingException {
        int n,first,pos, i=0;
        int position;
        int resource;
        ResourceType resourcetype = null;

        n = clientModelView.getNumOfPlayers();
        first = clientModelView.getFirstPosition();
        pos = clientModelView.getMyPlayer().getPosInGame();

        if(((n ==2 || n ==3) && (pos != first)) ||
                (n==4 && ( (first== 0 && (pos==1 || pos == 2)) || (first ==1 && (pos == 2 || pos ==3)) ||
                        (first== 2 && (pos== 3 || pos == 0)) || (first ==3 && (pos ==0 || pos ==1))))){
            i=1;
            System.out.println("Choose the initial resource");
            System.out.println("Write 1 to select COIN, 2 to select STONE, 3 to select SERVANT, 4 to select SHIELD");
            do{
                resource = input.nextInt();
                switch (resource) {
                    case 0:
                        break;
                    case 1:
                        resourcetype = ResourceType.COIN;
                        break;
                    case 2:
                        resourcetype = ResourceType.STONE;
                        break;
                    case 3:
                        resourcetype = ResourceType.SERVANT;
                        break;
                    case 4:
                        resourcetype = ResourceType.SHIELD;
                        break;
                    default:
                        System.out.println("invalid resource\n");
                        break;
                }
            }while(resource < 0 || resource > 4);
            System.out.println("Choose the deposit in which you want to place the resource");
            position = input.nextInt();

            PacketChooseInitialResources packet = new PacketChooseInitialResources(position - 1, resourcetype);
            sendPacket(packet);
        }
        else if(n == 4 && ((first == 0 && pos==3) || ( first == 1 && pos==0) || (first ==2 && pos== 1) || (first== 3 && pos==2))){
            i=2;
            System.out.println("Choose the first initial resource");
            System.out.println("Write 1 to select COIN, 2 to select STONE, 3 to select SERVANT, 4 to select SHIELD");
            do{
                resource = input.nextInt();
                switch (resource) {
                    case 0:
                        break;
                    case 1:
                        resourcetype = ResourceType.COIN;
                        break;
                    case 2:
                        resourcetype = ResourceType.STONE;
                        break;
                    case 3:
                        resourcetype = ResourceType.SERVANT;
                        break;
                    case 4:
                        resourcetype = ResourceType.SHIELD;
                        break;
                    default:
                        System.out.println("invalid resource\n");
                        break;
                }
            }while(resource < 0 || resource > 4);
            System.out.println("Choose the deposit in which you want to place the resource");
            position = input.nextInt();
            PacketChooseInitialResources packet1 = new PacketChooseInitialResources(position - 1, resourcetype);
            sendPacket(packet1);
            System.out.println("Choose the second initial resource");
            do{
                resource = input.nextInt();
                switch (resource) {
                    case 0:
                        break;
                    case 1:
                        resourcetype = ResourceType.COIN;
                        break;
                    case 2:
                        resourcetype = ResourceType.STONE;
                        break;
                    case 3:
                        resourcetype = ResourceType.SERVANT;
                        break;
                    case 4:
                        resourcetype = ResourceType.SHIELD;
                        break;
                    default:
                        System.out.println("invalid resource\n");
                        break;
                }
            }while(resource < 0 || resource > 4);
            System.out.println("Choose the deposit in which you want to place the resource");
            position = input.nextInt();
            PacketChooseInitialResources packet2 = new PacketChooseInitialResources(position - 1, resourcetype);
            sendPacket(packet2);
        }
        else{
            System.out.println("You're the first player, you can't have any resources or faith points" +
                    "");
        }
        return i;
    }

    public void endTurn() throws JsonProcessingException {
        PacketEndTurn packet = new PacketEndTurn();
        sendPacket(packet);
    }
}

