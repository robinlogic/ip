

public class Task {
    protected String description;
    protected boolean done;

    Task(String desc) {
        this.description = desc;
        this.done = false;
    }

    @Override
    public String toString() {
        return (done ? "[X] " : "[ ] ") + this.description;
    }
}
