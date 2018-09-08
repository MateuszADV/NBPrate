package pl.mateusz.NBPrateSecurity.hash;

import org.junit.Test;
import pl.mateusz.NBPrateSecurity.utils.HashUtils;

import static org.junit.Assert.assertTrue;

public class HashUtilTest {

    @Test
    public void testPasswordRutine(){
        String secredPhare = "moje tajne hasło";
        String passwordHashed = HashUtils.hashPassword(secredPhare);
        System.out.println(passwordHashed);

        String secredpharam2 = "tajne hasło 2";
        String passwordHashed2 = HashUtils.hashPassword(secredpharam2);

        assertTrue(HashUtils.veryfiPassword(secredPhare,passwordHashed));
        assertTrue(HashUtils.veryfiPassword(secredpharam2,passwordHashed2));

    }
}
