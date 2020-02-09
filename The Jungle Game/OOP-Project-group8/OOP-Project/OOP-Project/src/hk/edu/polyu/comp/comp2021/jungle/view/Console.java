package hk.edu.polyu.comp.comp2021.jungle.view;

import hk.edu.polyu.comp.comp2021.jungle.model.*;

import java.io.*;
import java.util.Scanner;

/**
 * console for console
 */
public class Console {
    /**
     * enum class for command
     */
    public enum Command {
        /**
         * save command
         */
        CMD_SAVE("save"),
        /**
         * open command
         */
        CMD_OPEN("open"),
        /**
         * move command
         */
        CMD_MOVE("move"),
        /**
         *  exit command
         */
        CMD_EXIT("exit");
        private String commandStr;

        Command(String commandStr) {
            this.commandStr = commandStr;
        }

        /**
         *
         * @return the command str
         */
        public String getCommandStr() {
            return commandStr;
        }

        /**
         *
         * @param inputStr input content
         * @return the command line
         */
        public static Command getByString(String inputStr) {
            for (Command cmd : Command.values()) {
                if (cmd.getCommandStr().equalsIgnoreCase(inputStr.toLowerCase()))
                    return cmd;
            }
            return null;
        }
    }

    private boolean saved;
    private String nameX, nameY;
    private JungleGame game;

    /**
     *
     * @param game the jungle game
     */
    public Console(JungleGame game) {
        this.game = game;
        saved = false;
    }

    /**
     * save the game in the provided file path
     * @param path the path to save
     * @param toSave string to save
     * @return a boolean value indicating whether the game is saved successfully
     */
    private boolean save(String path, String toSave) {
        try {
            File file = new File(path);
            if (!file.exists()) file.createNewFile();
            System.out.println(path+" File Created");
            FileWriter fw = new FileWriter(file);
            fw.write(toSave);
            fw.close();
            System.out.println("Save successfully!");
            saved = true;
            return true;
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            System.out.println(Message.MSG_INVALID_INPUT.getMessageStr()+" (no file found!)");
            return false;
        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println(Message.MSG_INVALID_INPUT.getMessageStr()+" (IOException!)");
            return false;
        }
    }


    /**
     * open a saved game file in the provided file path
     * @param path to the game
     * @return the content
     */
    private String open(String path) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null)
                contentBuilder.append(line).append("\n");
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return contentBuilder.toString();
    }

    /**
     *
     * @return whether save prompt
     */
    private boolean savePrompt() {
        System.out.println(Message.MSG_UNSAVED.getMessageStr());
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        scanner.nextLine();
        while (input != 1 &&  input != 2) {
            System.out.println(Message.MSG_INVALID_INPUT.getMessageStr());
            input = scanner.nextInt();
            scanner.nextLine();
        }
        return input == 2; // continue - true
    }

    /**
     *
     * @param string what to execute
     * @return whether to execute
     */
    private boolean execute(String string){
        String[] str = string.split(" ");
        Command command = Command.getByString(str[0]);
        if (command != null){
            switch (command) {
                case CMD_SAVE:
                    return save(str[1], game.toString());
                case CMD_OPEN:
                    if (!saved && !savePrompt()) return true;
                    String content = open(str[1]);
                    if (content != null) {
                        game = new JungleGame();
                        game.openSaved(content);
                        nameX = game.getPlayer1().getName();
                        nameY = game.getPlayer2().getName();
                        return true;}
                    return false;
                case CMD_MOVE:
                    boolean b = game.move(str[1],str[2]);
                    game.print();
                    return b;
                case CMD_EXIT:
                    System.exit(0);
            }
        }
        return false;
    }

    /**
     * start the game
     */
    private void start() {
        System.out.println(Message.NEW_OR_SAVED.getMessageStr());
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        scanner.nextLine();

        while (input != 1 && input != 2) {
            System.out.println(Message.MSG_INVALID_INPUT.getMessageStr());
            input = scanner.nextInt();
            scanner.nextLine();
        }
        if (input == 1) {
            game.openNew();
            System.out.println(Message.MSG_NAMEX.getMessageStr());
            nameX = scanner.nextLine();
            game.setPlayer1(nameX);
            System.out.println(Message.MSG_NAMEY.getMessageStr());
            nameY = scanner.nextLine();
            game.setPlayer2(nameY);
        } else {
            System.out.println(Message.MSG_FILEPATH.getMessageStr());
            String path = scanner.nextLine();
            String content = open(path);
            while (content == null) {
                System.out.println(Message.MSG_INVALID_INPUT.getMessageStr());
                path = scanner.nextLine();
                content = open(path);
            }
            game.openSaved(content);
            nameX = game.getPlayer1().getName();
            nameY = game.getPlayer2().getName();
        }
    }

    /**
     * run the game
     */
    public void run(){
        // step1: select a new game / open a saved game
        start();

        //Step2: prompt user to input command and return corresponding result
        Scanner scanner = new Scanner(System.in);
        int c = 0;
        game.print();
        while(c == 0){
            if (game.getTurn() == 0) {
                System.out.println(nameX+Message.MSG_INPUT_COMMAND.getMessageStr());
            } else {
                System.out.println(nameY+Message.MSG_INPUT_COMMAND.getMessageStr());
            }
            String s = scanner.nextLine();
            boolean e = execute(s);
            while (!e) {
                System.out.println(Message.MSG_INVALID_INPUT.getMessageStr());
                s = scanner.nextLine();
                e = execute(s);
            }
            c = game.check();
        }
        if(c == 1){
            System.out.println(Message.MSG_WINNER.getMessageStr()+nameX);
        }
        else if(c == 2){
            System.out.println(Message.MSG_WINNER.getMessageStr()+nameY);
        }
    }
}