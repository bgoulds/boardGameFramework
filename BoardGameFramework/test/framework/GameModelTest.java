
package framework;

import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

/**
 *
 * @author BrianGouldsberry
 */
public class GameModelTest extends TestCase {
    GameModel instance;
    GameTableModel tableModel;
    PieceRenderer renderer;
    
    public GameModelTest(String testName) {
        super(testName);
        tableModel = mock(GameTableModel.class);
        when(tableModel.validateMove(any(int.class), any(int.class),
                any(boolean.class))).thenReturn("").thenReturn("Status");
        renderer = mock(PieceRenderer.class);
        instance = new GameModel(tableModel, "test");
    }

    /**
     * Test of getTableModel method, of class GameModel.
     */
    public void testGetTableModel() {
        System.out.println("getTableModel");
        assertEquals(tableModel, instance.getTableModel());
    }

    /**
     * Test of validateMove method, of class GameModel.
     */
    public void testValidateMove() {
        System.out.println("validateMove");
        int xCoord = 0;
        int yCoord = 0;
        boolean left = false;
        instance.validateMove(xCoord, yCoord, left);
        assertEquals("Click on a cell to make a move!", instance.getStatus());
        instance.validateMove(xCoord, yCoord, left);
        assertEquals("Status", instance.getStatus());
    }

    /**
     * Test of changeTheme method, of class GameModel.
     */
    public void testChangeTheme() {
        System.out.println("changeTheme");
        instance.setTableCellRenderer(null);
        instance.changeTheme("Classic");
        verify(renderer, never()).changeTheme("Classic");
        instance.setTableCellRenderer(renderer);
        instance.changeTheme("Classic");
        verify(renderer).changeTheme("Classic");
        verify(tableModel, times(2)).newGame();
        assertEquals("Classic", instance.getTheme());
    }
}
