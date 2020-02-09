package hk.edu.polyu.comp.comp2021.jungle.view;

public enum  Message {

    NEW_OR_SAVED("Welcome to Jungle game.\n new game? input 1\n saved game? input 2\n"),
    MSG_INVALID_INPUT("Invalid input!"),
    MSG_NAMEX("Please input player X's mame"),
    MSG_NAMEY("Please input player Y's mame"),
    MSG_INPUT_COMMAND(", please input a command:"),
    MSG_WINNER("The winner is ");

    private String messageStr;

    Message(String messageStr) {
        this.messageStr = messageStr;
    }

    public String getMessageStr() {
        return messageStr;
    }

}

