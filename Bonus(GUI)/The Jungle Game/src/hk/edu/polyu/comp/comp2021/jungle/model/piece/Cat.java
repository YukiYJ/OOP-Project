package hk.edu.polyu.comp.comp2021.jungle.model.piece;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;

public class Cat extends Piece {

    public Cat(String position, Player player) { super(2, position, player); }

    public String name(){
        return "Cat";
    }

}
