public class DeadlineCommand extends Command {
    private final String desc;
    public DeadlineCommand(String desc) {
        super(CommandType.DEADLINE);
        this.desc = desc;
    }

    @Override
    public void execute(Library library, Ui ui, Storage storage) {
        try {
            ui.printMessage(handleDeadline(library));
        } catch (DykeException e) {
            ui.printMessage(e.getMessage());
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
