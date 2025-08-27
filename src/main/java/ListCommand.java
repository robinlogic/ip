public class ListCommand extends Command {
    public ListCommand() {
        super(CommandType.LIST);
    }

    @Override
    public void execute(Library library, Ui ui, Storage storage) {
        ui.printMessage(library.PrintList());
    }
}
