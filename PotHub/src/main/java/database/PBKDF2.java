package database;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import database.model.LoginModel;

public class PBKDF2 {
	
	public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
	public static final int SALT_BYTES = 24;
	public static final int HASH_BYTES = 24;
	public static final int PBKDF2_ITERATIONS = 100000;
	
	public static void main(String [] args) throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		
	}
	
	public static String createHash(String password, String email) throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		return createHash(password.toCharArray(), email);
	}
	
	public static String createHash(char[] password, String email) throws NoSuchAlgorithmException, InvalidKeySpecException
	{
	    // Generate a random salt
	    SecureRandom random = new SecureRandom();
	    byte[] salt = new byte[SALT_BYTES];
	    random.nextBytes(salt);
	    
	    // Hash the password
	    byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTES);
	    
	    LoginModel lm = new LoginModel();
	    lm.setEmail(email);
	    lm.setPassword(toHex(hash));
	    lm.setSalt(toHex(salt));
	    
	    try 
	    {
			Database db = new Database(1);
			db.insertLogin(lm);
		} 
	    catch (FileNotFoundException | ClassNotFoundException | SQLException e)
	    {
			e.printStackTrace();
	    }
	    
	    System.out.println(email + ":" + toHex(salt) + ":" + toHex(hash));
	    return null;
	}
	
	public static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) throws NoSuchAlgorithmException, InvalidKeySpecException
	{
	    PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
	    SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
	    return skf.generateSecret(spec).getEncoded();
	}
	
	public static byte[] fromHex(String hex)
    {
        byte[] binary = new byte[hex.length() / 2];
        for(int i = 0; i < binary.length; i++)
        {
            binary[i] = (byte)Integer.parseInt(hex.substring(2*i, 2*i+2), 16);
        }
        return binary;
    }
	
	public static String toHex(byte[] array)
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0) 
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }
}
