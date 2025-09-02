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
    public void parseCommand(String[] input) {
        try {
            CommandType command = CommandType.fromString(input[0]);

            switch (command) {

            case BYE:
                ByeCommand bye  = new ByeCommand();
                bye.execute(library, ui, storage);
                this.isRunning = false;
                break;
            case LIST:
                new ListCommand().execute(library, ui, storage);
                break;
            case HELP:
                new HelpCommand().execute(library, ui, storage);
                break;
            case MARK:
                new MarkCommand(Integer.parseInt(input[1]) - ACCOUNT_INDEX)
                    .execute(library, ui, storage);
                break;
            case DEADLINE:
                try {
                    if (input.length < 2) {
                        throw new DykeException("I think you forgot the" +
                                " description...");
                    }
                    new DeadlineCommand(input[1]).execute(library, ui, storage);
                } catch (DykeException e) {
                    ui.printMessage(e.getMessage());
                }
                break;

            case TODO:
                try {
                    if (input.length < 2) {
                        throw new DykeException("Phineas, I don't know what " +
                                "we're gonna do today!");
                    }
                    new TodoCommand(input[1]).execute(library, ui, storage);
                } catch (DykeException e) {
                    ui.printMessage(e.getMessage());
                }
                break;

            case EVENT:
                try {
                    if (input.length < 2) {
                        throw new DykeException("What is this event about?");
                    }
                    new EventCommand(input[1]).execute(library, ui, storage);
                } catch (DykeException e) {
                    ui.printMessage(e.getMessage());
                }
                break;

            case DELETE:
                try {
                    if (input.length < 2) {
                        throw new DykeException("What do you wanna delete?");
                    }
                    new DeleteCommand(Integer.parseInt(input[1]))
                        .execute(library, ui, storage);
                } catch (DykeException e) {
                    System.out.println("\t " + e.getMessage() + "\n");
                }
                break;
            case WHAT:
                new WhatCommand().execute(library, ui, storage);
            case FIND:
                new FindCommand(input[1]).execute(library, ui, storage);
            }
        } catch (DykeException e) {
            ui.printMessage(e.getMessage() + "\n Dude, IDK whats that??" +
                    " type 'help' for help, bruv.\n" +
                    " Or you could just say, 'Bye!', like exactly.");
        }
    }
}
