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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RowColumnIndex implements JsonSerializable {
    //
    private int rowIndex;
    private int columnIndex;

    public String toString() {
        //
        return toJson();
    }

    public RowColumnIndex nextRow() {
        //
        return new RowColumnIndex(rowIndex + 1, columnIndex);
    }

    public RowColumnIndex nextColumn() {
        //
        return new RowColumnIndex(rowIndex, columnIndex + 1);
    }

    public boolean isValid() {
        //
        return rowIndex >= 0 && columnIndex >= 0;
    }

    public static RowColumnIndex fromJson(String json) {
        //
        return JsonUtil.fromJson(json, RowColumnIndex.class);
    }

    public static RowColumnIndex sample() {
        //
        return new RowColumnIndex(1,4);
    }

    public static void main(String[] args) {
        //
        System.out.println(sample());
    }
}