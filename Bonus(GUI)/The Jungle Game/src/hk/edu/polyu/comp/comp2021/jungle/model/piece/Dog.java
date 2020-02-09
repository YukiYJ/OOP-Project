package hk.edu.polyu.comp.comp2021.jungle.model.piece;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;

public class Dog extends Piece {
    public Dog(String position, Player player) { super(3, position, player); }

    public String name(){
        return "Dog";
    }
}
