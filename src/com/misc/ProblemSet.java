package com.misc;/*
 * Click `Run` to execute the snippet below!
 */

import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class ProblemSet {

    public List<String> parseAcceptedLanguages(String headerValue, Set<String> supportedLanguages) {
        List<String> reqList = List.of(headerValue.split(","));
        List<String> output = new ArrayList<>();

        for (String reqItem : reqList) {
            reqItem = reqItem.trim();
            if (supportedLanguages.contains(reqItem)) {
                output.add(reqItem);
            }
        }

       return output;
    }

     public List<String> parseLanguagesv2(String header, Set<String> supported) {
         Map<String, List<String>> mapOfSupported = new HashMap<>();
         List<String> output = new ArrayList<>();

         for(String language : supported) {  // en-CA

             // en  -> en-CA eUS,  fr  FR CA
             String[] split = language.split("-");
             if (mapOfSupported.get(split[0]) == null) {
                 mapOfSupported.put(split[0], new ArrayList());
             }
             mapOfSupported.get(split[0]).add(language);
         }

         String[] headerSplit = header.split(",");

         for(String headerItem : headerSplit) {
             output.addAll(mapOfSupported.get(headerItem.trim()));
         }
         return output;
     }
}


/* 
Your previous Plain Text content is preserved below:

Part 1
 
In an HTTP request, the Accept-Language header describes the list of
languages that the requester would like content to be returned in. The header
takes the form of a comma-separated list of language tags. For example:
 
  Accept-Language: en-US, fr-CA, fr-FR
 
means that the reader would accept:
 
  1. English as spoken in the United States (most preferred)
  2. French as spoken in Canada
  3. French as spoken in France (least preferred)
 
We're writing a server that needs to return content in an acceptable language
for the requester, and we want to make use of this header. Our server doesn't
support every possible language that might be requested (yet!), but there is a
set of languages that we do support. Write a function that receives two arguments:
an Accept-Language header value as a string and a set of supported languages,
and returns the list of language tags that will work for the request. The
language tags should be returned in descending order of preference (the
same order as they appeared in the header).
 
In addition to writing this function, you should use tests to demonstrate that it's
correct, either via an existing testing system or one you create.
 
Examples:
 
parse_accept_language(
  "en-US, fr-CA, fr-FR",  # the client's Accept-Language header, a string
  ["fr-FR", "en-US"]      # the server's supported languages, a set of strings
)
returns: ["en-US", "fr-FR"]
 
parse_accept_language("fr-CA, fr-FR", ["en-US", "fr-FR"])
returns: ["fr-FR"]
 
parse_accept_language("en-US", ["en-US", "fr-CA"])
returns: ["en-US"]

en_CA , [fr_CA] -> [ ]

 */