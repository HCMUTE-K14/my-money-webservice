package com.vn.hcmute.team.cortana.mymoney.utils;

import java.security.Key;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
    
    private static final String ALGORITHM = "AES";
    private static final byte[] KEY_AES = "zLuERy7&i&QajG=~".getBytes();
    private static String SECRET_KEY = "B&F;%DOO$M@O>U3~";
    
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    public static String generateMD5(String input) {
        return Base64.getEncoder().encodeToString(DigestUtils.md5Hex(input).getBytes());
    }
    
    public static String generateApiKey(String token) {
        return Base64.getEncoder()
                  .encodeToString(DigestUtils.sha256Hex(SECRET_KEY + token).getBytes());
    }
    
    public static String generatePassword() {
        return String.valueOf(new Random().nextInt(900000) + 100000);
    }
    
    public static String encrypt(String valueToEnc) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encValue = c.doFinal(valueToEnc.getBytes());
        String encryptedValue = Base64.getEncoder().encodeToString(encValue);
        return encryptedValue;
    }
    
    public static String decrypt(String encryptedValue) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = Base64.getDecoder().decode(encryptedValue);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
    
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(KEY_AES, ALGORITHM);
        return key;
    }
    
}
