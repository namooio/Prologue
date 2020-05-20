/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 * @since 2014. 6. 10.
 */

package io.namoo.prologue.util.security;

public interface PasswordEncoder {
    //
    String encode(String password);
    boolean check(String password, String encodedPassword);
}