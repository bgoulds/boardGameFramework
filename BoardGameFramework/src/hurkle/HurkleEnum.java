
package hurkle;

import framework.BoardSpot;

/**
 * Enumeration value for Hurkle. UNCLICKED denotes a spot yet to be tested, 
 * CLICKED is an incorrect choice, and HURKLE denotes the solution
 * @author BrianGouldsberry
 */
public enum HurkleEnum implements BoardSpot
{
    /**
     * A spot yet to be guessed
     */
    UNCLICKED, 
    
    /**
     * An incorrect choice
     */
    CLICKED, 
    
    /**
     * A correct choice
     */
    HURKLE;

    /**
     * Returns an ascii representation of the enum
     * @return UNCLICKED -> "X"
     *         CLICKED   -> " "
     *         HURKLE    -> "H"
     */
    @Override
    public String toAscii() 
    {
        String ret = "";
        //ADD "X" if unclicked
        if (this == UNCLICKED)
        {
            ret += "X";
        }
        //ADD " " if clicked and "H" if Hurklle
        else if (this == CLICKED)
        {
            ret += " ";
        }
        else
        {
            ret += "H";
        }
        return ret;
    }
    
}
