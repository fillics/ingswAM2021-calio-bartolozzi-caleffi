package it.polimi.ingsw.client;

public interface SetupClientInterface {

    /**
     * Method serverMatch used to create the communication with the server to play online
     */
    void serverMatch();

    /**
     * Method choosePlayerNumber asks how many players there will be in the game and sends the message to the server
     */
    void choosePlayerNumber(int number_of_players);


}
