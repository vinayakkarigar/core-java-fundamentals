package com.modern.refresh;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Date;
import java.util.Locale;

import static java.time.temporal.TemporalAdjusters.*;

public class DateTimeDemo {
    public static TemporalAdjuster nextWorkingDay() {
        return (Temporal t) -> {
            int daysToAdd = 1;
            final DayOfWeek of = DayOfWeek.of(t.get(ChronoField.DAY_OF_WEEK));
            if (of == DayOfWeek.FRIDAY) daysToAdd = 3;
            if (of == DayOfWeek.SATURDAY) daysToAdd = 2;
            return t.plus(daysToAdd, ChronoUnit.DAYS);
        };
    }

    public static void main(String[] args) {
        final Date date = new Date(117, 8, 21);
        System.out.println(date);
        System.out.println("New Data Time Demo: LocalDate LocalTime");
        final LocalDate l = LocalDate.of(2020, 11, 11);
        System.out.println(l.getYear());
        System.out.println(l.getMonth());
        System.out.println(l.getMonthValue());
        System.out.println(l.getDayOfMonth());
        System.out.println(l.getDayOfWeek().getValue());
        System.out.println(l.getDayOfYear());
        System.out.println(LocalDate.now());
        final LocalTime now = LocalTime.now();
        System.out.println(now);
        System.out.println("Hour: "+now.getHour());
        System.out.println("Minute: "+ now.getMinute());
        System.out.println("Second: "+ now.getSecond());

        System.out.println("New DateTime Demo: parse date time string");
        final LocalDate parse = LocalDate.parse("2020-10-23");
        final LocalTime parse1 = LocalTime.parse("10:20:30");
        System.out.println("DATE: "+parse+" TIME: "+parse1);

        System.out.println("New DateTime Demo: combining both date and time");
        final LocalDateTime dt1 = LocalDateTime.of(2017, Month.SEPTEMBER, 19, 10, 20, 30);
        System.out.println(dt1);
        final LocalDateTime dt2 = parse.atTime(10, 23, 20);
        System.out.println(now.atDate(parse));
        System.out.println("Just Date: "+ dt1.toLocalDate());
        System.out.println("Just Time: "+ dt1.toLocalTime());

        System.out.println("New DateTime DEmo: Instant usfage");
        Instant.ofEpochSecond(2);
        Instant.ofEpochSecond(2, 0);
        Instant.ofEpochSecond(1, 1_000_000_000);
        final Instant instant = Instant.ofEpochSecond(3, -1_000_000_000);
        System.out.println("Two Seconds after the UNIX EPOCH: " + instant);

        System.out.println("New DateTime Demo: Duration Demo");
        System.out.println(Duration.between(dt1, dt2));

        final LocalDate date1 = LocalDate.of(2020, 10, 23);
        final LocalDate date2 = LocalDate.of(2019, 8, 20);
        final LocalTime time1 = LocalTime.of(10, 20, 20);
        final LocalTime time2 = LocalTime.of(8, 18, 20);
        final Instant instant1 = Instant.ofEpochSecond(4, 980);
        final Instant instant2 = Instant.ofEpochSecond(2, 400);

        System.out.println("Duration between two localDAteTime: "+Duration.between(dt2, dt1));
        final Duration duration2 = Duration.between(time2, time1);
        final Duration duration1 = duration2;
        System.out.println("Duration between two localtimes: "+ duration1);
        final Duration duration3 = Duration.between(instant2, instant1);
        System.out.println("Duration between two instants: " + duration3);

        System.out.println("New DateTime Demo: usage of Period to find the diff between two Dates");
        final Period period1 = Period.between(date1, date2);
        System.out.println(period1);

        System.out.println("Creating durations and periods on their own");
        System.out.println(Duration.of(3, ChronoUnit.MINUTES));
        System.out.println(Duration.ofSeconds(30));

        System.out.println(Period.ofDays(20));
        System.out.println(Period.of(10, 12, 30));
        System.out.println(Duration.from(duration1));
        System.out.println(Duration.parse("PT4H2M"));

        System.out.println(time1.plus(duration1));
        System.out.println(duration1.addTo(time1));
        System.out.println(duration1.minus(duration2));

        System.out.println("Manipulation Parsing and Formatting new Date Time objects");
        System.out.println("Manipulating Two LocalDates");

        final LocalDate ld1 = LocalDate.of(2011, 10, 23);
        System.out.println(ld1.withYear(2019));

        System.out.println("Using predefined Temporal Adjusters");
        LocalDate dat3 = LocalDate.of(2014, 3, 18);
        final LocalDate date4 = dat3.with(nextOrSame(DayOfWeek.SUNDAY));
        System.out.println(date4);
        LocalDate date5 = date4.with(nextOrSame(DayOfWeek.FRIDAY));
        System.out.println(date5);
        System.out.println(date5.with(lastDayOfMonth()));
        System.out.println(dat3.with(dayOfWeekInMonth(1, DayOfWeek.MONDAY)));
        System.out.println(dat3.with(firstDayOfMonth()));
        System.out.println(dat3.with(firstDayOfNextMonth()));
        System.out.println(dat3.with(firstDayOfNextYear()));
        System.out.println(dat3.with(firstDayOfYear()));
        System.out.println(dat3.with(firstInMonth(DayOfWeek.SUNDAY)));
        System.out.println(dat3.with(lastDayOfMonth()));
        System.out.println(dat3.with(lastDayOfYear()));
        System.out.println(dat3.with(lastInMonth(DayOfWeek.SUNDAY)));
        System.out.println(dat3.with(previous(DayOfWeek.SUNDAY)));

        System.out.println("custom temporalAdjuster demo");
        System.out.println("before next working day adjuster: " + date5);
        System.out.println("After next working day adjuster: " + date5.with(nextWorkingDay()));

        System.out.println("Printing and parsing of date time objects");
        System.out.println(dat3 );
        System.out.println(dat3.format(DateTimeFormatter.ISO_DATE));
        System.out.println(dat3.format(DateTimeFormatter.BASIC_ISO_DATE));

        System.out.println("custom datetime formatter");
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(dat3.format(dateTimeFormatter));

        final DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        System.out.println(dat3.format(dateTimeFormatter1));

        final DateTimeFormatter italianFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);
        System.out.println(dat3.format(italianFormatter));
    }

}
