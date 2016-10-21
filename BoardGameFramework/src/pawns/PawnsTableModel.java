
package pawns;

import framework.BoardSpot;
import framework.GameTableModel;
import java.util.Arrays;

/**
 * The table model to be used to represent the game board
 * @author BrianGouldsberry
 */
public class PawnsTableModel extends GameTableModel
{
    private final int k_SMALL = 5, k_MEDIUM = 8, k_LARGE = 10;
    private int size = k_MEDIUM, blackScore = 0, whiteScore = 0;
    private BoardSpot[][] undo;
    
    /**
     * Starts a new game 
     */
    public PawnsTableModel()
    {
        newGame();
    }
    
    /**
     * Sets the board size if the input is valid
     * @param size the desired 
     */
    public void setSize(int size)
    {
        //IF its a valid size then set the size, else default to medium
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
     * modifies the board based upon the cell chosen
     * @param row the numerical row
     * @param column the numerical column
     * @param leftClick whether or not there was a left click
     * @return the status to be displayed
     */
    @Override
    public String validateMove(int row, int column, boolean leftClick) 
    {
        String status = "";
        int direction = 1;
        
        //IF left then direction should look left on the array
        if (leftClick)
        {
            direction = -1;
        }
        //IF the spot is a black piece
        if (board[row][column] == PawnsEnum.BLACK)
        {
            undo = copyBoard();
            //IF the spot diagonal is in play and an opponent then capture it
            if (inBounds(row + 1, column + direction) &&
                    board[row + 1][column + direction] == PawnsEnum.WHITE)
            {
                board[row + 1][column + direction] = PawnsEnum.BLACK;
                board[row][column] = PawnsEnum.NONE;
                blackScore++;
            }
            //ELSE if clear then move forward
            else if (inBounds(row + 1, column) && 
                    board[row + 1][column] == PawnsEnum.NONE)
            {
                board[row + 1][column] = PawnsEnum.BLACK;
                board[row][column] = PawnsEnum.NONE;
            }
        }
        //IF the spot is a white piece
        else if (board[row][column] == PawnsEnum.WHITE)
        {
            undo = copyBoard();
            //IF the spot diagonal is in play and an opponent then capture it
            if (inBounds(row - 1, column + direction) && 
                    board[row - 1][column + direction] == PawnsEnum.BLACK)
            {
                board[row - 1][column + direction] = PawnsEnum.WHITE;
                board[row][column] = PawnsEnum.NONE;
                whiteScore++;
            }
            //ELSE if clear then move forward
            else if (inBounds(row - 1, column) &&
                    board[row - 1][column] == PawnsEnum.NONE)
            {
                board[row - 1][column] = PawnsEnum.WHITE;
                board[row][column] = PawnsEnum.NONE;
            }
        }
        //IF game over then change the status
        if (gameOver() != null)
        {
            status = gameOver();
        }
        //IF nothing changed then use invalid move
        else if (board[row][column] == undo[row][column])
        {
            status = "Invalid move!";
        }
        else 
        {
            status = "Black: " + blackScore + " | White: " + whiteScore;
        }
        this.fireTableDataChanged();
        return status;
    }
    
    /**
     * Determines if the game is done
     * @return null if game is done, if not then "[Team] Wins!" 
     */
    private String gameOver()
    {
        String result = null;
        boolean whiteExists = false, blackExists = true;
        //CHECK the first row
        for (BoardSpot spot : board[0])
        {
            //IF a pawn is there then they won
            if (spot == PawnsEnum.WHITE || whiteScore == size)
            {
                result = "White Wins!";
            }
        }
        //CHECK the last row
        for (BoardSpot spot : board[size - 1])
        {
            //IF a pawn is there then they won
            if (spot == PawnsEnum.BLACK || blackScore == size)
            {
                result = "Black Wins!";
            }
        }
        return result;
    }
    
    /**
     * returns the board to its previous state
     */
    public void undo()
    {
        board = undo;
        this.fireTableDataChanged();
    }
   
    private boolean inBounds(int row, int column)
    {
        return (row >= 0 && row < size) && (column >= 0 && column < size);
    }

    /**
     * initializes the game with two rows of pawns, each one row from the top 
     * and bottom
     */
    @Override
    public void newGame() 
    {
        board = new PawnsEnum[size][size];
        //FOR each row
        for (int row = 0; row < size; row++)
        {
            //FOR each column
            for (int column = 0; column < size; column++)
            {
                //SET to black if 2nd row
                if (row == 1)
                {
                    board[row][column] = PawnsEnum.BLACK;
                }
                //SET to white if 2nd to last row
                else if (row == size - 2)
                {
                    board[row][column] = PawnsEnum.WHITE;
                }
                else
                {
                    board[row][column] = PawnsEnum.NONE;
                }
            }
        }
        whiteScore = 0;
        blackScore = 0;
        columnNames = new String[size];
        //MAKE column names blank
        for (int column = 0; column < size; column++)
        {
            columnNames[column] = "";
        }
        undo = board;
        this.fireTableChanged(null);
    }
     
    /**
     * Returns the name of the enum class being used
     * @param c the column number
     * @return PawnsEnum.class
     */
    public Class getColumnClass(int c)
    {
        return PawnsEnum.class;
    }
    
    private BoardSpot[][] copyBoard()
    {
        BoardSpot[][] copy = new BoardSpot[size][size];
        //FOR each row
        for (int row = 0; row < size; row++)
        {
            //FOR each column, copy the spot into the copy array
            for (int col = 0; col < size; col++)
            {
                copy[row][col] = board[row][col];
            }
        }
        return copy;
    }
}