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

import java.util.StringTokenizer;

@Getter
@Setter
@NoArgsConstructor
public class LongPair implements JsonSerializable {
    //
    private static final String PAIR_TOKEN = ":";
    private long left;
    private long right;

    public LongPair(long left, long right) {
        //
        this.left = left;
        this.right = right;
    }

    public LongPair(long left) {
        //
        this.left = left;
        this.right = 0L;
    }

    public LongPair(String pairString) {
        //
        StringTokenizer tokenizer = new StringTokenizer(pairString, PAIR_TOKEN);
        this.left = Long.parseLong(tokenizer.nextToken());
        this.right = Long.parseLong(tokenizer.nextToken());
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static LongPair sample() {
        //
        return new LongPair("3:5");
    }

    public static LongPair fromJson(String json) {
        //
        return JsonUtil.fromJson(json, LongPair.class);
    }

    public String toSimpleString() {
        //
        return String.format("%d:%d", left, right);
    }

    public void increaseLeft() {
        //
        left++;
    }

    public void increaseRight() {
        //
        right++;
    }

    public void decreaseLeft() {
        //
        left--;
    }

    public void decreaseRight() {
        //
        right--;
    }

    public long gap() {
        //
        return right - left;
    }

    public static void main(String[] args) {
        //
        System.out.println(sample());
    }
}