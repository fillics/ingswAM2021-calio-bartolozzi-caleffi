package it.polimi.ingsw.controller;

/**
 * This enum class represents the states of a Game
 */
public enum GameStates {
    FILL_LOBBY("People connecting to the server..."),
    SETUP("Creation of the game and the players choose which leader card to use during the game "),
    PHASE_ONE("in this phase you can call any method related to the player turn"),
    PHASE_TWO("in this phase you can call only the secondary method of the turn like moving the resources in the deposit"),
    FINAL_TURN("Client ends his final turn"),
    END("The game is finished");


    private final String descriptionState;

    GameStates(String descriptionState) {
        this.descriptionState = descriptionState;
    }

    public String getDescriptionState() {
        return descriptionState;
    }
}
