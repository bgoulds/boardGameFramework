
package pawns;

import framework.GameModel;
import hurkle.HurkleTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action Listener to perform menu actions for pawns
 * @author BrianGouldsberry
 */
public class PawnsListener implements ActionListener
{
    private final int k_SMALL = 5, k_MEDIUM = 8, k_LARGE = 10;
    
    private GameModel model;
    
    /**
     * makes a new pawnsListener with the given model
     * @param model model to manipulate
     */
    public PawnsListener(GameModel model)
    {
        this.model = model;
    }

    /**
     * Modifies the model based upon the action command, supported actions:
     * Quit, New Game, Board Size-Small, Board Size-Medium, Board Size-Large,
     * Skin-Default, Skin-Classic, Skin-Night, Undo-Undo
     * @param e the event found
     */
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String command = e.getActionCommand();
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
                model.newGame();
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
                break;
            case "Skin-Classic":
                model.changeTheme("Classic");
                break;
            case "Skin-Night":
                model.changeTheme("Night");
                break;
            case "Undo-Undo":
                ((PawnsTableModel)model.getTableModel()).undo();
                break;
            default:
                break;
        }
        model.notifyObservers();
    }

}