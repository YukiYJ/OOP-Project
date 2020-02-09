package hk.edu.polyu.comp.comp2021.jungle.view;

/**
 * enum class for message
 */
public enum Message {
    /**
     * judge new or saved
     */
    NEW_OR_SAVED("Welcome to Jungle game.\n- Start a new game: Enter 1\n- Open a saved game: Enter 2\n"),
    /**
     * invalid input
     */
    MSG_INVALID_INPUT("Invalid input! Please input again:"),
    /**
     * name x
     */
    MSG_NAMEX("Please input player X's mame"),
    /**
     * name y
     */
    MSG_NAMEY("Please input player Y's mame"),
    /**
     * input command
     */
    MSG_INPUT_COMMAND(", please input a command:"),
    /**
     * winner message
     */
    MSG_WINNER("The winner is "),
    /**
     * file path
     */
    MSG_FILEPATH("Please input the path of the file: "),
    /**
     * unsaved
     */
    MSG_UNSAVED("Your current game has not been saved. Are you sure to unsave and open another one?\n" +
            "- No, back to save: Enter 1\n- Yes, continue anyway: Enter 2\n ");

    private String messageStr;

    Message(String messageStr) {
        this.messageStr = messageStr;
    }

    /**
     *
     * @return the message str
     */
    public String getMessageStr() {
        return messageStr;
    }
}

