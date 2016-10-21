
package framework;

import java.awt.event.MouseAdapter;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 * A Mouse Controller that translates clicks into row and column values for a
 * cell
 * @author BrianGouldsberry
 */
public class GameController extends MouseAdapter
{
    private JTable table;
    private GameModel model;
    
    /**
     * Sets the model to be used
     * @param model the game model to modify
     */
    public GameController(GameModel model)
    {
        this.model = model;
    }
    
    /**
     * Sets the JTable value so that the event can figure out what cell was 
     * clicked
     * @param table the JTable used by the view
     */
    public void setTable(JTable table)
    {
        this.table = table;
    }
    
    /**
     * Returns the game model
     * @return model
     */
    public GameModel getModel()
    {
        return model;
    }
    
    /**
     * Tells the model to validate the click based off of its position in the 
     * JTable
     * @param evt the mouse click event
     */
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) 
    {
        //AS long as the table isn't null, validate the click
        if (table != null)
        {
            int row = table.rowAtPoint(evt.getPoint());
            int col = table.columnAtPoint(evt.getPoint());
            boolean leftClick = SwingUtilities.isLeftMouseButton(evt);
            model.validateMove(row, col, leftClick);
        }
    }
}
