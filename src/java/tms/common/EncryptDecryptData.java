package tms.common;

import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class EncryptDecryptData
    {
    private static String keyStr = "Uob2022ITProgram";

    public static String encrypt(final String data)
        {
        byte[] decodedKey = Base64.getDecoder().decode(keyStr);

        try
            {
            Cipher cipher = Cipher.getInstance("AES");
            // rebuild key using SecretKeySpec
            SecretKey originalKey = new SecretKeySpec(Arrays.copyOf(decodedKey, 16), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, originalKey);
            byte[] cipherText = cipher.doFinal(data.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(cipherText);
            }
        catch(Exception e)
            {
            throw new RuntimeException("Error occured while encrypting data", e);
            }

        }

    public static String decrypt(final String encryptedString)
        {
        byte[] decodedKey = Base64.getDecoder().decode(keyStr);
        try
            {
            Cipher cipher = Cipher.getInstance("AES");
            // rebuild key using SecretKeySpec
            SecretKey originalKey = new SecretKeySpec(Arrays.copyOf(decodedKey, 16), "AES");
            cipher.init(Cipher.DECRYPT_MODE, originalKey);
            byte[] cipherText = cipher.doFinal(Base64.getDecoder().decode(encryptedString));
            return new String(cipherText);
            }
        catch(Exception e)
            {
            throw new RuntimeException("Error occured while decrypting data", e);
            }
        }

    }
