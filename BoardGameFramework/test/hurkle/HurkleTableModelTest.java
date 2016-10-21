
package hurkle;

import framework.BoardSpot;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;

/**
 *
 * @author BrianGouldsberry
 */
public class HurkleTableModelTest extends TestCase {
    HurkleTableModel instance;
    
    public HurkleTableModelTest(String testName) 
    {
        super(testName);
        instance = new HurkleTableModel();
    }

    /**
     * Test of setSize method, of class HurkleTableModel.
     */
    public void testSetSize() 
    {
        System.out.println("setSize");
        int size = 5;
        instance.setSize(size);
        instance.newGame();
        assertEquals(5, instance.getBoard().length);
        size = 9;
        instance.setSize(size);
        instance.newGame();
        assertEquals(9, instance.getBoard().length);
        size = 11;
        instance.setSize(size);
        instance.newGame();
        assertEquals(11, instance.getBoard().length);
        size = 21;
        instance.setSize(size);
        instance.newGame();
        assertEquals(9, instance.getBoard().length);
    }

    /**
     * Test of validateMove method, of class HurkleTableModel.
     */
    public void testValidateMove() 
    {
        System.out.println("validateMove");
        boolean left = false;
        instance.setHurkle(1, 1);
        String result = instance.validateMove(0, 0, left);
        assertEquals("SouthEast", result);
        result = instance.validateMove(0, 1, left);
        assertEquals("South", result);
        result = instance.validateMove(0, 2, left);
        assertEquals("SouthWest", result);
        result = instance.validateMove(1, 0, left);
        assertEquals("East", result);
        result = instance.validateMove(1, 1, left);
        assertEquals("", result);
        result = instance.validateMove(1, 2, left);
        assertEquals("West", result);
        result = instance.validateMove(2, 0, left);
        assertEquals("NorthEast", result);
        result = instance.validateMove(2, 1, left);
        assertEquals("North", result);
        result = instance.validateMove(2, 2, left);
        assertEquals("NorthWest", result);
    }

    /**
     * Test of newGame method, of class HurkleTableModel.
     */
    public void testNewGame() {
        System.out.println("newGame");
        instance.newGame();
        BoardSpot[][] board = instance.getBoard();
        for (BoardSpot[] row: board)
        {
            for (BoardSpot spot: row)
            {
                assertEquals(HurkleEnum.UNCLICKED, spot);
            }
        }
    }

    /**
     * Test of getColumnClass method, of class HurkleTableModel.
     */
    public void testGetColumnClass() {
        System.out.println("getColumnClass");
        Class result = instance.getColumnClass(0);
        assertEquals(HurkleEnum.class, result);
    }

}
