package hk.edu.polyu.comp.comp2021.jungle.model.piece;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;
/**
 * subclass of piece
 */

public class Tiger extends Piece {
    /**
     *
     * @param position of the Tiger
     * @param player the owner of this tiger piece
     */
    public Tiger(String position, Player player) { super(6, position, player); }

    @Override
    public boolean canJumpRiver() { return true; }

    @Override
    public String name(){
        return "Tiger";
    }
}
