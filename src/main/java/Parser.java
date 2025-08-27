public class Parser {
    private static final int ACCOUNT_INDEX = 1;
    private boolean isRunning = true;
    private Library library; // Library is Mutable

    public Parser(Library library) {
        this.library = library;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void parseCommand(String[] input) {
        try {
            CommandType command = CommandType.fromString(input[0]);

            switch (command) {

                case BYE:
                    System.out.println("\t Bye. Catch you later!");
                    this.isRunning = false;
                    break;
                case LIST:
                    this.library.PrintList();
                    break;
                case HELP:
                    helpLine();
                    break;
                case MARK:
                    this.library.markTask(Integer.parseInt(input[1])
                            - ACCOUNT_INDEX);
                    break;
                case DEADLINE:
                    try {
                        if (input.length < 2) {
                            throw new DykeException("I think you forgot the" +
                                    " description...");
                        }
                        handleDeadline(input[1]);
                    } catch (DykeException e) {
                        System.out.println("\t " + e.getMessage() + "\n");
                    }
                    break;

                case TODO:
                    try {
                        if (input.length < 2) {
                            throw new DykeException("Phineas, I don't know what " +
                                    "we're gonna do today!");
                        }
                        handleTodo(input[1]);
                    } catch (DykeException e) {
                        System.out.println("\t " + e.getMessage() + "\n");
                    }
                    break;

                case EVENT:
                    try {
                        if (input.length < 2) {
                            throw new DykeException("What is this event about?");
                        }
                        handleEvent(input[1]);
                    } catch (DykeException e) {
                        System.out.println("\t " + e.getMessage() + "\n");
                    }
                    break;

                case DELETE:
                    try {
                        if (input.length < 2) {
                            throw new DykeException("What do you wanna delete?");
                        }
                        handleDelete(input[1]);
                    } catch (DykeException e) {
                        System.out.println("\t " + e.getMessage() + "\n");
                    }
                    break;
                case WHAT:
                    System.out.println("\tWhat do you mean?\n " +
                            "\t\tWhen you nod your head yes,\n" +
                            "\t\t\tBut you wanna say no... \n");
            }
        } catch (DykeException e) {
            System.out.println(e.getMessage());
            System.out.println("\t Dude, IDK whats that??" +
                    " type 'help' for help, bruv.\n" +
                    "\t Or you could just say, 'Bye!', like exactly.");
        }
    }

    private void handleDeadline(String desc) throws DykeException {
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
        this.library.addTask(deadline);
    }

    private void handleEvent(String desc) throws DykeException {
        if (desc.isBlank()) {
            throw new DykeException("What is this event about?");
        }

        // Split on /from to get Event and Period
        String[] fromSplit = desc.split("/from", 2);
        String activity = fromSplit[0].trim();

        // Checking if From day-time given
        if (fromSplit.length != 2) {
            throw new DykeException("You know, I kinda gotta know when it's gonna happen." +
                    "\t format: /from *day-time* /to *day-time*");
        }
        String[] toSplit = fromSplit[1].split("/to", 2);
        String from = toSplit[0].trim();

        // Checking if To day-time given
        if (toSplit.length != 2) {
            throw new DykeException("You know, I kinda gotta know when it's gonna happen." +
                    "\t format: /from *day-time* /to *day-time*");
        }

        String to = (toSplit.length > 1) ? toSplit[1].trim() : "";

        Event event = new Event(activity, from, to);
        this.library.addTask(event);
    }

    private void handleTodo(String desc) throws DykeException {
        if (desc.isBlank()) {
            throw new DykeException("Phineas, I don't know what we gonna do today!");
        }
        String description = desc.trim();
        Todo todo = new Todo(description);
        this.library.addTask(todo);
    }

    private void handleDelete(String input) throws DykeException {
        if (input.isBlank()) {
            throw new DykeException("\t What do you wanna delete?");
        }
        this.library.deleteTask(Integer.parseInt(input) - ACCOUNT_INDEX);
    }

    private void helpLine() {
        String helpStatement = "\t COMMANDS I /actually/ understand:\n\n" +
                "\t DAY-TIME Format: yyyy-MM-dd hh:mm\n\n" +
                "\t Main COMMANDS:\n" +
                "\t 1. LIST #to see all your tasks in the Library\n" +
                "\t 2. BYE #I'll talk to you later\n" +
                "\t 3. HELP #you literally just typed this...\n" +
                "\t 4. WHAT? #surprise...\n" +
                "\n\t Library Commands:\n" +
                "\t 1. TODO *insert activity*\n" +
                "\t 2. DEADLINE *day-time*\n" +
                "\t 3. EVENT *activity* /from *day-time* /to *day-time*\n" +
                "\t 4. MARK *task number*\n" +
                "\t 5. DELETE *task number*";
        System.out.println(helpStatement);
    }
}
