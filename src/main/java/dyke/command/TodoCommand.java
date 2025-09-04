package dyke.command;

import dyke.data.Library;
import dyke.parse.CommandType;
import dyke.parse.DykeException;
import dyke.parse.Storage;
import dyke.tasks.Todo;
import dyke.ui.Ui;

/**
 * Houses methods for {@code TodoCommand}
 */
public class TodoCommand extends Command {
    private final String desc;

    /**
     * Creates {@code TodoCommand}
     * @param desc Description of the task
     */
    public TodoCommand(String desc) {
        super(CommandType.TODO);
        this.desc = desc;

    }

    @Override
    public String execute(Library library, Ui ui, Storage storage) {
        try {
            String msg = handleTodo(desc, library);
            ui.printMessage(msg);
            return msg;
        } catch (DykeException e) {
            ui.printMessage(e.getMessage());
            return e.getMessage();
        }
    }

    private String handleTodo(String desc, Library library) throws DykeException {
        if (desc.isBlank()) {
            throw new DykeException("Phineas, I don't know what we gonna do today!");
        }
        String description = desc.trim();
        Todo todo = new Todo(description);
        return library.addTask(todo);
    }
}
