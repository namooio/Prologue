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
public class Offset implements JsonSerializable {
    //
    private int offset;
    private int limit;

    public Offset(int offset,
                  int limit) {
        //
        if(limit < 0) {
            throw new IllegalArgumentException("Limit value should be plus: " + limit);
        }

        this.offset = offset;
        this.limit = limit;
    }

    public static Offset newDefault() {
        //
        return new Offset(0, 10);
    }

    public static Offset newInstance(int offset, int limit) {
        //
        return new Offset(offset, limit);
    }

    public String toString() {
        //
        return toJson();
    }

    public static Offset fromJson(String json) {
        //
        return JsonUtil.fromJson(json, Offset.class);
    }

    public static Offset sample() {
        //
        return new Offset(0,20);
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

    public int nextOffsetValue() {
        //
        return offset + limit;
    }

    public Offset nextOffset() {
        //
        return new Offset(nextOffsetValue(), limit);
    }

    public static void main(String[] args) {
        //
        System.out.println(sample());
    }
}
