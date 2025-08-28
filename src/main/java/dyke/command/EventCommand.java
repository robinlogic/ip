package dyke.command;

import dyke.data.Library;
import dyke.parse.CommandType;
import dyke.parse.DykeException;
import dyke.parse.Storage;
import dyke.tasks.Event;
import dyke.ui.Ui;

public class EventCommand extends Command {
    private final String desc;
    public EventCommand(String desc) {
        super(CommandType.EVENT);
        this.desc = desc;
    }

    @Override
    public void execute(Library library, Ui ui, Storage storage) {
        try {
            ui.printMessage(handleEvent(library));
        } catch (DykeException e) {
            ui.printMessage(e.getMessage());
        }
    }

    private String handleEvent(Library library) throws DykeException {
        if (desc.isBlank()) {
            throw new DykeException("What is this event about?");
        }

        // Split on /from to get dyke.tasks.Event and Period
        String[] fromSplit = desc.split("/from", 2);
        String activity = fromSplit[0].trim();

        // Checking if From day-time given
        if (fromSplit.length != 2) {
            throw new DykeException("You know, I kinda gotta know when it's gonna happen." +
                    "\n\t format: /from *day-time* /to *day-time*");
        }
        String[] toSplit = fromSplit[1].split("/to", 2);
        String from = toSplit[0].trim();

        // Checking if To day-time given
        if (toSplit.length != 2) {
            throw new DykeException("You know, I kinda gotta know when it's gonna happen." +
                    "\n\t format: /from *day-time* /to *day-time*");
        }

        String to = (toSplit.length > 1) ? toSplit[1].trim() : "";

        Event event = new Event(activity, from, to);
        return library.addTask(event);
    }
}
