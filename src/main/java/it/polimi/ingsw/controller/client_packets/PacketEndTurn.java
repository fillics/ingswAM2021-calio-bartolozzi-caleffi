package it.polimi.ingsw.controller.client_packets;

import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.*;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.model.singleplayer.SinglePlayerGame;
import it.polimi.ingsw.model.singleplayer.SinglePlayerGameInterface;
import it.polimi.ingsw.model.singleplayer.SoloActionToken;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;


public class PacketEndTurn implements ClientPacketHandler{


    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) {

        if(gameInterface instanceof SinglePlayerGame) {

            switch (gameInterface.getState()) {
                case SETUP -> gameInterface.setState(GameStates.PHASE_ONE);
                case PHASE_ONE -> clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLEENDTURN));
                case PHASE_TWO -> {
                    if (clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()) {
                        int sizeResourceBuffer = gameInterface.getUsernameClientActivePlayers().get(clientHandler.getUsername()).getResourceBuffer().size();

                        if (sizeResourceBuffer != 0) {
                            gameInterface.getActivePlayers().get(0).getResourceBuffer().clear();
                            ((SinglePlayerGame) gameInterface).increaseBlackCross(sizeResourceBuffer);
                        }

                        SoloActionToken token = ((SinglePlayerGame) gameInterface).drawSoloActionToken();

                        switch (token.getType()) {
                            case DISCARD -> {
                                ((SinglePlayerGame) gameInterface).useSoloActionToken(token);
                                clientHandler.sendPacketToClient(new PacketBlackCross(((SinglePlayerGame) gameInterface).getBlackCross()));
                                clientHandler.sendPacketToClient(new PacketFaithTrack(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getTrack(), gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getFaithMarker(), gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getVaticanReportSections()));
                                clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.DISCARDDEVCARD));
                                clientHandler.sendPacketToClient(new PacketLiteDevelopmentGrid(gameInterface.getDevGridLite()));
                                clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.YOUR_TURN));
                            }
                            case BLACKCROSS_1 -> {
                                ((SinglePlayerGame) gameInterface).useSoloActionToken(token);
                                clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.BLACKCROSS1));
                                clientHandler.sendPacketToClient(new PacketBlackCross(((SinglePlayerGame) gameInterface).getBlackCross()));
                                clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.BLACKCROSSUPDATE));
                                clientHandler.sendPacketToClient(new PacketFaithTrack(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getTrack(), gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getFaithMarker(),gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getVaticanReportSections()));
                                clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.YOUR_TURN));

                            }
                            case BLACKCROSS_2 -> {
                                ((SinglePlayerGame) gameInterface).useSoloActionToken(token);
                                clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.BLACKCROSS2));
                                clientHandler.sendPacketToClient(new PacketBlackCross(((SinglePlayerGame) gameInterface).getBlackCross()));
                                clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.BLACKCROSSUPDATE));
                                clientHandler.sendPacketToClient(new PacketFaithTrack(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getTrack(), gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getFaithMarker(),gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getVaticanReportSections()));
                                clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.YOUR_TURN));
                            }
                        }
                        if (gameInterface.isEndgame() && clientHandler.getPosInGame() == gameInterface.getActivePlayers().size() - 1) {
                            ((SinglePlayerGame) gameInterface).winner();
                            System.out.println(gameInterface.getWinner());
                            clientHandler.sendPacketToClient(new PacketWinner(gameInterface.getWinner()));
                        }
                        gameInterface.setState(GameStates.PHASE_ONE);
                    }
                }
            }

        }
        else{
            gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).endTurn();
            clientHandler.sendPacketToClient(new PacketFaithTrack(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getTrack(), gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getFaithMarker(), gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getVaticanReportSections()));
            server.saveClientProxy(clientHandler.getUsername(), gameInterface);

            switch (gameInterface.getState()) {

                case PHASE_ONE -> clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLEENDTURN));

                case PHASE_TWO -> {
                    if (clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()){
                        gameInterface.nextPlayer();

                        if(gameInterface.isEndgame() && clientHandler.getPosInGame() == gameInterface.getActivePlayers().size() - 1){
                            clientHandler.sendPacketToClient(new PacketWinner(gameInterface.getWinner()));
                        }
                        else{
                            server.getMapUsernameClientHandler().get(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getUsername()).
                                    sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.YOUR_TURN));
                            server.getMapUsernameClientHandler().get(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getUsername()).
                                    sendPacketToClient(new PacketFaithTrack(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getTrack(), gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getFaithMarker(),
                                            gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getVaticanReportSections()));

                            gameInterface.setState(GameStates.PHASE_ONE);
                        }
                    }
                    else {
                        clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLEMOVE));
                    }
                }
            }

        }

    }
}
