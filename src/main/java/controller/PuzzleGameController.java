package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.tinylog.Logger;
import puzzle.Move;
import puzzle.Position;
import puzzle.PuzzleState;

import java.io.IOException;

public class PuzzleGameController {

    @FXML
    private GridPane board;

    private PuzzleState model;
    private Stage primaryStage; // Main application stage

    private String playerName; // Store player's name

    // Paths to images
    private static final String KING_IMAGE = "/king.png";
    private static final String BISHOP_IMAGE = "/bishop.png";
    private static final String ROOK_IMAGE = "/rook.png";

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @FXML
    public void initialize() {
        // Initialize model and render board
        model = new PuzzleState();
        renderBoard();

        Logger.info("Puzzle game initialized");
    }

    private void renderBoard() {
        board.getChildren().clear();
        char[][] puzzleBoard = model.getBoard();

        for (int row = 0; row < puzzleBoard.length; row++) {
            for (int col = 0; col < puzzleBoard[row].length; col++) {
                StackPane cell = new StackPane();
                Rectangle rect = new Rectangle(100, 100);
                rect.setStroke(Color.BLACK);
                rect.setFill(Color.BEIGE);

                if (puzzleBoard[row][col] != ' ') {
                    ImageView piece = new ImageView();
                    piece.setFitWidth(80);
                    piece.setFitHeight(80);

                    switch (puzzleBoard[row][col]) {
                        case 'K':
                            piece.setImage(new Image(getClass().getResourceAsStream(KING_IMAGE)));
                            break;
                        case 'B':
                            piece.setImage(new Image(getClass().getResourceAsStream(BISHOP_IMAGE)));
                            break;
                        case 'R':
                            piece.setImage(new Image(getClass().getResourceAsStream(ROOK_IMAGE)));
                            break;
                    }
                    cell.getChildren().addAll(rect, piece);
                } else {
                    rect.setFill(Color.WHITE);
                    cell.getChildren().add(rect);
                }
                final int finalRow = row;
                final int finalCol = col;
                cell.setOnMouseClicked(e -> handleSquareClick(e, finalRow, finalCol));
                board.add(cell, col, row);
            }
        }

        Logger.info("Board rendered");
    }

    private void handleSquareClick(MouseEvent event, int row, int col) {
        Position from = new Position(row, col);
        Position to = findEmptyPosition(model.getBoard());

        if (to != null) {
            Move move = new Move(from, to);

            if (model.isLegalMove(move)) {
                model.makeMove(move);
                renderBoard();

                if (model.isSolved()) {
                    showEndingScreen();
                }
            }
            Logger.info("Click on square ({},{})", row, col);
        }
    }

    private Position findEmptyPosition(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == ' ') {
                    return new Position(row, col);
                }
            }
        }
        return null;
    }

    private void showEndingScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ending_screen.fxml"));
            Parent root = loader.load();

            EndingScreenController endingController = loader.getController();
            endingController.setPlayerName(playerName);

            Stage stage = new Stage();
            stage.setTitle("Congratulations!");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();

            Logger.info("Showing ending screen");
        } catch (IOException e) {
            Logger.error("Error loading ending screen", e);
            e.printStackTrace();
        }
    }
}