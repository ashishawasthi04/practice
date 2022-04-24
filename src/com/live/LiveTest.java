package com.live;

import java.util.*;

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
public class LiveTest {
    //No other patterns
    //Non-empty input request and supported language
    //Returns List or Array

    //Iterate over AL list
    // For each lang, check if that exists in the set (supported language)
    // If YES => Add the lang to the output collection
    // Return output collection
    //TC: O(N) => N size of AL list
    //SC: O(M) => M is common lang b/w AL & Supported language

    public List<String> getMatchingLanguage(String acceptedLang, Set<String> supportedLang){
        List<String> output = new ArrayList<>();
        String[] requestLangArr = acceptedLang.split(",");

        for(String requestLang : requestLangArr){
            requestLang = requestLang.trim();
            if(supportedLang.contains(requestLang)){
                output.add(requestLang);
            }
        }
        return output;
    }

    public List<String> getMatchingLanguageV2(String acceptedLang, Set<String> supportedLang){
        //Create a MAP fr => fr-FR, fr-CA
        //Iterate over AL list
        // For each lang, check if that exists in the set (supported language)
        // If YES => Add the lang to the output collection
        // If NO then check in the MAP
            // If YES then add all languages for given lang code to the output
        // Return output collection
        List<String> output = new ArrayList<>();
        Map<String, Set<String>> langMap = new HashMap<>();
        String[] requestLangArr = acceptedLang.split(",");

        for(String supported : supportedLang){
            String[] langCountryPair = supported.split("-");
            langMap.putIfAbsent(langCountryPair[0], new HashSet<>());
            langMap.get(langCountryPair[0]).add(supported);
        }

        for(String requestLang : requestLangArr){
            requestLang = requestLang.trim();
            String[] langCountryPair = requestLang.split("-");
            if(supportedLang.contains(requestLang)){
                output.add(requestLang);
                supportedLang.remove(requestLang);
                langMap.get(langCountryPair[0]).remove(requestLang);
            }else if(langMap.containsKey(langCountryPair[0])){
                langMap.get(langCountryPair[0]).forEach(item -> {
                    output.add(item);
                    supportedLang.remove(item);
                });
                langMap.remove(langCountryPair[0]);
            }
        }
        return output;
    }

}
