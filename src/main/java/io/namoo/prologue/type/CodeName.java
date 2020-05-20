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

import java.util.Objects;
import java.util.StringTokenizer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CodeName implements ValueObject {
    //
    private String code;
    private String name;

    public String toString() {
        //
        return toJson();
    }

    public String toSimpleString() {
        //
        return code + ":" + name;
    }

    public static CodeName fromSimpleString(String codeNameStr) {
        //
        StringTokenizer tokenizer = new StringTokenizer(codeNameStr, ":");
        String code = tokenizer.nextToken();
        String name = tokenizer.nextToken();

        return new CodeName(code, name);
    }

    public static CodeName sample() {
        //
        String code = "1234";
        String name = "NEXTREE";

        return new CodeName(code, name);
    }

    public static CodeName fromJson(String json) {
        //
        return JsonUtil.fromJson(json, CodeName.class);
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

        CodeName idName = (CodeName)target;
        return Objects.equals(this.code, idName.code)
                && Objects.equals(this.name, idName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code+name);
    }

    public static void main(String[] args) {
        //
        System.out.println(sample());
        System.out.println(CodeName.fromSimpleString(sample().toSimpleString()));
    }
}