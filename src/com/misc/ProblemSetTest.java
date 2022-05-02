package com.misc;

import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;


public class ProblemSetTest {

        @Test
        void testParseAcceptedLanguages() {
                ProblemSet ps = new ProblemSet();
                String req = "en-US, fr-CA, fr-FR";
                Set<String> supported = Set.of("fr-FR", "en-US");

                assertEquals(2, ps.parseAcceptedLanguages(req, supported).size());
                assertEquals("en-US", ps.parseAcceptedLanguages(req, supported).get(0));
        }

        @Test
        void testParseAcceptedLanguagesWithNoMatching() {
                ProblemSet ps = new ProblemSet();
                String req = "en-CA";
                Set<String> supported = Set.of("fr-CA");

                assertEquals(0, ps.parseAcceptedLanguages(req, supported).size());
        }

        @Test
        void testParseAcceptedLanguagesWithMatchingInOrder() {
                ProblemSet ps = new ProblemSet();
                String req = "en-US,fr-FR";
                Set<String> supported = Set.of( "fr-FR", "en-US");

                assertEquals(2, ps.parseAcceptedLanguages(req, supported).size());
                assertEquals("en-US", ps.parseAcceptedLanguages(req, supported).get(0));
                assertEquals("fr-FR", ps.parseAcceptedLanguages(req, supported).get(1));
        }

}