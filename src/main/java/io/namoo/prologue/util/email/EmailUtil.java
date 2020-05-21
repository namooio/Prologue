/*
 * COPYRIGHT (c) NEXTREE 2014
 * This software is the proprietary of NEXTREE CO.
 * @since 2014. 6. 10.
 */
package io.namoo.prologue.util.email;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Reference: https://howtodoinjava.com/regex/java-regex-validate-email-address/

public class EmailUtil {

    private static final String VALID_EMAIL_REG_EXP =
            "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    private static Pattern  pattern = Pattern.compile(VALID_EMAIL_REG_EXP);

    public static boolean isValid(final String email) {
        //
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void main(String[] args) {
        //
        boolean result = isValid("hello@steve.io");
        System.out.println(result);
    }
}