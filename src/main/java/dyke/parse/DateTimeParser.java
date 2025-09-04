package dyke.parse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Provides methods to parse datetime from recognized formats
 */
public class DateTimeParser {

    private static final DateTimeFormatter DATE_ONLY =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_HH =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
    private static final DateTimeFormatter DATE_HH_MM =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DATE_HH_MM_SS =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final LocalDateTime date;
    private final DateTimeFormatter format;


    private DateTimeParser(LocalDateTime date, DateTimeFormatter fmt) {
        this.date = date;
        this.format = fmt;
    }

    /**
     * Helper method to Parse Datetime based on recognized formats.
     * @param input String datetime input from User.
     * @return DateTimeParser object for internal tracking.
     * @throws DykeException If datetime input is not in a recognized format.
     */
    public static DateTimeParser parse(String input) throws DykeException {
        try {
            // if it's just a date
            LocalDate date = LocalDate.parse(input, DATE_ONLY);
            return new DateTimeParser(date.atStartOfDay(), DATE_ONLY);
        } catch (Exception ignored) {
            // Intentionally ignored: input did not match the format
        }

        for (DateTimeFormatter fmt
                : new DateTimeFormatter[]{DATE_HH_MM_SS, DATE_HH_MM, DATE_HH}) {
            try {
                return new DateTimeParser(LocalDateTime.parse(input, fmt), fmt);
            } catch (Exception ignored) {
                // Intentionally ignored: input did not match the format
            }
        }
        throw new DykeException("Invalid datetime: " + input);
    }

    /**
     * @return Date-time in an appropriate format.
     */
    public String reString() {
        return format.format(date);
    }
}
