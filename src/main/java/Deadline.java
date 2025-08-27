import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private DateTimeParser deadline;

    public Deadline(String desc, String deadline) throws DykeException {
        super(desc);
        this.deadline = DateTimeParser.parse(deadline);
    }

    @Override
    public String encode() {
        return "D | " + super.encode() + " | "
                + this.deadline.reString();
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.deadline.reString() + ")";
    }
}
