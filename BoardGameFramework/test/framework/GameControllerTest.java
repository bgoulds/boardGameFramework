
package framework;

import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

/**
 *
 * @author BrianGouldsberry
 */
public class GameControllerTest extends TestCase {
    MouseEvent evt;
    GameController instance;
    GameModel model;
    JTable table;
    
    public GameControllerTest(String testName) 
    {
        super(testName);
        model = mock(GameModel.class);
        table = mock(JTable.class);
        evt = mock(MouseEvent.class);
        when(evt.getPoint()).thenReturn(new Point());
        when(table.rowAtPoint(any(Point.class))).thenReturn(1);
        when(table.columnAtPoint(any(Point.class))).thenReturn(1);
        instance = new GameController(model);
    }

    /**
     * Test of getModel method, of class GameController.
     */
    public void testGetModel() {
        System.out.println("getModel");
        assertEquals(model, instance.getModel());
    }

    /**
     * Test of mouseClicked method, of class GameController.
     */
    public void testMouseClicked() {
        System.out.println("mouseClicked");
        instance.setTable(null);
        instance.mouseClicked(evt);
        verify(model, never()).validateMove(1, 1, false);
        instance.setTable(table);
        instance.mouseClicked(evt);
        verify(model).validateMove(1, 1, false);
    }

}
