package dyke.command;

import dyke.data.Library;
import dyke.parse.CommandType;
import dyke.parse.Storage;
import dyke.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        super(CommandType.BYE);
    }
    @Override
    public void execute(Library library, Ui ui, Storage storage) {
        String msg = "Bye, Catch you Later!";
        ui.printMessage(msg);
    }
}
