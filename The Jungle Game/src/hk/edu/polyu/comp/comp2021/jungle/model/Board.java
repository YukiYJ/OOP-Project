package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.model.piece.BoardGrid;

public class Board {

    static final int NUMBER_OF_ROW = 9;
    static final int NUMBER_OF_COLUMN = 7;
    private BoardGrid[][] board;

    public Board() {
        board =  new BoardGrid[NUMBER_OF_ROW][NUMBER_OF_COLUMN];
    }

    public BoardGrid[][] getBoard() {
        return board;
    }

    //...
}
