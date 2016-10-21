package framework;

import hurkle.HurkleEnum;
import javax.swing.table.AbstractTableModel;


/**
 * Table Model to be used for board games
 * @author BrianGouldsberry
 */
public abstract class GameTableModel extends AbstractTableModel
{
    protected BoardSpot[][] board;
    protected String[] columnNames;
    
    /**
     * Exits the program
     */
    public void quit()
    {
        System.exit(1);
    }
       
    /**
     * Getter for the board as a 2 dimensional array
     * @return the board
     */
    public BoardSpot[][] getBoard()
    {
        return board;
    }
   
    /**
     * getter for the number of columns
     * @return the number of columns in the board
     */
    public int getColumnCount() 
    {
        return columnNames.length;
    }

    /**
     * getter for the number of rows
     * @return the number of rows in the board
     */
    public int getRowCount() 
    {
        return board.length;
    }

    /**
     * getter for columnNames
     * @param col the desired column number
     * @return the name of the column
     */
    public String getColumnName(int col) 
    {
        return columnNames[col];
    }

    /**
     * getter for the value of a specific cell
     * @param row the row number
     * @param col the column number
     * @return the BoardSpot at the given spot in the board array
     */
    public Object getValueAt(int row, int col) 
    {
        return board[row][col];
    }
    
    /**
     * Resizes the board
     * @param size the size as an int
     */
    public abstract void setSize(int size);
    
    /**
     * Should return the Enum class that is specific to the game plugin
     * @param c the column number
     * @return whatever enum class the plugin uses
     */
    public abstract Class getColumnClass(int c);
    
    /**
     * Starts a new game
     */
    public abstract void newGame();

    /**
     * Validates the move at a given spot
     * @param row row number
     * @param column column number
     * @param leftClick if it was a left click
     * @return the status to be displayed in the view
     */
    public abstract String validateMove(int row, int column, boolean leftClick);

}
