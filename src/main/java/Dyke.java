/**
 * A chatbot called Dyke that responds according to commands
 * within {@code CommandType} class.
 */

import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.List;

public class Dyke {
    static Library library = new Library();

    // file location for saved tasks
    static String FILE_PATH = "./data/dyke.txt";

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
        boolean isRunning = true;

        // Loadin existing Tasks into Library
        Storage storage = new Storage(FILE_PATH);
        storage.loadTasks(library);

        // Create Parse Engine
        Parser parser = new Parser(library);

        while (sc.hasNextLine() && isRunning) {
            String[] input = sc.nextLine().split(" ", 2);
            System.out.println(bar);
            parser.parseCommand(input);
            System.out.println(bar);
            if (!parser.isRunning()) {
                break;
            }

        }

        // Save current tasks into Dyke.txt
        if (!library.isEmpty()) {
            storage.saveTasks(library);
        }
        sc.close();
    }
}
