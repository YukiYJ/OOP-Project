package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.model.BoardGrid;
import hk.edu.polyu.comp.comp2021.jungle.model.JungleGame;
import hk.edu.polyu.comp.comp2021.jungle.model.piece.*;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;
import org.junit.*;

import static junit.framework.TestCase.assertEquals;

/**
 * the class for test
 */
public class JungleGameTest {

    private Player player1, player2;
    private JungleGame game;
    private Piece rat1, rat2;
    private Piece cat1, cat2;
    private Piece dog1, dog2;
    private Piece wolf1, wolf2;
    private Piece leopard1, leopard2;
    private Piece tiger1, tiger2;
    private Piece lion1, lion2;
    private Piece elephant1, elephant2;
    private BoardGrid grid0, grid1, grid2, grid3;

    /**
     * check for constructor in game
     */
    @Before
    public void checkConstructor() {
        game = new JungleGame();
        player1 = new Player('X', "Qin", 0);
        player2 = new Player('Y', "Rui", 1);
        game = new JungleGame();
        game.openNew();
        game.setPlayer1(player1);
        game.setPlayer2(player2);

        grid0 = new BoardGrid(2, 4);
        rat1 = new Rat("E3", player1);
        rat2 = new Rat("B4", player2);
        cat1 = new Cat("C9", player1);
        leopard1 = new Leopard("B7", player1);
    }

    /**
     * test open a new game
     */
    @Test
    public void testOpenNew() {
        game.openNew();
        assertEquals(game.getTurn(), 0);
        assertEquals(game.getPlayer1().getMark(), 'X');
        assertEquals(game.getPlayer2().getMyTurn(), 1);
        assertEquals(game.getRat1().getPosition(), "A7");
        assertEquals(game.getDog1().getPosition(), "B8");
        assertEquals(game.getElephant2().getPlayer().getMark(), 'Y');
    }

    /**
     * test open a saved game
     */
    @Test
    public void TestOpenSaved() {
        game.openSaved("A7 F8 B8 E7 A8 C7 G9 G7\tG3 B2 F2 C3 G2 E3 A1 A3\tYU\tGAO\t1\t");
        assertEquals(game.getTurn(), 1);
        assertEquals(game.getPlayer1().getName(), "YU");
        assertEquals(game.getPlayer2().getName(), "GAO");
        assertEquals(game.getPlayer1().getMyTurn(), 1);
        assertEquals(game.getPlayer2().getMyTurn(), 0);
        assertEquals(game.getCat1().getPosition(), "F8");
        assertEquals(game.getDog1().getPosition(), "B8");
        assertEquals(game.getLeopard2().getPosition(), "E3");
        assertEquals(game.getTiger2().getPosition(), "A1");
        assertEquals(game.getElephant2().getPosition(), "A3");
        assertEquals(game.getElephant2().getPlayer().getName(), "GAO");
    }

    /**
     * test convert game information to String type
     */
    @Test
    public void TestToString() {
        assertEquals(game.toString(), "A7 F8 B8 E7 A9 C7 G9 G7\tG3 B2 F2 C3 G1 E3 A1 A3\tQin\tRui\t0\t");
    }

    /**
     * check piece
     */
    @Test
    public void checkPiece() {
        assertEquals(game.getRat1().getRank(), 1);
        assertEquals(game.getElephant2().getStatus(), true);
        assertEquals(game.getLeopard2().getColIdx(), 4);
        assertEquals(game.getLeopard2().getRowIdx(), 2);
        assertEquals(game.getLeopard2().getPosition(), "E3");
        assertEquals(game.getRat1().canJumpRiver(), false);
        assertEquals(game.getRat1().canSwim(), true);
        assertEquals(game.getRat1().name(), "Rat");
        assertEquals(game.getElephant1().isInTheRiver(),false);
        assertEquals(game.getRat2().canCapture(game.getRat1()),true);
        assertEquals(game.getTiger2().getPlayer().getMark(),'Y');
        assertEquals(game.getLion1().canSwim(),false);
    }

