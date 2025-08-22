/**
 * A chatbot called Dyke that responds according to commands
 * within CommandType class.
 */

import java.util.Scanner;
import java.util.List;
public class Dyke {
    static Library library = new Library();
    static int ACCOUNT_INDEX = 1;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        // Startup Banner
        String bar = "\t______________________________________________" +
                "______________";
        System.out.println( "\n" + bar + "\n\t Hello! I'm DYKE\n\t" +
                " What can I do for you?\n\n" +
                "\t\t\t\t\t\t\t\tpsst! type 'help' for help, duh.\n"
                + bar);

        // flag for exiting while loop on "Bye!" command
        boolean running = true;

        while (sc.hasNextLine() && running) {
            String[] input = sc.nextLine().split(" ", 2);
            try {
                CommandType command = CommandType.fromString(input[0]);

                switch (command) {

                    case BYE:
                        System.out.println(bar + "\n\t Bye. Catch you later!\n" + bar);
                        running = false;
                        break;
                    case LIST:
                        System.out.print(bar);
                        library.PrintList();
                        System.out.println(bar);
                        break;
                    case HELP:
                        System.out.println(bar);
                        helpLine();
                        System.out.println(bar);
                        break;
                    case MARK:
                        System.out.println(bar);
                        library.markTask(Integer.parseInt(input[1])
                                - ACCOUNT_INDEX);
                        System.out.println(bar);
                        break;
                    case DEADLINE:
                        System.out.println(bar);
                        try {
                            if (input.length < 2) {
                                throw new DykeException("I think you forgot the" +
                                        " description...");
                            }
                            handleDeadline(input[1]);
                        } catch (DykeException e) {
                            System.out.println("\t " + e.getMessage() + "\n");
                        }
                        System.out.println(bar);
                        break;

                    case TODO:
                        System.out.println(bar);
                        try {
                            if (input.length < 2) {
                                throw new DykeException("Phineas, I don't know what " +
                                        "we're gonna do today!");
                            }
                            handleTodo(input[1]);
                        } catch (DykeException e) {
                            System.out.println("\t " + e.getMessage() + "\n");
                        }

                        System.out.println(bar);
                        break;

                    case EVENT:
                        System.out.println(bar);
                        try {
                            if (input.length < 2) {
                                throw new DykeException("What is this event about?");
                            }
                            handleEvent(input[1]);
                        } catch (DykeException e) {
                            System.out.println("\t " + e.getMessage() + "\n");
                        }
                        System.out.println(bar);
                        break;

                    case DELETE:
                        System.out.println(bar);
                        try {
                            if (input.length < 2) {
                                throw new DykeException("What do you wanna delete?");
                            }
                            handleDelete(input[1]);
                        } catch (DykeException e) {
                            System.out.println("\t " + e.getMessage() + "\n");
                        }
                        System.out.println(bar);
                        break;
                    case WHAT:
                        System.out.println(bar + "\n\tWhat do you mean?\n " +
                                "\t\tWhen you nod your head yes,\n" +
                                "\t\t\tBut you wanna say no... \n" + bar);
                    }
            } catch (DykeException e) {
                    System.out.println(bar);
                    System.out.println(e.getMessage());
                    System.out.println("\t Dude, IDK whats that??" +
                            " type 'help' for help, bruv.\n" +
                            "\t Or you could just say, 'Bye!', like exactly.");
                    System.out.println(bar);
            }
        }
        sc.close();
    }


    private static void handleDeadline(String desc) throws DykeException {
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
        library.addTask(deadline);
    }

    private static void handleEvent(String desc) throws DykeException {
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
        library.addTask(event);
    }

    private static void handleTodo(String desc) throws DykeException {
        if (desc.isBlank()) {
            throw new DykeException("Phineas, I don't know what we gonna do today!");
        }
        String description = desc.trim();
        Todo todo = new Todo(description);
        library.addTask(todo);
    }

    private static void handleDelete(String input) throws DykeException {
        if (input.isBlank()) {
            throw new DykeException("\t What do you wanna delete?");
        }
        library.deleteTask(Integer.parseInt(input) - ACCOUNT_INDEX);
    }

    private static void helpLine() {
        String helpStatement = "\t COMMANDS I /actually/ understand:\n\n" +
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
