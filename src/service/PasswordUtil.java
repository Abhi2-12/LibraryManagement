
package service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {

    private static final int ITERATIONS = 600_000;
    private static final int KEY_LENGTH = 256;

    public static String hashPassword(String password)
            throws Exception {

        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);

        PBEKeySpec spec = new PBEKeySpec(
            password.toCharArray(),
            salt,
            ITERATIONS,
            KEY_LENGTH
        );

        byte[] hash = SecretKeyFactory
            .getInstance("PBKDF2WithHmacSHA256")
            .generateSecret(spec)
            .getEncoded();

        spec.clearPassword();

        return ITERATIONS + ":"
            + Base64.getEncoder().encodeToString(salt) + ":"
            + Base64.getEncoder().encodeToString(hash);
    }

    
    public static boolean verifyPassword(String password, String storedHash)
            throws Exception {

        String[] parts = storedHash.split(":");

        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = Base64.getDecoder().decode(parts[1]);
        byte[] expectedHash = Base64.getDecoder().decode(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(
            password.toCharArray(),
            salt,
            iterations,
            expectedHash.length * 8
        );

        byte[] actualHash = SecretKeyFactory
            .getInstance("PBKDF2WithHmacSHA256")
            .generateSecret(spec)
            .getEncoded();

        spec.clearPassword();

        return java.security.MessageDigest.isEqual(expectedHash, actualHash);
    }
}