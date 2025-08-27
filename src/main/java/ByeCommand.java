public class ByeCommand extends Command {
    public ByeCommand() {
        super(CommandType.BYE);
    }
    @Override
    public void execute(Library library, Ui ui, Storage storage) {
        String msg = "Bye, Catch you Later!";
        ui.printMessage(msg);
    }

    public boolean isExit() {
        return true;
    }
}
