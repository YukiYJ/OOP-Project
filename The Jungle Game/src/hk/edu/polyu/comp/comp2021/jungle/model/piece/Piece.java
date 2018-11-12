package hk.edu.polyu.comp.comp2021.jungle.model.piece;

public class Piece {

    private int rank;
    private String position;
    private boolean status;
    private char player; // the player (1 or 2) that the piece belongs to

    public Piece(int rank, String position, char player) {
        this.rank = rank;
        this.position = position;
        this.status = true;
        this.player = player;
    }

    public int getRank() { return rank; }

    public String getPosition() { return position; }

    public int getColIdx() {
        // convert the capital letter to corresponding row index
        return position.charAt(0) - 'A';
    }

    public int getRowIdx() {
        // convert the number from type char to int
        return position.charAt(1) - '0';
    }

    public boolean getStatus() { return status; }

    public char getPlayer() { return player; }


    // set status
    public void setIsCaptured() { status = false; }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean canCapture(Piece otherPiece) { return this.rank >= otherPiece.rank; }

    public boolean canSwim() { return false; }

    public boolean canJumpRiver() { return false; }


    // ...
}
