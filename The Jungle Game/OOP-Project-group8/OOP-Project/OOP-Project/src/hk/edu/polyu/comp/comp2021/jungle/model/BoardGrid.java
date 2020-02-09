package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.model.piece.Piece;

/**
 * class for the board grid
 */
public class BoardGrid {

    private final int rowIndex;
    private final int colIndex;
    private Piece piece;

    /**
     * constructor
     *
     * @param rowIndex of the board grid
     * @param colIndex of the board grid
     */
    public BoardGrid(int rowIndex, int colIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    /**
     *
     * @return the piece on this grid
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     *
     * @return the string position of this board grid
     */
    private String getPosition(){
        return Character.toString((char)(this.colIndex+'A'))+ Integer.toString(this.rowIndex+1);
    }

    /**
     *
     * @param piece set this to this position
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
        if (this.piece != null) {
            piece.setPosition(this.getPosition());
        }
    }

    /**
     *
     * @return judge whether it is the river
     */
    public boolean isRiver() {
        return ((rowIndex > 2 && rowIndex < 6) &&
                (colIndex == 1 || colIndex == 2 || colIndex == 4 || colIndex == 5));
    }

    /**
     *
     * @param player player's trap
     * @return whether it is my trap
     */
    public boolean isTrap(Player player) {
        if (((rowIndex == 0 && (colIndex == 2 || colIndex == 4)) || (rowIndex == 1 && colIndex == 3)) && player.getMark() == 'Y') {
            return true;
        } else return (((rowIndex == 8 && (colIndex == 2 || colIndex == 4)) || (rowIndex == 7 && colIndex == 3)) && player.getMark() == 'X');
    }
    /**
     *
     * @param player player's den
     * @return whether it is my den
     */

    public boolean isDen(Player player) {
        return ((colIndex == 3 && rowIndex == 0 && player.getMark() == 'Y')
                || (colIndex == 3 && rowIndex == 8 && player.getMark() == 'X'));
    }

    /**
     *
     * @return whether it is a den, no matter whose
     */
    public boolean isDen() {
        return ((colIndex == 3 && rowIndex == 0) || (colIndex == 3 && rowIndex == 8));
    }

    /**
     *
     * @return whether it is a trap, no matter whose
     */
    public boolean isTrap() {
        if (((rowIndex == 0 && (colIndex == 2 || colIndex == 4)) || (rowIndex == 1 && colIndex == 3))) {
            return true;
        } else return (((rowIndex == 8 && (colIndex == 2 || colIndex == 4)) || (rowIndex == 7 && colIndex == 3)));
    }
}
