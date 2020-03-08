package ie.ucd.DoHO.model.Contracts;

import ie.ucd.DoHO.util.Formatter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class IndexGreeter {
    private static List<OpeningHours> allOpeningHours;
    private static OpeningHours todayOpeningHours;
    private static OpeningHours dayOpenAgain;

    private static LocalTime currentTime;
    private static DayOfWeek todayOfWeek;

    public IndexGreeter(List<OpeningHours> allOpeningHours) {
        currentTime = LocalTime.now();
        todayOfWeek = DayOfWeek.from(LocalDateTime.now());

        IndexGreeter.allOpeningHours = allOpeningHours.stream().sorted().collect(Collectors.toList());
        todayOpeningHours = todaysOpeningHours();
        dayOpenAgain = nextOpenDay();
    }

    public List<OpeningHours> getAllOpeningHours() {
        return allOpeningHours;
    }

    private OpeningHours todaysOpeningHours() {
        return allOpeningHours.stream()
                .filter(openingHours -> openingHours.getDay() == todayOfWeek)
                .findFirst()
                .orElse(new OpeningHours());
    }

    private OpeningHours nextOpenDay() {
        Optional<OpeningHours> nextOpen = Optional.empty();

        for (int i = 1; i < 7; ++i) {
            DayOfWeek nextDay = todayOfWeek.plus(i);
            nextOpen = allOpeningHours.stream()
                    .filter(openingHours -> openingHours.getDay() == nextDay)
                    .filter(OpeningHours::isOpenToday)
                    .findFirst();

            if (nextOpen.isPresent()) break;
        }

        return nextOpen.orElse(new OpeningHours());
    }

    private boolean openNow() {
        return todayOpeningHours.isOpenToday()
                && currentTime.isAfter(todayOpeningHours.getOpening())
                && currentTime.isBefore(todayOpeningHours.getClosing());
    }

    private boolean closedBefore() {
        return todayOpeningHours.isOpenToday() && currentTime.isBefore(todayOpeningHours.getOpening());
    }

    private boolean closedAfter() {
        return currentTime.isAfter(todayOpeningHours.getOpening());
    }

    public String openString() {
        if (openNow()) {
            return "Open now, until " + Formatter.toTimeString(todayOpeningHours.getClosing());
        } else if (closedBefore()) {
            return "We open today at " + Formatter.toTimeString(todayOpeningHours.getOpening());
        } else {
            return "We open again at " + Formatter.toTimeString(dayOpenAgain.getOpening()) +
                    " on " + dayOpenAgain.getDayString();
        }
    }
}
