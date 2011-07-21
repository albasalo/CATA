package br.usp.cata.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoService {
	
    public static String md5(String text) {
        if(text == null)
            return null;
        
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger hash = new BigInteger(1, md.digest(text.getBytes()));
        
        return hash.toString(16);
    }
    
}
