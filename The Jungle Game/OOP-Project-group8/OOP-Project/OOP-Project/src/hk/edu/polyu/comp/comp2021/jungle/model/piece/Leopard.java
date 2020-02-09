package hk.edu.polyu.comp.comp2021.jungle.model.piece;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;
/**
 * subclass of piece
 */
public class Leopard extends Piece {
    /**
     *
     * @param position of the Leopard
     * @param player the owner of this Leopard piece
     */
    public Leopard(String position, Player player) { super(5, position, player); }

    @Override
    public String name(){
        return "Leopard";
    }
}

