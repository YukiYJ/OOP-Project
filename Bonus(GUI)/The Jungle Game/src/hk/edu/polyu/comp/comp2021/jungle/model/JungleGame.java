package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.model.piece.*;

public class JungleGame {

    static final int NUMBER_OF_ROW = 9;
    static final int NUMBER_OF_COLUMN = 7;
    private BoardGrid[][] board;
    private static int turn = 0;

    public Piece Rat1, Cat1, Dog1, Wolf1, Leopard1, Tiger1, Lion1, Elephant1;
    public Piece Rat2, Cat2, Dog2, Wolf2, Leopard2, Tiger2, Lion2, Elephant2;

    private Player player1, player2;

    public JungleGame() {
        turn=0;

        board = new BoardGrid[NUMBER_OF_ROW][NUMBER_OF_COLUMN];
        for (int i = 0; i < NUMBER_OF_ROW; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
                board[i][j] = new BoardGrid(i, j);
            }
        }
    }

    public int getTurn() { return turn; }

    public void changeTurn(){ turn = 1 - turn; }

    public void setPlayer1(String name) { this.player1 = new Player('X', name, 0);}
    public void setPlayer2(String name) { this.player2 = new Player('Y', name, 1);}
    public void setPlayer1(Player player1) { this.player1 = player1;}
    public void setPlayer2(Player player2) { this.player2 = player2;}


    public void openNew() {

        player1 = new Player('X', " ", 0);
        player2 = new Player('Y', " ", 1);

        Rat1 = new Rat("A7", player1); Rat2 = new Rat("G3", player2);
        Cat1 = new Cat("F8", player1); Cat2 = new Cat("B2", player2);
        Dog1 = new Dog("B8",player1); Dog2 = new Dog("F2", player2);
        Wolf1 = new Wolf("E7", player1); Wolf2 = new Wolf("C3", player2);
        Lion1 = new Lion("A9", player1); Lion2 = new Lion("G1", player2);
        Leopard1 = new Leopard("C7", player1); Leopard2 = new Leopard("E3", player2);
        Tiger1 = new Tiger("G9", player1); Tiger2 = new Tiger("A1", player2);
        Elephant1 = new Elephant("G7", player1); Elephant2 = new Elephant("A3", player2);

        board[2][6].setPiece(Rat2); board[6][0].setPiece(Rat1);
        board[1][1].setPiece(Cat2); board[7][5].setPiece(Cat1);
        board[1][5].setPiece(Dog2); board[7][1].setPiece(Dog1);
        board[2][2].setPiece(Wolf2); board[6][4].setPiece(Wolf1);
        board[0][6].setPiece(Lion2); board[8][0].setPiece(Lion1);
        board[2][4].setPiece(Leopard2); board[6][2].setPiece(Leopard1);
        board[0][0].setPiece(Tiger2); board[8][6].setPiece(Tiger1);
        board[2][0].setPiece(Elephant2); board[6][6].setPiece(Elephant1);
    }


    public boolean move(String oldPosition, String newPosition) {
        int oldCol = oldPosition.charAt(0) - 'A';
        int oldRow = oldPosition.charAt(1) - '0' - 1;
        Piece piece = board[oldRow][oldCol].getPiece();
        System.out.println(oldCol);
        System.out.println(oldRow);
        System.out.println(piece);
        return move(piece, newPosition);
    }

    public boolean move(Piece piece, String newPosition) {

        if(!isValidMove(piece, newPosition)) return false;

        int row = piece.getRowIdx();
        int column = piece.getColIdx();
        int colNext = newPosition.charAt(0) - 'A';
        int rowNext = newPosition.charAt(1) - '0' - 1;

        Piece p = board[rowNext][colNext].getPiece();

        if (p == null) {// if new position is null
            if (board[rowNext][colNext].myDen(piece.getPlayer())) {
                System.out.println("You can not enter your own den");
                return false;
            }
            else if (!board[rowNext][colNext].isRiver()) {  // not river
                board[rowNext][colNext].setPiece(piece);
                board[row][column].setPiece(null);
                changeTurn();
                return true;
            }
            else {                                     // null and river
                if (piece.canSwim()) {
                    board[rowNext][colNext].setPiece(piece);
                    board[row][column].setPiece(null);
                    changeTurn();
                    return true;
                }
                else {
                    System.out.println("This piece can not move into river.");
                    return false;
                }
            }
        }
        else { // there is a piece in new position
            if (p.getPlayer() == piece.getPlayer()) { System.out.println("Two pieces can not be in the same grid.");return false; }
            else {
                if (piece.canSwim()) {  // in the river
                    // opponent's rat in the river and my rat not in river
                    if (board[rowNext][colNext].isRiver() && !board[row][column].isRiver()) return false;
                    else if (!board[rowNext][colNext].isRiver() && board[row][column].isRiver()) return false;
                    else if (board[rowNext][colNext].isRiver() && board[row][column].isRiver()) {
                        p.setIsCaptured();
                        board[rowNext][colNext].setPiece(piece);
                        board[row][column].setPiece(null);
                        changeTurn();
                        return true;
                    } else if (piece.canCapture(p)) {
                        p.setIsCaptured();
                        board[rowNext][colNext].setPiece(piece);
                        board[row][column].setPiece(null);
                        System.out.println(board[row][column].getPiece() + board[rowNext][colNext].getPiece().name());
                        changeTurn();
                        return true;
                    }
                }
                else {
                    if (board[rowNext][colNext].isRiver()) {System.out.println("This piece can not move into river.");return false;}
                    else if (board[rowNext][colNext].myTrap(piece.getPlayer())) { //enemy in my trap
                        p.setIsCaptured();
                        board[rowNext][colNext].setPiece(piece);
                        board[row][column].setPiece(null);
                        changeTurn();
                        return true;
                    }
                    else if (piece.canCapture(p)) {
                        p.setIsCaptured();
                        board[rowNext][colNext].setPiece(piece);
                        board[row][column].setPiece(null);
                        System.out.println(board[row][column].getPiece()+board[rowNext][colNext].getPiece().name());
                        changeTurn();
                        return true;
                    }
                    else {
                        System.out.println("can not capture that");
                        return false;
                    }
                }
            }
        }
        changeTurn();
        return true;
    }

    public boolean isValidMove(Piece piece, String newPosition){
        System.out.println(piece);
        System.out.println(piece.getPlayer());
        if (piece.getPlayer().getMyTurn()!=turn) { // move the other player's piece
            System.out.print("It is not your turn.");
            return false;
        }
        int row = piece.getRowIdx();
        int column = piece.getColIdx();
        int rowNext = newPosition.charAt(1) - '0' - 1;
        int colNext = newPosition.charAt(0) - 'A';

        if (rowNext < 0 || rowNext > 8 || colNext < 0 || colNext > 6) {
            System.out.println("Move beyond the board. Please try again");
            return false;
        }

        if ((Math.abs(row - rowNext) == 1 && Math.abs(column - colNext) == 0) || (Math.abs(row - rowNext) == 0 && Math.abs(column - colNext) == 1)) return true;

        if ((Math.abs(row - rowNext) >= 1 && Math.abs(column - colNext) >= 1) || (Math.abs(row - rowNext) == 0 && Math.abs(column - colNext) == 0)){
            System.out.println("Each piece moves one square horizontally or vertically (not diagonally) and you must move.");
            return false;
        }

        if(piece.canJumpRiver() && (Math.abs(row - rowNext) == 4)){
            if ((row == 2 || row == 6) && (column > 0 && column < 6 && column != 3)) {
                if ((Rat1.isInTheRiver() && Rat1.getColIdx() == column) || (Rat2.isInTheRiver() && Rat2.getColIdx() == column)) return false;
                else return true;
            }
        }

        if(piece.canJumpRiver() && (Math.abs(column - colNext) == 3)) {
            if ((column == 0 || column == 3 || column == 6) && (row > 2 && row < 6)) {
                if (Rat1.isInTheRiver() && Rat1.getRowIdx() == row) {
                    if (colNext > column && (colNext - Rat1.getColIdx()) < 3 && (colNext - Rat1.getColIdx()) > 0) return false;
                    if (colNext < column && (Rat1.getColIdx() - colNext) < 3 && (Rat1.getColIdx()-colNext) > 0) return false;
                    return true;
                }
                else if (Rat2.isInTheRiver() && Rat2.getRowIdx() == row){
                    if (colNext > column && (colNext - Rat2.getColIdx()) < 3 && (colNext - Rat2.getColIdx()) > 0) return false;
                    if (colNext < column && (Rat2.getColIdx() - colNext) < 3 && (Rat2.getColIdx()-colNext > 0)) return false;
                    return true;
                }
                else return true;
            }
        }

        System.out.println("Each piece moves one square horizontally or vertically (not diagonally) and you must move.");
        return false;
    }

    public int check() { // 0 - in game; 1 - X win; 2 - Y win
        if (Rat1.getStatus() == false && Cat1.getStatus() == false && Dog1.getStatus() == false && Elephant1.getStatus() == false
                && Leopard1.getStatus() == false && Lion1.getStatus() == false && Tiger1.getStatus() == false && Wolf1.getStatus() == false) {
            return 1;
        }
        else if (Rat2.getStatus() == false && Cat2.getStatus() == false && Dog2.getStatus() == false && Elephant2.getStatus() == false
                && Leopard2.getStatus() == false && Lion2.getStatus() == false && Tiger2.getStatus() == false && Wolf2.getStatus() == false) {
            return 2;
        }
        else if (board[0][3].getPiece() != null) {
            return 2;
        }
        else if (board[8][3].getPiece() != null) {
            return 1;
        }
        return 0;
    }

    public void print(){
        for (int i = NUMBER_OF_ROW - 1; i >= 0; i--){
            for (int j = 0; j < NUMBER_OF_COLUMN; j++){
                if(board[i][j].getPiece() != null) System.out.printf("%-10s",board[i][j].getPiece().name());
                else if(board[i][j].getPiece() == null && board[i][j].isRiver()) System.out.printf("%-10s","River");
                else if(board[i][j].getPiece() == null && board[i][j].isDen()) System.out.printf("%-10s","Den");
                else if(board[i][j].getPiece() == null && board[i][j].isTrap()) System.out.printf("%-10s","Trap");
                else System.out.printf("%-10s"," ");
            }
            System.out.print("\n");
        }
    }
}
