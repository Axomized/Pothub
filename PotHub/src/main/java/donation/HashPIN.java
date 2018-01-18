package donation;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class HashPIN {
	public static final int iterations = 100000;
	public static final int keyLength = 256;

	public byte [] hashPIN(String password, byte[] salt, int iterations, int keyLength ) {
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
			PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);
			SecretKey key = skf.generateSecret(spec);
			byte [] hash = key.getEncoded();
			return hash;
	 
	       } catch(NoSuchAlgorithmException | InvalidKeySpecException e) {
	           throw new RuntimeException(e);
	       }
	   }
	
	public byte [] createSalt() {
		SecureRandom SRandom = new SecureRandom();
		byte [] salt = new byte [32];
		SRandom.nextBytes(salt);
		return salt;
	}
	
	public String getEncodedSalt(byte[] salt) {
		Base64.Encoder enc = Base64.getEncoder();
		String encodedSalt = enc.encodeToString(salt);
		return encodedSalt;
	}
	
	public byte [] getDecodedSalt(String saltToDecode) {
		Base64.Decoder dnc = Base64.getDecoder();
		byte [] saltDecoded = dnc.decode(saltToDecode);
		return saltDecoded;
	}
	
	public String getHashedPIN(String password, byte[] salt) {
		Base64.Encoder enc = Base64.getEncoder();
		String hashedPIN = enc.encodeToString(hashPIN(password, salt, iterations, keyLength));
		return hashedPIN;
	}

	public static void main(String[] args) {
		HashPIN HP = new HashPIN();
		byte [] newSalt = HP.createSalt();
		System.out.println("Salt: " + HP.getEncodedSalt(HP.createSalt()));
		System.out.println("Hash: " + HP.getHashedPIN("S3675418I", newSalt));
	}

}
