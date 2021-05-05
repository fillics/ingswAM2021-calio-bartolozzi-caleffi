package it.polimi.ingsw.controller;

public enum State {
    FILL_LOBBY("People connecting to the server..."),
    NUMOF_PLAYERS("The first player connected inserts the number of the player for the game"),
    SETUP(""),
    PHASE_ONE(""),
    PHASE_TWO(""),
    END_TURN("Client ends his own turn");

    private final String descriptionState;

    State(String descriptionState) {
        this.descriptionState = descriptionState;
    }

    public String getDescriptionState() {
        return descriptionState;
    }
}
