package dyke.command;

import dyke.data.Library;
import dyke.parse.CommandType;
import dyke.parse.Storage;
import dyke.ui.Ui;

public class DeleteCommand  extends Command {
    private final int index;
    public DeleteCommand(int index){
        super(CommandType.DELETE);
        this.index = index;
    }

    @Override
    public void execute(Library library, Ui ui, Storage storage) {
        ui.printMessage(library.deleteTask(this.index));
    }
}
