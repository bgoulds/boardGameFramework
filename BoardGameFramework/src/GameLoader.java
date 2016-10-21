
import framework.GameConsole;
import framework.GameController;
import framework.GameGUIView;
import framework.GameModel;
import framework.GameTableModel;
import framework.GameView;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * Driver for the board game, command line arguments should come in the form of
 * -g GameName, or -c if console is desired
 * @author BrianGouldsberry
 */
public class GameLoader 
{
    /**
     * Main method for the game
     * @param args first should be the view flag (-g for gui, -c for console)
     * and the second arg should be the name of the game in uppercase
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException 
     */
    public static void main(String[] args) throws ClassNotFoundException, 
            InstantiationException, IllegalAccessException, 
            NoSuchMethodException, InvocationTargetException
    {
        String game = args[0];
        boolean isGui = args[1].equals("-g");
        Class modelClass = Class.forName(game.toLowerCase() + "." + game
                + "TableModel");
        GameModel model = new GameModel(
                (GameTableModel) modelClass.newInstance(),
                "Click on a cell to make a move!");
        GameController cont = new GameController(model);
        Class listenerClass = Class.forName(game.toLowerCase() + "." + game
                + "Listener");
        Constructor constructor = listenerClass.getConstructor(
                new Class[]{GameModel.class});
        ActionListener listener = (ActionListener) 
                constructor.newInstance(model);
        GameView view;
        //SET the gui based off of the flag
        if (isGui)
        {
            view = new GameGUIView(listener, game, model);
        }
        else
        {
            view = new GameConsole(listener, game, model);
        }
        view.setController(cont);
        view.setUpBoard();
        cont.setTable(view.getTable());
        model.addObserver(view);
        view.setVisible(true);
        view.update(model, null);
    }
}
