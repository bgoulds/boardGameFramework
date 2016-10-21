
package pawns;

import framework.BoardSpot;
import static hurkle.HurkleEnum.CLICKED;
import static hurkle.HurkleEnum.UNCLICKED;

/**
 * Enum for pawns game, options to set to NONE, BLACK, or WHITE
 * @author BrianGouldsberry
 */
public enum PawnsEnum implements BoardSpot
{
    /**
     * Option for no piece on the board
     */
    NONE, 
    
    /**
     * Option for a black piece on the board
     */
    BLACK, 
    
    /**
     * Option for a white piece on the board
     */
    WHITE;
    
    /**
     * finds an ascii representation of the enum
     * @return BLACK -> B
     *         WHITE -> W
     *         NONE  -> -
     */
    @Override
    public String toAscii() 
    {
        String ret = "";
        //ADD "B" if black
        if (this == BLACK)
        {
            ret += "B";
        }
        //ADD "W" if white or "-" if none
        else if (this == WHITE)
        {
            ret += "W";
        }
        else
        {
            ret += "-";
        }
        return ret;
    }
}
