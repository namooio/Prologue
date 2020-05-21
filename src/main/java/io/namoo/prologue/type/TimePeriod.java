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
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.zone.ZoneRulesException;
import java.util.Calendar;

@Getter
@Setter
public class TimePeriod implements JsonSerializable {
    //
    private static String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    private String zoneId;
    private String startTime;
    private String endTime;

    public TimePeriod() {
        //
        this.zoneId = ZoneId.systemDefault().getId();
        this.startTime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
        this.endTime = LocalDateTime.now().plusHours(1).format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    public TimePeriod(LocalDateTime startTime, LocalDateTime endTime) {
        //
        this.zoneId = ZoneId.systemDefault().getId();
        this.startTime = startTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
        this.endTime = startTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    public TimePeriod(String startTime, String endTime) {
        //
        this(ZoneId.systemDefault().getId(), startTime, endTime);
    }

    public TimePeriod(String zoneId, String startTime, String endTime) {
        //
        try {
            ZoneId.of(zoneId);
            this.zoneId = zoneId;
        } catch (ZoneRulesException e) {
            throw new IllegalArgumentException("Invalid zone id: " + zoneId);
        }

        try {
            LocalDateTime.parse(startTime);
            this.startTime = startTime;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format: " + startTime);
        }

        try {
            LocalDateTime.parse(endTime);
            this.endTime = endTime;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format: " + endTime);
        }
    }

    public String toString() {
        //
        return toJson();
    }

    public String toSimpleString() {
        //
        return String.format("%s:%s", startTime, endTime);
    }

    public static TimePeriod fromJson(String json) {
        //
        return JsonUtil.fromJson(json, TimePeriod.class);
    }

    public static TimePeriod sample() {
        //
        return new TimePeriod();
    }

    public LocalDateTime getStartLocalDateTime() {
        //
        return LocalDateTime.parse(startTime);
    }

    public static void main(String[] args) {
        //
        System.out.println(sample());
        System.out.println(sample().toSimpleString());
    }
}
