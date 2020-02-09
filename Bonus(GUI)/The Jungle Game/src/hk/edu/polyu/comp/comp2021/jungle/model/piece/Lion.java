package hk.edu.polyu.comp.comp2021.jungle.model.piece;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;

public class Lion extends Piece {
    public Lion(String position, Player player) { super(7, position, player); }

    public String name(){
        return "Lion";
    }

    @Override
    public boolean canJumpRiver() { return true; }
}
