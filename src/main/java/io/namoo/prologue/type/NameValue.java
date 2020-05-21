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

import java.util.Objects;
import java.util.StringTokenizer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NameValue implements JsonSerializable {
    //
    private String name;
    private String value;

    public static NameValue newInstance(String name, String value) {
        //
        return new NameValue(name, value);
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    public static NameValue fromJson(String json) {
        //
        return JsonUtil.fromJson(json, NameValue.class);
    }

    @Override
    public boolean equals(Object target) {
        //
        if (this == target) {
            return true;
        }

        if (target == null || getClass() != target.getClass()) {
            return false;
        }

        NameValue nameValue = (NameValue)target;
        return Objects.equals(this.name, nameValue.name)
                && Objects.equals(this.value, nameValue.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name+value);
    }

    public String toSimpleString() {
        //
        return String.format("%s:%s", name, value);
    }

    public static NameValue fromSimpleString(String nameValueString) {
        //
        StringTokenizer tokenizer = new StringTokenizer(nameValueString, ":");
        String name = tokenizer.nextToken();
        String value = tokenizer.nextToken();

        return new NameValue(name, value);
    }

    public static NameValue sample() {
        //
        return new NameValue("name", "Cheolsoo Kim");
    }

    public static void main(String[] args) {
        //
        System.out.println(sample());
        System.out.println(sample().toSimpleString());
    }
}
