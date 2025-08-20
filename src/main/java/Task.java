

public class Task {
    protected String description;
    protected boolean done;

    Task(String desc) {
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

    @Override
    public String toString() {
        return (done ? "[X] " : "[ ] ") + this.description;
    }
}
