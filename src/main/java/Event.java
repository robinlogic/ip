public class Event extends Task {
    private DateTimeParser from;
    private DateTimeParser to;

    public Event(String desc, String from, String to) throws DykeException {
        super(desc);
        this.from = DateTimeParser.parse(from);
        this.to = DateTimeParser.parse(to);
    }

    @Override
    public String encode() {
        return "E | " + super.encode() + " | "
                + this.from.reString() + " | " + this.to.reString();
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.from.reString()
                + " to: " + this.to.reString() + ")";
    }
}
