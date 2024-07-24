package puzzle;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Represents the state of a puzzle game.
 * The puzzle board is a 2x3 grid where each cell can contain a piece ('K', 'B', 'R') or be empty (' ').
 * The goal is to arrange the pieces in a specific solved configuration.
 */

//This class manages the state of the puzzle board,
// checks if the game is solved, validates and executes moves
// and keeps track of the empty space.
public class PuzzleState implements State<Move> {

    private static final int ROWS = 2;
    private static final int COLS = 3;

    //A 2D array representing the puzzle board.
    //Each cell contains a piece ('K', 'B', 'R') or an empty space (' ').
    private final char[][] board;
    //Keeps track of the position of the empty space on the board.
    private Position emptyPosition;

    /**
     * Constructs a {@code PuzzleState} with the initial board configuration.
     * The initial configuration is as follows:
     * <pre>
     *     {'K', 'B', 'B'}
     *     {'R', 'R', ' '}
     * </pre>
     */
    //Default constructor: Initializes the board to the default
    // starting configuration and sets the empty space at position (1, 2).
    public PuzzleState() {
        board = new char[][]{
                {'K', 'B', 'B'},
                {'R', 'R', ' '}
        };
        emptyPosition = new Position(1, 2);
    }

    /**
     * Constructs a {@code PuzzleState} with a specified board configuration and empty position.
     *
     * @param board         the initial configuration of the puzzle board
     * @param emptyPosition the position of the empty space on the board
     */

    //Parameterized constructor: Allows creating a PuzzleState with a
    //specified board configuration and empty position.
    //Useful for creating copies or specific scenarios.
    public PuzzleState(char[][] board, Position emptyPosition) {
        this.board = board;
        this.emptyPosition = emptyPosition;
    }

    /**
     * Returns the current configuration of the puzzle board.
     *
     * @return the puzzle board
     */
   //Accessor Methods
    // getBoard(): Returns the current board configuration.
    public char[][] getBoard() {
        return board;
    }

    /**
     * Checks if the puzzle is solved.
     * The solved configuration is:
     * <pre>
     *     {'B', 'B', ' '}
     *     {'R', 'R', 'K'}
     * </pre>
     *
     * @return {@code true} if the puzzle is solved; {@code false} otherwise
     */
    //Checking If Solved
    //
    @Override
    public boolean isSolved() {
        return board[0][0] == 'B' && board[0][1] == 'B' && board[0][2] == ' ' &&
                board[1][0] == 'R' && board[1][1] == 'R' && board[1][2] == 'K';
    }

    /**
     * Checks if a given move is legal.
     * A move is legal if it moves a piece to the empty position, and the move conforms to the rules for each piece:
     * <ul>
     *     <li>K: Can move to any adjacent square.</li>
     *     <li>B: Can move diagonally to the empty square if the path is clear.</li>
     *     <li>R: Can move horizontally or vertically to the empty square if the path is clear.</li>
     * </ul>
     *
     * @param move the move to check
     * @return {@code true} if the move is legal; {@code false} otherwise
     */
    @Override
    public boolean isLegalMove(Move move) {
        Position from = move.getFrom();
        Position to = move.getTo();

        if (!isOnBoard(from) || !isOnBoard(to) || !to.equals(emptyPosition)) {
            return false;
        }

        char piece = board[from.row()][from.col()];
        switch (piece) {
            case 'K':
                return Math.abs(from.row() - to.row()) <= 1 && Math.abs(from.col() - to.col()) <= 1;
                //<= 1: This movement constraint ensures that the King only moves one square in any direction.
                //vertical if from.row() - to.row() = 1 and from.col() - to.col() = 0
                //horizontal if from.row() - to.row() = 0 and from.col() - to.col() =1
                //diagonal if both = 1
            case 'B':
                return Math.abs(from.row() - to.row()) == Math.abs(from.col() - to.col()) &&
                        isPathClear(from, to);
                //The absolute difference in the row and column indices must be the same for a move to be diagonal.
                //
            case 'R':
                return (from.row() == to.row() || from.col() == to.col()) && isPathClear(from, to);
                //The Rook can move horizontally or vertically, but not diagonally.
                // Therefore, either the row index should remain constant (horizontal move)
                // or the column index should remain constant (vertical move).
            default:
                return false;
        }
    }

