package ie.ucd.DoHO.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Formatter {
    private static DateFormat dateFormat;
    private static DateTimeFormatter timeFormat;

    static {
        dateFormat = new SimpleDateFormat("dd-MMM yyyy");
        timeFormat = DateTimeFormatter.ofPattern("h.mm a");
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

    public static String toTimeString(LocalTime lt) {
        return timeFormat.format(lt);
    }
}
