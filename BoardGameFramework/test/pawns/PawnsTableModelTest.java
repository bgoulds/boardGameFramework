
package pawns;

import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;

/**
 *
 * @author BrianGouldsberry
 */
public class PawnsTableModelTest extends TestCase {
    PawnsTableModel instance;
    
    public PawnsTableModelTest(String testName) {
        super(testName);
        instance = new PawnsTableModel();
    }

    /**
     * Test of setSize method, of class PawnsTableModel.
     */
    public void testSetSize() {
        System.out.println("setSize");
        instance.setSize(5);
        instance.newGame();
        assertEquals(5, instance.getBoard().length);
        instance.setSize(8);
        instance.newGame();
        assertEquals(8, instance.getBoard().length);
        instance.setSize(10);
        instance.newGame();
        assertEquals(10, instance.getBoard().length);
        instance.setSize(-1);
        instance.newGame();
        assertEquals(8, instance.getBoard().length);
    }

    /**
     * Test of validateMove method, of class PawnsTableModel.
     */
    public void testValidateMove() {
        System.out.println("validateMove");
        //black scores
        String result = instance.validateMove(1, 0, true);
        assertEquals("Black: 0 | White: 0", result);
        result = instance.validateMove(2, 0, true);
        assertEquals("Black: 0 | White: 0", result);
        result = instance.validateMove(3, 0, true);
        assertEquals("Black: 0 | White: 0", result);
        result = instance.validateMove(4, 0, true);
        assertEquals("Black: 0 | White: 0", result);
        result = instance.validateMove(5, 0, false);
        assertEquals("Black: 1 | White: 0", result);
        
        //white scores
        result = instance.validateMove(6, 7, true);
        assertEquals("Black: 1 | White: 0", result);
        result = instance.validateMove(5, 7, true);
        assertEquals("Black: 1 | White: 0", result);
        result = instance.validateMove(4, 7, true);
        assertEquals("Black: 1 | White: 0", result);
        result = instance.validateMove(3, 7, true);
        assertEquals("Black: 1 | White: 0", result);
        result = instance.validateMove(2, 7, true);
        assertEquals("Black: 1 | White: 1", result);
        
        //A team wins
        result = instance.validateMove(6, 1, true);
        assertEquals("Black Wins!", result);
    }

    /**
     * Test of undo method, of class PawnsTableModel.
     */
    public void testUndo() {
        System.out.println("undo");
        instance.validateMove(1, 0, true);
        assertEquals(PawnsEnum.NONE, instance.getBoard()[1][0]);
        assertEquals(PawnsEnum.BLACK, instance.getBoard()[2][0]);
        instance.undo();
        assertEquals(PawnsEnum.BLACK, instance.getBoard()[1][0]);
    }

    /**
     * Test of newGame method, of class PawnsTableModel.
     */
    public void testNewGame() {
        System.out.println("newGame");
        instance.validateMove(1, 0, true);
        assertEquals(PawnsEnum.NONE, instance.getBoard()[1][0]);
        assertEquals(PawnsEnum.BLACK, instance.getBoard()[2][0]);
        instance.validateMove(6, 0, true);
        assertEquals(PawnsEnum.NONE, instance.getBoard()[6][0]);
        assertEquals(PawnsEnum.WHITE, instance.getBoard()[5][0]);
        instance.newGame();
        assertEquals(PawnsEnum.BLACK, instance.getBoard()[1][0]);
        assertEquals(PawnsEnum.WHITE, instance.getBoard()[6][0]);
    }

    /**
     * Test of getColumnClass method, of class PawnsTableModel.
     */
    public void testGetColumnClass() {
        System.out.println("getColumnClass");
        Class result = instance.getColumnClass(0);
        assertEquals(PawnsEnum.class, result);
    }

}
