/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 * @since 2014. 6. 10.
 */

package io.namoo.prologue.type;

import io.namoo.prologue.util.json.JsonSerializable;
import io.namoo.prologue.util.json.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderedOffset implements JsonSerializable {
    //
    private int offset;
    private int limit;

    private String sortKey;
    private boolean descending;

    public OrderedOffset(int offset,
                         int limit,
                         String sortKey) {
        //
        this(offset, limit, sortKey, false);
    }

    public OrderedOffset(int offset,
                         int limit,
                         String sortKey,
                         boolean descending) {
        //
        this.offset = offset;
        this.limit = limit > 0 ? limit : 10;
        this.sortKey = sortKey;
        this.descending = descending;
    }

    public static OrderedOffset newDefault(String sortKey) {
        //
        return new OrderedOffset(0, 10, sortKey);
    }

    public static OrderedOffset newOne(int offset,
                                                                    int limit,
                                                                    String sortKey) {
        //
        return new OrderedOffset(offset, limit, sortKey);
    }

    public static OrderedOffset newOne(int offset,
                                                                    int limit,
                                                                    String sortKey,
                                                                    boolean descending) {
        //
        return new OrderedOffset(offset, limit, sortKey, descending);
    }

    public String toString() {
        //
        return toJson();
    }

    public static OrderedOffset fromJson(String json) {
        //
        return JsonUtil.fromJson(json, OrderedOffset.class);
    }

    public static OrderedOffset sample() {
        //
        return new OrderedOffset(0,20, "title");
    }

    public boolean ascending() {
        //
        return !descending;
    }

    public int offset() {
        //
        return offset;
    }

    public int limit() {
        //
        return limit;
    }

    public int page() {
        //
        return (offset/limit);
    }

    public int sum() {
        //
        return offset + limit;
    }

    public String sortKey() {
        //
        return sortKey;
    }

    public boolean descending() {
        //
        return descending;
    }

    public static void main(String[] args) {
        //
        System.out.println(sample());
    }
}