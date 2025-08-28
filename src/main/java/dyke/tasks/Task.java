package dyke.tasks;

public class Task {
    protected String description;
    protected boolean done;

    public Task(String desc) {
        this.description = desc;
        this.done = false;
    }

    /**
     * Asserts that {@code Task} has been marked as done.
     * @return {@code Task}
     */
    public Task isDone() {
        this.done = true;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Encodes Tasks into a suitable format for {@code Storage}.
     * @return The message after {@code encode} operation.
     */
    public String encode() {
        return (done ? "1" : "0") + " | "  + this.description;
    }

    /**
     * Represents Tasks into a suitable format for {@code Ui}.
     * @return {@code String}
     */
    @Override
    public String toString() {
        return (done ? "[X] " : "[ ] ") + this.description;
    }
}
