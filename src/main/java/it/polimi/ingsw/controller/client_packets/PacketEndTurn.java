package it.polimi.ingsw.controller.client_packets;

import it.polimi.ingsw.controller.ConnectionMessages;
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
        if(gameInterface instanceof SinglePlayerGame){
            if(gameInterface.getState().equals(GameStates.SETUP)) gameInterface.setState(GameStates.PHASE_ONE);
            if ((gameInterface.getState().equals(GameStates.PHASE_ONE) || gameInterface.getState().equals(GameStates.PHASE_TWO)) && clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()) {
                gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getResourceBuffer().clear();
                if(gameInterface.getIdClientActivePlayers().get(clientHandler.getIdClient()).getResourceBuffer().size() != 0){
                    System.out.println("ginopino");
                    clientHandler.sendPacketToClient(new PacketResourceBuffer(gameInterface.getIdClientActivePlayers().get(clientHandler.getIdClient()).getResourceBuffer()));
                }
                if(gameInterface.isEndgame() && clientHandler.getPosInGame() == gameInterface.getActivePlayers().size() - 1){
                    ((SinglePlayerGame) gameInterface).winner();
                    System.out.println(gameInterface.getWinner());
                    clientHandler.sendPacketToClient(new PacketWinner(gameInterface.getWinner()));
                }
                SoloActionToken token = ((SinglePlayerGame) gameInterface).drawSoloActionToken();
                if (token.getType().equals(SoloActionTokenType.DISCARD)) {
                    ((SinglePlayerGame) gameInterface).useSoloActionToken(token);
                    clientHandler.sendPacketToClient(new PacketLiteDevelopmentGrid(((SinglePlayerGame) gameInterface).getDevGridLite()));
                    clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.DISCARDDEVCARD));
                } else if (token.getType().equals(SoloActionTokenType.BLACKCROSS_1)) {
                    ((SinglePlayerGame) gameInterface).useSoloActionToken(token);
                    clientHandler.sendPacketToClient(new PacketFaithTrack(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getTrack()));
                    clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.BLACKCROSS1));
                } else if (token.getType().equals(SoloActionTokenType.BLACKCROSS_2)) {
                    ((SinglePlayerGame) gameInterface).useSoloActionToken(token);
                    clientHandler.sendPacketToClient(new PacketFaithTrack(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getTrack()));
                    clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.BLACKCROSS2));
                }
                if(gameInterface.isEndgame() && clientHandler.getPosInGame() == gameInterface.getActivePlayers().size() - 1){
                    ((SinglePlayerGame) gameInterface).winner();
                    clientHandler.sendPacketToClient(new PacketWinner(gameInterface.getWinner()));
                }
                gameInterface.setState(GameStates.PHASE_ONE);
            }
        }
        else{
            if ((gameInterface.getState().equals(GameStates.PHASE_ONE) || gameInterface.getState().equals(GameStates.PHASE_TWO)) && clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()) {
                gameInterface.nextPlayer();
                System.out.println(gameInterface.getIdClientActivePlayers().get(clientHandler.getIdClient()).getResourceBuffer());
                clientHandler.sendPacketToClient(new PacketResourceBuffer(gameInterface.getIdClientActivePlayers().get(clientHandler.getIdClient()).getResourceBuffer()));

                if(gameInterface.isEndgame() && clientHandler.getPosInGame() == gameInterface.getActivePlayers().size() - 1){
                    clientHandler.sendPacketToClient(new PacketWinner(gameInterface.getWinner()));
                }
                else{
                    System.out.println(clientHandler.getPosInGame());
                    if (clientHandler.getPosInGame() == gameInterface.getActivePlayers().size() - 1) {
                        server.getMapUsernameClientHandler().get(gameInterface.getActivePlayers().get(0).getUsername()).sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.YOUR_TURN));
                    } else {
                        server.getMapUsernameClientHandler().get(gameInterface.getActivePlayers().get(clientHandler.getPosInGame() + 1).getUsername()).sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.YOUR_TURN));
                    }
                    gameInterface.setState(GameStates.PHASE_ONE);
                }
            }
            else {
                clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLEMOVE));
            }
        }

    }
}
