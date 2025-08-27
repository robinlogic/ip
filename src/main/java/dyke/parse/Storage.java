package dyke.parse;

import dyke.data.Library;
import dyke.tasks.Deadline;
import dyke.tasks.Event;
import dyke.tasks.Task;
import dyke.tasks.Todo;

import java.io.*;


import java.util.ArrayList;


public class Storage {
    private final String filePath;
    private ArrayList<Task> library;

    public Storage(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
        file.getParentFile().mkdirs();
    }

    public String saveTasks(Library library) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : library.getTasks()) {
                writer.write(task.encode());
                writer.newLine();
            }
            return library.getTasks().size() + " tasks have been saved!";
        } catch (IOException e) {
            return "Error saving tasks: " + e.getMessage();
        }
    }

    public String loadTasks(Library library) {
        File file = new File(filePath);

        if (!file.exists()) {
            return "No saved tasks found. Starting fresh!";
        }
        else {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        library.silentAdd(this.decode(line));
                    } catch (DykeException e) {
                        return e.getMessage();
                    }
                }
                return "Loaded tasks: " + library.getTasks().size();
            } catch (IOException e) {
                return "Error loading tasks: " + e.getMessage();
            }
        }
    }

    // Decoder for decoding dyke.data from saved file
    private Task decode(String line) throws DykeException{
        String[] parts = line.split(" \\| ");
        String type = parts[0];

        boolean isDone = parts[1].equals("1");

        switch (type) {
        case "T":
            if (parts.length == 3) {
                return isDone ? new Todo(parts[2]).isDone() : new Todo(parts[2]);
            }
            break;
        case "D":
            if (parts.length == 4) {
                return isDone ? new Deadline(parts[2], parts[3]).isDone() :
                        new Deadline(parts[2], parts[3]);
            }
            break;
        case "E":
            if (parts.length == 5) {
                return isDone ? new Event(parts[2], parts[3], parts[4]) :
                        new Event(parts[2], parts[3], parts[4]).isDone();
            }
            break;
        default:
            throw new DykeException("Unknown task type in file: " + type);
        }
        return new Task("dummy");
    }


}
