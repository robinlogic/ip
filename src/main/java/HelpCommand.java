public class HelpCommand  extends Command {
    public HelpCommand() {
        super(CommandType.HELP);
    }

    @Override
    public void execute(Library library, Ui ui, Storage storage) {
        String helpDesk = " COMMANDS I /actually/ understand:\n\n" +
                " DAY-TIME Format: yyyy-MM-dd hh:mm\n\n" +
                " Main COMMANDS:\n" +
                " 1. LIST #to see all your tasks in the Library\n" +
                " 2. BYE #I'll talk to you later\n" +
                " 3. HELP #you literally just typed this...\n" +
                " 4. WHAT? #surprise...\n" +
                "\n Library Commands:\n" +
                " 1. TODO *insert activity*\n" +
                " 2. DEADLINE *day-time*\n" +
                " 3. EVENT *activity* /from *day-time* /to *day-time*\n" +
                " 4. MARK *task number*\n" +
                " 5. DELETE *task number*";
        ui.printMessage(helpDesk);
    }
}
