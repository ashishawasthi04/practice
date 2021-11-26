package com.practice;

import org.junit.Test;
import static org.junit.Assert.*;


public class CaesarCipherTest {
    @Test
    public void testEncryptionWithShift(){
        assertEquals("BcDe", CaesarCipher.rotate("AbCd", 27));
        assertEquals("AbCd", CaesarCipher.rotate("BcDe", -27));
    }

}
