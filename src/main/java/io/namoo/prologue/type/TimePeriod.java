/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 * @since 2014. 6. 10.
 */

package io.namoo.prologue.type;

import io.namoo.prologue.util.json.JsonSerializable;
import io.namoo.prologue.util.json.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;

@Getter
@Setter
@AllArgsConstructor
public class TimePeriod implements JsonSerializable {
    //
    private static String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    private String zoneId;
    private Long startTime;
    private Long endTime;

    public TimePeriod() {
        //
        this.zoneId = ZoneId.systemDefault().getId();
        this.startTime = System.currentTimeMillis();
        this.endTime = null;
    }

    public TimePeriod(long startTime, long endTime) {
        //
        this.zoneId = ZoneId.systemDefault().getId();
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TimePeriod(ZonedDateTime startTime, ZonedDateTime endTime) {
        //
        this.zoneId = startTime.getZone().getId();
        this.startTime = startTime.toInstant().toEpochMilli();
        this.endTime = endTime.toInstant().toEpochMilli();
    }

    public TimePeriod(LocalDateTime startTime, LocalDateTime endTime) {
        //
        this.zoneId = ZoneId.systemDefault().getId();
        this.startTime = startTime.atZone(ZoneId.of(zoneId)).toInstant().toEpochMilli();
        this.endTime = endTime.atZone(ZoneId.of(zoneId)).toInstant().toEpochMilli();
    }

    public TimePeriod(long startTime) {
        //
        this(startTime, 0L);
    }

    public String toString() {
        //
        return toJson();
    }

    public String toSimpleString() {
        //
        return String.format("StartTime[%s], EndTime[%s]", getStartTimeString(), getEndTimeString());
    }

    public static TimePeriod fromJson(String json) {
        //
        return JsonUtil.fromJson(json, TimePeriod.class);
    }

    public static TimePeriod sample() {
        //
        return new TimePeriod(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
            LocalDateTime.now().plusDays(10).toEpochSecond(ZoneOffset.UTC));
    }

    public LocalDateTime getStartLocalDateTime() {
        //
        return Instant.ofEpochMilli(startTime)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
    }

    public ZonedDateTime getStartZonedDateTime() {
        //
        return Instant.ofEpochMilli(startTime)
            .atZone(ZoneId.systemDefault());
    }

    public LocalDateTime getEndLocalDateTime() {
        //
        return Instant.ofEpochMilli(endTime)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
    }

    public ZonedDateTime getEndZonedDateTime() {
        //
        return Instant.ofEpochMilli(endTime)
            .atZone(ZoneId.systemDefault());
    }

    public String getStartTimeString() {
        //
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(startTime);
        return  new SimpleDateFormat(DEFAULT_TIME_FORMAT).format(cal.getTime());
    }

    public String getEndTimeString() {
        //
        if (endTime == null || endTime == 0L) {
            return "00:00:00";
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(startTime);
        return  new SimpleDateFormat(DEFAULT_TIME_FORMAT).format(cal.getTime());
    }

    public static void main(String[] args) {
        //
        System.out.println(sample());
        System.out.println(sample().toSimpleString());
    }
}
