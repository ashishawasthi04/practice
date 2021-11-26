package com.practice;

import java.util.ArrayList;
import java.util.List;

public class CouponCode {
    public static void main(String[] args) {
        List<String> discount = List.of("abba", "abca", "abbacbbc", "aabb", "xaaxybbyzccz", "vaas", "jay");
        // Expected O/P - [1, 0, 1, 1, 1, 0, 0]
        System.out.println(discount.toString());
        List<Integer> list = findDiscounts(discount);
        System.out.println(list);
        // TC - O(n * k), n--> length of the array input & k --> length of each string in input array
        // SC - O(n) - no DS used other than to store response
    }

    private static List<Integer> findDiscounts(List<String> discount) {
        List<Integer> result = new ArrayList<>();
        for (String str : discount) {
            if (str.equals("")) {
                // Condition #1
                result.add(1);
            } else if (str.length() % 2 != 0) {
                // String has to be even length as per given condition, else its invalid coupon
                result.add(0);
            } else if (isPalindrome(str)) {
                // If string is even length and palindrome, it's valid!
                result.add(1);
            } else if (isPalindrome(findPalindromicPart(str, true)) && isPalindrome(findPalindromicPart(str, false))) {
                // We can also have concatenation of 2 even palindromes (condition #3) which are valid!
                result.add(1);
            } else {
                result.add(0);
            }
        }
        return result;
    }

    private static boolean isPalindrome(String str) {
        if (str == null || str.trim().equals("")) return false;
        int start = 0,
                end = str.length() - 1;
        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    private static String findPalindromicPart(String str, boolean findLeftPart) {
        // We need to extract palindrome part - it can either be on left side or right side
        // of the string. So we need to check both.
        // So we have this findLeftPart boolean variable
        // We could have simplified this and have 2 functions instead - one for finding
        // left and other for finding right side palindrome, but that would cause duplicate lines of code
        int start = 0, end = str.length() - 1;
        StringBuilder result = new StringBuilder("");
        while (start < end) {
            if (str.charAt(start) == str.charAt(end)) {
                result.append(str.charAt(start));
                start++;
                end--;
            } else if (str.charAt(start) != str.charAt(end)) {
                if (findLeftPart) end--;
                else start++;
                result.setLength(0);
            }
        }
        return result.toString() + result.reverse().toString();
    }
}
