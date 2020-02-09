package hk.edu.polyu.comp.comp2021.jungle;

import hk.edu.polyu.comp.comp2021.jungle.model.*;
import hk.edu.polyu.comp.comp2021.jungle.view.Console;


/**
 * @author group8
 *
 */
public class Application {
    /**
     * controller: initialize and run the game
     * @param args start the game
     */
    public static void main(String[] args){

        JungleGame game = new JungleGame();
        Console cons = new Console(game);
        cons.run();
    }
}