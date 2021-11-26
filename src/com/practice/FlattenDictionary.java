package com.practice;

import java.util.HashMap;
import java.util.Map;

/*
N key pairs
TC: O(N)
SC: O(N)
*/

class FlattenDictionary {
    static HashMap<String, String> flattenDictionary(HashMap<String, Object> dict) {
        HashMap<String, String> map = new HashMap<>();
        dict.forEach((key, val) -> {
            if (val instanceof Map) {
                HashMap<String, String> innerMap = flattenDictionary((HashMap<String, Object>) val);
                innerMap.forEach((innerKey, innerVal) -> {
                    String finalKey = (key.length() == 0 || innerKey.length() == 0) ?
                            key + innerKey : key + "." + innerKey;
                    map.put(finalKey, innerVal);
                });
            } else {
                map.put(key, String.valueOf(val));
            }
        });
        return map;
    }

    public static void main(String[] args) {

    }

}
