package it.polimi.ingsw.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.controller.client_packets.*;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Warehouse;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;

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

    public void HandleOperation(String input) throws IOException {
        switch(input) {
            case "1":
                System.out.println("You have chosen to activate a Leader Card\n");
                activateLeaderCard();
                break;
            case "2":
                System.out.println("You have chosen to buy a Development Card\n");
                buyDevCard();
                break;
            case "3":
                System.out.println("You have chosen to choose a Discount\n");
                chooseDiscount();
                break;
            case "4":
                System.out.println("You have chosen to remove two Leader Cards\n");
                chooseLeaderCardToRemove();
                break;
            case "5":
                System.out.println("You have chosen to discard one of your Leader Card\n");
                discardLeaderCard();
                break;
            case "6":
                System.out.println("You have chosen to move one of your resources\n");
                moveResource();
                break;
            case "7":
                System.out.println("You have chosen to place one of your resource\n");
                placeResource();
                break;
            case "8":
                System.out.println("You have chosen to take some resources from the market\n");
                takeResourceFromMarket();
                break;
            case "9":
                System.out.println("You have chosen to use your production powers\n");
                useAndChooseProductionPower();
                break;
            default:
                System.out.println("invalid choice, retry\n");
        }
    }

    //finito
    public void activateLeaderCard() throws IOException {
        System.out.println("Choose the ID of the leader card to activate");

        String ID = input.nextLine();
        PacketActivateLeaderCard packet = new PacketActivateLeaderCard(Integer.parseInt(ID));
        mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(packet);
        socketClientConnection.sendToServer(jsonResult);
    }

    public void buyDevCard() throws IOException {
        System.out.println("Select the card ID you want to buy");

        String id = input.nextLine();

        System.out.println("Choose the resource and the place in which you want to take it\n" +
                "write 0 when you have finished");

        String resource = "";
        String position;

        ArrayList<ResourceType> resources = new ArrayList<>();
        ArrayList<Warehouse> warehouse = new ArrayList<>();

        while(!resource.equals("0")){
            resource = input.nextLine();
            switch (resource) {
                case "1":
                    resources.add(ResourceType.COIN);
                    break;
                case "2":
                    resources.add(ResourceType.STONE);
                    break;
                case "3":
                    resources.add(ResourceType.SERVANT);
                    break;
                case "4":
                    resources.add(ResourceType.SHIELD);
                    break;
            }
            if(!resource.equals("0")){
                position = input.nextLine();
                switch (position) {
                    case "1":
                        warehouse.add(clientModelView.getMyPlayer().getBoard().getDeposits().get(0));
                        //qui inserisco nell'array il deposito 1 della board del giocatore
                        break;
                    case "2":
                        warehouse.add(clientModelView.getMyPlayer().getBoard().getDeposits().get(1));
                        //qui inserisco nell'array il deposito 2 della board del giocatore
                        break;
                    case "3":
                        warehouse.add(clientModelView.getMyPlayer().getBoard().getDeposits().get(2));
                        //qui inserisco nell'array il deposito 3 della board del giocatore
                        break;
                    case "4":
                        warehouse.add(clientModelView.getMyPlayer().getBoard().getStrongbox());
                        //qui inserisco nell'array il forziere della board del giocatore
                        break;
                }
            }
        }

        System.out.println("Choose in which development space you want to put your new card");

        String devSpace = input.nextLine();
        String jsonResult;
        PacketBuyDevCard packet;

        switch (devSpace) {
            case "1":
                packet = new PacketBuyDevCard(Integer.parseInt(id), resources,warehouse, clientModelView.getMyPlayer().getBoard().getDevelopmentSpaces().get(0) );
                mapper = new ObjectMapper();
                jsonResult = mapper.writeValueAsString(packet);
                socketClientConnection.sendToServer(jsonResult);
                //in questo caso inserisco nel pacchetto il devSpace in prima posizione
                break;
            case "2":
                packet = new PacketBuyDevCard(Integer.parseInt(id), resources,warehouse, clientModelView.getMyPlayer().getBoard().getDevelopmentSpaces().get(1) );
                mapper = new ObjectMapper();
                jsonResult = mapper.writeValueAsString(packet);
                socketClientConnection.sendToServer(jsonResult);
                //in questo caso inserisco nel pacchetto il devSpace in prima posizione
                break;
            case "3":
                packet = new PacketBuyDevCard(Integer.parseInt(id), resources,warehouse, clientModelView.getMyPlayer().getBoard().getDevelopmentSpaces().get(2) );
                mapper = new ObjectMapper();
                jsonResult = mapper.writeValueAsString(packet);
                socketClientConnection.sendToServer(jsonResult);
                //in questo caso inserisco nel pacchetto il devSpace in prima posizione
                break;
        }
    }

    //finito
    public void chooseDiscount() throws IOException {
        System.out.println("Choose the ID of the leader card to activate, when you have finished write 0");

        String id = "";
        ArrayList<Integer> leaderCards = new ArrayList<>();
        while(!id.equals("0")){
            id = input.nextLine();
            if(id.equals("1") || id.equals("2")){
                leaderCards.add(Integer.parseInt(id));
            }
        }
        mapper = new ObjectMapper();
        PacketChooseDiscount packet = new PacketChooseDiscount(leaderCards);
        String jsonResult = mapper.writeValueAsString(packet);
        socketClientConnection.sendToServer(jsonResult);
    }

    //finito
    public void chooseLeaderCardToRemove() throws IOException {
        System.out.println("Choose the 2 IDs of the leader cards to remove");
        String id1;
        String id2;

        id1 = input.nextLine();
        id2 = input.nextLine();

        PacketChooseLeaderCardToRemove packet = new PacketChooseLeaderCardToRemove(Integer.parseInt(id1),Integer.parseInt(id2));
        mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(packet);
        socketClientConnection.sendToServer(jsonResult);
    }

    //finito
    public void discardLeaderCard() throws IOException {
        System.out.println("Write the ID of the leader card to discard");
        String id;

        id = input.nextLine();

        PacketDiscardLeaderCard packet = new PacketDiscardLeaderCard(Integer.parseInt(id));
        mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(packet);
        socketClientConnection.sendToServer(jsonResult);
    }

    //finito
    public void moveResource() throws IOException {
        System.out.println("Choose the deposit in which you want to take the resource");

        String position;

        position = input.nextLine();
        PacketMoveResource packet = new PacketMoveResource(Integer.parseInt(position) - 1);
        mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(packet);
        socketClientConnection.sendToServer(jsonResult);
    }

    //finito
    public void placeResource() throws IOException {
        System.out.println("Choose the resource and the deposit in which you want to place the resource");

        String resource;
        String position;

        resource = input.nextLine();
        position = input.nextLine();

        PacketPlaceResource packet = new PacketPlaceResource(Integer.parseInt(resource) - 1, Integer.parseInt(position) - 1);
        mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(packet);
        socketClientConnection.sendToServer(jsonResult);
    }

    //finito
    public void takeResourceFromMarket() throws IOException {
        System.out.println("Select Row or Column and which of the lines you choose");

        String line;
        String numLine;
        String id = "";

        line = input.next();
        numLine = input.next();

        System.out.println("If you have already activated one or more leader cards that transforms the white marble in some resource,\n" +
                "select the id of the leader card to use\n" +
                "write 0 when you have finished");

        ArrayList<Integer> leaderCards = new ArrayList<>();
        while(!id.equals("0")){
            id = input.nextLine();
            if(id.equals("1") || id.equals("2")){
                leaderCards.add(Integer.parseInt(id));
            }
        }

        PacketTakeResourceFromMarket packet = new PacketTakeResourceFromMarket(line, Integer.parseInt(numLine),leaderCards);
        mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(packet);
        socketClientConnection.sendToServer(jsonResult);
    }


    public void useAndChooseProductionPower() throws IOException {
        System.out.println("Select the IDs of the development cards to use for the production. \n" +
                "write 0 when you have finished");

        String id = "";

        ArrayList<ProductionPower> productionPowers = new ArrayList<>();
        while(!id.equals("0")){
            id = input.nextLine();
            //qui metto l'id della development card del modello, e, se esiste (quindi fare il controllo) inserisco
            //il relativo production power all'interno dell'array che poi invierò come pacchetto.
            }

        System.out.println("Choose the resource and the place in which you want to take it\n" +
                "write 0 when you have finished");

        String resource = "";
        String position;

        ArrayList<ResourceType> resources = new ArrayList<>();
        ArrayList<Warehouse> warehouse = new ArrayList<>();

        while(!resource.equals("0")){
            resource = input.nextLine();
            switch (resource) {
                case "1":
                    resources.add(ResourceType.COIN);
                    break;
                case "2":
                    resources.add(ResourceType.STONE);
                    break;
                case "3":
                    resources.add(ResourceType.SERVANT);
                    break;
                case "4":
                    resources.add(ResourceType.SHIELD);
                    break;
            }
            if(!resource.equals("0")){
                position = input.nextLine();
                switch (position) {
                    case "1":
                        warehouse.add(clientModelView.getMyPlayer().getBoard().getDeposits().get(0));
                        //qui inserisco nell'array il deposito 1 della board del giocatore
                        break;
                    case "2":
                        warehouse.add(clientModelView.getMyPlayer().getBoard().getDeposits().get(1));
                        //qui inserisco nell'array il deposito 2 della board del giocatore
                        break;
                    case "3":
                        warehouse.add(clientModelView.getMyPlayer().getBoard().getDeposits().get(2));
                        //qui inserisco nell'array il deposito 3 della board del giocatore
                        break;
                    case "4":
                        warehouse.add(clientModelView.getMyPlayer().getBoard().getStrongbox());
                        //qui inserisco nell'array il forziere della board del giocatore
                        break;
                }
            }
        }

        System.out.println("If the production powers you selected have some jolly resource, choose the resource instead of the jolly\n" +
                "if there's no jolly resource obtainable or in case you have finished write 0");

        ArrayList<ResourceType> newResources = new ArrayList<>();

        String newResource = "";

        while (!newResource.equals("0")){
            newResource = input.nextLine();
            switch (newResource) {
                case "1":
                    newResources.add(ResourceType.COIN);
                    break;
                case "2":
                    newResources.add(ResourceType.STONE);
                    break;
                case "3":
                    newResources.add(ResourceType.SERVANT);
                    break;
                case "4":
                    newResources.add(ResourceType.SHIELD);
                    break;
            }
        }
        PacketUseAndChooseProdPower packet = new PacketUseAndChooseProdPower(productionPowers, resources, warehouse, newResources);
        mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(packet);
        socketClientConnection.sendToServer(jsonResult);

    }


}
