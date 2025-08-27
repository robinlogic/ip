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
