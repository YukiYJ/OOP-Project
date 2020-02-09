package hk.edu.polyu.comp.comp2021.jungle.model.piece;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;

public class Leopard extends Piece {
    public Leopard(String position, Player player) { super(5, position, player); }

    public String name(){
        return "Leopard";
    }

}

