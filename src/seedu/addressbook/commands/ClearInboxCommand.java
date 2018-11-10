//@@author ongweekeong
package seedu.addressbook.commands;

import seedu.addressbook.inbox.*;
import seedu.addressbook.password.Password;

import java.io.IOException;
import java.util.TreeSet;

/**
 * Clears the text file storing the messages sent to user.
 */

public class ClearInboxCommand extends Command {
    public static final String COMMAND_WORD = "clearinbox";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Clears all existing messages in user's inbox. \n\t"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_CLEARINBOX_SUCCESSFUL = "Inbox cleared!";
    public static final String MESSAGE_CLEARINBOX_UNSUCCESSFUL = "Unable to clear inbox. Ensure that the file exists.";

    private String myInbox;

    public ClearInboxCommand(){

    }
    public ClearInboxCommand(String filepath){
        myInbox = filepath;
    }

    @Override
    public CommandResult execute() {

        try {
            if(myInbox == null) {
                myInbox = MessageFilePaths.getFilePathFromUserId(Password.getID());
            }
            ReadNotification dummyReader = new ReadNotification(myInbox);
            dummyReader.ReadFromFile(); // Exception thrown if file does not exist
            WriteNotification.clearInbox(myInbox);
            Inbox.clearInboxRecords();
            return new CommandResult(MESSAGE_CLEARINBOX_SUCCESSFUL);
        }
        catch (IOException e){
            return new CommandResult(MESSAGE_CLEARINBOX_UNSUCCESSFUL);
        }

    }
}
