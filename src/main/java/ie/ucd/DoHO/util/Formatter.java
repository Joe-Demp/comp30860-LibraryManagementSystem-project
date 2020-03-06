package ie.ucd.DoHO.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;

public class Formatter {
    private static DateFormat dateFormat;

    static {
        dateFormat = new SimpleDateFormat("dd-MMM yyyy");
    }

    public static String toKeyString(String key) {
        String str = key.substring(0, 1);
        str = str.toUpperCase();
        str += key.substring(1);
        return str + ":";
    }

    public static String toDateString(Integer date) {
        return dateFormat.format(date);
    }

    public static String toDurationString(Duration duration) {
        return duration.toHours() + ":" + duration.toMinutes();
    }
}
