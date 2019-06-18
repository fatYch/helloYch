package com.yaoch.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

public class SHA256Util {

    private final static Logger log = LoggerFactory.getLogger(SHA256Util.class);

    public static byte[] HMACSHA256(String data, String key) {
        Mac sha256_HMAC = null;
        try {
            sha256_HMAC = Mac.getInstance("HmacSHA256");

            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
            return array;
//            StringBuilder sb = new StringBuilder();
//            for (byte item : array) {
//                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
//            }
//            return sb.toString();
        }
        catch (Exception e) {
            log.error("HMAC SHA256出错:",e);
            return null;
        }
    }
}
