package com.practice;

import java.util.ArrayList;
import java.util.List;

/*
# An ideal parse will look like this:
# [['John', 'Smith', 'john.smith@gmail.com', 'Los Angeles', '1'],
#  ['Jane', 'Roberts', 'janer@msn.com', 'San Francisco, CA', '0'],
#  ['Alexandra "Alex"', 'Menendez', 'alex.menendez@gmail.com', 'Miami', '1'],
#  ['1','2','','4','5']]

csv_lines = [
  'John,Smith,john.smith@gmail.com,Los Angeles,10',
  'Jane,Roberts,janer@msn.com,"San Francisco, CA",0',
  "Alexandra 'Alex',Menendez,alex.menendez@gmail.com,Miami,1",
  '1,2,,4,"5"'
]
 */
public class CSVParser {
    public static void main(String[] args) {
        String[] lines = {"John,Smith,john.smith@gmail.com,Los Angeles,10",
                "Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0",
                "Alexandra 'Alex',Menendez,alex.menendez@gmail.com,Miami,1",
                "1,2,,4,\"5\""};
        parse(lines);
    }

    public static void parse(String[] lines) {
        Character marker = null;
        StringBuilder sb = new StringBuilder();
        List<String> list;
        for (String line : lines) {
            sb.setLength(0);
            list = new ArrayList<>();
            for (char c : line.toCharArray()) {
                if (marker == null && c == '"') {
                    marker = c;
                }else if (marker != null && marker == c) {
                    marker = null;
                }  else if (marker == null && c == ',') {
                    list.add(sb.toString());
                    sb.setLength(0);
                } else {
                    sb.append(c);
                }
            }
            if(sb.length() > 0) list.add(sb.toString());
            System.out.println(String.join(" || ", list));
        }
    }
}
