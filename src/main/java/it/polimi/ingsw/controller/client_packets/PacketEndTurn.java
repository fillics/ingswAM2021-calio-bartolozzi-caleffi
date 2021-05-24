package it.polimi.ingsw.controller.client_packets;

import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.*;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.model.singleplayer.SinglePlayerGame;
import it.polimi.ingsw.model.singleplayer.SoloActionToken;
import it.polimi.ingsw.model.singleplayer.SoloActionTokenType;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;


public class PacketEndTurn implements ClientPacketHandler{


    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) {
        System.out.println("inizio di tutto");
        if(gameInterface instanceof SinglePlayerGame){
            if (gameInterface.getState().equals(GameStates.PHASE_TWO) && clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()) {
                System.out.println("prima if");
                if(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getResourceBuffer().size() > 0){
                    //incrementiamo la black cross di lorenzo
                    System.out.println("prima op");
                    ((SinglePlayerGame) gameInterface).increaseBlackCross(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getResourceBuffer().size());

                    //puliamo il resource buffer
                    System.out.println("seconda op");
                    gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getResourceBuffer().clear();
                }


                if(gameInterface.getIdClientActivePlayers().get(clientHandler.getIdClient()).getResourceBuffer().size() != 0){
                    clientHandler.sendPacketToClient(new PacketResourceBuffer(gameInterface.getIdClientActivePlayers().get(clientHandler.getIdClient()).getResourceBuffer()));
                }
                if(gameInterface.isEndgame() && clientHandler.getPosInGame() == gameInterface.getActivePlayers().size() - 1){
                    ((SinglePlayerGame) gameInterface).winner();
                    System.out.println(gameInterface.getWinner());
                    clientHandler.sendPacketToClient(new PacketWinner(gameInterface.getWinner()));
                }
                SoloActionToken token = ((SinglePlayerGame) gameInterface).drawSoloActionToken();
                System.out.println("inizio switch case");
                switch (token.getType()) {
                    case DISCARD -> {
                        ((SinglePlayerGame) gameInterface).useSoloActionToken(token);
                        clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.DISCARDDEVCARD));
                        clientHandler.sendPacketToClient(new PacketLiteDevelopmentGrid(gameInterface.getDevGridLite()));
                        clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.YOUR_TURN));
                    }
                    case BLACKCROSS_1 -> {
                        ((SinglePlayerGame) gameInterface).useSoloActionToken(token);
                        clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.BLACKCROSS1));
                        clientHandler.sendPacketToClient(new PacketFaithTrack(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getTrack(), gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getFaithMarker()));
                        clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.YOUR_TURN));

                    }
                    case BLACKCROSS_2 -> {
                        ((SinglePlayerGame) gameInterface).useSoloActionToken(token);
                        clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.BLACKCROSS2));
                        clientHandler.sendPacketToClient(new PacketFaithTrack(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getTrack(), gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getFaithMarker()));
                        clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.YOUR_TURN));

                    }
                }
                System.out.println("fine switch case");
                if(gameInterface.isEndgame() && clientHandler.getPosInGame() == gameInterface.getActivePlayers().size() - 1){
                    ((SinglePlayerGame) gameInterface).winner();
                    clientHandler.sendPacketToClient(new PacketWinner(gameInterface.getWinner()));
                }
                gameInterface.setState(GameStates.PHASE_ONE);
            }
            else{
                clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLEENDTURN));
            }
        }
        else{

            //TODO: Fare condizione invio pacchetto di risorse
            //TODO il player può fare end turn solo se è nella fase due
            if (gameInterface.getState().equals(GameStates.PHASE_TWO) &&
                    clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()) {

                gameInterface.nextPlayer();
                //System.out.println(gameInterface.getIdClientActivePlayers().get(clientHandler.getIdClient()).getResourceBuffer());
                //clientHandler.sendPacketToClient(new PacketResourceBuffer(gameInterface.getIdClientActivePlayers().get(clientHandler.getIdClient()).getResourceBuffer()));


                if(gameInterface.isEndgame() && clientHandler.getPosInGame() == gameInterface.getActivePlayers().size() - 1){
                    clientHandler.sendPacketToClient(new PacketWinner(gameInterface.getWinner()));
                }
                else{

                    server.getMapUsernameClientHandler().get(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getUsername()).
                            sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.YOUR_TURN));
                    /*if (clientHandler.getPosInGame() == gameInterface.getActivePlayers().size() - 1) {
                        server.getMapUsernameClientHandler().get(gameInterface.getActivePlayers().get(0).getUsername()).sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.YOUR_TURN));
                    } else {
                        server.getMapUsernameClientHandler().get(gameInterface.getActivePlayers().get(clientHandler.getPosInGame() + 1).getUsername()).sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.YOUR_TURN));
                    }*/
                    gameInterface.setState(GameStates.PHASE_ONE);
                }
            }

            // TODO: 23/05/2021 togliere riga commentata
            //if((gameInterface.getState().equals(GameStates.PHASE_ONE)) clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLEMOVE));
            else {
                clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLEENDTURN));
            }

            //mando la nuova posizione a tutti solo quando finiscono tutti il turno->fa end turn l'ultima persona
            //server.sendNewPositionInGame(clientHandler);


        }

    }
}
