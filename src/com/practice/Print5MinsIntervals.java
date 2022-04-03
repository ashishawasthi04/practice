package com.practice;

import java.util.*;

/*
Input: "mon 10:00 am"
Output: "11000"
Explanation: the first 1 is day so Sunday will be 7 for example. That's followed by 10 for the hour and 00 for the minute.

The question is to generate the 5 minutes intervals between say

"mon 10:00am" and "mon 11:00am"
output ->11000, 11005, 11010, 11015 ... 11100

 */
public class Print5MinsIntervals {
    static List<String> daysList = daysList = Arrays.asList("", "MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN");

    public static void main(String[] args) {
        print("mon 10:00 am", "Tue11:00pm");
    }

    public static void print(String start, String end) {
        int[] values1 = convert(start);
        int[] values2 = convert(end);
        String target = format(values2);
        String source = format(values1);
        System.out.println(source);
        while (!source.equals(target)) {
            values1[2] += 5;
            if (values1[2] == 60) {
                values1[2] = 0;
                values1[1]++;
            }
            if (values1[1] == 24) {
                values1[1] = 0;
                values1[0]++;
            }
            if (values1[0] > 7) values1[0] = 1;
            source = format(values1);
            System.out.println(source);
        }
    }

    public static String format(int[] values) {
        return values[0] +
                (values[1] < 10 ? "0" : "") +
                values[1] +
                (values[2] < 10 ? "0" : "") +
                values[2];
    }

    public static int[] convert(String time) {
        time = time.trim();
        int length = time.length();
        String day = time.substring(0, 3).toUpperCase();
        boolean isPM = "PM".equals(time.substring(length - 2).toUpperCase());
        String[] tokens = time.substring(3, length - 2).trim().split(":");
        int hours = Integer.parseInt(tokens[0]);
        int minutes = Integer.parseInt(tokens[1]);
        hours += isPM ? 12 : 0;
        return new int[]{daysList.indexOf(day), hours, minutes};
    }
}
