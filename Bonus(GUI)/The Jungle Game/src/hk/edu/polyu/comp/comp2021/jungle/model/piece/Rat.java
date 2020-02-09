package hk.edu.polyu.comp.comp2021.jungle.model.piece;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;

public class Rat extends Piece {
    public Rat(String position, Player player) { super(1, position, player); }

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

    public boolean isInTheRiver(){
        int row = this.getRowIdx();
        int col = this.getColIdx();

        if ((row > 2 && row < 6) && (col == 1 || col == 2 || col == 4 || col == 5)) return true;
        else return false;
    }
}