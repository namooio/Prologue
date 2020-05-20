/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 * @since 2014. 6. 10.
 */

package io.namoo.prologue.util.security.bcrypt;


import io.namoo.prologue.util.security.PasswordEncoder;
import org.mindrot.jbcrypt.BCrypt;

public class BCryptPasswordEncoder implements PasswordEncoder {
    //
    @Override
    public String encode(String password) {
        //
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean check(String password, String encodedPassword) {
        //
        return BCrypt.checkpw(password, encodedPassword);
    }
}
