import puzzle.PuzzleState;
import puzzle.Move;
import puzzle.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PuzzleStateTest {

    @Test
    public void testIsSolved() {
        PuzzleState solvedState = new PuzzleState(new char[][]{
                {'B', 'B', ' '},
                {'R', 'R', 'K'}
        }, new Position(0, 2));

        assertTrue(solvedState.isSolved(), "The puzzle should be considered solved");
    }

    @Test
    public void testIsNotSolved() {
        PuzzleState unsolvedState = new PuzzleState(new char[][]{
                {'K', 'B', 'B'},
                {'R', 'R', ' '}
        }, new Position(1, 2));

        assertFalse(unsolvedState.isSolved(), "The puzzle should not be considered solved");
    }


    @Test
    public void testClone() {
        PuzzleState originalState = new PuzzleState();
        PuzzleState clonedState = originalState.clone();

        assertNotSame(originalState, clonedState, "Cloned state should not be the same object as the original state");
        assertEquals(originalState, clonedState, "Cloned state should be equal to the original state");
    }
}