package hk.edu.polyu.comp.comp2021.jungle.model.piece;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;

/**
 * subclass of piece
 */

public class Wolf extends Piece {
    /**
     *
     * @param position of the Wolf
     * @param player the owner of this wolf piece
     */
    public Wolf(String position, Player player) { super(4, position, player); }

    @Override
    public String name(){
        return "Wolf";
    }
}
