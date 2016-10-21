
package framework;

import hurkle.HurkleEnum;
import pawns.PawnsEnum;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Sets the icons for a JTable based upon
 * @author BrianGouldsberry
 */
public class PieceRenderer extends DefaultTableCellRenderer
{
    private Map<BoardSpot, ImageIcon> images;
    private String game;
    
    /**
     * Sets up the renderer and uses the Default skin
     * @param game name of the game being used
     */
    public PieceRenderer(String game)
    { 
        super(); 
        this.game = game;
        changeTheme("Default");
            
    }
    
    /**
     * Changes the theme of the table by changing the image icons
     * @param theme the name of the theme directory to use
     */
    public void changeTheme(String theme)
    {
        try
        {
            Class enums = Class.forName(game.toLowerCase() + "." + game
                    + "Enum");
            Method method = enums.getMethod("values", null);
            images = new HashMap<BoardSpot, ImageIcon>();
            //CONNECT each enum value to the image that matches its name
            for (BoardSpot spot : (BoardSpot[])method.invoke(null))
            {
                ImageIcon icon = new ImageIcon("images/" + game +
                        "/" + theme + "/" + spot.toString() + ".jpg");
                images.put(spot, icon);
            }
        } 
        catch (NoSuchMethodException ex) 
        {
            Logger.getLogger(PieceRenderer.class.getName()).
                    log(Level.SEVERE, null, ex);
        } 
        catch (SecurityException ex) 
        {
            Logger.getLogger(PieceRenderer.class.getName()).
                    log(Level.SEVERE, null, ex);
        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(PieceRenderer.class.getName()).
                    log(Level.SEVERE, null, ex);
        } 
        catch (IllegalAccessException ex) 
        {
            Logger.getLogger(PieceRenderer.class.getName()).
                    log(Level.SEVERE, null, ex);
        } 
        catch (IllegalArgumentException ex) 
        {
            Logger.getLogger(PieceRenderer.class.getName()).
                    log(Level.SEVERE, null, ex);
        } 
        catch (InvocationTargetException ex) 
        {
            Logger.getLogger(PieceRenderer.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sets the cell to image matching the value
     * @param value BoardSpot enum matching an image icon
     */
    public void setValue(Object value) 
    {
        BoardSpot spot = (BoardSpot) value;
        setIcon(images.get(spot));
    }
}
