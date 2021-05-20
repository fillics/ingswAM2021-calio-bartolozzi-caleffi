package it.polimi.ingsw.GUI;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        ClientGUI.setRoot("secondary");
    }
}
