package dyke.command;

import dyke.data.Library;
import dyke.parse.CommandType;
import dyke.parse.Storage;
import dyke.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super(CommandType.LIST);
    }

    @Override
    public void execute(Library library, Ui ui, Storage storage) {
        ui.printMessage(library.PrintList());
    }
}
