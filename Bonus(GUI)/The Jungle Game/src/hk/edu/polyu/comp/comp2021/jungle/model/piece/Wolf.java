package hk.edu.polyu.comp.comp2021.jungle.model.piece;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;

public class Wolf extends Piece {
    public Wolf(String position, Player player) { super(4, position, player); }

    public String name(){
        return "Wolf";
    }
}
