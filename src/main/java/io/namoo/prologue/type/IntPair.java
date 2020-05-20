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

import java.io.Serializable;
import java.util.StringTokenizer;

@Getter
@Setter
@NoArgsConstructor
public class IntPair implements JsonSerializable, Serializable {
    //
    private static final long serialVersionUID = 7064327073572573418L;
    private static final String PAIR_TOKEN = ":";
    private int left;
    private int right;

    public IntPair(int left, int right) {
        //
        this.left = left;
        this.right = right;
    }

    public IntPair(String pairString) {
        //
        StringTokenizer tokenizer = new StringTokenizer(pairString, PAIR_TOKEN);
        this.left = Integer.parseInt(tokenizer.nextToken());
        this.right = Integer.parseInt(tokenizer.nextToken());
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static IntPair sample() {
        //
        return new IntPair("3:5");
    }

    public static IntPair fromJson(String json) {
        //
        return JsonUtil.fromJson(json, IntPair.class);
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

    public static void main(String[] args) {
        //
        System.out.println(sample());
    }
}
