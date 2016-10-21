
package framework;

import hurkle.HurkleEnum;
import junit.framework.TestCase;

/**
 *
 * @author BrianGouldsberry
 */
public class GameTableModelTest extends TestCase {
    GameTableModelImpl instance;
    
    public GameTableModelTest(String testName) {
        super(testName);
        instance = new GameTableModelImpl();
        instance.newGame();
    }

    /**
     * Test of getBoard method, of class GameTableModel.
     */
    public void testGetBoard() {
        System.out.println("getBoard");
        BoardSpot[][] result = instance.getBoard();
        for (BoardSpot[] row: result)
        {
            for (BoardSpot spot: row)
            {
                assertEquals(HurkleEnum.UNCLICKED, spot);
            }
        }
    }

    /**
     * Test of getColumnCount method, of class GameTableModel.
     */
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        int result = instance.getColumnCount();
        assertEquals(8, result);
    }

    /**
     * Test of getRowCount method, of class GameTableModel.
     */
    public void testGetRowCount() {
        System.out.println("getRowCount");
        int result = instance.getRowCount();
        assertEquals(8, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getColumnName method, of class GameTableModel.
     */
    public void testGetColumnName() {
        System.out.println("getColumnName");
        int col = 0;
        String expResult = "";
        String result = instance.getColumnName(col);
        assertEquals(expResult, result);
    }

    /**
     * Test of getValueAt method, of class GameTableModel.
     */
    public void testGetValueAt() {
        System.out.println("getValueAt");
        Object result = instance.getValueAt(0, 0);
        assertEquals(HurkleEnum.UNCLICKED, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    public class GameTableModelImpl extends GameTableModel {

        public void setSize(int size) 
        {
            
        }

        public Class getColumnClass(int c) 
        {
            return null;
        }

        public void newGame() 
        {
            board = new BoardSpot[8][8];
            for (int row = 0; row < 8; row++)
            {
                //FOR each column, set the cell to unclicked
                for (int column = 0; column < 8; column++)
                {
                    board[row][column] = HurkleEnum.UNCLICKED;
                }
            }
            columnNames = new String[8];
            for (int col = 0; col < 8; col++)
            {
                columnNames[col] = "";
            }
        }

        public String validateMove(int row, int column, boolean leftClick) 
        {
            return "";
        }
    }

}