    /**
     * Checks if a position is on the board.
     *
     * @param position the position to check
     * @return {@code true} if the position is on the board; {@code false} otherwise
     */
    public boolean isOnBoard(Position position) {
        return position.row() >= 0 && position.row() < ROWS && position.col() >= 0 && position.col() < COLS;
    }
    //index not negative + index not  less than the total number of rows/columns on the board

    /**
     * Checks if the path between two positions is clear.
     * A path is clear if all positions between {@code from} and {@code to} are empty.
     *
     * @param from the starting position
     * @param to   the ending position
     * @return {@code true} if the path is clear; {@code false} otherwise
     */

    //It iterates from the from position to the to position, checking each step along the way.
    // If any position is not empty, it returns false.
    // If it reaches the to position without finding any obstacles, it returns true.
    private boolean isPathClear(Position from, Position to) {
        int rowStep = Integer.signum(to.row() - from.row());
        int colStep = Integer.signum(to.col() - from.col());
        int currentRow = from.row() + rowStep;
        int currentCol = from.col() + colStep;

        while (currentRow != to.row() || currentCol != to.col()) {
            if (board[currentRow][currentCol] != ' ') {
                return false;
            }
            currentRow += rowStep;
            currentCol += colStep;
        }
        return true;
    }

    /**
     * Executes a legal move on the board.
     * Moves the piece from the starting position to the ending position, and updates the empty position.
     *
     * @param move the move to execute
     * @throws IllegalArgumentException if the move is not legal
     */
    //updates the board according to the move and shifts the position of the empty space
    @Override
    public void makeMove(Move move) {
        Position from = move.getFrom(); //Gets the starting position
        Position to = move.getTo(); //Gets the ending position

        if (!isLegalMove(move)) {
            throw new IllegalArgumentException("Move is not legal");
        }

        board[to.row()][to.col()] = board[from.row()][from.col()]; //Moves the piece from from position to to position on the board
        board[from.row()][from.col()] = ' '; //Sets the from position to empty (' '), indicating that the piece has moved away.
        emptyPosition = from; //The empty position moves to where the piece was (from)
    }

    /**
     * Returns a set of all legal moves from the current board configuration.
     *
     * @return a set of legal moves
     */
    @Override
    public Set<Move> getLegalMoves() {
        Set<Move> legalMoves = new HashSet<>(); //Initializes an empty set legalMoves to store all the valid moves.
        //Uses a HashSet to ensure no duplicate moves
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Position from = new Position(row, col); //Creates a Position object for the current cell (row, col) on the board.
                Position to = emptyPosition; //Defines to as the current empty position on the board
                Move move = new Move(from, to); //Creates a Move object representing moving the piece from from to to.
                if (isLegalMove(move)) {
                    legalMoves.add(move); //isLegalMove(move) checks if the generated move is valid according to the rules.
                }
            }
        }
        return legalMoves;
    }

    /**
     * Creates and returns a copy of this {@code PuzzleState}.
     * The copy will have the same board configuration and empty position as the original.
     *
     * @return a clone of this instance
     */
    @Override
    public PuzzleState clone() {
        char[][] newBoard = new char[ROWS][COLS];
        for (int row = 0; row < ROWS; row++) {
            System.arraycopy(board[row], 0, newBoard[row], 0, COLS);
        }
        return new PuzzleState(newBoard, emptyPosition);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare
     * @return {@code true} if this object is the same as the argument; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PuzzleState that = (PuzzleState) o;
        return Objects.equals(emptyPosition, that.emptyPosition) &&
                java.util.Arrays.deepEquals(board, that.board);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(emptyPosition);
        result = 31 * result + java.util.Arrays.deepHashCode(board);
        return result;
    }

    /**
     * Returns a string representation of the object.
     * The string representation consists of the board configuration in a readable format.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",", "[", "]");
        for (char[] row : board) {
            sj.add(java.util.Arrays.toString(row));
        }
        return sj.toString();
    }
}

