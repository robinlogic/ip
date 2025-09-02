package dyke.command;

import dyke.data.Library;
import dyke.parse.CommandType;
import dyke.parse.DykeException;
import dyke.parse.Storage;
import dyke.tasks.Deadline;
import dyke.ui.Ui;

public class DeadlineCommand extends Command {
    private final String desc;
    public DeadlineCommand(String desc) {
        super(CommandType.DEADLINE);
        this.desc = desc;
    }

    @Override
    public String execute(Library library, Ui ui, Storage storage) {
        try {
            String msg = handleDeadline(library);
            ui.printMessage(msg);
            return msg;
        } catch (DykeException e) {
            ui.printMessage(e.getMessage());
            return e.getMessage();
        }
    }

    private String handleDeadline(Library library) throws DykeException {
        if (desc.isBlank()) {
            throw new DykeException("I think you forgot the description...");
        }

        // Split input string with "/by"
        String[] parts = desc.split("/by", 2);

        // Checking if day-time is given
        if (parts.length != 2 || parts[1].isBlank()) {
            throw new DykeException("When should this be done by?");
        }

        String activity = parts[0].trim();
        String by = parts[1].trim();

        Deadline deadline  =  new Deadline(activity, by);
        return library.addTask(deadline);
    }
}
