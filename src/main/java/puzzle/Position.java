package puzzle;

import java.util.Objects;

/**
 * Represents a position on the puzzle board with row and column indices.
 * The {@code Position} class provides methods to navigate to adjacent positions.
 */
public class Position {

    //Both fields are final, meaning they are set when the Position object is created and cannot be changed afterward.
    private final int row;
    private final int col;

    /**
     * Constructs a {@code Position} with the specified row and column.
     *
     * @param row the row index of the position
     * @param col the column index of the position
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Returns the row index of this position.
     *
     * @return the row index
     */

    // getter methods for the rows and columns
    public int row() {
        return row;
    }

    /**
     * Returns the column index of this position.
     *
     * @return the column index
     */
    public int col() {
        return col;
    }

    /**
     * Returns a new {@code Position} that represents moving up from this position.
     *
     * @return the new {@code Position} one row up
     */

    // Movement Methods that represent moving from the current position in a specific direction.

    public Position moveUp() {
        return new Position(row - 1, col);
    }

    /**
     * Returns a new {@code Position} that represents moving down from this position.
     *
     * @return the new {@code Position} one row down
     */
    public Position moveDown() {
        return new Position(row + 1, col);
    }

    /**
     * Returns a new {@code Position} that represents moving left from this position.
     *
     * @return the new {@code Position} one column to the left
     */
    public Position moveLeft() {
        return new Position(row, col - 1);
    }

    /**
     * Returns a new {@code Position} that represents moving right from this position.
     *
     * @return the new {@code Position} one column to the right
     */
    public Position moveRight() {
        return new Position(row, col + 1);
    }

    /**
     * Compares this position to the specified object. The result is {@code true} if and only if the argument is not {@code null}
     * and is a {@code Position} object that has the same {@code row} and {@code col} values as this object.
     *
     * @param o the object to compare this {@code Position} against
     * @return {@code true} if the given object represents a {@code Position} equivalent to this position, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        //Checks if both references point to the same object.
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    /**
     * Returns a hash code value for the position.
     * This method is supported for the benefit of hash tables such as those provided by {@link java.util.HashMap}.
     *
     * @return a hash code value for this position
     */


    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    /**
     * Returns a string representation of the position.
     * The string representation consists of the row and column indices.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}

