package ie.ucd.DoHO.model.Contracts;

import ie.ucd.DoHO.util.Formatter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalTime;

@Entity
public class OpeningHours {
    public static final DayOfWeek[] allDays = new DayOfWeek[]{DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY};
    public static String[] dayKeys;

    static {
        dayKeys = new String[allDays.length];
        for (int i = 0; i < allDays.length; ++i) {
            dayKeys[i] = Formatter.toKeyString(allDays[i].toString());
        }
    }

    @Id
    private DayOfWeek day = DayOfWeek.MONDAY;
    private LocalTime opening = LocalTime.MIN;
    private LocalTime closing = LocalTime.MIN;

    public OpeningHours() {
    }

    public OpeningHours(DayOfWeek day, LocalTime opening, LocalTime closing) {
        this.day = day;
        this.opening = opening;
        this.closing = closing;
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
    }

    public LocalTime getClosing() {
        return closing;
    }

    public void setClosing(LocalTime closing) {
        this.closing = closing;
    }

    public boolean openNow() {
        LocalTime now = LocalTime.from(Instant.now());
        return now.isAfter(this.opening) && now.isBefore(this.closing);
    }
}
