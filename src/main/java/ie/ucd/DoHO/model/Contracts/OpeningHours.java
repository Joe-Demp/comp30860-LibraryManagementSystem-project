package ie.ucd.DoHO.model.Contracts;

import ie.ucd.DoHO.util.Formatter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
public class OpeningHours implements Comparable<OpeningHours> {
    public static final DayOfWeek[] allDays = new DayOfWeek[]{DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY};
    public static String[] dayStrings = new String[]{
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };

    @Id
    private DayOfWeek day = DayOfWeek.MONDAY;
    private LocalTime opening = LocalTime.NOON;
    private LocalTime closing = LocalTime.NOON;

    private boolean openToday = false;

    public OpeningHours() {
    }

    public OpeningHours(DayOfWeek day, LocalTime opening, LocalTime closing) {
        this.day = day;
        this.opening = opening;
        this.closing = closing;
        computeOpenToday();
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public LocalTime getOpening() {
        return opening;
    }

    public void setOpening(LocalTime opening) {
        this.opening = opening;
        computeOpenToday();
    }

    public LocalTime getClosing() {
        return closing;
    }

    public void setClosing(LocalTime closing) {
        this.closing = closing;
        computeOpenToday();
    }

    public boolean isOpenToday() {
        return openToday;
    }

    public void setOpenToday(boolean openToday) {
        this.openToday = openToday;
    }

    private void computeOpenToday() {
        if (opening.isBefore(closing)) {
            this.openToday = true;
        }
    }

    public String getDayString() {
        return dayStrings[getDay().getValue() - 1];
    }

    public String toString() {
        if (isOpenToday()) {
            return Formatter.toTimeString(opening) + " to " + Formatter.toTimeString(closing);
        }
        return "closed";
    }

    public String getAsString() {
        return toString();
    }

    @Override
    public int compareTo(OpeningHours other) {
        return this.getDay().compareTo(other.getDay());
    }
}
