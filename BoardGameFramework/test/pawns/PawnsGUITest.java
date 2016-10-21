
package pawns;

import framework.GameController;
import framework.GameGUIView;
import framework.GameModel;
import framework.GameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import junit.framework.TestCase;
import org.uispec4j.Key;
import org.uispec4j.MenuBar;
import org.uispec4j.MenuItem;
import org.uispec4j.Table;
import org.uispec4j.Window;

/**
 *
 * @author BrianGouldsberry
 */
public class PawnsGUITest extends TestCase {
    GameModel model;
    ActionListener listener;
    GameView view;
    
    public PawnsGUITest(String testName) {
        super(testName);
        String game = "Pawns";
        GameModel model = new GameModel(new PawnsTableModel(), "Click on a cell to make a move!");
        this.model = model;
        GameController cont = new GameController(model);
        ActionListener listener = new PawnsListener(model);
        this.listener = listener;
        GameView view = new GameGUIView(listener, game, model);
        this.view = view;
        view.setController(cont);
        view.setUpBoard();
        cont.setTable(view.getTable());
        model.addObserver(view);
        view.setVisible(true);
        view.update(model, null);
    }

    /**
     * Test of setSize method, of class PawnsTableModel.
     */
    public void testSystem() throws InterruptedException 
    {
        Window win = new Window((GameGUIView)view);
        MenuBar bar = win.getMenuBar();
        MenuItem menu;
        MenuItem item;
                
        //Black wins and capture left
        Table table = win.getTable();
        Thread.sleep(1000);
        table.click(1, 2);
        Thread.sleep(1000);
        table.click(2, 2);
        Thread.sleep(1000);
        table.click(3, 2);
        Thread.sleep(1000);
        table.click(4, 2);
        Thread.sleep(1000);
        table.click(5, 2);
        Thread.sleep(1000);
        table.click(6, 1);
        Thread.sleep(3500);
        
        //white wins and capture right
        menu = bar.getMenu("Game");
        item = menu.getSubMenu("New Game");
        item.click();
        Thread.sleep(1000);
        table.click(6, 2);
        Thread.sleep(1000);
        table.click(5, 2);
        Thread.sleep(1000);
        table.click(4, 2);
        Thread.sleep(1000);
        table.click(3, 2);
        Thread.sleep(1000);
        table.rightClick(2, 2);
        Thread.sleep(1000);
        table.click(1, 3);
        Thread.sleep(3500);
        
        //Invalid mode and undo
        item.click();
        Thread.sleep(1000);
        table.click(1, 4);
        Thread.sleep(1000);
        table.click(2, 4);
        Thread.sleep(1000);
        table.click(6, 5);
        Thread.sleep(1000);
        table.click(5, 5);
        Thread.sleep(1000);
        table.click(4, 5);
        Thread.sleep(1000);
        table.click(6, 4);
        Thread.sleep(1000);
        table.click(5, 4);
        Thread.sleep(1000);
        table.click(4, 4);
        Thread.sleep(2000);
        table.click(1, 0);
        Thread.sleep(1000);
        menu = bar.getMenu("Preferences");
        item = menu.getSubMenu("Undo");
        item = item.getSubMenu("Undo");
        item.click();
        Thread.sleep(2000);
        item = menu.getSubMenu("Board Size");
        item = item.getSubMenu("Large");
        item.click();
        Thread.sleep(2000);
        item = menu.getSubMenu("Board Size");
        item = item.getSubMenu("Small");
        item.click();
        Thread.sleep(2000);
        item = menu.getSubMenu("Skin");
        item = item.getSubMenu("Classic");
        item.click();
        Thread.sleep(2000);
        item = menu.getSubMenu("Skin");
        item = item.getSubMenu("Night");
        item.click();
        Thread.sleep(3500);
    }
}
