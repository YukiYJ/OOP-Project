package hk.edu.polyu.comp.comp2021.jungle.view;

import hk.edu.polyu.comp.comp2021.jungle.model.*;
import java.util.Scanner;

public class Console {
    public enum Command {
        CMD_SAVE("save"),
        CMD_OPEN("open"),
        CMD_MOVE("move");

        private String commandStr;

        Command(String commandStr) {
            this.commandStr = commandStr;
        }

        public String getCommandStr() {
            return commandStr;
        }

        public static Command getByString(String inputStr) {
            for (Command cmd : Command.values()) {
                if (cmd.getCommandStr().equalsIgnoreCase(inputStr.toLowerCase()))
                    return cmd;
            }
            return null;
        }
    }

    private JungleGame game;

    public Console(JungleGame game) {
        this.game = game;
    }

    private void execute(String string){
        String[] str = string.split(" ");
        Command command = Command.getByString(str[0]);
        if (command != null){
            switch (command) {
                case CMD_MOVE:
                    System.out.println(game.move(str[1],str[2]));;
                    break;
            }
        }
        else {
            System.out.println(Message.MSG_INVALID_INPUT.getMessageStr());
        }
    }

    private void start(int a){
        if(a==1)
            game.openNew();
    }

    public void run(){
        //Step1
        System.out.println(Message.NEW_OR_SAVED.getMessageStr());
        Scanner scanner = new Scanner(System.in);
        int n= scanner.nextInt();
        scanner.nextLine();
        while (n!=1 && n!=2) {
            System.out.println(Message.MSG_INVALID_INPUT.getMessageStr());
            n = scanner.nextInt();
            scanner.nextLine();
        }
        start(n);

        //Step2
        System.out.println(Message.MSG_NAMEX.getMessageStr());
        String nameX = scanner.nextLine();
        game.setPlayer1(nameX);

        System.out.println(Message.MSG_NAMEY.getMessageStr());
        String nameY = scanner.nextLine();
        game.setPlayer2(nameY);

        game.print();

        //Step3:
        System.out.println(nameX+Message.MSG_INPUT_COMMAND.getMessageStr());
        String s = scanner.nextLine();
        execute(s);

        int c = 0;
        while(c == 0){
            game.print();
            if (game.getTurn() == 0) {
                System.out.println(nameX+Message.MSG_INPUT_COMMAND.getMessageStr());
            } else {
                System.out.println(nameY+Message.MSG_INPUT_COMMAND.getMessageStr());
            }
            s = scanner.nextLine();
            execute(s);
            c = game.check();
        }
        if(c == 1){
            game.print();
            System.out.println(Message.MSG_WINNER.getMessageStr()+nameX);
        }
        else if(c == 2){
            game.print();
            System.out.println(Message.MSG_WINNER.getMessageStr()+nameY);
        }
    }
}

