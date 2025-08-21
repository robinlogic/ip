import java.util.Scanner;
import java.util.List;
public class Dyke {
    static Library library = new Library();
    static int accountIndex = 1;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String bar = "\n\t____________________________________________________________";
        System.out.println( "\n" + bar + "\n\t Hello! I'm DYKE\n\t What can I do for you?\n\n" +
                "\t\t\t\t\t\t\t\tpsst! type 'help' for help, duh.\n"
                + bar);

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
                        library.markTask(Integer.parseInt(input[1]) - accountIndex);
                        System.out.println(bar);
                        break;
                    case DEADLINE:
                        System.out.println(bar);
                        try {
                            if (input.length < 2) {
                                throw new DykeException("I think you forgot the description...");
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
                    System.out.println(e.getMessage());
                    break;
            }
        }
        sc.close();
    }


    public static void handleDeadline(String input) throws DykeException {
        if (input.isBlank()) {
            throw new DykeException("I think you forgot the description...");
        }
        String[] parts = input.split("/by", 2);
        if (parts.length != 2 || parts[1].isBlank()) {
            throw new DykeException("When should this be done by?");
        }
        String description = parts[0].trim();
        String by = parts[1].trim();

        Deadline deadline  =  new Deadline(description, by);
        library.addTask(deadline);
    }

    public static void handleEvent(String input) throws DykeException {
        if (input.isBlank()) {
            throw new DykeException("What is this event about?");
        }
        String[] fromSplit = input.split("/from", 2);
        String description = fromSplit[0].trim();

        if (fromSplit.length != 2) {
            throw new DykeException("You know, I kinda gotta know when it's gonna happen.\n " +
                    "\t format: /from *day-time* /to *day-time*");
        }
        String[] toSplit = fromSplit[1].split("/to", 2);
        String from = toSplit[0].trim();

        String to = (toSplit.length > 1) ? toSplit[1].trim() : "";

        Event event = new Event(description, from, to);
        library.addTask(event);
    }

    public static void handleTodo(String input) throws DykeException {
        if (input.isBlank()) {
            throw new DykeException("Phineas, I don't know what we gonna do today!");
        }
        String description = input.trim();
        Todo todo = new Todo(description);
        library.addTask(todo);
    }

    public static void handleDelete(String input) throws DykeException {
        if (input.isBlank()) {
            throw new DykeException("\t What do you wanna delete?");
        }
        library.deleteTask(Integer.parseInt(input) - accountIndex);
    }

    public static void helpLine() {
        String helpStatement = "\t *list of commands under construction*";
        System.out.println(helpStatement);
    }

}
