package hk.edu.polyu.comp.comp2021.jungle.model.piece;

public class Tiger extends Piece {
    public Tiger(String position, char player) { super(6, position, player); }

    @Override
    public boolean canJumpRiver() { return true; }
}
