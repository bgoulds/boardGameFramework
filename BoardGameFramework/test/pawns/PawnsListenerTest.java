
package pawns;

import framework.GameModel;
import framework.GameTableModel;
import java.awt.event.ActionEvent;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

/**
 *
 * @author BrianGouldsberry
 */
public class PawnsListenerTest extends TestCase {
    PawnsListener instance;
    GameModel model;
    GameTableModel tableModel;
    PawnsTableModel pawnsModel;
    
    public PawnsListenerTest(String testName) {
        super(testName);
        model = mock(GameModel.class);
        tableModel = mock(GameTableModel.class);
        when(model.getTableModel()).thenReturn(tableModel);
        instance = new PawnsListener(model);
        pawnsModel = new PawnsTableModel();
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
        verify(tableModel).setSize(8);
        e = new ActionEvent("", 0, "Board Size-Large");
        instance.actionPerformed(e);
        verify(tableModel).setSize(10);
        e = new ActionEvent("", 0, "Skin-Default");
        instance.actionPerformed(e);
        verify(model).changeTheme("Default");
        e = new ActionEvent("", 0, "Skin-Classic");
        instance.actionPerformed(e);
        verify(model).changeTheme("Classic");
        e = new ActionEvent("", 0, "Skin-Night");
        instance.actionPerformed(e);
        verify(model).changeTheme("Night");
        
        when(model.getTableModel()).thenReturn(pawnsModel);
        pawnsModel.validateMove(1, 0, true);
        assertEquals(PawnsEnum.NONE, pawnsModel.getBoard()[1][0]);
        assertEquals(PawnsEnum.BLACK, pawnsModel.getBoard()[2][0]);
        e = new ActionEvent("", 0, "Undo-Undo");
        instance.actionPerformed(e);
        assertEquals(PawnsEnum.BLACK, pawnsModel.getBoard()[1][0]);
    }


}
