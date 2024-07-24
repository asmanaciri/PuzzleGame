package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

public class OpeningScreenController {

    @FXML
    private TextField nameField;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    //This method is triggered by a user action in the UI (clicking a "Start Game" button)

    @FXML
    public void startGame() {
        String playerName = nameField.getText();

        Logger.info("Starting game for player: {}", playerName); // Log player name

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/puzzle_game.fxml"));
            Parent root = loader.load();
            //Loads the main game screen from the FXML file (puzzle_game.fxml)
            PuzzleGameController puzzleController = loader.getController();
            //Retrieves the controller for the puzzle game screen (PuzzleGameController)
            puzzleController.setPrimaryStage(primaryStage);
            //passes the primary stage to the puzzle game controller.
            puzzleController.setPlayerName(playerName);
            //Passes the player’s name to the puzzle game controller


            //Sets up and displays the main game screen.
            primaryStage.setTitle("Puzzle Game");
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

            Logger.info("Game started successfully");
        } catch (IOException e) {
            Logger.error("Error loading puzzle game screen", e);
            e.printStackTrace();
        }
    }
}