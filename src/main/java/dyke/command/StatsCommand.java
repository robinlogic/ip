package dyke.command;

import dyke.data.Library;
import dyke.data.Stats;
import dyke.parse.CommandType;
import dyke.parse.Storage;
import dyke.ui.Ui;

public class StatsCommand extends Command {
    public StatsCommand() {
        super(CommandType.STATS);
    }

    @Override
    public String execute(Library library, Ui ui, Storage storage) {
        return new Stats(library).result();
    }
}
