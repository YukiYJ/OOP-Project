package hk.edu.polyu.comp.comp2021.jungle.model;

public class Player {

    private char mark; // X or Y
    private String name;
    private int myturn;

    public Player(char mark, String name, int myturn) {
        this.mark = mark;
        this.name = name;
        this.myturn = myturn;
    }
    public char getMark() {return this.mark;}

    public String getName() {return this.name;}

    private Player player1, player2;

    public void setPlayer1(String name) {
        this.name = name;
        this.player1 = new Player('X', this.name, 0);
    }
    public void setPlayer2(String name) {
        this.name  = name;
        this.player2 = new Player('Y', this.name, 1);
    }

    public int getMyTurn(){
        return this.myturn;
    }
}
