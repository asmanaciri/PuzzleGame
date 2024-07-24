package view;

import controller.OpeningScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PuzzleGameApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/opening_screen.fxml"));
        Parent root = loader.load();

        OpeningScreenController openingController = loader.getController();
        openingController.setPrimaryStage(stage);

        stage.setTitle("Puzzle Game");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }


}
