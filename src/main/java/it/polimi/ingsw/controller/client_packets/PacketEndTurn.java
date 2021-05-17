package it.polimi.ingsw.controller.client_packets;

import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.PacketLiteDevelopmentGrid;
import it.polimi.ingsw.controller.server_packets.PacketMessage;
import it.polimi.ingsw.controller.server_packets.ServerPacketHandler;
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
                SoloActionToken token = ((SinglePlayerGame) gameInterface).drawSoloActionToken();
                if (token.getType().equals(SoloActionTokenType.DISCARD)) {
                    ((SinglePlayerGame) gameInterface).useSoloActionToken();
                    clientHandler.sendPacketToClient(new PacketLiteDevelopmentGrid(((SinglePlayerGame) gameInterface).getDevGridLite()));
                    clientHandler.sendPacketToClient(new PacketMessage(ConnectionMessages.DISCARDDEVCARD));
                } else if (token.getType().equals(SoloActionTokenType.BLACKCROSS_1)) {
                    ((SinglePlayerGame) gameInterface).useSoloActionToken();
                    //TODO: aggiungere un paccchetto che invia il tracciato fede modificato
                    clientHandler.sendPacketToClient(new PacketMessage(ConnectionMessages.BLACKCROSS1));
                } else if (token.getType().equals(SoloActionTokenType.BLACKCROSS_2)) {
                    ((SinglePlayerGame) gameInterface).useSoloActionToken();
                    //TODO: aggiungere un paccchetto che invia il tracciato fede modificato
                    clientHandler.sendPacketToClient(new PacketMessage(ConnectionMessages.BLACKCROSS2));
                }
            }
        }
        else{
            if ((gameInterface.getState().equals(GameStates.PHASE_ONE) || gameInterface.getState().equals(GameStates.PHASE_TWO)) && clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()) {
                gameInterface.nextPlayer();
                System.out.println(clientHandler.getPosInGame());
                if (clientHandler.getPosInGame() == gameInterface.getActivePlayers().size() - 1) {
                    server.getMapUsernameClientHandler().get(gameInterface.getActivePlayers().get(0).getUsername()).sendPacketToClient(new PacketMessage(ConnectionMessages.YOUR_TURN));
                } else {
                    server.getMapUsernameClientHandler().get(gameInterface.getActivePlayers().get(clientHandler.getPosInGame() + 1).getUsername()).sendPacketToClient(new PacketMessage(ConnectionMessages.YOUR_TURN));
                }
                gameInterface.setState(GameStates.PHASE_ONE);
            }
            else {
                System.out.println("I'm sorry, you can't do this action in this moment of the game");
            }
        }

    }
}
