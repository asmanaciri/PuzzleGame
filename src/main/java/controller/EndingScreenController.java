package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.tinylog.Logger;

public class EndingScreenController {

    @FXML
    private Label congratsLabel;

    private String playerName;

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
        displayMessage();
    }

    private void displayMessage() {
        congratsLabel.setText("Congratulations, " + playerName + "!\nYou have solved the puzzle.");
        Logger.info("Game is over, you solved the puzzle.");
    }
}
