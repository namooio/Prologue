/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 * @since 2014. 6. 10.
 */

package io.namoo.prologue.type;

import io.grpc.netty.shaded.io.netty.channel.local.LocalChannel;
import io.namoo.prologue.util.date.DateFormatUtil;
import io.namoo.prologue.util.date.DateUtil;
import io.namoo.prologue.util.json.JsonSerializable;
import io.namoo.prologue.util.json.JsonUtil;
import io.namoo.prologue.util.string.StringUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.zone.ZoneRulesException;
import java.util.NoSuchElementException;
import java.util.Objects;

@Getter
@Setter
public class DatePeriod implements JsonSerializable, Serializable {
    //
    private static final long serialVersionUID = -271823209462009194L;
    private String zoneId;
    private String startDate;
    private String endDate;

    public DatePeriod() {
        //
        this.zoneId = ZoneId.systemDefault().getId();
        this.startDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.endDate =  LocalDate.now().plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);;
    }

    public DatePeriod(LocalDate startDate) {
        //
        this.zoneId = ZoneId.systemDefault().getId();
        this.startDate = startDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.endDate = startDate.plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);;
    }

    public DatePeriod(LocalDate startDate, LocalDate endDate) {
        //
        this.zoneId = ZoneId.systemDefault().getId();
        this.startDate = startDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.endDate = endDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public DatePeriod(ZoneId zoneId, LocalDate startDate, LocalDate endDate) {
        //
        this(zoneId.getId(), startDate, endDate);
    }

    public DatePeriod(String zoneId, LocalDate startDate, LocalDate endDate) {
        //
        this.zoneId = zoneId;
        this.startDate = startDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        if (endDate == null) {
            this.endDate = null;
        } else {
            this.endDate = endDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
    }

    public DatePeriod(String zoneId, String startDate, String endDate) {
        //
        try {
            ZoneId.of(zoneId);
        } catch (ZoneRulesException e) {
            throw new IllegalArgumentException("Zone id is not valid: " + zoneId);
        }

        try {
            LocalDate.parse(startDate);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("ISO_LOCAL_DATE format error: " + startDate);
        }

        try {
            LocalDate.parse(endDate);
        } catch(DateTimeParseException e) {
            throw new IllegalArgumentException("ISO_LOCAL_DATE format error: " + endDate);
        }

        this.zoneId = zoneId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public DatePeriod(LocalDate startDate, int days) {
        //
        this.zoneId = ZoneId.systemDefault().getId();
        this.startDate = startDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.endDate = startDate.plusDays(days).format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public DatePeriod(String startDateStr, int days) {
        //
        this.zoneId = ZoneId.systemDefault().getId();

        try {
            LocalDate.parse(startDate);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("ISO_LOCAL_DATE format error: " + startDate);
        }

        this.startDate = startDateStr;
        LocalDate start = LocalDate.parse(startDateStr);
        this.endDate = start.plusDays(days).format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public DatePeriod(String startDate, String endDate) {
        //
        this(ZoneId.systemDefault().getId(), startDate, endDate);
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public LocalDate genStartLocalDate() {
        //
        return LocalDate.parse(startDate);
    }

    public LocalDate genEndLocalDate() {
        //
        if (endDate == null) {
            throw new NoSuchElementException("No endDate value.");
        }

        return LocalDate.parse(endDate);
    }

    public static DatePeriod sample() {
        //
        return new DatePeriod(LocalDate.now(), 5);
    }

    public int genMonthPeriod() {
        //
        if(!hasEndDate()) {
            return 0;
        }

        if (StringUtil.isEmpty(startDate) || StringUtil.isEmpty(endDate)) return 0;
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        Period period = Period.between(start, end);
        return period.getMonths();
    }

    public static DatePeriod fromJson(String json) {
        //
        return JsonUtil.fromJson(json, DatePeriod.class);
    }

    public boolean hasEndDate() {
        //
        if(this.endDate != null) {
            return true;
        }

        return false;
    }

    public int genStartYear() {
        //
        if (startDate == null) return 0;
        LocalDate start = LocalDate.parse(startDate);
        return start.getYear();
    }

    public int genStartMonthValue() {
        //
        if (startDate == null) return 0;
        LocalDate start = LocalDate.parse(startDate);
        return start.getMonthValue();
    }

    public int genEndYear() {
        //
        if (endDate == null) return 0;
        LocalDate end = LocalDate.parse(endDate);
        return end.getYear();
    }

    public int genEndMonthValue() {
        //
        if (endDate == null) return 0;
        LocalDate end = LocalDate.parse(endDate);
        return end.getMonthValue();
    }

    public boolean contains(LocalDate date) {
        //
        if (!hasEndDate()) {
            return false;
        }

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        return (start.isEqual(date) || start.isBefore(date)) && (end.isEqual(date) || end.isAfter(date));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final DatePeriod datePeriod = (DatePeriod) o;

        return Objects.equals(zoneId, datePeriod.zoneId)
            && Objects.equals(startDate, datePeriod.startDate)
            && Objects.equals(endDate, datePeriod.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zoneId, startDate, endDate);
    }

    public static void main(String[] args) {
        //
        System.out.println(sample());
        System.out.println(sample().genMonthPeriod());

        LocalDate now = LocalDate.now();
        LocalDate yesterday = now.minusDays(1);
        LocalDate tomorrow = now.plusDays(1);

        DatePeriod datePeriod = new DatePeriod(ZoneId.systemDefault().getId(), yesterday, tomorrow);

        System.out.println(datePeriod);
        System.out.println(datePeriod.contains(LocalDate.now()));
        System.out.println(datePeriod.contains(LocalDate.now().minusDays(1)));
        System.out.println(datePeriod.contains(LocalDate.now().minusDays(2)));
        System.out.println(datePeriod.contains(LocalDate.now().plusDays(1)));
        System.out.println(datePeriod.contains(LocalDate.now().plusDays(2)));

    }
}