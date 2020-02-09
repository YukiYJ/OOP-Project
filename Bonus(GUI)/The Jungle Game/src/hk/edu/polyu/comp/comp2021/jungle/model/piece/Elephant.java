package hk.edu.polyu.comp.comp2021.jungle.model.piece;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;

public class Elephant extends Piece {
    public Elephant(String position, Player player) { super(8, position, player); }


    @Override
    public boolean canCapture(Piece otherPiece) {
        return(otherPiece instanceof Rat) ? false : true;
    }
    public String name(){
        return "Elephant";
    }
}
