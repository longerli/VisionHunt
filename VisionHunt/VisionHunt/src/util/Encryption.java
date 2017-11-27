package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
	
	public String encoderByMd5(String str) throws NoSuchAlgorithmException{
		
		MessageDigest md5=MessageDigest.getInstance("MD5");
		byte[] inputData = str.getBytes(); 
        md5.update(inputData);   
        BigInteger bigInteger = new BigInteger(md5.digest());
        return bigInteger.toString(16);  
	}

}
