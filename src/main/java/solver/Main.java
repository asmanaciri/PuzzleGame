package solver;

import puzzle.solver.BreadthFirstSearch;
import puzzle.Move;
import puzzle.Position;
import puzzle.PuzzleState;

/**
 * Solves the chess puzzle using the breadth-first search algorithm.
 */
public class Main {

    public static void main(String[] args) {
        var bfs = new BreadthFirstSearch<Move>();  // Use Move instead of Direction
        bfs.solveAndPrintSolution(new PuzzleState());
    }
}