package hk.edu.polyu.comp.comp2021.jungle.model.piece;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;

/**
 * subclass of piece
 */

public class Rat extends Piece {
    /**
     *
     * @param position of the Rat
     * @param player the owner of this rat piece
     */
    public Rat(String position, Player player) { super(1, position, player); }

    @Override
    public String name(){
        return "Rat";
    }

    @Override
    public boolean canCapture(Piece otherPiece) {
        return (otherPiece instanceof Rat || otherPiece instanceof Elephant);
    }

    @Override
    public boolean canSwim() {
        return true;
    }

    @Override
    public boolean isInTheRiver(){
        if(!this.getStatus()){
            return false;
        }
        int row = this.getRowIdx();
        int col = this.getColIdx();
        return ((row > 2 && row < 6) && (col == 1 || col == 2 || col == 4 || col == 5));
    }
}