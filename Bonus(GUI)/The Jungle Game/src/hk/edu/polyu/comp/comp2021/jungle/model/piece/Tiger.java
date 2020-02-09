package hk.edu.polyu.comp.comp2021.jungle.model.piece;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;

public class Tiger extends Piece {
    public Tiger(String position, Player player) { super(6, position, player); }

    @Override
    public boolean canJumpRiver() { return true; }

    public String name(){
        return "Tiger";
    }
}
