package hk.edu.polyu.comp.comp2021.jungle.model.piece;

public class Lion extends Piece {
    public Lion(String position, char player) { super(7, position, player); }

    @Override
    public boolean canJumpRiver() { return true; }
}
