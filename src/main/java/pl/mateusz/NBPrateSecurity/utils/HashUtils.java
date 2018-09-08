package pl.mateusz.NBPrateSecurity.utils;

import org.mindrot.jbcrypt.BCrypt;

public class HashUtils {

    public static String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static Boolean veryfiPassword(String password, String hashedPassword){
        return BCrypt.checkpw(password, hashedPassword);
    }
}
