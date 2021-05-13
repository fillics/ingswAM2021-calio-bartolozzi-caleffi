package it.polimi.ingsw.controller;

public enum State {
    FILL_LOBBY("People connecting to the server..."),
    SETUP("Creation of the game and the players choose which leader card to use during the game "),
    PHASE_ONE("in this phase you can call any method related to the player turn"),
    PHASE_TWO("in this phase you can call only the secondary method of the turn like moving the resources in the deposit"),
    END_TURN("Client ends his own turn");

    private final String descriptionState;

    State(String descriptionState) {
        this.descriptionState = descriptionState;
    }

    public String getDescriptionState() {
        return descriptionState;
    }
}
