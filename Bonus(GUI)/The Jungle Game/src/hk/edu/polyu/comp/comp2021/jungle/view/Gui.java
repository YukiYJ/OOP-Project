package hk.edu.polyu.comp.comp2021.jungle.view;

import hk.edu.polyu.comp.comp2021.jungle.model.BoardGrid;
import hk.edu.polyu.comp.comp2021.jungle.model.Player;
import hk.edu.polyu.comp.comp2021.jungle.model.piece.*;
import java.io.*;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FileDialog;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public abstract class Gui implements ActionListener {

    //define JFrame/menu/chessManual
    //create frame
    static JFrame frame = new JFrame("The Jungle Game");
    //create menu
    static JMenuBar menuBar = new JMenuBar();
    static JMenu menu;
    static JMenuItem item;
    //create board
    static final int NUMBER_OF_ROW = 9;
    static final int NUMBER_OF_COLUMN = 7;
    static BoardGrid[][] board = new BoardGrid[NUMBER_OF_ROW][NUMBER_OF_COLUMN];
    static String[][] boardName = new String[NUMBER_OF_ROW][NUMBER_OF_COLUMN];

    static Piece Rat1, Cat1, Dog1, Wolf1, Leopard1, Tiger1, Lion1, Elephant1;
    static Piece Rat2, Cat2, Dog2, Wolf2, Leopard2, Tiger2, Lion2, Elephant2;

    //define mouse position
    public static String mousePosition1, mousePosition2;
    //record whether it is the first click
    static boolean isFirst = true;
    private static int turn = 0;

    public static int getTurn() {
        return turn;
    }

    public static void changeTurn() {
        turn = 1 - turn;
    }

    // create the initial column
    static String[] columns = {"A", "B", "C", "D", "E", "F", "G"};

    // create JTable
    static JTable table = new JTable(boardName, columns);
    //create JTable default model
    static DefaultTableModel dtm = new DefaultTableModel();

    //get action command
    static Gui gui = new Gui() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            if ("new".equals(s)) {
                openNew();
            }
            if ("saved".equals(s)) {
                //open filepath to find
                try {
                    openSavePath();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if ("filepath".equals(s)) {
                //choose save path；
                selectSavePath();
            }
        }
    };

    //initial GUI
    public static void initGui() {

        //set font style and size
        Font font = new Font("Times", Font.PLAIN, 24);
        //set the dimension of menu
        Dimension dimension = new Dimension(200, 35);
        // set the size of frame
        frame.setSize(1000, 1000);

        int windowWidth = frame.getWidth(); //get the width of window
        int windowHeight = frame.getHeight(); //get the height of window
        Toolkit kit = Toolkit.getDefaultToolkit(); //define the tool kit
        Dimension screenSize = kit.getScreenSize(); //get the size of screen
        int screenWidth = screenSize.width; //get the size of screen
        int screenHeight = screenSize.height; //get the height of screen
        frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//put the window in the middle
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //menu has open, save, new, saved and filepath
        String[] cmdKind = {"open", "save"};
        String[][] cmdItem = {{"new", "saved"}, {"filepath"}};

        //set menu style and add action listener
        for (int i = 0; i < cmdKind.length; i++) {
            menu = new JMenu(cmdKind[i]);
            menu.setFont(font);
            menu.setPreferredSize(dimension);
            menuBar.add(menu);
            for (int j = 0; j < cmdItem[i].length; j++) {
                String caption = cmdItem[i][j];
                item = new JMenuItem(cmdItem[i][j]);
                item.setPreferredSize(dimension);
                item.setFont(font);
                menu.add(item);
                item.addActionListener(gui);
            }
        }
        frame.setJMenuBar(menuBar);
        table.setPreferredScrollableViewportSize(dimension);
        table.setFont(font);
        table.setRowHeight(100);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        frame.add(table);
        frame.setVisible(true);
    }

    public void openNew() {

        System.out.println("open a new game");

        Player player1, player2;

        player1 = new Player('X', " ", 0);
        player2 = new Player('Y', " ", 1);

        Rat2 = new Rat("A7", player2);
        Rat1 = new Rat("G3", player1);
        Cat2 = new Cat("F8", player2);
        Cat1 = new Cat("B2", player1);
        Dog2 = new Dog("B8", player2);
        Dog1 = new Dog("F2", player1);
        Wolf2 = new Wolf("E7", player2);
        Wolf1 = new Wolf("C3", player1);
        Lion2 = new Lion("A9", player2);
        Lion1 = new Lion("G1", player1);
        Leopard2 = new Leopard("C7", player2);
        Leopard1 = new Leopard("E3", player1);
        Tiger2 = new Tiger("G9", player2);
        Tiger1 = new Tiger("A1", player1);
        Elephant2 = new Elephant("G7", player2);
        Elephant1 = new Elephant("A3", player1);

        //initialize the position of board grid
        for (int i = 0; i < NUMBER_OF_ROW; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
                board[i][j] = new BoardGrid(i, j);
            }
        }

        //set the initial position of piece, trap and den
        board[2][6].setPiece(Elephant2);
        board[6][0].setPiece(Elephant1);
        board[1][1].setPiece(Dog2);
        board[7][5].setPiece(Dog1);
        board[1][5].setPiece(Cat2);
        board[7][1].setPiece(Cat1);
        board[2][2].setPiece(Leopard2);
        board[6][4].setPiece(Leopard1);
        board[0][6].setPiece(Tiger2);
        board[8][0].setPiece(Tiger1);
        board[2][4].setPiece(Wolf2);
        board[6][2].setPiece(Wolf1);
        board[0][0].setPiece(Lion2);
        board[8][6].setPiece(Lion1);
        board[2][0].setPiece(Rat2);
        board[6][6].setPiece(Rat1);

        for (int i = 0; i < NUMBER_OF_ROW; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
                if (board[i][j].getPiece() != null) {
                    boardName[i][j] = board[i][j].getPiece().name() + "-" + board[i][j].getPiece().getPlayer().getMark();
                } else {
                    boardName[i][j] = null;
                }
            }
        }
        if(boardName[0][2] == null) {boardName[0][2] = "trap-Y";}
        if(boardName[0][4] == null) {boardName[0][4] = "trap-Y";}
        if(boardName[1][3] == null) {boardName[1][3] = "trap-Y";}
        if(boardName[8][2] == null) {boardName[8][2] = "trap-X";}
        if(boardName[8][4] == null) {boardName[8][4] = "trap-X";}
        if(boardName[7][3] == null) {boardName[7][3] = "trap-X";}
        if(boardName[0][3] == null) {boardName[0][3] = "den-Y";}
        if(boardName[8][3] == null) {boardName[8][3] = "den-X";}
        for (int i = 3; i < 6; i++) {
            for (int j = 1; j < 6; j++) {
                if (j != 3) {
                    if(boardName[i][j] == null) boardName[i][j] = "river";
                }
            }
        }
        dtm = new DefaultTableModel(boardName, columns) {
            public boolean isCellEditable(int row, int column) {
                return false;   //do not allowed to click twice
            }
        };
        table.setModel(dtm);
        dtm.setDataVector(boardName, columns);// refresh the data in JTable
        table.addMouseListener(new MouseAdapter() {  //add mouseListener
            @Override
            public void mouseClicked(MouseEvent event) {
                if (isFirst) {    //first click
                    int r = table.getSelectedRow();
                    int c = table.getSelectedColumn();
                    if(board[r][c].getPiece() == null)  {   //do not click any piece
                        JOptionPane.showMessageDialog(null, "first-click must be one animal piece!");
                        isFirst = true;
                        return;
                    }
                    //convert horizontal ordinate to A-G, vertical ordinate to 1-9
                    mousePosition1 = columns[c] + String.valueOf(NUMBER_OF_ROW - r);
                    isFirst = false;
                } else {
                    int r = table.getSelectedRow();
                    int c = table.getSelectedColumn();
                    mousePosition2 = columns[c] + String.valueOf(NUMBER_OF_ROW - r);
                    isFirst = true;
                }
                if (isFirst) {   //second click
                    String s = "move" + " " + mousePosition1 + " " + mousePosition2;
                    System.out.println(s);
                    execute(s);
                    int c = 0;
                    c = check();    // 0 - in game; 1 - X win; 2 - Y win
                    if (c == 1) {
                        System.out.println(Message.MSG_WINNER.getMessageStr() + player1.getName());
                        JOptionPane.showMessageDialog(null, Message.MSG_WINNER.getMessageStr() + player1.getName());
                    } else if (c == 2) {
                        System.out.println(Message.MSG_WINNER.getMessageStr() + player2.getName());
                        JOptionPane.showMessageDialog(null, Message.MSG_WINNER.getMessageStr() + player2.getName());
                    }
                    for (int i = 0; i < NUMBER_OF_ROW; i++) {
                        for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
                            if (board[i][j].getPiece() != null) {
                                boardName[i][j] = board[i][j].getPiece().name() + "-" + board[i][j].getPiece().getPlayer().getMark();
                            } else {
                                boardName[i][j] = null;
                            }
                        }
                    }
                    if(boardName[0][2] == null) {boardName[0][2] = "trap-Y";}
                    if(boardName[0][4] == null) {boardName[0][4] = "trap-Y";}
                    if(boardName[1][3] == null) {boardName[1][3] = "trap-Y";}
                    if(boardName[8][2] == null) {boardName[8][2] = "trap-X";}
                    if(boardName[8][4] == null) {boardName[8][4] = "trap-X";}
                    if(boardName[7][3] == null) {boardName[7][3] = "trap-X";}
                    if(boardName[0][3] == null) {boardName[0][3] = "den-Y";}
                    if(boardName[8][3] == null) {boardName[8][3] = "den-X";}
                    for (int i = 3; i < 6; i++) {
                        for (int j = 1; j < 6; j++) {
                            if (j != 3) {
                                if(boardName[i][j] == null) boardName[i][j] = "river";
                            }
                        }
                    }
                    table.setModel(dtm);
                    dtm.setDataVector(boardName, columns);// refresh the data in the JTable

                    //if someone wins, finish game
                     if(c == 1 || c == 2) {
                         initGui();
                     }
                    if (getTurn() == 0) {
                        System.out.println(player1.getName() + Message.MSG_INPUT_COMMAND.getMessageStr());
                        JOptionPane.showMessageDialog(null, player1.getName() + Message.MSG_INPUT_COMMAND.getMessageStr());
                    } else {
                        System.out.println(player2.getName() + Message.MSG_INPUT_COMMAND.getMessageStr());
                        JOptionPane.showMessageDialog(null, player2.getName() + Message.MSG_INPUT_COMMAND.getMessageStr());
                    }
                }
            }
        });

        //input the names of player in the dialog box
        System.out.println(Message.MSG_NAMEX.getMessageStr());
        String inputValueX = JOptionPane.showInputDialog(Message.MSG_NAMEX.getMessageStr());
        player1.setPlayer1(inputValueX);

        System.out.println(Message.MSG_NAMEY.getMessageStr());
        String inputValueY = JOptionPane.showInputDialog(Message.MSG_NAMEY.getMessageStr());
        player2.setPlayer2(inputValueY);

        //remind player X to move
        System.out.println(player1.getName() + Message.MSG_INPUT_COMMAND.getMessageStr());
        JOptionPane.showMessageDialog(null, player1.getName() + Message.MSG_INPUT_COMMAND.getMessageStr());

    }

    static void execute(String string) {
        String[] str = string.split(" ");
        Console.Command command = Console.Command.getByString(str[0]);
        if (command != null) {
            switch (command) {
                case CMD_MOVE:
                    move(str[1], str[2]);
                    break;
            }
        } else {
            System.out.println(Message.MSG_INVALID_INPUT.getMessageStr());
            JOptionPane.showMessageDialog(null, Message.MSG_INVALID_INPUT.getMessageStr());

        }
    }

    public static boolean move(String oldPosition, String newPosition) {
        int oldCol = oldPosition.charAt(0) - 'A';
        int oldRow = '9' - oldPosition.charAt(1);
        Piece piece = board[oldRow][oldCol].getPiece();
        return move(piece, newPosition);
    }

    public static boolean move(Piece piece, String newPosition) {

        if (!isValidMove(piece, newPosition)) return false;

        int row = piece.getRowIdx();
        int column = piece.getColIdx();
        int colNext = newPosition.charAt(0) - 'A';
        int rowNext = '9' - newPosition.charAt(1);

        Piece p = board[rowNext][colNext].getPiece();

        if (p == null) {    // if new position is null
            if (board[rowNext][colNext].myDen(piece.getPlayer())) {
                System.out.println("You can not enter your own den");
                JOptionPane.showMessageDialog(null, "You can not enter your own den");
                return false;
            } else if (!board[rowNext][colNext].isRiver()) {  // not river
                board[rowNext][colNext].setPiece(piece);
                board[row][column].setPiece(null);
                changeTurn();
                return true;
            } else {                                     // null and river
                if (piece.canSwim()) {
                    board[rowNext][colNext].setPiece(piece);
                    board[row][column].setPiece(null);
                    changeTurn();
                    return true;
                } else {
                    System.out.println("This piece can not move into river.");
                    JOptionPane.showMessageDialog(null, "This piece can not move into river.");

                    return false;
                }
            }
        } else { // there is a piece in new position
            if (p.getPlayer() == piece.getPlayer()) {
                JOptionPane.showMessageDialog(null, "Two pieces can not be in the same grid.");
                System.out.println("Two pieces can not be in the same grid.");
                return false;
            } else {
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
                } else {
                    if (board[rowNext][colNext].isRiver()) {
                        System.out.println("This piece can not move into river.");
                        JOptionPane.showMessageDialog(null, "This piece can not move into river.");
                        return false;
                    } else if (board[rowNext][colNext].myTrap(piece.getPlayer())) { //enemy in my trap
                        p.setIsCaptured();
                        System.out.println("status:" + p.getStatus());
                        board[rowNext][colNext].setPiece(piece);
                        board[row][column].setPiece(null);
                        changeTurn();
                        return true;
                    } else if (piece.canCapture(p)) {
                        p.setIsCaptured();
                        System.out.println("status:" + p.getStatus());
                        board[rowNext][colNext].setPiece(piece);
                        board[row][column].setPiece(null);
                        System.out.println(board[row][column].getPiece() + board[rowNext][colNext].getPiece().name());
                        changeTurn();
                        return true;
                    } else {
                        System.out.println("can not capture that");
                        JOptionPane.showMessageDialog(null, "can not capture that");
                        return false;
                    }
                }
            }
        }
        changeTurn();
        return true;
    }

    public static boolean isValidMove(Piece piece, String newPosition) {
        System.out.println(piece);
        System.out.println(piece.getPlayer());
        if (piece.getPlayer().getMyTurn() != turn) { // move the other player's piece
            System.out.print("It is not your turn.");
            JOptionPane.showMessageDialog(null, "It is not your turn.");
            return false;
        }
        int row = piece.getRowIdx();
        int column = piece.getColIdx();
        int rowNext = '9' - newPosition.charAt(1);
        int colNext = newPosition.charAt(0) - 'A';

        if (rowNext < 0 || rowNext > 8 || colNext < 0 || colNext > 6) {
            System.out.println("Move beyond the board. Please try again");
            JOptionPane.showMessageDialog(null, "Move beyond the board. Please try again");
            return false;
        }

        if ((Math.abs(row - rowNext) == 1 && Math.abs(column - colNext) == 0) || (Math.abs(row - rowNext) == 0 && Math.abs(column - colNext) == 1))
            return true;

        if ((Math.abs(row - rowNext) >= 1 && Math.abs(column - colNext) >= 1) || (Math.abs(row - rowNext) == 0 && Math.abs(column - colNext) == 0)) {
            System.out.println("Each piece moves one square horizontally or vertically (not diagonally) and you must move.");
            JOptionPane.showMessageDialog(null, "Each piece moves one square horizontally or vertically (not diagonally) and you must move.");
            return false;
        }

        if (piece.canJumpRiver() && (Math.abs(row - rowNext) == 4)) {
            if ((row == 2 || row == 6) && (column > 0 && column < 6 && column != 3)) {
                if ((Rat1.isInTheRiver() && Rat1.getColIdx() == column) || (Rat2.isInTheRiver() && Rat2.getColIdx() == column))
                    return false;
                else return true;
            }
        }

        if (piece.canJumpRiver() && (Math.abs(column - colNext) == 3)) {
            if ((column == 0 || column == 3 || column == 6) && (row > 2 && row < 6)) {
                if (Rat1.isInTheRiver() && Rat1.getRowIdx() == row) {
                    if (colNext > column && (colNext - Rat1.getColIdx()) < 3 && (colNext - Rat1.getColIdx()) > 0)
                        return false;
                    if (colNext < column && (Rat1.getColIdx() - colNext) < 3 && (Rat1.getColIdx() - colNext) > 0)
                        return false;
                    return true;
                } else if (Rat2.isInTheRiver() && Rat2.getRowIdx() == row) {
                    if (colNext > column && (colNext - Rat2.getColIdx()) < 3 && (colNext - Rat2.getColIdx()) > 0)
                        return false;
                    if (colNext < column && (Rat2.getColIdx() - colNext) < 3 && (Rat2.getColIdx() - colNext > 0))
                        return false;
                    return true;
                } else return true;
            }
        }

        System.out.println("Each piece moves one square horizontally or vertically (not diagonally) and you must move.");
        JOptionPane.showMessageDialog(null, "Each piece moves one square horizontally or vertically (not diagonally) and you must move.");

        return false;
    }

    public static int check() { // 0 - in game; 1 - X win; 2 - Y win
        if (Rat1.getStatus() == false && Cat1.getStatus() == false && Dog1.getStatus() == false && Elephant1.getStatus() == false
                && Leopard1.getStatus() == false && Lion1.getStatus() == false && Tiger1.getStatus() == false && Wolf1.getStatus() == false) {
            return 1;
        } else if (Rat2.getStatus() == false && Cat2.getStatus() == false && Dog2.getStatus() == false && Elephant2.getStatus() == false
                && Leopard2.getStatus() == false && Lion2.getStatus() == false && Tiger2.getStatus() == false && Wolf2.getStatus() == false) {
            return 2;
        } else if (board[0][3].getPiece() != null) {
            return 1;
        } else if (board[8][3].getPiece() != null) {
            return 2;
        }
        return 0;
    }

    //open saved path, read
    private static void openSavePath() throws IOException {

        FileDialog openFile;
        openFile = new FileDialog(frame, "open file", FileDialog.LOAD);
        openFile.setVisible(true);
        String dirName = openFile.getDirectory();
        String fileName = openFile.getFile();
        File file = new File(dirName + fileName);
        System.out.println(dirName + fileName);

        FileInputStream fis = null;
        fis = new FileInputStream(file);
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        String string = new String(data);
        int num = 0;
        String[] a = string.split(";");
        for (int i = 0; i < a.length; i++) {
            String[] b = a[i].split(",");
            if (b[0].equals("1") && b[3].equals("Y")) { //RAT2
                Player player = new Player(b[3].charAt(0), b[4], Integer.valueOf(b[5]));
                Rat2 = new Rat(b[2], player);
            } else if (b[0].equals("1") && b[3].equals("X")) { //RAT1
                Player player = new Player(b[3].charAt(0), b[4], Integer.valueOf(b[5]));
                Rat1 = new Rat(b[2], player);
            } else if (b[0].equals("2") && b[3].equals("Y")) { //CAT2
                Player player = new Player(b[3].charAt(0), b[4], Integer.valueOf(b[5]));
                Cat2 = new Cat(b[2], player);
            } else if (b[0].equals("2") && b[3].equals("X")) { //CAT1
                Player player = new Player(b[3].charAt(0), b[4], Integer.valueOf(b[5]));
                Cat1 = new Cat(b[2], player);
            } else if (b[0].equals("3") && b[3].equals("Y")) { //DOG2
                Player player = new Player(b[3].charAt(0), b[4], Integer.valueOf(b[5]));
                Dog2 = new Dog(b[2], player);
            } else if (b[0].equals("3") && b[3].equals("X")) { //DOG1
                Player player = new Player(b[3].charAt(0), b[4], Integer.valueOf(b[5]));
                Dog1 = new Dog(b[2], player);
            } else if (b[0].equals("4") && b[3].equals("Y")) { //Wolf2
                Player player = new Player(b[3].charAt(0), b[4], Integer.valueOf(b[5]));
                Wolf2 = new Wolf(b[2], player);
            } else if (b[0].equals("4") && b[3].equals("X")) { //Wolf1
                Player player = new Player(b[3].charAt(0), b[4], Integer.valueOf(b[5]));
                Wolf1 = new Wolf(b[2], player);
            } else if (b[0].equals("5") && b[3].equals("Y")) { //Leopard2
                Player player = new Player(b[3].charAt(0), b[4], Integer.valueOf(b[5]));
                Leopard2 = new Leopard(b[2], player);
            } else if (b[0].equals("5") && b[3].equals("X")) { //Leopard1
                Player player = new Player(b[3].charAt(0), b[4], Integer.valueOf(b[5]));
                Leopard1 = new Leopard(b[2], player);
            } else if (b[0].equals("6") && b[3].equals("Y")) { //Tiger2
                Player player = new Player(b[3].charAt(0), b[4], Integer.valueOf(b[5]));
                Tiger2 = new Tiger(b[2], player);
            } else if (b[0].equals("6") && b[3].equals("X")) { //Tiger1
                Player player = new Player(b[3].charAt(0), b[4], Integer.valueOf(b[5]));
                Tiger1 = new Tiger(b[2], player);
            } else if (b[0].equals("7") && b[3].equals("Y")) { //Lion2
                Player player = new Player(b[3].charAt(0), b[4], Integer.valueOf(b[5]));
                Lion2 = new Lion(b[2], player);
            } else if (b[0].equals("7") && b[3].equals("X")) { //Lion1
                Player player = new Player(b[3].charAt(0), b[4], Integer.valueOf(b[5]));
                Lion1 = new Lion(b[2], player);
            } else if (b[0].equals("8") && b[3].equals("Y")) { //Elephant2
                Player player = new Player(b[3].charAt(0), b[4], Integer.valueOf(b[5]));
                Elephant2 = new Elephant(b[2], player);
            } else if (b[0].equals("8") && b[3].equals("X")) { //Elephant1
                Player player = new Player(b[3].charAt(0), b[4], Integer.valueOf(b[5]));
                Elephant1 = new Elephant(b[2], player);
            }
        }

        turn = Integer.valueOf(a[a.length - 1]);
        System.out.println("turn:" + turn);
        //initialize the value of board grid
        for (int i = 0; i < NUMBER_OF_ROW; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
                board[i][j] = new BoardGrid(i, j);
            }
        }

        //initialize the position of piece, trap and den
        board[Elephant2.getRowIdx()][Elephant2.getColIdx()].setPiece(Elephant2);
        board[Elephant1.getRowIdx()][Elephant1.getColIdx()].setPiece(Elephant1);
        board[Dog2.getRowIdx()][Dog2.getColIdx()].setPiece(Dog2);
        board[Dog1.getRowIdx()][Dog1.getColIdx()].setPiece(Dog1);
        board[Cat2.getRowIdx()][Cat2.getColIdx()].setPiece(Cat2);
        board[Cat1.getRowIdx()][Cat1.getColIdx()].setPiece(Cat1);
        board[Leopard2.getRowIdx()][Leopard2.getColIdx()].setPiece(Leopard2);
        board[Leopard1.getRowIdx()][Leopard1.getColIdx()].setPiece(Leopard1);
        board[Tiger2.getRowIdx()][Tiger2.getColIdx()].setPiece(Tiger2);
        board[Tiger1.getRowIdx()][Tiger1.getColIdx()].setPiece(Tiger1);
        board[Wolf2.getRowIdx()][Wolf2.getColIdx()].setPiece(Wolf2);
        board[Wolf1.getRowIdx()][Wolf1.getColIdx()].setPiece(Wolf1);
        board[Lion2.getRowIdx()][Lion2.getColIdx()].setPiece(Lion2);
        board[Lion1.getRowIdx()][Lion1.getColIdx()].setPiece(Lion1);
        board[Rat2.getRowIdx()][Rat2.getColIdx()].setPiece(Rat2);
        board[Rat1.getRowIdx()][Rat1.getColIdx()].setPiece(Rat1);

        for (int i = 0; i < NUMBER_OF_ROW; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
                if (board[i][j].getPiece() != null) {
                    boardName[i][j] = board[i][j].getPiece().name() + "-" + board[i][j].getPiece().getPlayer().getMark();
                } else {
                    boardName[i][j] = null;
                }
            }
        }
        if(boardName[0][2] == null) {boardName[0][2] = "trap-Y";}
        if(boardName[0][4] == null) {boardName[0][4] = "trap-Y";}
        if(boardName[1][3] == null) {boardName[1][3] = "trap-Y";}
        if(boardName[8][2] == null) {boardName[8][2] = "trap-X";}
        if(boardName[8][4] == null) {boardName[8][4] = "trap-X";}
        if(boardName[7][3] == null) {boardName[7][3] = "trap-X";}
        if(boardName[0][3] == null) {boardName[0][3] = "den-Y";}
        if(boardName[8][3] == null) {boardName[8][3] = "den-X";}
        for (int i = 3; i < 6; i++) {
            for (int j = 1; j < 6; j++) {
                if (j != 3) {
                    if(boardName[i][j] == null) boardName[i][j] = "river";
                }
            }
        }
        dtm = new DefaultTableModel(boardName, columns) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(dtm);
        dtm.setDataVector(boardName, columns);// refresh JTable data
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (isFirst) {    //first click
                    int r = table.getSelectedRow();
                    int c = table.getSelectedColumn();
                    if(board[r][c].getPiece() == null)  {   //don't click any piece in the first click
                        JOptionPane.showMessageDialog(null, "first-click must be one animal piece!");
                        isFirst = true;
                        return;
                    }
                    //convert horizontal ordinate to A-G, vertical ordinate to1-9
                    mousePosition1 = columns[c] + String.valueOf(NUMBER_OF_ROW - r);
                    isFirst = false;
                } else {
                    int r = table.getSelectedRow();
                    int c = table.getSelectedColumn();
                    mousePosition2 = columns[c] + String.valueOf(NUMBER_OF_ROW - r);
                    isFirst = true;
                }
                if (isFirst) {   //finish the second click
                    String s = "move" + " " + mousePosition1 + " " + mousePosition2;
                    System.out.println(s);
                    execute(s);
                    int c = 0;
                    c = check();     //0 - in game; 1 - X win; 2 - Y win
                    if (c == 1) {
                        System.out.println(Message.MSG_WINNER.getMessageStr() + Cat1.getPlayer().getName());
                        JOptionPane.showConfirmDialog(null, Message.MSG_WINNER.getMessageStr() + Cat1.getPlayer().getName(), "move", JOptionPane.YES_NO_OPTION);
                    } else if (c == 2) {
                        System.out.println(Message.MSG_WINNER.getMessageStr() + Cat2.getPlayer().getName());
                        JOptionPane.showConfirmDialog(null, Message.MSG_WINNER.getMessageStr() + Cat2.getPlayer().getName(), "move", JOptionPane.YES_NO_OPTION);
                    }
                    for (int i = 0; i < NUMBER_OF_ROW; i++) {
                        for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
                            if (board[i][j].getPiece() != null) {
                                boardName[i][j] = board[i][j].getPiece().name() + "-" + board[i][j].getPiece().getPlayer().getMark();
                            } else {
                                boardName[i][j] = null;
                            }
                        }
                    }
                    table.setModel(dtm);
                    dtm.setDataVector(boardName, columns);// refresh JTable data

                    //if someone wins, print the winner name
                    if(c == 1 || c == 2) {
                        initGui();
                    }
                    if (getTurn() == 0) {
                        System.out.println(Cat1.getPlayer().getName() + Message.MSG_INPUT_COMMAND.getMessageStr());
                        JOptionPane.showConfirmDialog(null, Cat1.getPlayer().getName() + Message.MSG_INPUT_COMMAND.getMessageStr(), "X move", JOptionPane.YES_NO_OPTION);
                    } else {
                        System.out.println(Cat2.getPlayer().getName() + Message.MSG_INPUT_COMMAND.getMessageStr());
                        JOptionPane.showConfirmDialog(null, Cat2.getPlayer().getName() + Message.MSG_INPUT_COMMAND.getMessageStr(), "Y move", JOptionPane.YES_NO_OPTION);
                    }
                }
            }
        });

        JOptionPane.showMessageDialog(null, "read success！");
    }

    //save
    private static void selectSavePath() {

        FileDialog openFile;
        openFile = new FileDialog(frame, "save file", FileDialog.LOAD);
        openFile.setVisible(true);
        String dirName = openFile.getDirectory();
        String fileName = openFile.getFile();
        System.out.println(dirName + fileName);
        File file = new File(dirName + fileName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < NUMBER_OF_ROW; i++) {
                for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
                    if (board[i][j].getPiece() != null) {
                        stringBuilder.append(board[i][j].getPiece().getRank() + "," + board[i][j].getPiece().getStatus()
                                + "," + board[i][j].getPiece().getPosition() + "," + board[i][j].getPiece().getPlayer().getMark()
                                + "," + board[i][j].getPiece().getPlayer().getName() + "," + board[i][j].getPiece().getPlayer().getMyTurn());
                        stringBuilder.append(";");
                    }
                }
            }
            System.out.println("haha" + turn);
            stringBuilder.append(turn);
            String t = stringBuilder.toString();
            byte[] data = t.getBytes();
            fos.write(data, 0, data.length);
            JOptionPane.showMessageDialog(null, "save success！");
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
