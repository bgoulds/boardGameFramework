
package hurkle;

import framework.GameModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for Hurkle Menu Actions
 * @author BrianGouldsberry
 */
public class HurkleListener implements ActionListener
{
    private final int k_SMALL = 5, k_MEDIUM = 9, k_LARGE = 11;
    
    private GameModel model;
    
    /**
     * Creates a new listener and sets the model
     * @param model the game model that represents the UI
     */
    public HurkleListener(GameModel model)
    {
        this.model = model;
    }

    /**
     * Modifies the model based upon the action command, supported actions:
     * Quit, New Game, Board Size-Small, Board Size-Medium, Board Size-Large,
     * Skin-Default, Skin-Classic, Skin-Night, Cheat-Cheat
     * @param e the event found
     */
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String command = e.getActionCommand();
//        HurkleTableModel tm = (HurkleTableModel) model.getTableModel();
        //Performs actions based upon the actionCommand
        switch (command)
        {
            case "Quit": 
                System.exit(1);
                break;
            case "New Game":
                model.newGame();
                break;
            case "Board Size-Small":
                model.getTableModel().setSize(k_SMALL);
                model.getTableModel().newGame();
                break;
            case "Board Size-Medium":
                model.getTableModel().setSize(k_MEDIUM);
                model.newGame();
                break;
            case "Board Size-Large":
                model.getTableModel().setSize(k_LARGE);
                model.newGame();
                break;
            case "Skin-Default":
                model.changeTheme("Default");
                model.newGame();
                break;
            case "Skin-Classic":
                model.changeTheme("Classic");
                model.newGame();
                break;
            case "Skin-Night":
                model.changeTheme("Night");
                model.newGame();
                break;
            case "Cheat-Cheat":
                model.getTableModel().newGame();
                ((HurkleTableModel)model.getTableModel()).setHurkle(1, 1);
                break;
            default:
                break;
        }
        model.notifyObservers();
    }

}
