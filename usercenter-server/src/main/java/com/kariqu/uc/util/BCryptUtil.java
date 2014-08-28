package com.kariqu.uc.util;

import org.apache.log4j.Logger;

/**
 * @author: Koala
 * @Date: 14-7-7 上午10:17
 * @Version: 1.0
 */
public class BCryptUtil {

    private static final Logger LOGGER = Logger.getLogger(BCryptUtil.class);

    public static String encryptPassword(String password){
        String salt = BCrypt.gensalt();
        return  BCrypt.hashpw(password,salt);
    }

    public static boolean check(String password , String hashedPassword){
        try {
            return BCrypt.checkpw(password,hashedPassword);
        } catch (Exception e) {
            LOGGER.error("检查密码时异常(" + password + ", " + hashedPassword + "):", e);
            return false;
        }
    }
}
