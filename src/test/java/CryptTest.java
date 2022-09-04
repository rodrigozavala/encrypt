import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CryptTest {

    @Test
    void encryptCookie() {
        String pass="jojojo";
        String salt="1234";
        String textCryp=Crypt.encryptText("Hola",pass,salt);
        System.out.println("The encrypted message is:"+textCryp);
        String original=Crypt.decryptText(textCryp,pass,salt);
        System.out.println("The message is:"+original);
        assertEquals("Hola",original);
        /*
        Note: I need to make a hardcoded initialization vector because the current program always make a new one
        with random numbers.
        This initialization vector must be the same in encrypt and decrypt
        Some questions:
        -Is there a way to make a better initialization vector than others? where do I put it?
        -Maybe I need some insight from other programmers, and also I need to know how does this
        encryption algorithm works.
        * */
        byte[] b=new byte[10];
        /*for(int i=0;i<b.length;i++){
            b[i]=(byte)i;
            System.out.println(b[i]);
        }*/




    }

    @Test
    void decryptCookie() {
    }
}