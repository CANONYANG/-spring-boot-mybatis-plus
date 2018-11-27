package com.njbandou.web.shiro;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * The type Encrypt utils.
 */
public class EncryptUtils {

    public static void main(String[] args) {
//        System.out.println(encrypt("SysAdmin","123456","YzcmCZNvbXocrsz9dm8e"));
//        System.out.println(createSalt());
    }

    /**
     * Encrypt string.
     *
     * @param account  the account
     * @param password the password
     * @param salt     the salt
     * @return the string
     */
    public static String encrypt(String account, String password, String salt){
        String algorithmName = "SHA-512";
        int hashIterations = 2;
        SimpleHash hash = new SimpleHash(algorithmName, password, account + salt, hashIterations);
        String encodedPassword = hash.toHex();
        return encodedPassword;
    }
    /**
     * Create salt string.
     *
     * @return the string
     */
    public static String createSalt(){
        return new SecureRandomNumberGenerator().nextBytes().toHex();
    }
    
}
