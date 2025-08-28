package dyke.command;

import dyke.data.Library;
import dyke.parse.CommandType;
import dyke.parse.Storage;
import dyke.ui.Ui;

public abstract class Command {
    protected CommandType command;

    public Command(CommandType command) {
        this.command = command;
    }

    public CommandType getCommand() {
        return command;
    }

    /**
     * Executes this command
     * @param library {@code Library} that stores all the tasks.
     * @param ui User Interface for user interaction.
     * @param storage Manages data read from and stored into disk.
     */
    public abstract void execute(Library library, Ui ui, Storage storage);
}
