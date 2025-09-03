package dyke.parse;

import dyke.command.*;
import dyke.data.Library;
import dyke.ui.Ui;

/**
 * Houses methods for parsing inputs from User
 */
public class Parser {
    private static final int ACCOUNT_INDEX = 1;
    private boolean isRunning = true;
    private Library library; // dyke.data.Library is Mutable
    private final Ui ui;
    private final Storage storage;

    public Parser(Library library, Ui ui, Storage storage) {
        this.library = library;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Asserts that "Bye!" command has been invoked.
     * @return {@code boolean}
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Parses commands from User.
     * @param input The inputs from User
     */
    public String parseCommand(String[] input) {
        try {
            CommandType command = CommandType.fromString(input[0]);

            switch (command) {

            case BYE:
                ByeCommand bye  = new ByeCommand();
                this.isRunning = false;
                return bye.execute(library, ui, storage);
            case LIST:
                return new ListCommand().execute(library, ui, storage);
            case HELP:
                return new HelpCommand().execute(library, ui, storage);
            case MARK:
                return new MarkCommand(Integer.parseInt(input[1]) - ACCOUNT_INDEX)
                    .execute(library, ui, storage);
            case DEADLINE:
                try {
                    if (input.length < 2) {
                        throw new DykeException("I think you forgot the" +
                                " description...");
                    }
                    return new DeadlineCommand(input[1]).execute(library, ui, storage);
                } catch (DykeException e) {
                    ui.printMessage(e.getMessage());
                    return e.getMessage();
                }

            case TODO:
                try {
                    if (input.length < 2) {
                        throw new DykeException("Phineas, I don't know what " +
                                "we're gonna do today!");
                    }
                    return new TodoCommand(input[1]).execute(library, ui, storage);
                } catch (DykeException e) {
                    ui.printMessage(e.getMessage());
                    return e.getMessage();
                }

            case EVENT:
                try {
                    if (input.length < 2) {
                        throw new DykeException("What is this event about?");
                    }
                    return new EventCommand(input[1]).execute(library, ui, storage);
                } catch (DykeException e) {
                    ui.printMessage(e.getMessage());
                    return e.getMessage();
                }

            case DELETE:
                try {
                    if (input.length < 2) {
                        throw new DykeException("What do you wanna delete?");
                    }
                    return new DeleteCommand(Integer.parseInt(input[1]) - ACCOUNT_INDEX)
                        .execute(library, ui, storage);
                } catch (DykeException e) {
                    System.out.println("\t " + e.getMessage() + "\n");
                    return e.getMessage();
                }
            case WHAT:
                return new WhatCommand().execute(library, ui, storage);
            case FIND:
                return new FindCommand(input[1]).execute(library, ui, storage);
            }
        } catch (DykeException e) {
            String msg = e.getMessage() + "\n Dude, IDK whats that??" +
                    " type 'help' for help, bruv.\n" +
                    " Or you could just say, 'Bye!', like exactly.";
            ui.printMessage(msg);
            return msg;
        }
        return "\n Dude, IDK whats that??" +
                " type 'help' for help, bruv.\n" +
                " Or you could just say, 'Bye!', like exactly.";
    }
}
