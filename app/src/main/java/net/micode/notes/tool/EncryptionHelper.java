package net.micode.notes.tool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import android.util.Base64;

public class EncryptionHelper {

    public static byte[] encrypt(byte[] key, byte[] initVector, byte[] value) throws Exception {
        if (!isValidKeyLength(key.length)) {
            throw new IllegalArgumentException("Invalid AES key length: " + key.length);
        }

        IvParameterSpec iv = new IvParameterSpec(initVector);
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

        return cipher.doFinal(value);
    }

    public static byte[] decrypt(byte[] key, byte[] initVector, byte[] encrypted) throws Exception {
        if (!isValidKeyLength(key.length)) {
            throw new IllegalArgumentException("Invalid AES key length: " + key.length);
        }

        IvParameterSpec iv = new IvParameterSpec(initVector);
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

        return cipher.doFinal(encrypted);
    }

    private static boolean isValidKeyLength(int length) {
        return length == 16 || length == 24 || length == 32;
    }
}
