package dyke.command;

import dyke.data.Library;
import dyke.parse.CommandType;
import dyke.parse.DykeException;
import dyke.parse.Storage;
import dyke.tasks.Task;
import dyke.ui.Ui;

public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String keyword) {
        super(CommandType.FIND);
        this.keyword = keyword;
    }

    @Override
    public void execute(Library library, Ui ui, Storage storage) {
        try {
            ui.printMessage(this.handleFind(keyword, library));
        } catch (DykeException e) {
            ui.printMessage(e.getMessage());
        }
    }

    private String handleFind(String keyword, Library library) throws DykeException {
        if (keyword.equals(" ") || keyword.isEmpty()) {
            throw new DykeException("i'm sorry, i did not get that :(");
        }
        Library findLibrary = new Library();
        for (Task t : library.getTasks()) {
            if (t.encode().contains(keyword)) {
                findLibrary.addTask(t);
            }
        }
        int size = findLibrary.size();
        return size > 0 ? findLibrary.printList() :
                "There are no tasks with the keyword: " + keyword;
    }
}
