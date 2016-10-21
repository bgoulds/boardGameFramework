
package hurkle;

import framework.GameController;
import framework.GameGUIView;
import framework.GameModel;
import framework.GameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import junit.framework.TestCase;
import org.uispec4j.*;

/**
 *
 * @author BrianGouldsberry
 */
public class HurkleGUITest extends TestCase {
    GameModel model;
    ActionListener listener;
    GameView view;
    
    public HurkleGUITest(String testName) {
        super(testName);
        String game = "Hurkle";
        GameModel model = new GameModel(new HurkleTableModel(), "Click on a cell to make a move!");
        this.model = model;
        GameController cont = new GameController(model);
        ActionListener listener = new HurkleListener(model);
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
     * Test of loadPreferences method, of class GameGUIView.
     */
    public void testSystem() throws InterruptedException 
    {
        Window win = new Window((GameGUIView)view);
        Table table = win.getTable();
        MenuBar bar = win.getMenuBar();
        MenuItem menu;
        MenuItem item;
        
        menu = bar.getMenu("Preferences");
        item = menu.getSubMenu("Cheat").getSubMenu("Cheat");
        item.click();
        Thread.sleep(100);
        table.click(0, 0);
        Thread.sleep(1000);
        table.click(0, 1);
        Thread.sleep(1000);
        table.click(0, 2);
        Thread.sleep(1000);
        
        Thread.sleep(100);
        table.click(1, 0);
        Thread.sleep(1000);
        table.click(1, 2);
        Thread.sleep(1000);
        
        Thread.sleep(100);
        table.click(2, 0);
        Thread.sleep(1000);
        table.click(2, 1);
        Thread.sleep(1000);
        table.click(2, 2);
        Thread.sleep(1000);
        table.click(1, 1);
        Thread.sleep(1000);
        
        item = menu.getSubMenu("Skin").getSubMenu("Classic");
        item.click();
        Thread.sleep(2500);
        item = menu.getSubMenu("Skin").getSubMenu("Night");
        item.click();
        Thread.sleep(2500);
        item = menu.getSubMenu("Board Size").getSubMenu("Small");
        item.click();
        Thread.sleep(2500);
        item = menu.getSubMenu("Board Size").getSubMenu("Large");
        item.click();
        Thread.sleep(2500);
        
        table.click(0, 0);
        Thread.sleep(1000);
        table.click(0, 1);
        Thread.sleep(1000);
        table.click(0, 2);
        Thread.sleep(1000);
        table.click(1, 0);
        Thread.sleep(1000);
        table.click(1, 1);
        Thread.sleep(2000);
    }
}
