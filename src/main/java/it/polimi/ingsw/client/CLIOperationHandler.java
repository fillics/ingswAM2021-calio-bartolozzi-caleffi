package it.polimi.ingsw.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.client_packets.*;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentCard;
import it.polimi.ingsw.model.cards.leadercards.ConcreteStrategyMarble;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class CLIOperationHandler implements ClientOperationHandler{
    private final SocketClientConnection socketClientConnection;
    private final Scanner input;
    private ClientModelView clientModelView;
    private ViewInterface viewInterface;

    // TODO: 24/05/2021 sistemare che non si può fare end turn se non prima il completamento della fase di setup di tutti i giocatori
    public CLIOperationHandler(SocketClientConnection socketClientConnection, ClientModelView clientModelView, ViewInterface viewInterface) {
        this.socketClientConnection = socketClientConnection;
        this.clientModelView = clientModelView;
        this.viewInterface = viewInterface;
        input = new Scanner(System.in);
    }

    @Override
    public void setViewInterface(ViewInterface viewInterface) {
        this.viewInterface = viewInterface;
    }

    @Override
    public void setClientModelView(ClientModelView clientModelView) {
        this.clientModelView = clientModelView;
    }

    public void handleOperation(String input) throws IOException {
        switch (input) {
            case "1", "activate" -> {
                System.out.println("You have chosen to activate a Leader Card");
                activateLeaderCard();
            }
            case "2", "buy" -> {
                System.out.println("You have chosen to buy a Development Card");
                buyDevCard();
            }
            case "3", "choose" -> {
                System.out.println("You have chosen to choose a Discount");
                chooseDiscount();
            }
            case "4", "prodpowers" -> {
                System.out.println("You have chosen to use your production powers");
                useAndChooseProductionPower();
            }
            case "5", "discard" -> {
                System.out.println("You have chosen to discard one of your Leader Card");
                discardLeaderCard();
            }
            case "6", "move" -> {
                System.out.println("You have chosen to move one of your resource");
                moveResource();
            }
            case "7", "place" -> {
                System.out.println("You have chosen to place one of your resource");
                placeResource();
            }
            case "8", "take" -> {
                System.out.println("You have chosen to take some resources from the market");
                takeResourceFromMarket();
            }
            case "9", "showmarket" -> {
                System.out.println("You have chosen to see the market tray");
                viewInterface.printMarketTray();
            }
            case "10", "showgrid" -> {
                System.out.println("You have chosen to see the development grid");
                viewInterface.printResourcesLegend();
                viewInterface.printDevGrid();
            }
            case "11", "showboard" -> {
                System.out.println("You have chosen to see your personal board");
                viewInterface.printFaithTrack();
                viewInterface.printResourcesLegend();
                viewInterface.printDeposits();
                viewInterface.printStrongbox();
                viewInterface.printDevSpaces();
                viewInterface.printLeaderCards();
            }
            case "12", "showanotherboard" ->
                askBoardOfOtherPlayer();
            case "13", "end" ->{
                System.out.println("Ending turn");
                endTurn();
            }
            default -> System.err.println("Invalid choice, retry. "+Constants.commands);
        }
    }

    public void sendPacket(ClientPacketHandler packet) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(packet);
        socketClientConnection.sendToServer(jsonResult);
    }

    public void askBoardOfOtherPlayer() throws JsonProcessingException {
        String username;
        if(clientModelView.getNumOfPlayers()==1) {
            System.out.println("You are in single player mode, there are no other players, please choose another action");
            System.out.println(Constants.commands);
        }
        else{
            System.out.println("You have chosen to see the board of the another player");
            System.out.println("Choose the username of the player whose board you want to see");
            username = input.nextLine();

            if(username.equals("exit"))
                return;
            else{
                PacketUsernameOfAnotherPlayer packet= new PacketUsernameOfAnotherPlayer(username);
                sendPacket(packet);
            }
        }
    }

    public void activateLeaderCard() throws IOException {
        viewInterface.printLeaderCards();
        System.out.println("Choose the ID of the leader card to activate: ");

        String id;
        boolean LeaderCardcheck = false;
        do {
            id = input.nextLine();
            if(id.equals("exit")) return;
            for(LeaderCard leaderCard : clientModelView.getMyPlayer().getLeaderCards()){
                if (Integer.parseInt(id) == leaderCard.getId()) {
                    LeaderCardcheck = true;
                    break;
                }
            }
            if(!LeaderCardcheck) System.err.println("You don't have this card, retry");
        } while (!LeaderCardcheck );
        PacketActivateLeaderCard packet = new PacketActivateLeaderCard(Integer.parseInt(id));
        sendPacket(packet);
    }

    public void buyDevCard() throws IOException {
        viewInterface.printResourcesLegend();
        viewInterface.printDevGrid();

        System.out.println("Select the card ID you want to buy");
        Scanner input1 = new Scanner(System.in);
        String id;
        boolean devCardcheck = false;
        do {
            id = input.nextLine();
            if(id.equals("exit")) return;
            for(DevelopmentCard developmentCard : clientModelView.getDevelopmentGrid().getDevelopmentCards()){
                if(developmentCard!=null){
                    if(Integer.parseInt(id) == developmentCard.getId()){
                        devCardcheck = true;
                        break;
                    }
                }
            }
            if(!devCardcheck) System.err.println("Development card id not found");
        } while (!devCardcheck);

        System.out.println("Choose the resource and the place in which you want to take it\n" +
                "write 0 when you have finished");

        String resource;
        String position;

        viewInterface.printDeposits();
        viewInterface.printStrongbox();

        ArrayList<ResourceType> resources = new ArrayList<>();
        ArrayList<Integer> warehouse = new ArrayList<>();
        Constants.printConnectionMessage(ConnectionMessages.RESOURCE_CHOICES);
        do {
            do{
                System.out.println("Choose the resources: ");
                resource = input.nextLine();
                switch (resource) {
                    case "1", "coin" -> resources.add(ResourceType.COIN);
                    case "2", "stone" -> resources.add(ResourceType.STONE);
                    case "3", "servant" -> resources.add(ResourceType.SERVANT);
                    case "4", "shield" -> resources.add(ResourceType.SHIELD);
                    case "exit" -> {
                        return;
                    }
                    case "0" -> {}
                    default -> System.err.println("invalid resource\n");
                }
            }while(Integer.parseInt(resource) < 0 || Integer.parseInt(resource) > 4 ||
                    resource.equals("coin") || resource.equals("stone") || resource.equals("servant") || resource.equals("shield"));

            if (Integer.parseInt(resource) != 0) {
                do{
                    System.out.println("Choose the place in which you want to take it (write 0 when you have finished)");
                    position = input.nextLine();
                    switch (position) {
                        case "1", "2", "3", "4" -> warehouse.add(Integer.parseInt(position));
                        case "exit" -> {
                            return;
                        }
                        default -> System.err.println("invalid position\n");
                    }
                }while(Integer.parseInt(position) < 1 || Integer.parseInt(position) > 4);

            }
        }while (Integer.parseInt(resource) != 0);

        System.out.println("Choose in which development space you want to put your new card");

        PacketBuyDevCard packet;

        boolean devSpacecheck = false;
        do {
            String devSpace = input1.nextLine();
            switch (devSpace) {
                case "1", "2", "3" -> {
                    packet = new PacketBuyDevCard(Integer.parseInt(id), resources, warehouse, Integer.parseInt(devSpace));
                    try {
                        sendPacket(packet);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    devSpacecheck = true;
                }
                case "0" -> {}
                case "exit" -> {
                    return;
                }
                default -> System.err.println("invalid development space\n");
            }
        } while (!devSpacecheck);
    }

    public void chooseDiscount() throws IOException {
        System.out.println("Choose the IDs of the leader cards to activate, press 0 to finish");
        viewInterface.printLeaderCards();
        boolean check = false;
        String id;
        ArrayList<Integer> leaderCards = new ArrayList<>();
        do {
            id = input.nextLine();
            if(id.equals("exit")) return;
            for(LeaderCard leaderCard : clientModelView.getMyPlayer().getLeaderCards()){
                if (Integer.parseInt(id) == leaderCard.getId()) {
                    leaderCards.add(Integer.parseInt(id));
                    check = true;
                }
            }
            if(!check && Integer.parseInt(id) != 0) System.err.println("You don't have this leader card");
        }while (Integer.parseInt(id) != 0);
        PacketChooseDiscount packet = new PacketChooseDiscount(leaderCards);
        sendPacket(packet);
    }

    public void chooseLeaderCardToRemove() {
        String id1 = null;
        String id2 = null;
        boolean checkId1 = false;
        boolean checkId2 = false;

        viewInterface.printLeaderCards();

        do {
            try{
                System.out.println("First card to remove: ");
                id1 = input.nextLine();
                if(id1.equals("exit")) return;
            }catch(InputMismatchException|NumberFormatException e){
                System.err.println("Please, insert a number!");

            }

            for(LeaderCard leaderCard : clientModelView.getMyPlayer().getLeaderCards()){
                if (Integer.parseInt(id1) == leaderCard.getId()) {
                    checkId1 = true;
                    break;
                }
            }
            if(!checkId1) {
                System.err.println("Chosen id not present. Please reinsert the id of the first card to remove:");
            }
        } while (!checkId1);


        do {
            try{
                System.out.println("Second card to remove: ");
                id2 = input.nextLine();
                if(id2.equals("exit")) return;
            }catch(InputMismatchException|NumberFormatException e){
                System.err.println("insert a number!");

            }
            for(LeaderCard leaderCard : clientModelView.getMyPlayer().getLeaderCards()){
                if (Integer.parseInt(id2) == leaderCard.getId() && Integer.parseInt(id2) != Integer.parseInt(id1)) {
                    checkId2 = true;
                    break;
                }
            }
            if(!checkId2) {
                if(Integer.parseInt(id2) == Integer.parseInt(id1)) System.err.println("card already discarded");
                else System.err.println("Chosen id not present. Please reinsert the id of the second card to remove:");
            }
        } while (!checkId2);

        PacketChooseLeaderCardToRemove packet = new PacketChooseLeaderCardToRemove(Integer.parseInt(id1), Integer.parseInt(id2));

        try {
            sendPacket(packet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int scannerChooseDeposit(BufferedReader bf) {
        return 0;
    }


    public void discardLeaderCard(){
        viewInterface.printLeaderCards();
        System.out.println("Write the ID of the leader card to discard");
        boolean check = false;
        String id;

        do{
            id = input.nextLine();
            if(id.equals("exit")) return;
            for(LeaderCard leaderCard : clientModelView.getMyPlayer().getLeaderCards()){
                if (Integer.parseInt(id) == leaderCard.getId()) {
                    check = true;
                    break;
                }
            }
            if(!check) System.err.println("You don't have this Leader Card");
        }while(!check);

        PacketDiscardLeaderCard packet = new PacketDiscardLeaderCard(Integer.parseInt(id));
        try {
            sendPacket(packet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    public void moveResource(){
        System.out.println("Choose the deposit in which you want to take the resource");
        viewInterface.printDeposits();
        String position = null;

        do{
            try{
                position = input.nextLine();
                if(position.equals("exit")) return;
            }catch(InputMismatchException e){
                System.err.println("Don't write strings");
            }
            if(Integer.parseInt(position) < 1 || Integer.parseInt(position) > clientModelView.getLiteBoard().getDeposits().size()) System.err.println("Invalid position, retry");
        }while(Integer.parseInt(position) < 1 || Integer.parseInt(position) > clientModelView.getLiteBoard().getDeposits().size());

        PacketMoveResource packet = new PacketMoveResource(Integer.parseInt(position) - 1);
        try {
            sendPacket(packet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    public void placeResource() {
        if(clientModelView.getMyPlayer().getResourceBuffer().size() == 0){
            System.err.println("I'm sorry, you don't have any resource to place.");
            System.out.println(Constants.commands);
        }
        else{
            viewInterface.printResourceBuffer();
            viewInterface.printDeposits();
            String position;
            String resource;

            do{
                System.out.println("Choose the resource you want to place:");
                resource = input.nextLine();
                if(resource.equals("exit")) return;
                if(Integer.parseInt(resource) > clientModelView.getMyPlayer().getResourceBuffer().size()) System.err.println("Invalid resource position, retry");
            }while(Integer.parseInt(resource) > clientModelView.getMyPlayer().getResourceBuffer().size());

            do{
                System.out.println("Choose the deposit in which you want to place the resource "+clientModelView.getMyPlayer().getResourceBuffer().get(Integer.parseInt(resource)-1).getType());
                position = input.nextLine();
                if(position.equals("exit")) return;
                if(Integer.parseInt(position) < 1 || Integer.parseInt(position) > clientModelView.getLiteBoard().getDeposits().size()) System.err.println("Invalid position, retry");
            }while(Integer.parseInt(position) < 1 || Integer.parseInt(position) > clientModelView.getLiteBoard().getDeposits().size());

            PacketPlaceResource packet = new PacketPlaceResource(Integer.parseInt(position) - 1, Integer.parseInt(resource) - 1);
            try {
                sendPacket(packet);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }


    }

    public void takeResourceFromMarket(){
        viewInterface.printMarketTray();

        System.out.println("Do you want a:\n-ROW\n-COLUMN");

        Scanner input1 = new Scanner(System.in);
        boolean check = false;
        String line;
        String numLine = null;
        do {
            line = input1.nextLine().toLowerCase(Locale.ROOT);
            if(line.equals("exit")) return;
            if(!line.equals("row") && !line.equals("column")) System.err.println("invalid choice");
        } while (!line.equals("row") && !line.equals("column"));

        System.out.println("Which "+line.toUpperCase(Locale.ROOT)+" do you choose?");
        if (line.equals("row")) {
            do {
                numLine = input.nextLine();
                if(numLine.equals("exit")) return;
                if(Integer.parseInt(numLine) < 1 || Integer.parseInt(numLine)  > 3) System.err.println("invalid row");
            } while (Integer.parseInt(numLine)  < 1 || Integer.parseInt(numLine)  > 3);
        }

        if (line.equals("column")) {
            do {
                numLine = input.nextLine();
                if(numLine.equals("exit")) return;
                if(Integer.parseInt(numLine)  < 1 || Integer.parseInt(numLine)  > 4) System.err.println("invalid column");
            } while (Integer.parseInt(numLine)  < 1 || Integer.parseInt(numLine)  > 4);
        }

        for(LeaderCard leaderCard : clientModelView.getMyPlayer().getLeaderCards()){
            if(leaderCard.getStrategy().isActive() && leaderCard.getStrategy() instanceof ConcreteStrategyMarble) check = true;
        }

        ArrayList<Integer> leaderCards = new ArrayList<>();

        if(check){
            System.out.println("You have already activated one or more leader cards that transforms the white marble in some resource,\n" +
                    "select the id of the leader card to use if you want, otherwise press 0 to finish");

            String id;
            do {
                id = input.nextLine();
                if(id.equals("exit")) return;
                if (Integer.parseInt(id) == clientModelView.getMyPlayer().getLeaderCards().get(0).getId() ||
                        Integer.parseInt(id) == clientModelView.getMyPlayer().getLeaderCards().get(1).getId()) {
                    leaderCards.add(Integer.parseInt(id));
                }
                else if( Integer.parseInt(id) == 0){}
                else{
                    System.err.println("invalid id");
                }
            } while (Integer.parseInt(id) != 0);
        }


        PacketTakeResourceFromMarket packet = new PacketTakeResourceFromMarket(line, Integer.parseInt(numLine), leaderCards);
        try {
            sendPacket(packet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    public void useAndChooseProductionPower(){

        System.out.println("Select the IDs of the development space to use for the production. \n" +
                "Press 0 when you have finished");
        viewInterface.printDevSpaces();
        boolean checkProd = false;
        String id;

        ArrayList<Integer> productionPowers = new ArrayList<>();
        ArrayList<Integer> newProductionPowers = new ArrayList<>();
        do {
            id = input.nextLine();
            if(id.equals("exit")) return;
            switch (id) {
                case "1":
                    if (clientModelView.getLiteBoard().getDevelopmentSpaces().get(0).getTopCard() != null) {
                        if(!productionPowers.contains(Integer.parseInt(id))){
                            productionPowers.add(Integer.parseInt(id));
                        }
                    }
                    break;
                case "2":
                    if (clientModelView.getLiteBoard().getDevelopmentSpaces().get(1).getTopCard() != null) {
                        if(!productionPowers.contains(Integer.parseInt(id))){
                            productionPowers.add(Integer.parseInt(id));
                        }
                    }
                    break;
                case "3":
                    if (clientModelView.getLiteBoard().getDevelopmentSpaces().get(2).getTopCard() != null) {
                        if(!productionPowers.contains(Integer.parseInt(id))){
                            productionPowers.add(Integer.parseInt(id));
                        }
                    }
                    break;
                case "0":
                    break;
                default:
                    System.err.println("Invalid development space");
            }
        }while (Integer.parseInt(id) != 0);

        if(clientModelView.getLiteBoard().getSpecialProductionPower().size() == 1) {
            System.out.println("Press 1 to use the special production power of the board. \n"+ "Press 0 to continue the production");
            viewInterface.printBaseProdPower();
        }
        if(clientModelView.getLiteBoard().getSpecialProductionPower().size() > 1) {
            System.out.println("You have other production powers thanks to the leader cards you have selected, also you can use the board production power.\n" +
                    "Select one or more of them, press 0 to continue the production");
            viewInterface.printBaseProdPower();
        }
            String prodPosition;

            do{
               prodPosition = input.nextLine();
               if(prodPosition.equals("exit")) return;
               if(Integer.parseInt(prodPosition) > 0 && Integer.parseInt(prodPosition)  <= clientModelView.getLiteBoard().getSpecialProductionPower().size()){
                   if(!newProductionPowers.contains(Integer.parseInt(prodPosition) )){
                       newProductionPowers.add(Integer.parseInt(prodPosition) );
                   }
               }
               else if(Integer.parseInt(prodPosition) == 0){
               }
               else{
                   System.err.println("invalid special production power positions, retry");
               }
            }while(Integer.parseInt(prodPosition) != 0);


        System.out.println("Choose the resource and the place in which you want to take it.\n" +
                        "Press 0 once you have finished");
        Constants.printConnectionMessage(ConnectionMessages.RESOURCE_CHOICES);

        viewInterface.printDeposits();
        viewInterface.printStrongbox();

            String resource;
            String position;

            ArrayList<ResourceType> resources = new ArrayList<>();
            ArrayList<Integer> warehouse = new ArrayList<>();

        do {
            do{
                resource = input.nextLine();
                if(resource.equals("exit")) return;
                switch (resource) {
                    case "1" -> resources.add(ResourceType.COIN);
                    case "2" -> resources.add(ResourceType.STONE);
                    case "3" -> resources.add(ResourceType.SERVANT);
                    case "4" -> resources.add(ResourceType.SHIELD);
                    case "0" -> {}
                    default -> System.err.println("invalid resource\n");
                }
            }while(Integer.parseInt(resource) < 0 || Integer.parseInt(resource)  > 4);

            if (Integer.parseInt(resource)  != 0) {
                do{
                    position = input.nextLine();
                    if(position.equals("exit")) return;
                    switch (position) {
                        case "1", "2", "3", "4" -> warehouse.add(Integer.parseInt(position) );
                        default -> System.err.println("invalid position\n");
                    }
                }while(Integer.parseInt(position)  < 1 || Integer.parseInt(position)  > 4);
            }
        }while (Integer.parseInt(resource)  != 0);



        for(int i : productionPowers){
            if(clientModelView.getLiteBoard().getDevelopmentSpaces().get(i).getTopCard() != null){
                if (clientModelView.getLiteBoard().getDevelopmentSpaces().get(i - 1).getTopCardProductionPower().getResourceObtained().get(ResourceType.JOLLY) > 0) {
                    checkProd = true;
                    break;
                }
            }
        }
        for(int i : newProductionPowers){
            if (clientModelView.getLiteBoard().getSpecialProductionPower().get(i - 1).getResourceObtained().get(ResourceType.JOLLY) > 0) {
                checkProd = true;
                break;
            }
        }
        ArrayList<ResourceType> newResources = new ArrayList<>();
        if(checkProd){
            System.out.println("The production powers you selected have some jolly resource, choose the resources instead of the jolly\n" +
                    "press 0 to finish");
            Constants.printConnectionMessage(ConnectionMessages.RESOURCE_CHOICES);

            String newResource;

            do {
                newResource = input.nextLine();
                if(newResource.equals("exit")) return;
                switch (newResource) {
                    case "1" -> newResources.add(ResourceType.COIN);
                    case "2" -> newResources.add(ResourceType.STONE);
                    case "3" -> newResources.add(ResourceType.SERVANT);
                    case "4"-> newResources.add(ResourceType.SHIELD);
                    case "0" -> {}
                    default -> System.out.println("invalid resource\n");
                }
            } while (Integer.parseInt(newResource) < 0 || Integer.parseInt(newResource) > 4);
        }

        PacketUseAndChooseProdPower packet = new PacketUseAndChooseProdPower(productionPowers, newProductionPowers, resources, warehouse, newResources);
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
        if (howManyResources==2) System.out.println(Constants.ANSI_YELLOW+"You can choose two resources"+Constants.ANSI_RESET);
        for (int i = 0; i < howManyResources; i++) {
            //TODO: Mettere il ciclo do while per fare il controllo che la risorsa e il deposito siano input giusti
            if(i==0) Constants.printConnectionMessage(ConnectionMessages.CHOOSE_FIRST_RESOURCE);
            if(i==1) Constants.printConnectionMessage(ConnectionMessages.CHOOSE_SECOND_RESOURCE);

            resources.add(scannerChooseResources(bufferRead));
            deposits.add(scannerChooseDeposit(bufferRead,i));

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
            } catch (IOException|NumberFormatException e) {
                e.printStackTrace();
                // TODO: 24/05/2021 exception se stringa vuota
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

    public int scannerChooseDeposit(BufferedReader bufferRead, int i){
        int position=0;
        Constants.printConnectionMessage(ConnectionMessages.CHOOSE_DEPOSIT);
        if (i!=1)
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

