package com.iguanafix.example;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Auth {

    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.out.println("Exactly 3 arguments required: clientId apiKey apiSecret");
            System.exit(1);
        }
        System.out.println(generatePassword(args[0], args[1], args[2]));
    }

    private static String generatePassword(String clientId, String apiKey, String apiSecret) throws Exception {
        String plainPassword = String.format("%s$%s$%s", clientId, String.valueOf(System.currentTimeMillis()), apiSecret);
        Cipher cipher = prepareCipher(apiKey);
        byte[] cypheredPassword = cipher.doFinal(plainPassword.getBytes());
        return new String(Hex.encodeHex(cypheredPassword));
    }

    private static Cipher prepareCipher(String apiKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(apiKey.getBytes(), "AES"));
        return cipher;
    }

}
