package hk.edu.polyu.comp.comp2021.jungle.model.piece;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;
/**
 * subclass of piece
 */
public class Elephant extends Piece {

    /**
     *
     * @param position of the Elephant
     * @param player the owner of this Elephant piece
     */
    public Elephant(String position, Player player) { super(8, position, player); }


    @Override
    public boolean canCapture(Piece otherPiece) {
        return !(otherPiece instanceof Rat);
    }

    @Override
    public String name(){
        return "Elephant";
    }
}
