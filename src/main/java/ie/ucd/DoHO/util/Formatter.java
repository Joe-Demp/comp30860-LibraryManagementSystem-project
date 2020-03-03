package ie.ucd.DoHO.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

    public static String toDateString(Date date) {
        return dateFormat.format(date);
    }
}
