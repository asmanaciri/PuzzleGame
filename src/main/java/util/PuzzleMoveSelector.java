package util;

import puzzle.PuzzleState;
import puzzle.Position;
import puzzle.Move;

//While PuzzleState manages the core puzzle logic,
// PuzzleMoveSelector provides a layer of user interaction management.

/**
 * Facilitates move selection for the puzzle game. The {@code PuzzleMoveSelector} class manages the phases of move selection,
 * allowing users to select a starting position and a target position to make a move on the {@link puzzle.PuzzleState}.
 */
public class PuzzleMoveSelector {

    /**
     * Represents the different phases of the move selection process.
     */
    public enum Phase {
        /**
         * Phase where the user selects the starting position of the piece to be moved.
         */
        SELECT_FROM,

        /**
         * Phase where the user selects the target position to move the piece to.
         */
        SELECT_TO,

        /**
         * Phase where the move is validated and ready to be executed.
         */
        READY_TO_MOVE
    }

    private final PuzzleState model;
    private Phase phase = Phase.SELECT_FROM; //Tracks the current phase of the move selection process
    private boolean invalidSelection = false; //Flags if the last selection was invalid
    private Position from; //Stores the starting position of the move
    private Position to; //Stores the target position of the move

    /**
     * Constructs a {@code PuzzleMoveSelector} with the given puzzle state.
     *
     * @param model the {@link puzzle.PuzzleState} to be managed by this selector
     */
    public PuzzleMoveSelector(PuzzleState model) {
        this.model = model;
    }

    /**
     * Returns the current phase of the move selection process.
     *
     * @return the current {@link Phase}
     */
    public Phase getPhase() {
        return phase;
    } //Returns the current phase of the move selection process

    /**
     * Checks if the move selection process is ready to make a move.
     *
     * @return {@code true} if ready to move, {@code false} otherwise
     */
    public boolean isReadyToMove() {
        return phase == Phase.READY_TO_MOVE;
    }

    /**
     * Selects a position on the puzzle board. This method handles the move selection process,
     * updating the phase accordingly.
     *
     * @param position the {@link Position} to select
     * @throws IllegalStateException if the phase is {@code READY_TO_MOVE}
     */
    public void select(Position position) {
        if (!model.isOnBoard(position)) {
            invalidSelection = true;
            return;
        }

        switch (phase) {
            case SELECT_FROM -> selectFrom(position);
            case SELECT_TO -> selectTo(position);
            case READY_TO_MOVE -> throw new IllegalStateException("Already ready to move");
        }
    }

    /**
     * Selects the starting position of the move.
     *
     * @param position the {@link Position} to select as the starting point
     */
    //Selects the starting position if it contains a piece and is valid.
    // Otherwise, it marks the selection as invalid.
    private void selectFrom(Position position) {
        if (model.isOnBoard(position)) {
            char piece = model.getBoard()[position.row()][position.col()];
            if (piece != ' ') {
                from = position;
                phase = Phase.SELECT_TO;
                invalidSelection = false;
                return;
            }
        }
        invalidSelection = true;
    }

    /**
     * Selects the target position of the move.
     *
     * @param position the {@link Position} to select as the target point
     */
    //Selects the target position if it results in a legal move.
    // Otherwise, it marks the selection as invalid.
    private void selectTo(Position position) {
        if (model.isLegalMove(new Move(from, position))) {
            to = position;
            phase = Phase.READY_TO_MOVE;
            invalidSelection = false;
        } else {
            invalidSelection = true;
        }
    }

    /**
     * Returns the selected starting position.
     *
     * @return the selected {@link Position} as the starting point
     * @throws IllegalStateException if the phase is {@code SELECT_FROM}
     */
    //Returns the selected starting position.
    public Position getFrom() {
        if (phase == Phase.SELECT_FROM) {
            throw new IllegalStateException("Selection not made yet");
        }
        return from;
    }

    /**
     * Returns the selected target position.
     *
     * @return the selected {@link Position} as the target point
     * @throws IllegalStateException if the phase is not {@code READY_TO_MOVE}
     */
    //Returns the selected target position.
    public Position getTo() {
        if (phase != Phase.READY_TO_MOVE) {
            throw new IllegalStateException("Move not ready");
        }
        return to;
    }

    /**
     * Checks if the last selection was invalid.
     *
     * @return {@code true} if the selection was invalid, {@code false} otherwise
     */
    //Returns whether the last selection was invalid
    public boolean isInvalidSelection() {
        return invalidSelection;
    }

    /**
     * Executes the move if the move selection process is ready.
     *
     * @throws IllegalStateException if the phase is not {@code READY_TO_MOVE}
     */
    //Executes the move if both the starting and target positions are valid and ready.
    // Resets the selection process afterward
    public void makeMove() {
        if (phase != Phase.READY_TO_MOVE) {
            throw new IllegalStateException("Not ready to move");
        }
        model.makeMove(new Move(from, to));
        reset();
    }

    /**
     * Resets the move selection process to allow for a new move to be selected.
     */
    public void reset() {
        from = null;
        to = null;
        phase = Phase.SELECT_FROM;
        invalidSelection = false;
    } //cleares the positions and sets the phase back to SELECT_FROM

    /**
     * Checks if the specified position is empty on the board.
     *
     * @param position the {@link Position} to check
     * @return {@code true} if the position is empty, {@code false} otherwise
     */
    private boolean isPositionEmpty(Position position) {
        return model.isOnBoard(position) && model.getBoard()[position.row()][position.col()] == ' ';
    }
}

