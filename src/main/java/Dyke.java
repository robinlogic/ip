/**
 * A chatbot called Dyke that responds according to commands
 * within {@code CommandType} class.
 */

import java.util.Scanner;

public class Dyke {
    static Library library = new Library();

    // file location for saved tasks
    static String FILE_PATH = "./data/dyke.txt";

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Ui ui = new Ui(); // User Interface class

        // Print Welcome Message
        ui.welcomeMessage();

        // flag for exiting while loop on "Bye!" command
        boolean isRunning = true;

        // Loadin existing Tasks into Library
        Storage storage = new Storage(FILE_PATH);
        ui.printMessageNoBar(storage.loadTasks(library));

        // Create Parse Engine
        Parser parser = new Parser(library, ui, storage);

        while (sc.hasNextLine() && isRunning) {
            String[] input = sc.nextLine().split(" ", 2);
            parser.parseCommand(input);
            if (!parser.isRunning()) {
                break;
            }

        }

        // Save current tasks into Dyke.txt
        if (!library.isEmpty()) {
            ui.printMessageNoBar(storage.saveTasks(library));
        }
        sc.close();
    }
}
