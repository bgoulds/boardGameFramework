
package framework;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTable;

/**
 * Basic View to be used with the BoardGame framework, doesnt specify console
 * or gui
 * @author BrianGouldsberry
 */
public interface GameView extends Observer
{   
    /**
     * makes the view visible or not
     * @param state true means show the view, false means hide it
     */
    public void setVisible(boolean state);
    
    /**
     * Sets the controller to be used for observing mouse clicks
     * @param cont a GameController
     */
    public void setController(GameController cont);
    
    /**
     * Updates the state of the GUI
     * @param obs a GameModel that is being observed
     * @param obj not utilized
     */
    public void update(Observable obs, Object obj);
    
    /**
     * loads the plugins preferences
     */
    public void loadPreferences();
    
    /**
     * initialized the board
     */
    public void setUpBoard();
    
    /**
     * returns the table if one exists
     * @return a JTable if the View uses one, or null
     */
    public JTable getTable();

    /**
     * Sets the listener
     * @param listener the ActionListener to be used for property actions
     */
    public void setListener(ActionListener listener);
}
