package it.polimi.ingsw.GUI;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        ClientGUI.setRoot("primary");
    }
}