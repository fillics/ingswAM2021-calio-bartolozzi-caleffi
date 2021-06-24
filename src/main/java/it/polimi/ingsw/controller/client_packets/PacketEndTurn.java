package it.polimi.ingsw.controller.client_packets;

import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.*;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.PlayerInfoEndMatch;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.gameinterfaces.GameInterface;
import it.polimi.ingsw.model.singleplayer.SinglePlayerGame;
import it.polimi.ingsw.model.singleplayer.SoloActionToken;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

import java.util.ArrayList;


public class PacketEndTurn implements ClientPacketHandler{


    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) {

        if(gameInterface instanceof SinglePlayerGame) {

            switch (gameInterface.getState()) {
                case SETUP -> gameInterface.setState(GameStates.PHASE_ONE);
                case PHASE_TWO -> {
                    if (clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()) {
                        int sizeResourceBuffer = gameInterface.getUsernameClientActivePlayers().get(clientHandler.getUsername()).getResourceBuffer().size();

                        if (sizeResourceBuffer != 0) {
                            gameInterface.getActivePlayers().get(0).getResourceBuffer().clear();
                            ((SinglePlayerGame) gameInterface).increaseBlackCross(sizeResourceBuffer);
                        }
                        clientHandler.sendPacketToClient(new PacketResourceBuffer(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getResourceBuffer()));

                        if (gameInterface.isEndgame() && clientHandler.getPosInGame() == gameInterface.getActivePlayers().size() - 1) {
                            ((SinglePlayerGame) gameInterface).winner();

                            clientHandler.sendPacketToClient(new PacketWinner(gameInterface.getWinner(), null));
                            gameInterface.setState(GameStates.END);
                        }

                        else{
                            SoloActionToken token = ((SinglePlayerGame) gameInterface).drawSoloActionToken();
                            switch (token.getType()) {
                                case DISCARD -> handleDrawingToken(gameInterface, clientHandler, token, ConnectionMessages.DISCARDDEVCARD);
                                case BLACKCROSS_1 ->  handleDrawingToken(gameInterface, clientHandler, token, ConnectionMessages.BLACKCROSS1);
                                case BLACKCROSS_2 -> handleDrawingToken(gameInterface, clientHandler, token, ConnectionMessages.BLACKCROSS2);
                            }
                            gameInterface.setState(GameStates.PHASE_ONE);
                        }
                    }
                }
                default -> clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLEENDTURN));
            }

        }
        else{
            gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).endTurn();
            Board board =  gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard();
            clientHandler.sendPacketToClient(new PacketFaithTrack(board.getTrack(), board.getFaithMarker(), board.getVaticanReportSections()));

            server.saveClientProxy(clientHandler.getUsername(), gameInterface);

            if (gameInterface.getState() == GameStates.PHASE_TWO) {
                if (clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()) {
                    server.getMapUsernameClientHandler().get(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getUsername()).
                            sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.WAIT_FOR_TURN));
                    gameInterface.nextPlayer();

                    clientHandler.sendPacketToClient(new PacketResourceBuffer(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getResourceBuffer()));
                    clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.UPDATE_AFTER_ENDTURN));

                    if (gameInterface.isEndgame() && clientHandler.getPosInGame() == gameInterface.getActivePlayers().size() - 1) {

                        ArrayList<PlayerInfoEndMatch> playerInfoEndMatches = new ArrayList<>();
                        for (Player player: gameInterface.getPlayers()){
                            Board boardPlayer = player.getBoard();
                            playerInfoEndMatches.add(new PlayerInfoEndMatch(player.getUsername(),
                                    boardPlayer.getFaithMarker(), boardPlayer.getNumOfDevCards(), boardPlayer.getTotalCoins(),
                                    boardPlayer.getTotalStones(), boardPlayer.getTotalShields(), boardPlayer.getTotalServants(),
                                    player.getTotalVictoryPoints()));
                        }

                        server.sendAll(new PacketWinner(gameInterface.getWinner(), playerInfoEndMatches), gameInterface);

                    } else {
                        ClientHandler clientHandler1 = server.getMapUsernameClientHandler().get(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getUsername());
                        clientHandler1.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.YOUR_TURN));
                        clientHandler1.sendPacketToClient(new PacketFaithTrack(board.getTrack(), board.getFaithMarker(), board.getVaticanReportSections()));

                        gameInterface.setState(GameStates.PHASE_ONE);
                    }
                } else {
                    clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLEMOVE));
                }
            } else {
                clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLEENDTURN));
            }

        }

    }

    public void handleDrawingToken( GameInterface gameInterface, ClientHandler clientHandler, SoloActionToken token, ConnectionMessages message){
        ((SinglePlayerGame) gameInterface).useSoloActionToken(token);
        clientHandler.sendPacketToClient(new PacketToken(token));
        clientHandler.sendPacketToClient(new PacketBlackCross(((SinglePlayerGame) gameInterface).getBlackCross()));
        clientHandler.sendPacketToClient(new PacketFaithTrack(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getTrack(),
                gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getFaithMarker(),
                gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getVaticanReportSections()));
        clientHandler.sendPacketToClient(new PacketConnectionMessages(message));
        clientHandler.sendPacketToClient(new PacketLiteDevelopmentGrid(gameInterface.getDevGridLite()));
        clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.YOUR_TURN));
    }



}
