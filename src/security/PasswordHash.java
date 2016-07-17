package security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Base64;

public class PasswordHash {

	// The higher the number of iterations the more
    // expensive computing the hash is for us and
    // also for an attacker.

	private static final int ITERATIONS = 20*1000;
    private static final int SALT_LENGTH = 32;
    private static final int DESIRED_KEY_LENGTH = 256;

    /** Computes a salted PBKDF2 hash of given plaintext password
        suitable for storing in a database.
        Empty passwords are not supported. */

    public static String getSaltedHash(String password)
    {

    	byte[] salt = new byte[]{};
		try {
			salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(SALT_LENGTH);
		} catch (NoSuchAlgorithmException exception) {

			exception.printStackTrace();
		}
        // store the salt with the password
        return Base64.encodeBase64String(salt) + "$" + hash(password, salt);
    }

    /** Checks whether given plaintext password corresponds
        to a stored salted hash of the password. */

    public static boolean check(String password, String stored){

    	String[] saltAndPass = stored.split("\\$");

        if (saltAndPass.length != 2) {
            throw new IllegalStateException(
                "The stored password have the form 'salt$hash'");
        }
        String hashOfInput = hash(password, Base64.decodeBase64(saltAndPass[0]));
        return hashOfInput.equals(saltAndPass[1]);
    }


    private static String hash(String password, byte[] salt) {

    	SecretKey key = null;
    	if (password == null || password.length() == 0)
            throw new IllegalArgumentException("Empty passwords are not supported.");
        try{
    	SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        key = f.generateSecret(new PBEKeySpec(
            password.toCharArray(), salt, ITERATIONS, DESIRED_KEY_LENGTH)
        );
        }
        catch(Exception exception){

        	exception.printStackTrace();

        }
        return Base64.encodeBase64String(key.getEncoded());
    }

}
