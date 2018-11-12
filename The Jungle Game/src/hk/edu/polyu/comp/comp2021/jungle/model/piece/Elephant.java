package hk.edu.polyu.comp.comp2021.jungle.model.piece;

public class Elephant extends Piece {
    public Elephant(String position, char player) { super(8, position, player); }

    @Override
    public boolean canCapture(Piece otherPiece) {
        return(otherPiece instanceof Rat) ? false : true;
    }
}
