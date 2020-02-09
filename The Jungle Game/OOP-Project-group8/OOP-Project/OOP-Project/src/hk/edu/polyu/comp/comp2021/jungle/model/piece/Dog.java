package hk.edu.polyu.comp.comp2021.jungle.model.piece;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;

/**
 * subclass of piece
 */
public class Dog extends Piece {
    /**
     *
     * @param position of the Dog
     * @param player the owner of this dog piece
     */
    public Dog(String position, Player player) { super(3, position, player); }

    @Override
    public String name(){
        return "Dog";
    }
}
