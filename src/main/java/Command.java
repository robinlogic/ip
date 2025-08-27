public abstract class Command {
    protected CommandType command;

    public Command(CommandType command) {
        this.command = command;
    }

    public CommandType getCommand() {
        return command;
    }

    public abstract void execute(Library library, Ui ui, Storage storage);
}
