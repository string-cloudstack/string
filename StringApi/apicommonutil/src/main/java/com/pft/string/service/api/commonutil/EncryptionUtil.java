package com.pft.string.service.api.commonutil;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {

	/**
	 * Turns array of bytes into string
	 * 
	 * @param buf
	 *            Array of bytes to convert to hex string
	 * @return Generated hex string
	 */
	public static String asHex(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;

		for (i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");

			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}

		return strbuf.toString();
	}

	/**
	 * Returns a hex byte
	 * 
	 * @param encoded
	 * @return
	 */
	private static byte[] hexByte(final String encoded) {
		if ((encoded.length() % 2) != 0)
			throw new IllegalArgumentException(
					"Input string must contain an even number of characters");

		final byte result[] = new byte[encoded.length() / 2];
		final char enc[] = encoded.toCharArray();
		for (int i = 0; i < enc.length; i += 2) {
			StringBuilder curr = new StringBuilder(2);
			curr.append(enc[i]).append(enc[i + 1]);
			result[i / 2] = (byte) Integer.parseInt(curr.toString(), 16);
			// result[i/2] = (byte) curr.toString().getBytes();
		}
		return result;
	}

	public static String RandomKeyGeneration(int length) throws Exception {
		KeyGenerator kg = KeyGenerator.getInstance("AES");
		SecretKey sk;
		kg.init(length);
		sk = kg.generateKey();
		return asHex(sk.getEncoded());
	}

	/**
	 * Encrypts a message using the encryption salt.
	 * 
	 * @param pass
	 *            - encryption salt
	 * @param message
	 *            - message to encrypt
	 * @return an encrypted message
	 * @throws Exception
	 */
	public static String encrypt(String pass, String message) throws Exception {
		if (pass.length() > 16) {
			pass = pass.substring(0, 16);
		}
		byte[] keyMaterial = pass.getBytes("UTF-8");		
	        
		SecretKeySpec key = new SecretKeySpec(keyMaterial, "AES");
		
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(new byte[16]));
		byte[] plaintext = message.getBytes("UTF-8");
		byte[] ciphertext = cipher.doFinal(plaintext);
		return asHex(ciphertext);

	}
	
	/*public static void main(String[] args)
	{
		 try {
			String encryptedMsg = EncryptionUtil.encrypt("2d54945cacf928a5e3bcb3d5af2b074d", "68088e833f0c4767ceddb453cff6338003d050972520867c29b86e067843d515");			
			System.out.println("Encrypted String = "+encryptedMsg);
			String decryptedMsg = EncryptionUtil.decrypt("2d54945cacf928a5e3bcb3d5af2b074d", encryptedMsg);
			System.out.println("Decrypted String = "+decryptedMsg);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
*/
	/**
	 * Decrypts a message using the encryption salt.
	 * 
	 * @param pass
	 *            - encryption salt
	 * @param message
	 *            - message to decrypt
	 * @return decrypted message
	 * @throws Exception
	 */
	public static String decrypt(String pass, String message) throws Exception {
		if (pass.length() > 16) {
			pass = pass.substring(0, 16);
		}
		byte[] keyMaterial = pass.getBytes("UTF-8");			        
		SecretKeySpec key = new SecretKeySpec(keyMaterial, "AES");
		
		// decrypting	
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(new byte[16]));
		//byte[] original = hexByte(message);
		byte[] original = cipher.doFinal(hexByte(message));
		String originalString = new String(original,"UTF-8");		
		return originalString;
	}
	public static String encryptWithPadding(String pass, String message) throws Exception {
		if (pass.length() > 16) {
			pass = pass.substring(0, 16);
		}
		byte[] keyMaterial = pass.getBytes("UTF-8");		
	        
		SecretKeySpec key = new SecretKeySpec(keyMaterial, "AES");
		int Padsize = message.length()%16;
		if(Padsize>0)
		{
			message=message+"#";
			for(int index=0;index < 15-Padsize ; index++)
			message=message+"a";
		}
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(new byte[16]));
		byte[] plaintext = message.getBytes("UTF-8");
		byte[] ciphertext = cipher.doFinal(plaintext);
		return asHex(ciphertext);
	}
	public static String decryptWithPadding(String pass, String message) throws Exception {
		if (pass.length() > 16) {
			pass = pass.substring(0, 16);
		}
		byte[] keyMaterial = pass.getBytes("UTF-8");			        
		SecretKeySpec key = new SecretKeySpec(keyMaterial, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");		
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(new byte[16]));
		//byte[] original = hexByte(message);
		byte[] original = cipher.doFinal(hexByte(message));
		String originalString = new String(original,"UTF-8");
		if(originalString.contains("#"));
		{
		  originalString=originalString.substring(0,originalString.lastIndexOf("#"));
		}
		return originalString;
	}

}
