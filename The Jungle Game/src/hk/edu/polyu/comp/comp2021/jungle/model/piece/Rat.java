package hk.edu.polyu.comp.comp2021.jungle.model.piece;

public class Rat extends Piece {
    public Rat(String position, char player) { super(1, position, player); }

    @Override
    public boolean canCapture(Piece otherPiece) {
        return (otherPiece instanceof Rat || otherPiece instanceof Elephant);
    }

    @Override
    public boolean canSwim() {
        return true;
    }
}
