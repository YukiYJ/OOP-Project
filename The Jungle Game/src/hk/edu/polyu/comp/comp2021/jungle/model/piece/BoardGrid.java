package hk.edu.polyu.comp.comp2021.jungle.model.piece;

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

    public Piece getPiece() { return piece; }


    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    //...
}
