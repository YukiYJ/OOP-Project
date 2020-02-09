package hk.edu.polyu.comp.comp2021.jungle.model.piece;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;

/**
 * subclass of piece
 */
public class Cat extends Piece {
    /**
     *
     * @param position of the Cat
     * @param player the owner of this cat piece
     */
    public Cat(String position, Player player) { super(2, position, player); }

    @Override
    public String name(){
        return "Cat";
    }

}

