package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.model.piece.*;

/**
 * the class for the game
 */
public class JungleGame {

    private static final int NUMBER_OF_ROW = 9;
    private static final int NUMBER_OF_COLUMN = 7;
    private final BoardGrid[][] board;
    private int turn;

    private Piece Rat1, Cat1, Dog1, Wolf1, Leopard1, Tiger1, Lion1, Elephant1;
    private Piece Rat2, Cat2, Dog2, Wolf2, Leopard2, Tiger2, Lion2, Elephant2;

    private Player player1, player2;

    /**
     * constructor for the jungle game
     */
    public JungleGame() {
        board = new BoardGrid[NUMBER_OF_ROW][NUMBER_OF_COLUMN];
        for (int i = 0; i < NUMBER_OF_ROW; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
                board[i][j] = new BoardGrid(i, j);
            }
        }
    }

    /**
     *
     * @return the turn currently 0 for player1, 1 for player 2
     */
    public int getTurn() { return turn; }

    /**
     * change the turn after each valid move
     */
    private void changeTurn(){ turn = 1 - turn; }

    /**
     * @return Rat1
     */
    public Piece getRat1() { return Rat1; }
    /**
     * @return Rat2
     */
    public Piece getRat2() { return Rat2; }
    /**
     * @return Cat1
     */
    public Piece getCat1() { return Cat1; }
    /**
     * @return Cat2
     */
    public Piece getCat2() { return Cat2; }

    /**
     * @return Dog1
     */
    public Piece getDog1() { return Dog1; }
    /**
     * @return Dog2
     */
    public Piece getDog2() { return Dog2; }
    /**
     * @return Wolf1
     */
    public Piece getWolf1() { return Wolf1; }
    /**
     * @return Wolf2
     */
    public Piece getWolf2() { return Wolf2; }
    /**
     * @return Leopard1
     */
    public Piece getLeopard1() { return Leopard1; }
    /**
     * @return Leopard2
     */
    public Piece getLeopard2() { return Leopard2; }
    /**
     * @return Tiger1
     */
    public Piece getTiger1() { return Tiger1; }
    /**
     * @return Tiger2
     */
    public Piece getTiger2() { return Tiger2; }
    /**
     * @return Lion1
     */
    public Piece getLion1() { return Lion1; }
    /**
     * @return Lion2
     */
    public Piece getLion2() { return Lion2; }
    /**
     * @return Elephant1
     */
    public Piece getElephant1() { return Elephant1; }
    /**
     * @return Elephant2
     */
    public Piece getElephant2() { return Elephant2; }

    /**
     * get player1
     * @return player1
     */
    public Player getPlayer1() { return player1; }

    /**
     * get player2
     * @return player2
     */
    public Player getPlayer2() { return player2; }
    /**
     *
     * @param name set player1 with this name
     */
    public void setPlayer1(String name) { this.player1 = new Player('X', name, 0);}
    /**
     *
     * @param name set player2 with this name
     */
    public void setPlayer2(String name) { this.player2 = new Player('Y', name, 1);}

    /**
     *
     * @param player1 set to player1
     */
    public void setPlayer1(Player player1) { this.player1 = player1;}

    /**
     *
     * @param player2 set to player2
     */
    public void setPlayer2(Player player2) { this.player2 = player2;}

    /**
    * start a new game
    */
    public void openNew() {
        turn = 0;
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

    /**
     * Start a saved game
     * @param savedStr already saved str
     */
    public void openSaved(String savedStr) {
        String[] a = savedStr.split("\t");
        turn = Integer.parseInt(a[4]);
        player1 = new Player('X', a[2], turn);
        player2 = new Player('Y', a[3], 1-turn);
        String[] p1 = a[0].split(" "), p2 = a[1].split(" ");

        Rat1 = new Rat(p1[0], player1); Rat2 = new Rat(p2[0], player2);
        Cat1 = new Cat(p1[1], player1); Cat2 = new Cat(p2[1], player2);
        Dog1 = new Dog(p1[2], player1); Dog2 = new Dog(p2[2], player2);
        Wolf1 = new Wolf(p1[3], player1); Wolf2 = new Wolf(p2[3], player2);
        Lion1 = new Lion(p1[4], player1); Lion2 = new Lion(p2[4], player2);
        Leopard1 = new Leopard(p1[5], player1); Leopard2 = new Leopard(p2[5], player2);
        Tiger1 = new Tiger(p1[6], player1); Tiger2 = new Tiger(p2[6], player2);
        Elephant1 = new Elephant(p1[7], player1); Elephant2 = new Elephant(p2[7], player2);

        board[2][6].setPiece(Rat2); board[6][0].setPiece(Rat1);
        board[1][1].setPiece(Cat2); board[7][5].setPiece(Cat1);
        board[1][5].setPiece(Dog2); board[7][1].setPiece(Dog1);
        board[2][2].setPiece(Wolf2); board[6][4].setPiece(Wolf1);
        board[0][6].setPiece(Lion2); board[8][0].setPiece(Lion1);
        board[2][4].setPiece(Leopard2); board[6][2].setPiece(Leopard1);
        board[0][0].setPiece(Tiger2); board[8][6].setPiece(Tiger1);
        board[2][0].setPiece(Elephant2); board[6][6].setPiece(Elephant1);
    }

    /**
     *
     * @param oldPosition the position where you want to operate
     * @param newPosition the position where you move to
     * @return if no piece in oldposition, return false, else go
     * to move to judge continuely
     */
    public boolean move(String oldPosition, String newPosition) {
        int oldCol = oldPosition.charAt(0) - 'A';
        int oldRow = oldPosition.charAt(1) - '0' - 1;
        Piece piece = board[oldRow][oldCol].getPiece();
        if(piece==null){
            System.out.println("Sorry, this grid do not have a piece.");
            return false;
        }
        return move(piece, newPosition);
    }

    /**
     *
     * @param piece the piece which you would like to move
     * @param newPosition  you want to move the piece to this position
     * @return whether it could be moved
     */
    public boolean move(Piece piece, String newPosition) {

        if(!isValidMove(piece, newPosition)) return false;

        int row = piece.getRowIdx();
        int column = piece.getColIdx();
        int colNext = newPosition.charAt(0) - 'A';
        int rowNext = newPosition.charAt(1) - '0' - 1;

        Piece p = board[rowNext][colNext].getPiece();

        if (p == null) {// if new position is null
            if (board[rowNext][colNext].isDen(piece.getPlayer())) {
                System.out.println("You can not enter your own den.");
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
                    else if (board[rowNext][colNext].isTrap(piece.getPlayer())) { //enemy in my trap
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


    /**
     *
     * @param piece the piece which you would like to move
     * @param newPosition  you want to move the piece to this position
     * @return whether this move is valid
     */
    public boolean isValidMove(Piece piece, String newPosition){
        if (piece.getPlayer().getMyTurn()!=turn) { // move the other player's piece
            System.out.print("It is not your piece.\n");
            return false;
        }
        int row = piece.getRowIdx();
        int column = piece.getColIdx();
        int rowNext = newPosition.charAt(1) - '0' - 1;
        int colNext = newPosition.charAt(0) - 'A';

        if (rowNext < 0 || rowNext > 8 || colNext < 0 || colNext > 6) {
            System.out.println("Move beyond the board.\n");
            return false;
        }

        if ((Math.abs(row - rowNext) == 1 && Math.abs(column - colNext) == 0) || (Math.abs(row - rowNext) == 0 && Math.abs(column - colNext) == 1)) return true;

        if ((Math.abs(row - rowNext) >= 1 && Math.abs(column - colNext) >= 1) || (Math.abs(row - rowNext) == 0 && Math.abs(column - colNext) == 0)){
            System.out.println("Each piece moves one square horizontally or vertically (not diagonally) and you must move. \n");
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

        System.out.println("Each piece moves one square horizontally or vertically (not diagonally) and you must move. ");
        return false;
    }

    /**
     *
     * @return who win the game, 0 for nobody wins, 1 for player 1, 2 for player 2
     */
    public int check() { // 0 - in game; 1 - X win; 2 - Y win
        if (!Rat1.getStatus() && !Cat1.getStatus() && !Dog1.getStatus() && !Elephant1.getStatus()
                && !Leopard1.getStatus() && !Lion1.getStatus() && !Tiger1.getStatus() && !Wolf1.getStatus()) {
            return 1;
        }
        else if (!Rat2.getStatus() && !Cat2.getStatus() && !Dog2.getStatus() && !Elephant2.getStatus()
                && !Leopard2.getStatus() && !Lion2.getStatus() && !Tiger2.getStatus() && !Wolf2.getStatus()) {
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

    /**
     * print the board
     */
    public void print(){
        System.out.printf("%32s\n","X");
        for (int i = NUMBER_OF_ROW - 1; i >= 0; i--){
            for (int j = 0; j < NUMBER_OF_COLUMN; j++){
                if(board[i][j].getPiece() != null) System.out.printf("%-10s",board[i][j].getPiece().name()+board[i][j].getPiece().getPlayer().getMark());
                else if(board[i][j].getPiece() == null && board[i][j].isRiver()) System.out.printf("%-10s","River");
                else if(board[i][j].getPiece() == null && board[i][j].isDen()) System.out.printf("%-10s","Den");
                else if(board[i][j].getPiece() == null && board[i][j].isTrap()) System.out.printf("%-10s","Trap");
                else System.out.printf("%-10s"," ");
            }
            System.out.print("\n");
        }
        System.out.printf("%32s\n","Y");
    }

    /**
     * store the game information in String type so that it can be saved into a file
     * @return the str
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(Rat1.getPosition()+" "+Cat1.getPosition()+" "+Dog1.getPosition()+" "+Wolf1.getPosition()+" "
                +Lion1.getPosition()+" "+Leopard1.getPosition()+" "+Tiger1.getPosition()+" "+Elephant1.getPosition()+"\t");
        builder.append(Rat2.getPosition()+" "+Cat2.getPosition()+" "+Dog2.getPosition()+" "+Wolf2.getPosition()+" "
                +Lion2.getPosition()+" "+Leopard2.getPosition()+" "+Tiger2.getPosition()+" "+Elephant2.getPosition()+"\t");
        builder.append(player1.getName()).append("\t");
        builder.append(player2.getName()).append("\t");
        builder.append(Integer.toString(turn)).append("\t");
        return builder.toString();
    }
}
