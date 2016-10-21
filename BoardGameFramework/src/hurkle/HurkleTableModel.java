
package hurkle;

import framework.BoardSpot;
import framework.GameTableModel;

/**
 * The model for the JTable of the gui, also is used to print out the board 
 * for the console
 * @author BrianGouldsberry
 */
public class HurkleTableModel extends GameTableModel
{
    private final int k_SMALL = 5, k_MEDIUM = 9, k_LARGE = 11;
    private int hurkleX, hurkleY, size = k_MEDIUM, numTurns = k_SMALL;
    
    /**
     * Sets up the board with medium size and a hurkle in a random location
     */
    public HurkleTableModel()
    {
        newGame();
    }
    
    /**
     * Sets the size to the given size or medium if invalid size
     * @param size should be either 5, 9, or 11
     */
    public void setSize(int size)
    {
        //IF valid then set the size to be correct
        if (size == k_SMALL || size == k_MEDIUM || size == k_LARGE)
        {
            this.size = size;
        }
        else
        {
            this.size = k_MEDIUM;
        }
    }
    
    /**
     * Checks to see if the selected spot is where the hurkle is
     * @param row the row that the cell is in
     * @param column the column that the cell was in
     * @param left whether or not there was a left click
     * @return the direction of the hurkle from the clicked spot
     */
    @Override
    public String validateMove(int row, int column, boolean left) 
    {
        String status = "";
        //IF correct guess then display a hurkle
        if (row == hurkleY && column == hurkleX)
        {
            board[row][column] = HurkleEnum.HURKLE;
            status = "You won";
        }
        else
        {
            board[row][column] = HurkleEnum.CLICKED;
            status = getHurkleDirection(row, column);
            numTurns--;
        }
        //IF no turns then lose
        if (numTurns <= 0 && !status.equals("You won"))
        {
            status = "You lose";
        }
        this.fireTableDataChanged();
        return status;
    }

    /**
     * Sets the board up with all unclicked values and sets the placement of
     * hurkle in a random x and y position
     */
    @Override
    public void newGame() 
    {
        board = new HurkleEnum[size][size];
        numTurns = k_SMALL;
        //FOR each row
        for (int row = 0; row < size; row++)
        {
            //FOR each column, set the cell to unclicked
            for (int column = 0; column < size; column++)
            {
                board[row][column] = HurkleEnum.UNCLICKED;
            }
        }

        columnNames = new String[size];
        //SET all the column names to null
        for (int column = 0; column < size; column++)
        {
            columnNames[column] = "";
        }
        
        hurkleX = (int) (Math.random() * size);
        hurkleY = (int) (Math.random() * size);
        this.fireTableChanged(null);
    }
    
    /**
     * Determines the compass direction that the hurkle is from the given spot
     * @param row the spots row
     * @param col the spots column
     * @return the compass direction as a string
     */
    private String getHurkleDirection(int row, int col)
    {
        String dir = "";
        //IF below the hurkle add north
        if (row > hurkleY)
        {
            dir += "North";
        }

        //IF above add south
        if (row < hurkleY)
        {
            dir += "South";
        }
        
        //IF to right of hurkle add west
        if (col > hurkleX)
        {
            dir += "West";
        }
        
        //if to left add east
        if (col < hurkleX)
        {
            dir += "East";
        }
        
        return dir;
    }
    
    protected void setHurkle(int x, int y)
    {
        hurkleX = x;
        hurkleY = y;
        numTurns = k_LARGE;
    }
    
    /**
     * Returns Hurkle enum as the class in each column
     * @param c the column to check
     * @return HurkleEnum.class
     */
    public Class getColumnClass(int c)
    {
        return HurkleEnum.class;
    }
}
