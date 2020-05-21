/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 * @since 2014. 6. 10.
 */

package io.namoo.prologue.type;

import io.namoo.prologue.util.date.DateFormatUtil;
import io.namoo.prologue.util.date.DateUtil;
import io.namoo.prologue.util.json.JsonSerializable;
import io.namoo.prologue.util.json.JsonUtil;
import io.namoo.prologue.util.string.StringUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.zone.ZoneRulesException;
import java.util.NoSuchElementException;
import java.util.Objects;

@Getter
@Setter
public class DateTimePeriod implements JsonSerializable, Serializable {
    //
    private static final long serialVersionUID = -271823209462009194L;
    private String zoneId;
    private String startDateTime;
    private String endDateTime;

    public DateTimePeriod() {
        //
        this.zoneId = ZoneId.systemDefault().getId();
        this.startDateTime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.endDateTime =  LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);;
    }

    public DateTimePeriod(LocalDateTime startDateTime) {
        //
        this.zoneId = ZoneId.systemDefault().getId();
        this.startDateTime = startDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.endDateTime = startDateTime.plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);;
    }

    public DateTimePeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        //
        this.zoneId = ZoneId.systemDefault().getId();
        this.startDateTime = startDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.endDateTime = endDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public DateTimePeriod(ZoneId zoneId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        //
        this(zoneId.getId(), startDateTime, endDateTime);
    }

    public DateTimePeriod(String zoneId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        //
        this.zoneId = zoneId;
        this.startDateTime = startDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        if (endDateTime == null) {
            this.endDateTime = null;
        } else {
            this.endDateTime = endDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
    }

    public DateTimePeriod(String zoneId, String startDateTime, String endDateTime) {
        //
        try {
            ZoneId.of(zoneId);
        } catch (ZoneRulesException e) {
            throw new IllegalArgumentException("Zone id is not valid: " + zoneId);
        }

        try {
            LocalDateTime.parse(startDateTime);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid ISO_LOCAL_DATE_TIME format: " + startDateTime);
        }

        try {
            LocalDateTime.parse(endDateTime);
        } catch(DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid ISO_LOCAL_DATE_TIME format: " + endDateTime);
        }

        this.zoneId = zoneId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public DateTimePeriod(LocalDateTime startDateTime, int days) {
        //
        this.zoneId = ZoneId.systemDefault().getId();
        this.startDateTime = startDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.endDateTime = startDateTime.plusDays(days).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public DateTimePeriod(String startDateTime, int days) {
        //
        this.zoneId = ZoneId.systemDefault().getId();

        try {
            LocalDateTime parsedStartDateTime = LocalDateTime.parse(startDateTime);
            this.startDateTime = startDateTime;
            this.endDateTime = parsedStartDateTime.plusDays(days).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid ISO_LOCAL_DATE_TIME format: " + startDateTime);
        }
    }

    public DateTimePeriod(String startDate, String endDate) {
        //
        this(ZoneId.systemDefault().getId(), startDate, endDate);
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public LocalDateTime genStartLocalDateTime() {
        //
        return LocalDateTime.parse(startDateTime);
    }

    public LocalDateTime genEndLocalDateTime() {
        //
        if (endDateTime == null) {
            throw new NoSuchElementException("No endDateTime.");
        }

        return LocalDateTime.parse(endDateTime);
    }

    public static DateTimePeriod sample() {
        //
        return new DateTimePeriod(LocalDateTime.now(), 5);
    }

    public int genMonthPeriod() {
        //
        if(!hasEndDateTime()) {
            return 0;
        }

        if (StringUtil.isEmpty(startDateTime) || StringUtil.isEmpty(endDateTime)) return 0;
        LocalDateTime start = LocalDateTime.parse(startDateTime);
        LocalDate startDate = LocalDate.of(start.getYear(), start.getMonth(), start.getDayOfMonth());

        LocalDateTime end = LocalDateTime.parse(endDateTime);
        LocalDate endDate = LocalDate.of(end.getYear(), end.getMonth(), end.getDayOfMonth());

        Period period = Period.between(startDate, endDate);
        return period.getMonths();
    }

    public static DateTimePeriod fromJson(String json) {
        //
        return JsonUtil.fromJson(json, DateTimePeriod.class);
    }

    public boolean hasEndDateTime() {
        //
        if(this.endDateTime != null) {
            return true;
        }

        return false;
    }

    public boolean contains(LocalDateTime dateTime) {
        //
        if (!hasEndDateTime()) {
            return false;
        }

        LocalDateTime start = LocalDateTime.parse(startDateTime);
        LocalDateTime end = LocalDateTime.parse(endDateTime);

        return (start.isEqual(dateTime) || start.isBefore(dateTime)) && (end.isEqual(dateTime) || end.isAfter(dateTime));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final DateTimePeriod datePeriod = (DateTimePeriod) o;

        return Objects.equals(zoneId, datePeriod.zoneId)
            && Objects.equals(startDateTime, datePeriod.startDateTime)
            && Objects.equals(endDateTime, datePeriod.endDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zoneId, startDateTime, endDateTime);
    }

    public static void main(String[] args) {
        //
        System.out.println(sample());
        System.out.println(sample().genMonthPeriod());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = now.minusDays(1);
        LocalDateTime fiveMonthLater = now.plusMonths(5);

        DateTimePeriod dateTimePeriod = new DateTimePeriod(ZoneId.systemDefault().getId(), yesterday, fiveMonthLater);

        System.out.println(dateTimePeriod);
        System.out.println(dateTimePeriod.genMonthPeriod());
        System.out.println(dateTimePeriod.contains(LocalDateTime.now()));
        System.out.println(dateTimePeriod.contains(LocalDateTime.now().minusDays(1)));
    }
}