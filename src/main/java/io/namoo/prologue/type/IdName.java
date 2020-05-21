/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 * @since 2014. 6. 10.
 */

package io.namoo.prologue.type;

import io.namoo.prologue.ddd.ValueObject;
import io.namoo.prologue.util.json.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringTokenizer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdName implements ValueObject, Serializable {
    //
    private static final long serialVersionUID = 253022376813035322L;

    private String id;
    private String name;

    public static IdName newInstance(String id, String name) {
        //
        return new IdName(id, name);
    }

    public String toString() {
        //
        return toJson();
    }

    public String toSimpleString() {
        //
        return String.format("%s:%s", id, name);
    }

    public static IdName fromSimpleString(String idNameStr) {
        //
        StringTokenizer tokenizer = new StringTokenizer(idNameStr, ":");
        String id = tokenizer.nextToken();
        String name = tokenizer.nextToken();

        return new IdName(id, name);
    }

    public static IdName sample() {
        //
        String id = "1234";
        String name = "Steve Jobs";

        return new IdName(id, name);
    }

    public static IdName fromJson(String json) {
        //
        return JsonUtil.fromJson(json, IdName.class);
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

        IdName idName = (IdName)target;
        return Objects.equals(this.id, idName.id)
                && Objects.equals(this.name, idName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id+name);
    }

    public static void main(String[] args) {
        //
        System.out.println(sample());
        System.out.println(IdName.fromSimpleString(sample().toSimpleString()));
    }
}
