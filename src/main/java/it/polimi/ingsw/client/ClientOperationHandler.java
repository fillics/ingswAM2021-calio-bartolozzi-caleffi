package it.polimi.ingsw.client;

import java.io.IOException;

public interface ClientOperationHandler {
    void handleCLIOperation(String input) throws IOException;
}
