
package framework;

import java.util.Observable;

/**
 * Model Class to be used for the gameView, contains strings for the status and 
 * theme, along with the Table Model to be used to populate the table
 * @author BrianGouldsberry
 */
public class GameModel extends Observable
{
    protected GameTableModel model;
    protected String status, theme;
    
    private PieceRenderer renderer;
    
    /**
     * Creates a new model with the given status and table model
     * @param model table model to be followed
     * @param status to be displayed 
     */
    public GameModel(GameTableModel model, String status)
    {
        this.model = model;
        this.status = status;
    }
    
    /**
     * getter for the table model
     * @return the model
     */
    public GameTableModel getTableModel()
    {
        return model;
    }
    
    /**
     * getter for the status
     * @return the status
     */
    public String getStatus()
    {
        return status;
    }
    
    /**
     * Validates the move through the table model, and then updates the status
     * if necessary
     * @param xCoord the row that the cell is in
     * @param yCoord the column that the cell is in
     * @param left whether or not it was a left click
     */
    public void validateMove(int xCoord, int yCoord, boolean left)
    {
        status = model.validateMove(xCoord, yCoord, left);
        //IF no status then make it a default
        if (status.length() == 0)
        {
            status = "Click on a cell to make a move!";
        }
        this.notifyObservers();
    }
    
    /**
     * setter for the tablecellrender
     * @param render the desired PieceRenderer
     */
    public void setTableCellRenderer(PieceRenderer render)
    {
        renderer = render;
    }
    
    /**
     * Causes the renderer to draw a theme image if the renderer exists
     * @param theTheme name of the directory to look for the theme in
     */
    public void changeTheme(String theTheme)
    {
        //CHANGE theme if there is a renderer
        if (renderer != null)
        {
            renderer.changeTheme(theTheme);
        }
        this.theme = theTheme;
        newGame();
        notifyObservers();
    }
    
    /**
     * Getter for the theme
     * @return the theme as a string
     */
    public String getTheme()
    {
        return theme;
    }
    
    /**
     * resets the gameboard and the game status
     */
    public void newGame()
    {
        status = "Click on a cell to make a move!";
        model.newGame();
        notifyObservers();
    }
    
    /**
     * Puts set changed in with notify observers to avoid forgetting to
     */
    @Override
    public void notifyObservers()
    {
        this.setChanged();
        super.notifyObservers();
    }
}
