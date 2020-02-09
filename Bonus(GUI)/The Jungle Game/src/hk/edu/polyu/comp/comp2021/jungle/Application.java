package hk.edu.polyu.comp.comp2021.jungle;

import hk.edu.polyu.comp.comp2021.jungle.view.Gui;
import javax.swing.*;


public class Application {

    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Gui.initGui();
            }
        });

    }
}