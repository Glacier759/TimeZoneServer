package com.glacier.tz.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Glacierlx on 2015/12/15.
 */
public class EncryptUtils {

    public static String md5DEncode(String baseStr) {
        if ( baseStr != null ) {
            return DigestUtils.md5Hex(baseStr);
        }
        return baseStr;
    }

    public static String base64Encode(String baseStr) {
        if ( baseStr != null ) {
            return Base64.encodeBase64String(baseStr.getBytes());
        }
        return baseStr;
    }

    public static String base64Decode(String baseStr) {
        if ( baseStr != null ) {
            return new String(Base64.decodeBase64(baseStr));
        }
        return baseStr;
    }

}
