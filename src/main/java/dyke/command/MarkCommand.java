package dyke.command;

import dyke.data.Library;
import dyke.parse.CommandType;
import dyke.parse.Storage;
import dyke.ui.Ui;

public class MarkCommand extends Command {
    private final int index;
    public MarkCommand(int index) {
        super(CommandType.MARK);
        this.index = index;
    }

    @Override
    public String execute(Library library, Ui ui, Storage storage) {
        String msg = library.markTask(this.index);
        ui.printMessage(msg);
        return msg;
    }
}
