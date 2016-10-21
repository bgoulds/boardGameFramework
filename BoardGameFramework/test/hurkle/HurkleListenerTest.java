
package hurkle;

import java.awt.event.ActionEvent;
import junit.framework.TestCase;
import framework.GameModel;
import framework.GameTableModel;
import static org.mockito.Mockito.*;

/**
 *
 * @author BrianGouldsberry
 */
public class HurkleListenerTest extends TestCase {
    HurkleListener instance;
    HurkleTableModel hurkleModel;
    GameModel model;
    GameTableModel tableModel;
    
    public HurkleListenerTest(String testName) {
        super(testName);
        model = mock(GameModel.class);
        tableModel = mock(GameTableModel.class);
        hurkleModel = new HurkleTableModel();
        when(model.getTableModel()).thenReturn(tableModel);
        instance = new HurkleListener(model);
    }

    /**
     * Test of actionPerformed method, of class HurkleListener.
     */
    public void testActionPerformed() 
    {
        System.out.println("actionPerformed");
        ActionEvent e = new ActionEvent("", 0, "New Game");
        instance.actionPerformed(e);
        verify(tableModel).newGame();
        e = new ActionEvent("", 0, "Board Size-Small");
        instance.actionPerformed(e);
        verify(tableModel).setSize(5);
        e = new ActionEvent("", 0, "Board Size-Medium");
        instance.actionPerformed(e);
        verify(tableModel).setSize(9);
        e = new ActionEvent("", 0, "Board Size-Large");
        instance.actionPerformed(e);
        verify(tableModel).setSize(11);
        e = new ActionEvent("", 0, "Skin-Default");
        instance.actionPerformed(e);
        verify(model).changeTheme("Default");
        e = new ActionEvent("", 0, "Skin-Classic");
        instance.actionPerformed(e);
        verify(model).changeTheme("Classic");
        e = new ActionEvent("", 0, "Skin-Night");
        instance.actionPerformed(e);
        verify(model).changeTheme("Night");
        when(model.getTableModel()).thenReturn(hurkleModel);
        e = new ActionEvent("", 0, "Cheat-Cheat");
        instance.actionPerformed(e);
        assertEquals("", hurkleModel.validateMove(1, 1, true));
    }

}
