package com.opsrag.backend.common.utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class PasswordEncoding {
    /**
     * 对原始密码进行 SHA-256 哈希
     * @param rawPassword 明文密码
     * @return 64位十六进制字符串（小写）
     */
    public static String encode(String rawPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    /**
     * 校验密码是否匹配
     * @param rawPassword 明文密码
     * @param encodedPassword 数据库中存储的哈希值
     * @return 是否匹配
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            String hexByte = Integer.toHexString(0xff & b);
            if (hexByte.length() == 1) hex.append('0');
            hex.append(hexByte);
        }
        return hex.toString();
    }
}