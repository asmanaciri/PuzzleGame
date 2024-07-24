package puzzle;

import java.util.Objects;

/**
 * Represents a move in the puzzle game, consisting of a starting position {@link Position} and an ending position {@link Position}.
 * The {@code Move} class encapsulates the transition of a piece from one position to another on the board.
 */
//Move Class: Represents the transition from one position to another on the board.
public class Move {

    private final Position from;
    private final Position to;

    /**
     * Constructs a {@code Move} with the specified starting and ending positions.
     *
     * @param from the starting position of the move
     * @param to   the ending position of the move
     */
    public Move(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the starting position of the move.
     *
     * @return the starting {@link Position}
     */

    //Getter Methods for the starting and ending positions.
    //These methods provide access to the from and to fields without allowing them to be changed.
    public Position getFrom() {
        return from;
    }

    /**
     * Returns the ending position of the move.
     *
     * @return the ending {@link Position}
     */
    public Position getTo() {
        return to;
    }

    /**
     * Compares this move to the specified object. The result is {@code true} if and only if the argument is not {@code null}
     * and is a {@code Move} object that has the same {@code from} and {@code to} positions as this object.
     *
     * @param o the object to compare this {@code Move} against
     * @return {@code true} if the given object represents a {@code Move} equivalent to this move, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return from.equals(move.from) && to.equals(move.to);
    }

    /**
     * Returns a hash code value for the move.
     * This method is supported for the benefit of hash tables such as those provided by {@link java.util.HashMap}.
     *
     * @return a hash code value for this move
     */
    @Override
   // Uses the from and to positions to create a combined hash code.
    // This ensures that if two Move objects are equal (same from and to), they will have the same hash code.
    public int hashCode() {
        return Objects.hash(from, to);
    }

    /**
     * Returns a string representation of the move.
     * The string representation consists of the starting and ending positions.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "Move{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
