package dyke.parse;

public enum CommandType {
    TODO("TODO"),
    DEADLINE("DEADLINE"),
    EVENT("EVENT"),
    BYE("BYE!"),
    LIST("LIST"),
    MARK("MARK"),
    DELETE("DELETE"),
    UNKNOWN("UNKNOWN"),
    HELP("HELP"),
    WHAT("WHAT?");

    private final String keyword;

    CommandType(String s) {
        this.keyword = s;
    }

    public static CommandType fromString(String s) throws DykeException {
        for (CommandType commandType : values()) {
            if (commandType.keyword.equals(s.toUpperCase())) {
                return commandType;
            }
        }
        throw new DykeException(" Unknown command: " + s);
    }
}
