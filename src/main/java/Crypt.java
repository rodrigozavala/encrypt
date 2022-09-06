import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Crypt {

    private static IvParameterSpec IV;

    static {
        IV=generateIv();
    }


    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        System.out.println("The key is:"+key.toString());
        return key;
    }

    public static SecretKey getKeyFromPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
                .getEncoded(), "AES");
        return secret;
    }

    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        /*System.out.println("The iv:"+iv.toString());
        for(int i=0;i<iv.length;i++){
            System.out.println(iv[i]);
        }*/
        return new IvParameterSpec(iv);
    }

    public static IvParameterSpec generateIv(byte[] iv){
        return new IvParameterSpec(iv);
    }


    public static String encrypt(String algorithm, String input, SecretKey key,
                                 IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {


        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }
    public static String decrypt(String algorithm, String cipherText, SecretKey key,
                                 IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
                .decode(cipherText));
        return new String(plainText);
    }

    /**
     * Not optimized to use in the program
     * @param originalText
     * @param password
     * @param salt
     * @return
     */
    public static String encryptText(String originalText, String password, String salt){
        try {
            SecretKey key = getKeyFromPassword(password,salt); //generateKey(128);
            IvParameterSpec ivParameterSpec = IV;//generateIv();
            String algorithm = "AES/CBC/PKCS5Padding";
            String cipherText = encrypt(algorithm, originalText, key, ivParameterSpec);
            //String plainText = decrypt(algorithm, cipherText, key, ivParameterSpec);

            return cipherText;
        }catch(RuntimeException e) {
            System.err.println(e.getMessage());
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
    public static String encryptText(String originalText, String password, String salt,byte[] iv){
        try {
            SecretKey key = getKeyFromPassword(password,salt); //generateKey(128);
            IvParameterSpec ivParameterSpec = generateIv(iv);//generateIv();
            String algorithm = "AES/CBC/PKCS5Padding";
            String cipherText = encrypt(algorithm, originalText, key, ivParameterSpec);

            return cipherText;
        }catch(RuntimeException e) {
            System.err.println(e.getMessage());
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println("1)"+e.getMessage());
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println("2)"+e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println("3)"+e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println("4)"+e.getMessage());
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println("5)"+e.getMessage());
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println("6)"+e.getMessage());
        } catch (InvalidKeySpecException e) {
            System.err.println("7)"+e.getMessage());
            throw new RuntimeException(e);
        }
        return "";
    }


    /**
     * Not optimized to run in the program
     * @param cipherText
     * @param password
     * @param salt
     * @return
     */
    public static String decryptText(String cipherText, String password, String salt) {
        try {
            SecretKey key = getKeyFromPassword(password,salt);//generateKey(128);
            IvParameterSpec ivParameterSpec = IV;;//generateIv();
            String algorithm = "AES/CBC/PKCS5Padding";
            //String cipherText = encrypt(algorithm, input, key, ivParameterSpec);
            String plainText = decrypt(algorithm, cipherText, key, ivParameterSpec);

            return plainText;
        }catch(RuntimeException e) {
            System.err.println(e.getMessage());
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    public static String decryptText(String cipherText, String password, String salt,byte[] iv) {
        //String input = "baeldung";
        try {
            SecretKey key = getKeyFromPassword(password,salt);//generateKey(128);
            IvParameterSpec ivParameterSpec = generateIv(iv);;//generateIv();
            String algorithm = "AES/CBC/PKCS5Padding";
            String plainText = decrypt(algorithm, cipherText, key, ivParameterSpec);

            return plainText;
        }catch(RuntimeException e) {
            System.err.println(e.getMessage());
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println("1)"+e.getMessage());
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println("2)"+e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println("3)"+e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println("4)"+e.getMessage());
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println("5)"+e.getMessage());
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println("6)"+e.getMessage());
        } catch (InvalidKeySpecException e) {
            System.err.println("7)"+e.getMessage());
            throw new RuntimeException(e);
        }
        return "";

    }


}
