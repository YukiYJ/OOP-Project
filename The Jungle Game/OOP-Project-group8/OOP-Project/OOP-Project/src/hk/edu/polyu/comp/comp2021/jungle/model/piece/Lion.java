package hk.edu.polyu.comp.comp2021.jungle.model.piece;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;

/**
 * subclass of piece
 */
public class Lion extends Piece {
    /**
     *
     * @param position of the Lion
     * @param player the owner of this lion piece
     */
    public Lion(String position, Player player) { super(7, position, player); }

    @Override
    public String name(){
        return "Lion";
    }

    @Override
    public boolean canJumpRiver() { return true; }
}