    /**
     * check boardgrid
     */
    @Test
    public void checkGrid() {
        grid0.setPiece(rat1);
        assertEquals(grid0.getPiece(), rat1);
        assertEquals(grid0.isRiver(), false);
        assertEquals(grid0.isTrap(), false);
        assertEquals(grid0.isDen(), false);
    }

    /**
     * check the board
     */
    @Test
    public void checkBoard() {
        Piece p1 = game.getRat1();
        Piece p2 = game.getRat2();
        assertEquals(p1.getStatus(), true);
        assertEquals(p1.getPosition(), "A7");
        assertEquals(p1.getPlayer().getMark(), 'X');
        assertEquals(game.check(), 0);
        assertEquals(game.isValidMove(p2, "E4"), false);
        assertEquals(game.move(p1, "B7"), true);
    }

    /**
     * check for the ini board
     */
    @Test
    public void checkInitialBoard() {
        game.print();
    }


    /**
     * check for player
     */
    @Test
    public void checkPlayer() {
        assertEquals(player1.getName(), "Qin");
        assertEquals(game.move(game.getDog1(), "B7"), true);
        assertEquals(game.move(game.getDog1(), "B8"), false);
    }


    /**
     * check for valid move
     */
    @Test
    public void checkMove() {
        //check two position move
        assertEquals(game.move("B1","C1"),false);
        //check valid move*******

        //must move
        assertEquals(game.isValidMove(game.getLeopard1(), "C7"), false);

        //can not move more than distance 1: except river
        assertEquals(game.isValidMove(game.getLeopard1(), "D5"), false);
        assertEquals(game.move(game.getLeopard1(), "B7"), true);
        assertEquals(game.getLeopard1().getPosition(), "B7");

        //can not enter the river except rat
        assertEquals(game.move(game.getWolf2(), "C2"), true);
        assertEquals(game.move(game.getRat1(), "B7"), false); // two piece from same player can not be in the same grid
        assertEquals(game.move(game.getRat1(), "A6"), true);
        assertEquals(game.move(game.getLeopard2(), "E4"), false);
        //can not move if not its turn
        assertEquals(game.move(game.getRat1(), "B6"), false);

        //can across the river without rat
        assertEquals(game.move(game.getLion2(), "G2"), true);
        assertEquals(game.move(game.getRat1(), "B6"), true);

        //check move from two positions
        assertEquals(game.move("G3","G4"),true);
        assertEquals(game.move("B6","C6"),true);


        assertEquals(game.move(game.getLion2(), "G3"), true);
        assertEquals(game.move(game.getRat1(), "D6"), true);
        assertEquals(game.move(game.getLion2(), "F3"), true);
        assertEquals(game.move(game.getTiger1(),"F9"),true);
        assertEquals(game.move(game.getLion2(), "F7"), true);

        //can not across with rat between vertically
        assertEquals(game.move(game.getElephant1(),"G6"),true);
        assertEquals(game.move(game.getRat2(), "F4"), true);
        assertEquals(game.move(game.getRat1(), "D5"), true);
        assertEquals(game.move(game.getLion2(), "F3"), false);

        //can not enter your own den
        assertEquals(game.move(game.getRat2(), "F5"), true);
        assertEquals(game.move(game.getTiger1(), "E9"), true);
        assertEquals(game.move(game.getLeopard2(),"E2"),true);
        assertEquals(game.move(game.getTiger1(), "D9"), false);

        // can not across with rat horizontally (whether it is the same pool)
        assertEquals(game.move(game.getWolf1(),"E8"),true);
        assertEquals(game.move(game.getLion2(),"E7"),true);
        assertEquals(game.move(game.getWolf1(), "D8"), true);
        assertEquals(game.move(game.getLion2(),"D7"),true);
        assertEquals(game.move(game.getElephant1(),"G5"),true);
        assertEquals(game.move(game.getLion2(),"D6"),true);
        assertEquals(game.move(game.getRat1(), "E5"), true);
        assertEquals(game.move(game.getLion2(),"D5"),true);
        assertEquals(game.move(game.getRat1(), "E4"), true);
        assertEquals(game.move(game.getLion2(),"G5"),false);
        assertEquals(game.move(game.getLion2(),"A5"),true);

        //check capture********

        //rat can not capture the elephant when it is in the water

        assertEquals(game.move(game.getRat1(), "E5"), true);
        assertEquals(game.move(game.getRat2(), "G5"), false);

        //rat can not attack another rat from land to water(vice versus)

        assertEquals(game.move(game.getCat2(),"B1"),true);
        assertEquals(game.move(game.getRat1(), "D5"), true);

        assertEquals(game.move(game.getRat2(), "E5"), true);
        assertEquals(game.move(game.getRat1(), "E5"), false);

        assertEquals(game.move(game.getRat1(), "D4"), true);
        assertEquals(game.move(game.getRat2(), "E4"), true);
        assertEquals(game.move(game.getDog1(),"A8"),true);
        assertEquals(game.move(game.getRat2(), "D4"), false);


        //rat could attack each other when both on land or water
        assertEquals(game.move(game.getRat2(), "F4"), true);
        assertEquals(game.move(game.getRat1(), "E4"), true);
        assertEquals(game.getRat1().isInTheRiver(),true);
        assertEquals(game.move(game.getRat2(), "E4"), true);

        assertEquals(game.getRat1().getStatus(),false);
        assertEquals(game.check(),0);

        //animal could catch higher ranking animal when in trap
        assertEquals(game.move(game.getTiger1(),"E8"),true);
        assertEquals(game.move(game.getLion2(), "A6"), true);
        assertEquals(game.move(game.getTiger1(),"E7"),true);
        assertEquals(game.move(game.getRat2(),"F4"),true);
        assertEquals(game.getTiger1().canJumpRiver(),true);
        assertEquals(game.move(game.getTiger1(),"E3"),true);
        assertEquals(game.move(game.getLion2(), "A7"), true);
        assertEquals(game.move(game.getTiger1(),"E2"),true);
        assertEquals(game.move(game.getLion2(), "B7"), true);
        assertEquals(game.move(game.getTiger1(),"E1"),true);
        assertEquals(game.move(game.getDog2(),"F1"),true);
        assertEquals(game.move(game.getCat1(),"G8"),true);
        assertEquals(game.move(game.getDog2(),"E1"),true);
        assertEquals(game.getTiger1().getStatus(),false);

        // when one animal enter the den, game over
        assertEquals(game.move(game.getElephant1(), "G4"), true);
        assertEquals(game.move(game.getLion2(), "B8"), true);
        assertEquals(game.move(game.getElephant1(), "G3"), true);
        assertEquals(game.move(game.getLion2(), "B9"), true);
        assertEquals(game.move(game.getElephant1(), "G2"), true);
        assertEquals(game.move(game.getLion2(), "C9"), true);
        assertEquals(game.move(game.getElephant1(), "F2"), true);
        assertEquals(game.move(game.getDog2(),"E2"),true);
        assertEquals(game.move(game.getElephant1(), "F3"), true);
        assertEquals(game.getElephant1().canCapture(game.getDog2()),true);
        assertEquals(game.getDog2().canCapture(game.getElephant1()),false);
        assertEquals(game.move(game.getLion2(), "D9"), true);
        assertEquals(game.check(),1);
        }

        /**
         * some other situation also testing for move
         */
        @Test
        public void TestMove(){
            assertEquals(game.move(game.getElephant1(),"G6"),true);
            assertEquals(game.getTurn(),1);
            assertEquals(game.move(game.getRat2(),"G4"),true);
            assertEquals(game.getTurn(),0);
            assertEquals(game.move(game.getElephant1(),"G5"),true);
            assertEquals(game.move(game.getRat2(),"G5"),true);
            assertEquals(game.move(game.getLion1(),"A0"),false);

        }
    }