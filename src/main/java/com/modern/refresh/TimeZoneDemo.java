package com.modern.refresh;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class TimeZoneDemo {
    public static void main(String[] args) {
        System.out.println("TimeZone new api demo");
        System.out.println("New DateTime API has greatly simplified wokring with different timezones");
//
        final ZoneId romZoneId = ZoneId.of("Europe/Rome");
        final ZoneId kolkotaTimeZoneId = ZoneId.of("Asia/Kolkata");
//
//
//        final LocalDate date1
//                = LocalDate.of(2014, Month.MARCH, 18);
//
//        System.out.println(date1.atStartOfDay(romZoneId));
//        System.out.println(date1.atStartOfDay(kolkotaTimeZoneId));
//
//        final LocalDateTime localDateTime =
//                LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
//        final ZonedDateTime x = localDateTime.atZone(romZoneId);
//        System.out.println(x);
//        System.out.println(x.withZoneSameInstant(kolkotaTimeZoneId));
//
//        final Instant now = Instant.now();
//        System.out.println("Instant:"+now);
//        System.out.println("local:"+LocalDateTime.now());
//        System.out.println(now.atZone(romZoneId));
//
//        System.out.println(localDateTime.toInstant(ZoneOffset.UTC));
//        System.out.println("conversion of localtimsdate and instant");
//        System.out.println(LocalDateTime.ofInstant(now, romZoneId ));
        System.out.println(ZoneOffset.systemDefault());

        final Instant now = Instant.now();;
        final LocalDateTime dateTi = LocalDateTime.ofInstant(now, kolkotaTimeZoneId);
        System.out.println("Instant: "+now);
        System.out.println(now.atZone(romZoneId).toInstant());
        System.out.println(now.atZone(kolkotaTimeZoneId).toInstant());
        System.out.println(dateTi.toInstant(ZoneOffset.of("+05:30")));

        System.out.println("Interoperability");
        final Date date = new Date();
        System.out.println(date.toInstant());

        final LocalDateTime localDateTime =
                LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);


        final ZoneOffset nyZoneOffset = ZoneOffset.of("-05:00");
        System.out.println(OffsetDateTime.of(localDateTime, nyZoneOffset));
    }

}
