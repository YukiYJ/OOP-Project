package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.model.piece.Piece;

public class BoardGrid {

    private int rowIndex;
    private int colIndex;
    private Piece piece;

    public BoardGrid(int rowIndex, int colIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        piece = null;
    }

    public int getRowIndex() { return rowIndex; }

    public int getColIndex() { return colIndex; }

    public Piece getPiece() {
        return piece;
    }

    public String getPosition(){
        return Character.toString((char)(this.colIndex+'A'))+ Integer.toString(9-this.rowIndex);
    }

    public void setPiece(Piece piece) {
        if(piece==null){
            this.piece=null;
        }
        else{
            this.piece = piece;
            piece.setPosition(this.getPosition());
        }
    }

    public boolean isRiver() {
        if ((rowIndex > 2 && rowIndex < 6) && (colIndex == 1 || colIndex == 2 || colIndex == 4 || colIndex == 5))
            return true;
        else return false;
    }

    public boolean myTrap(Player player) {
        if (((rowIndex == 0 && (colIndex == 2 || colIndex == 4)) || (rowIndex == 1 && colIndex == 3)) && player.getMark() == 'Y') {
            return true;
        } else if (((rowIndex == 8 && (colIndex == 2 || colIndex == 4)) || (rowIndex == 7 && colIndex == 3)) && player.getMark() == 'X') {
            return true;
        } else return false;
    }

    public boolean myDen(Player player) {
        if ((colIndex == 3 && rowIndex == 0 && player.getMark() == 'Y')
         || (colIndex == 3 && rowIndex == 8 && player.getMark() == 'X')) {
            return true;
        } else return false;
    }

    public boolean isDen() {
        if ((colIndex == 3 && rowIndex == 0) || (colIndex == 3 && rowIndex == 8)) {
            return true;
        }
        return false;
    }

    public boolean isTrap() {
        if (((rowIndex == 0 && (colIndex == 2 || colIndex == 4)) || (rowIndex == 1 && colIndex == 3))) {
            return true;
        } else if (((rowIndex == 8 && (colIndex == 2 || colIndex == 4)) || (rowIndex == 7 && colIndex == 3))) {
            return true;
        }
        return false;
    }
}
