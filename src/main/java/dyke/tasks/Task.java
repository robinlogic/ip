package dyke.tasks;

public class Task {
    protected String description;
    protected boolean done;

    public Task(String desc) {
        this.description = desc;
        this.done = false;
    }

    public Task isDone() {
        this.done = true;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public String encode() {
        return (done ? "1" : "0") + " | "  + this.description;
    }

    @Override
    public String toString() {
        return (done ? "[X] " : "[ ] ") + this.description;
    }
}
