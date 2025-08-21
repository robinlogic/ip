import java.util.Scanner;
import java.util.List;
public class Dyke {
    static Library library = new Library();
    static int accountIndex = 1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String bar = "\t____________________________________________________________";
        System.out.println( bar + "\n\t Hello! I'm DYKE\n\t What can I do for you?\n\n" +
                "\t\t\t\t\t\t\t\tpsst! type 'help' for help, duh.\n"
                + bar);

        while (sc.hasNext()) {
            String[] input = sc.nextLine().split(" ", 2);
            String command = input[0];

            if (command.equals("Bye!")) {
                System.out.println(bar + "\t Bye. Hope to see you again soon!\n" + bar);
                break;
            }
            else if (command.equals("list")) {
                System.out.print(bar);
                library.PrintList();
                System.out.println(bar);
            }

            else if (command.equals("help")) {
                System.out.println(bar);
                helpLine();
                System.out.println(bar);
            }

            else if (command.equals("mark") && input.length > 1) {
                System.out.println(bar);
                library = library.markTask(Integer.parseInt(input[1]) - accountIndex);
                System.out.println(bar);
            }

            else if (command.equals("deadline") && input.length > 1) {
                String[] parts = input[1].split("/by", 2);
                String desc = parts[0].trim();
                String by = (parts.length > 1) ? parts[1].trim() : "";

                System.out.println(bar);
                handleDeadline(input[1]);
                System.out.println(bar);

            }

            else if (command.equals("todo") && input.length > 1) {
                System.out.println(bar);
                handleTodo(input[1]);
                System.out.println(bar);

            }

            else if (command.equals("event") && input.length > 1) {
                System.out.println(bar);
                handleEvent(input[1]);
                System.out.println(bar);

            }

            else {
                System.out.println(bar + "\n\tWhat do you mean?\n " +
                        "\t\tWhen you nod your head yes,\n" +
                        "\t\t\tBut you wanna say no... \n" + bar);
            }
        }
        sc.close();
    }

    public static void handleDeadline(String input) {
        String[] parts = input.split("/by", 2);
        String description = parts[0].trim();
        String by = (parts.length > 1) ? parts[1].trim() : "";

        Deadline deadline  =  new Deadline(description, by);
        library.addTask(deadline);
    }

    public static void handleEvent(String input) {
        String[] fromSplit = input.split("/from", 2);
        String description = fromSplit[0].trim();

        String[] toSplit = fromSplit[1].split("/to", 2);
        String from = toSplit[0].trim();
        String to = (toSplit.length > 1) ? toSplit[1].trim() : "";

        Event event = new Event(description, from, to);
        library.addTask(event);
    }

    public static void handleTodo(String input) {
        String description = input.trim();
        Todo todo = new Todo(description);
        library.addTask(todo);
    }

    public static void helpLine() {
        String helpStatement = "\t Something here";
        System.out.println(helpStatement);
    }

}
