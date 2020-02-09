package hk.edu.polyu.comp.comp2021.jungle.model;

/**
 * the class for two players
 */
public class Player {

    private final char mark; // X or Y
    private final String name;
    private final int myturn;

    /**
     *
     * @param mark mark is either X or Y, which represent the player
     * @param name the name of the player which user could enter by themselves
     * @param myturn the turn of this player
     */
    public Player(char mark, String name, int myturn) {
        this.mark = mark;
        this.name = name;
        this.myturn = myturn;
    }

    /**
     *
     * @return the mark('X','Y') of this player
     */
    public char getMark() {return mark;}

    /**
     *
     * @return the name of this player
     */
    public String getName() {return name;}

    /**
     *
     * @return the int turn of mine
     */
    public int getMyTurn(){
        return this.myturn;
    }
}
