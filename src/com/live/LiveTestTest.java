package com.live;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Testable
class LiveTestTest {
    @Test
    public void testMatchingLanguage(){
        Set<String> supportedLang = new HashSet<>();
        supportedLang.add("fr-FR"); supportedLang.add("en-US");
        LiveTest lt = new LiveTest();
        List<String> output = lt.getMatchingLanguage("en-US, fr-CA, fr-FR", supportedLang);
        assertEquals(2, output.size());
        assertEquals("en-US", output.get(0));
        assertEquals("fr-FR", output.get(1));
    }

    @Test
    public void testMatchingLanguageV2(){
        Set<String> supportedLang = new HashSet<>();
        supportedLang.add("fr-FR"); supportedLang.add("en-US");
        LiveTest lt = new LiveTest();
        List<String> output = lt.getMatchingLanguageV2("en-US, fr-CA, fr-FR", supportedLang);
        assertEquals(2, output.size());
        assertEquals("en-US", output.get(0));
        assertEquals("fr-FR", output.get(1));
    }

    @Test
    public void testMatchingLanguageV2WithVariant(){
        Set<String> supportedLang = new HashSet<>();
        supportedLang.add("en-UK"); supportedLang.add("fr-FR"); supportedLang.add("en-US");
        LiveTest lt = new LiveTest();

        List<String> output = lt.getMatchingLanguageV2("en-US, fr, en, fr-FR", supportedLang);
        assertEquals(3, output.size());
        assertEquals("en-US", output.get(0));
        assertEquals("fr-FR", output.get(1));
        assertEquals("en-UK", output.get(2));
    }
}