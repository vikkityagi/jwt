package com.example.demo.config;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Hmac512PasswordEncoder implements PasswordEncoder {

private static final String SSHA512_PREFIX = "{SSHA-512}";
private static final String HMAC_SHA512 = "HmacSHA512";

private final String salt;


public Hmac512PasswordEncoder(String salt) {
    if (salt == null) {
        throw new IllegalArgumentException("salt cannot be null");
    }
    this.salt = salt;
}

public String encode(CharSequence rawPassword) {
    // String result = null;
    // System.out.println("Hmac512PasswordEncoder-rawPassword -"+rawPassword);
    // try {
    //     Mac sha512Hmac = Mac.getInstance(HMAC_SHA512);
    //     final byte[] byteKey = Utf8.encode(salt);
    //     SecretKeySpec keySpec = new SecretKeySpec(byteKey, HMAC_SHA512);
    //     sha512Hmac.init(keySpec);
    //     byte[] macData = sha512Hmac.doFinal(Utf8.encode(rawPassword.toString()));

    //     result = SSHA512_PREFIX + Base64.getEncoder().encodeToString(macData);
    //     //result = bytesToHex(macData);
    // } catch (InvalidKeyException | NoSuchAlgorithmException e) {
    //     e.printStackTrace();
    // }
    // System.out.println("Hmac512PasswordEncoder-result -"+result);

    // return result;
    rawPassword = encryptThisString(rawPassword.toString());
    return rawPassword.toString();
}

 public  String encryptThisString(String input)
    {
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");
  
            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());
  
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
  
            // Convert message digest into hex value
            String hashtext = no.toString(16);
  
            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
  
            // return the HashText
            return hashtext;
        }
  
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
public boolean matches(CharSequence rawPassword, String encodedPassword) {
    System.out.println("rawPassword -"+rawPassword);
    System.out.println("encodedPassword -"+encodedPassword);
    if (rawPassword == null || encodedPassword == null) {
        return false;
    }

    String encodedRawPass = encode(rawPassword);
  System.out.println("encodedRawPass -"+encodedRawPass);
    return MessageDigest.isEqual(Utf8.encode(encodedRawPass), Utf8.encode(encodedPassword));
}
}
