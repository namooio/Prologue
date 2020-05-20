/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 * @since 2014. 6. 10.
 */

package io.namoo.prologue.util.exception;

import io.namoo.prologue.util.json.JsonSerializable;
import io.namoo.prologue.util.json.JsonUtil;

public class Developer implements JsonSerializable {
    //
    private String id;
    private String name;
    private String email;
    private String teamName;

    public Developer(String id,
                     String name,
                     String email,
                     String teamName) {
        //
        this.id = id;
        this.name = name;
        this.email = email;
        this.teamName = teamName;
    }

    public String toString() {
        //
        return toJson();
    }

    public static Developer fromJson(String json) {
        //
        return JsonUtil.fromJson(json, Developer.class);
    }

    public static Developer anonymous() {
        //
        return new Developer(
                "no-id",
                "no-name",
                "no-email",
                "no-team"
        );
    }

    public static Developer sample() {
        //
        return new Developer(
                "A19-10901",
                "Steve Jobs",
                "steve@google.com",
                "Namoosori labs"
        );
    }

    public static void main(String[] args) {
        //
        System.out.println(sample());
    }
}