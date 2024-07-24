import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.PuzzleMoveSelector;
import puzzle.PuzzleState;
import puzzle.Position;

import static org.junit.jupiter.api.Assertions.*;

public class PuzzleMoveSelectorTest {

    private PuzzleMoveSelector puzzleMoveSelector;

    @BeforeEach
    public void setUp() {
        PuzzleState initialState = new PuzzleState();
        puzzleMoveSelector = new PuzzleMoveSelector(initialState);
    }


    @Test
    void testInvalidSelection() {
        PuzzleState puzzleState = new PuzzleState();
        PuzzleMoveSelector moveSelector = new PuzzleMoveSelector(puzzleState);

        // Selecting an out-of-bounds position
        moveSelector.select(new Position(8, 8));

        // Check if invalidSelection flag is set to true
        assertTrue(moveSelector.isInvalidSelection(), "Selecting an invalid position should set invalidSelection to true");
    }


    @Test
    public void testSelectFromAfterReset() {
        puzzleMoveSelector.select(new Position(0, 1));
        puzzleMoveSelector.reset();
        puzzleMoveSelector.select(new Position(0, 0));
        assertEquals(PuzzleMoveSelector.Phase.SELECT_TO, puzzleMoveSelector.getPhase(), "After reset, selecting 'from' position should set phase to SELECT_TO");
    }

    @Test
    public void testMakeMoveInvalidMove() {
        puzzleMoveSelector.select(new Position(0, 0));
        puzzleMoveSelector.select(new Position(0, 1)); // Selecting invalid move
        assertThrows(IllegalStateException.class, puzzleMoveSelector::makeMove, "Making an invalid move should throw IllegalStateException");
    }

    @Test
    public void testSelectToBeforeSelectFrom() {
        puzzleMoveSelector.select(new Position(1, 2)); // Selecting 'to' position before 'from'
        assertEquals(PuzzleMoveSelector.Phase.SELECT_FROM, puzzleMoveSelector.getPhase(), "Selecting 'to' position before 'from' should not change phase");
    }
}

