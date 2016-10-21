
package framework;

import pawns.PawnsEnum;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;
import org.ini4j.Wini;

/**
 * The GUI representation of the game framework, contains a MenuBar with options
 * loaded from the game's ini file, a status bar to display messages, and a 
 * JTable containing game pieces
 * @author BrianGouldsberry
 */
public class GameGUIView extends JFrame implements GameView
{
    protected JMenuBar menu;
    protected JMenu gameMenu;
    protected JMenu preferencesMenu;
    protected JPanel contentPane;
    protected JLabel statusLabel;
    protected GameController controller;
    protected JTable table;
    
    protected ActionListener listener;
    private String game, theme = "Default";

    
    /**
     * Stes up the gui with the correct title, menu, and table contents
     * @param listener action listener used to call menu bar events
     * @param game string containing the game name
     * @param model model to be used for the table
     */
    public GameGUIView(ActionListener listener, String game, GameModel model)
    {
        this.listener = listener;
        this.game = game;
        setUpMenu();
        setUpContents();
        this.setTitle(game + " - Brian Gouldsberry");
    }
    
    private void setUpMenu()
    {
        menu = new JMenuBar();
        menu.setName("menu");
        gameMenu = new JMenu("Game");

        JMenuItem quit = new JMenuItem("Quit");
        quit.setActionCommand("Quit");
        quit.addActionListener(listener);
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.setActionCommand("New Game");
        newGame.addActionListener(listener);

        gameMenu.add(newGame);
        gameMenu.add(quit);


        preferencesMenu = new JMenu("Preferences");

        menu.add(gameMenu);
        menu.add(preferencesMenu);
        loadPreferences();

        this.setJMenuBar(menu);
    }

    private void setUpContents() 
    {
        contentPane = new JPanel();
        contentPane.setBackground(new Color(192, 192, 192));
        contentPane.setLayout(new BorderLayout());
        
        statusLabel = new JLabel();
        statusLabel.setBackground(new Color(214, 217, 223));
        statusLabel.setForeground(new Color(0, 0, 0));
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        statusLabel.setVerticalAlignment(JLabel.TOP);
        statusLabel.setEnabled(true);
        statusLabel.setFont(new Font("DejaVu Sans", 0, 12));
        statusLabel.setVisible(true);
        
        contentPane.add(statusLabel, BorderLayout.NORTH);
        contentPane.setVisible(true);
        this.add(contentPane);
    }
    
    /**
     * Loads the games ini file to populate the menu with additional options
     */
    @Override
    public void loadPreferences() 
    {
        try 
        {
            Ini ini = new Ini(new File("config/" + game + ".ini"));
            //FOR each section
            for (String sectionName: ini.keySet()) 
            {
                JMenu sectionMenu = new JMenu(sectionName);
                Section section = ini.get(sectionName);
                //ADD the section and option as a menu item/set the listener
                for (String optionKey: section.keySet()) 
                {
                    JMenuItem option = new JMenuItem(optionKey);
                    option.setActionCommand(sectionName + "-" + optionKey);
                    option.addActionListener(listener);
                    sectionMenu.add(option);
                }
                preferencesMenu.add(sectionMenu);
            }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(GameGUIView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sets the status text and then changes the theme if necessary
     * @param obs the game model observable
     * @param obj not used
     */
    @Override
    public void update(Observable obs, Object obj) 
    {
        //IF the observable is a GameModel
        if (obs instanceof GameModel)
        {
            GameModel model = (GameModel) obs;
            statusLabel.setText(model.getStatus());
            //CHANGE the theme if necessary
            if (model.getTheme() != theme && model.getTheme() != null)
            {
                theme = model.getTheme();
                ((GameTable)table).setTheme(theme);
                table.repaint();
            }
        }
        this.pack();
    }
    
    /**
     * Sets the controller 
     * @param cont desired controller
     */
    @Override
    public void setController(GameController cont)
    {
        this.controller = cont;
    }
    
    /**
     * Sets the listener
     * @param list desired listener
     */
    @Override
    public void setListener(ActionListener list)
    {
        listener = list;
    }
    
    /**
     * Gets the JTable used by the GUI
     * @return the current table
     */
    public JTable getTable()
    {
        return table;
    }

    /**
     * Sets the Table up with the correct renderer and enum value
     */
    @Override
    public void setUpBoard() 
    {
        GameTableModel model = controller.getModel().getTableModel();
        table = new GameTable(model, game, theme);
        table.setRowHeight(63);  // height of icon
        Class enumClass = null;
        try 
        {
            enumClass = Class.forName(game.toLowerCase() + "." + game + "Enum");
        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(GameGUIView.class.getName()).log(Level.SEVERE, null, ex);
        }
        PieceRenderer render = new PieceRenderer(game);
        table.setDefaultRenderer(enumClass, render);
        controller.getModel().setTableCellRenderer(render);
        table.addMouseListener(controller);
        contentPane.add(table);
        this.pack();
    }
}
