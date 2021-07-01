package it.polimi.ingsw.client;

/**
 * Enum class that contains the possible client states during a match
 */
public enum ClientStates {
    SERVERCONNECTION,
    USERNAME,
    NUMPLAYERS,
    WAITPLAYERS,
    CREATEMODEL,
    LEADERSETUP,
    RESOURCESETUP,
    GAMESTARTED,
    GAME_ENDING,
    END,
    CLOSE_CONNECTION
}
