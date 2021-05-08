package it.polimi.ingsw.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.controller.client_packets.*;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Warehouse;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientOperationHandler {
    private SocketClientConnection socketClientConnection;
    private ObjectMapper mapper;
    private final PrintStream output;
    private final Scanner input;

    public ClientOperationHandler(SocketClientConnection socketClientConnection) {
        this.socketClientConnection = socketClientConnection;
        input = new Scanner(System.in);
        output = new PrintStream(System.out);
    }

    public void HandleOperation(String input) throws IOException {
        switch(input) {
            case "1":
                System.out.println("Activate Leader Card");
                activateLeaderCard();
                break;
            case "2":
                System.out.println("Buy Development Card");
                //TODO : scrivere l'ultimo metodo per le dev card
                break;
            case "3":
                System.out.println("Choose Discount");
                chooseDiscount();
                break;
            case "4":
                System.out.println("Choose Leader Card to remove");
                chooseLeaderCardToRemove();
                break;
            case "5":
                System.out.println("Discard Leader Card");
                discardLeaderCard();
                break;
            case "6":
                System.out.println("Move Resource");
                moveResource();
                break;
            case "7":
                System.out.println("Place Resource");
                placeResource();
                break;
            case "8":
                System.out.println("Take resource from the market");
                takeResourceFromMarket();
                break;
            case "9":
                System.out.println("Use and choose production powers");
                useAndChooseProductionPower();
                break;
            default:
                System.out.println("invalid choice");
        }
    }

    public void activateLeaderCard() throws IOException {
        System.out.println("Choose the ID of the leader card to activate");

        String ID = input.nextLine();
        mapper = new ObjectMapper();
        PacketActivateLeaderCard packet = new PacketActivateLeaderCard(Integer.parseInt(ID));
        String jsonResult = mapper.writeValueAsString(packet);
        socketClientConnection.sendToServer(jsonResult);
    }

    public void buyDevCard(){
        System.out.println("You have chosen to buy a development card");
    }

    public void chooseDiscount() throws IOException {
        System.out.println("Choose the ID of the leader card to activate, when you have finished write 0");

        String id = "";
        ArrayList<Integer> leaderCards = new ArrayList<>();
        while(!id.equals("0")){
            id = input.nextLine();
            if(id.equals("1") || id.equals("2")){
                leaderCards.add(Integer.parseInt(id));
                //qui metto l'id della leader card del modello con id 1 nell'arraylist di int per il pacchetto
            }
        }
        mapper = new ObjectMapper();
        PacketChooseDiscount packet = new PacketChooseDiscount(leaderCards);
        String jsonResult = mapper.writeValueAsString(packet);
        socketClientConnection.sendToServer(jsonResult);
    }

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

    public void discardLeaderCard() throws IOException {
        System.out.println("Write the ID of the leader card to discard");
        String id;

        id = input.nextLine();

        PacketDiscardLeaderCard packet = new PacketDiscardLeaderCard(Integer.parseInt(id));
        mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(packet);
        socketClientConnection.sendToServer(jsonResult);
    }

    public void moveResource() throws IOException {
        System.out.println("Choose the deposit in which you want to take the resource");

        String position;

        position = input.nextLine();
        PacketMoveResource packet = new PacketMoveResource(Integer.parseInt(position) - 1);
        mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(packet);
        socketClientConnection.sendToServer(jsonResult);
    }

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

    public void takeResourceFromMarket() throws IOException {
        System.out.println("Select line or column and which of the lines you choose");

        String line;
        String numLine;
        String id = "";

        line = input.nextLine();
        numLine = input.nextLine();

        ArrayList<Integer> leaderCards = new ArrayList<>();
        while(!id.equals("0")){
            id = input.nextLine();
            if(id.equals("1") || id.equals("2")){
                leaderCards.add(Integer.parseInt(id));
                //qui metto l'id della leader card del modello con id 1 nell'arraylist di int per il pacchetto
            }
        }

        System.out.println("If you have already activated one or more leader cards that transforms the white marble in some resource," +
                "\n select the id of the leader card to use\n" +
                "write 0 when you have finished");


        PacketTakeResourceFromMarket packet = new PacketTakeResourceFromMarket(line, Integer.parseInt(numLine),leaderCards);
        mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(packet);
        socketClientConnection.sendToServer(jsonResult);
    }


    public void useAndChooseProductionPower(){
        System.out.println("Select the IDs of the development cards to use for the production. \n" +
                "write 0 when you have finished");

        String id = "";

        ArrayList<Integer> productionPowers = new ArrayList<>();
        while(!id.equals("0")){
            id = input.nextLine();
            productionPowers.add(Integer.parseInt(id));
            //qui metto l'id della leader card del modello con id 1 nell'arraylist di int per il pacchetto
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
                        //qui inserisco nell'array il deposito 1 della board del giocatore
                        break;
                    case "2":
                        //qui inserisco nell'array il deposito 2 della board del giocatore
                        break;
                    case "3":
                        //qui inserisco nell'array il deposito 3 della board del giocatore
                        break;
                    case "4":
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
        //TODO: modificare il pacchetto usando le id delle dev card invece dei production power
        /*PacketUseAndChooseProdPower packet = new PacketUseAndChooseProdPower(productionPowers, resources, warehouse, newResources);
        mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(packet);
        socketClientConnection.sendToServer(jsonResult);*/

    }


}
