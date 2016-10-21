
package framework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import org.ini4j.Ini;
import org.ini4j.Profile;

/**
 * The console UI equivalent to GameGUIView
 * @author BrianGouldsberry
 */
public class GameConsole implements GameView
{
    protected ActionListener listener;
    private String game, preferences = "";
    private GameModel model;
    private HashMap<String, String> actionCommands;
    
    /**
     * Sets up the consoles parameters and loads the games preferences into the
     * map of action commands
     * @param listener the action listener to pass commands to
     * @param game String representing which game the framework should load
     * @param model the model to be used to validate moves
     */
    public GameConsole(ActionListener listener, String game, GameModel model)
    {
        this.listener = listener;
        this.game = game;
        this.model = model;
        actionCommands = new HashMap<String, String>();
        actionCommands.put("1", "New Game");
        actionCommands.put("3", "Quit");
        loadPreferences();

    }
    
    /**
     * Calls update and prints out the contents of the model if true
     * @param state whether or not to update
     */
    @Override
    public void setVisible(boolean state) 
    {
        //UPDATE if true
        if (true)
        {
            update(model, null);
        }
    }

    /**
     * The console doesn't use a controller, so this does nothing
     * @param cont the game controller
     */
    @Override
    public void setController(GameController cont) 
    {
    }

    /**
     * Prints out the game board as well as status and preferences menu and then
     * waits for a response. 
     * @param obs the model
     * @param obj not used
     */
    @Override
    public void update(Observable obs, Object obj) 
    {
        //PRINT if theres a game model
        if (obs instanceof GameModel)
        {
            GameModel gamemodel = (GameModel) obs;
            System.out.println("-------" + game + "-------");
            System.out.println(gamemodel.getStatus());
            BoardSpot[][] board = gamemodel.getTableModel().getBoard();
            String border = "   ";
            //PRINT out the column numbers
            for (int colNum = 0; colNum < board.length; colNum++)
            {
                border += colNum + " ";
            }
            System.out.println(border);
            char rowChar = 'A';
            //PRINT out the ascii values of the board as well as column values
            for (BoardSpot[] row : board)
            {
                System.out.print(rowChar++ + ": ");
                //PRINT out ascii values
                for (BoardSpot spot: row)
                {
                    System.out.print(spot.toAscii() + " ");
                }
                System.out.println();
            }
            System.out.println("1)New 2)Prefs 3)Quit ");
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            //IF input is a move, then validate it
            if (input.length() > 1)
            {
                gamemodel.validateMove(input.charAt(0) - 'A',
                        input.charAt(1) - '0', true);
            }
            //ELSE perform an action from the menu
            else
            {
                //PRINT out preferences menu and get a second input
                if (input.equals("2"))
                {
                    System.out.println(preferences);
                    input = scan.nextLine();
                }
                ActionEvent event = new ActionEvent("blah", 0,
                        actionCommands.get(input));
                listener.actionPerformed(event);
            }
        }
    }

    /**
     * Sets the preferences menu string and also sets up the hashmap of input to
     * action commands
     */
    @Override
    public void loadPreferences() 
    {
        try 
        {
            char inputVal = 'A';
            Ini ini = new Ini(new File("config/" + game + ".ini"));
            //FOR each section
            for (String sectionName: ini.keySet()) 
            {
                Profile.Section section = ini.get(sectionName);
                //ADD the section and option to preferences along with the map
                for (String optionKey: section.keySet()) 
                {
                    preferences += inputVal + ")" + sectionName + "-" + 
                            optionKey + " ";
                    actionCommands.put(inputVal + "", sectionName + "-" + 
                            optionKey);
                    inputVal += 1;
                }
            }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(GameGUIView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Invalid for console
     */
    @Override
    public void setUpBoard() 
    {
       
    }

    /**
     * Returns the JTable associated with the view
     * @return null because there is no table
     */
    @Override
    public JTable getTable() 
    {
        return null;
    }

    
    /**
     * Sets the action listener for the console
     * @param listener actionListener to use
     */
    @Override
    public void setListener(ActionListener listener) 
    {
        this.listener = listener;
    }

}
