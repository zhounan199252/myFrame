package com.gzhd.util;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.lang3.StringUtils;

  
public class SecurityHelper {
	private final static int ITERATIONS = 20;
	private  static String key = "MoneyToMoney"; //固定密钥，
	
	 /**
     * @name encode
     * @Description: 将字符串进行加密
     * @param String 
     * @return String 
     */
	public static String encode(String plainText)  {
		
		if(StringUtils.isBlank(plainText)) {
			return null;
		}
		
		String encodeData = null;
		try {
			
				byte[] salt = new byte[8];
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(key.getBytes());
				byte[] digest = md.digest();
				for (int i = 0; i < 8; i++) {
					salt[i] = digest[i];
				}
				
				PBEKeySpec pbeKeySpec = new PBEKeySpec(key.toCharArray());
				SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance("PBEWithMD5AndDES");
				SecretKey skey = keyFactory.generateSecret(pbeKeySpec);
				PBEParameterSpec paramSpec = new PBEParameterSpec(salt, ITERATIONS);
				
				Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
				cipher.init(Cipher.ENCRYPT_MODE, skey, paramSpec);
				byte[] cipherText = cipher.doFinal(plainText.getBytes());
				String saltString = new String(Base64.encode(salt));
				String ciphertextString = new String(Base64.encode(cipherText));
				encodeData = saltString + ciphertextString;
			} catch (Exception e) {
				e.printStackTrace();
			} 
		
		encodeData = encodeData.replace("+", "%2B");
		return encodeData;
	}

	
	/**
     * @name decode
     * @Description: 对使用类SecurityHelper的加密方法得到的数据解密
     * @param String 
     * @return String 
	 * @throws Exception 
     */
	public static String decode(String encodeTxt) throws Exception{
		encodeTxt = encodeTxt.replace("%2B", "+"); 
		int saltLength = 12;
		byte[] plaintextArray = null;
	
			String salt = encodeTxt.substring(0, saltLength);
			String ciphertext = encodeTxt.substring(saltLength,encodeTxt.length());
			
			byte[] saltarray = Base64.decode(salt.getBytes());
			byte[] ciphertextArray = Base64.decode(ciphertext.getBytes());
			
			PBEKeySpec keySpec = new PBEKeySpec(key.toCharArray());
			SecretKeyFactory keyFactory = SecretKeyFactory
				.getInstance("PBEWithMD5AndDES");
			SecretKey skey = keyFactory.generateSecret(keySpec);
			PBEParameterSpec paramSpec = new PBEParameterSpec(saltarray,
				ITERATIONS);
			Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
			cipher.init(Cipher.DECRYPT_MODE, skey, paramSpec);
			plaintextArray = cipher.doFinal(ciphertextArray);
				
			
			return new String(plaintextArray);
	}
		
}
