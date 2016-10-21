
package framework;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.JComponent;

/**
 * A JTable with a customizable background
 * @author BrianGouldsberry
 */
public class GameTable extends JTable
{
    private Image img;
    private String theme, game;
    
    /**
     * Creates a new table and sets the background image based upon the game and
     * theme
     * @param model specified model to be used
     * @param game name of the game being used
     * @param theme name of the theme being used
     */
    GameTable(GameTableModel model, String game, String theme) 
    {
        super(model);
        this.game = game;
        this.theme = theme;
        try 
        {
            File file = new File("images/" + game + "/" + theme 
                    + "/background.jpg");
            img = ImageIO.read(file);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(GameTable.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Sets the cells and table to be opaque, called automatically
     * @param renderer renderer being used
     * @param row row of the cell as an int
     * @param column column of the cell as an int
     * @return the cell
     */
    public Component prepareRenderer(TableCellRenderer renderer, int row,
            int column)
    {
        Component component = super.prepareRenderer(renderer, row, column);
        // We want renderer component to be
        // transparent so background image is visible
        if (component instanceof JComponent)
        {
            ((JComponent) component).setOpaque(false);
        }
        setOpaque(false);
        return component;
    }

    /**
     * Override paint so as to show the table background
     * @param gfx graphics being used to paint
     */
    public void paint(Graphics gfx)
    {
        // paint an image in the table background
        if (img != null)
        {
            gfx.drawImage(img, 0, 0, null, null);
        }
        // Now let the paint do its usual work
        super.paint(gfx);
    }

    /**
     * Changes background image based upon the theme
     * @param theme name of the directory that the theme belongs in
     */
    public void setTheme(String theme)
    {
        this.theme = theme;
        try 
        {
            File file = new File("images/" + game + "/" + theme
                    + "/background.jpg");
            img = ImageIO.read(file);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(GameTable.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

}
