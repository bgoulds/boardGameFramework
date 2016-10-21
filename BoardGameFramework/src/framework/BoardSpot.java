
package framework;

/**
 * Shell to represent a spot on the board, each game will independently create 
 * an Enum that implements boardspot to represent specific spot types
 * @author BrianGouldsberry
 */
public interface BoardSpot 
{
    /**
     * Returns an ascii representation of the spot for use on console views
     * @return the spot as ascii, however the developer would want it to look
     */
    public String toAscii();
}
