public class MarkCommand extends Command {
    private final int index;
    public MarkCommand(int index) {
        super(CommandType.MARK);
        this.index = index;
    }

    @Override
    public void execute(Library library, Ui ui, Storage storage) {
        ui.printMessage(library.markTask(this.index));
    }
}
