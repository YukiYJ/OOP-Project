package hk.edu.polyu.comp.comp2021.jungle.model.piece;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;

/**
 * class for piece
 */

public abstract class Piece {

    private final int rank;
    private String position;
    private boolean status;
    private final Player player; // the player (1 or 2) that the piece belongs to

    /**
     * constructor
     * @param rank the rank of the piece
     * @param player the people who piece belongs to
     * @param position the position of the piece
     */
    public Piece(int rank, String position, Player player) {
        this.rank = rank;
        this.position = position;
        this.status = true;
        this.player = player;
    }

    /**
     *
     * @return whether this piece is in the river
     */
    public boolean isInTheRiver() {return false;}

    /**
     *
     * @return the rank of this piece
     */
    public int getRank() { return rank; }

    /**
     *
     * @return the position of this piece
     */

    public String getPosition() { return position; }

    /**
     *
     * @return the col index of this piece
     */
    public int getColIdx() {
        // convert the capital letter to corresponding row index
        return position.charAt(0) - 'A';
    }

    /**
     *
     * @return the row index of this piece
     */
    public int getRowIdx() {
        // convert the number from type char to int
        return position.charAt(1) - '0' -1;
    }

    /**
     *
     * @return the status of this piece
     */
    public boolean getStatus() {
        return this.status; }

    /**
      * @return the owner of this piece
     */
    public Player getPlayer() { return player; }


    /**
     * change the status of this piece if being catched
     */
    public void setIsCaptured() {
        this.status=false; }

    /**
     * @param position change the position to new place
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return nothing as an abstract
     */
    public abstract String name();

    /**
     *
     * @param otherPiece another piece
     * @return whether this can capture
     */
    public boolean canCapture(Piece otherPiece) { return this.rank >= otherPiece.getRank(); }

    /**
     *
     * @return whether this piece can swim
     */
    public boolean canSwim() { return false;}

    /**
     *
     * @return whether this piece can jump river
     */
    public boolean canJumpRiver() { return false; }


}
