package com.practice;

import java.util.*;
import java.util.stream.Collectors;

public class CompareOrder {
    public static void main(String[] args) {
        List<String> x = Arrays.asList("nnnnnnaabb", "fasasa");
        List<String> y = Arrays.asList("nnffbb", "hhttsss");
        //compareOrder(x, y);
        //warehouse(new int[]{1, 12, 4, 12});
        List<String> logs = Arrays.asList("1 sign-in 2", "2 sign-in 3", "1 sign-out 10", "2 sign-out 7", "3 sign-out 15");
        earlyLogout(logs, 5);
    }

    public static List<String> compareOrder(List<String> x, List<String> y) {
        List<String> res = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < x.size(); i++) {
            map.clear();
            for (char c : x.get(i).toCharArray())
                map.merge(c, 1, Integer::sum);

            for (char c : y.get(i).toCharArray())
                map.merge(c, -1, Integer::sum);

            long count = map.values().stream().filter(val -> Math.abs(val) > 3).count();
            res.add(count == 0 ? "YES" : "NO");
        }
        res.forEach(System.out::println);
        return res;
    }

    public static List<Integer> warehouse(int[] shelves) {
        List<Integer> res = new ArrayList<>();
        int[] counter = new int[1];
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(shelves).distinct().boxed()
                .sorted(Comparator.naturalOrder())
                .forEach(shelf -> map.put(shelf, ++counter[0]));
        for (int shelf : shelves) {
            res.add(map.get(shelf));
        }
        res.forEach(System.out::println);
        return res;
    }

    public static List<String> earlyLogout(List<String> logs, int maxTime) {
        Map<String, int[]> map = new HashMap<>();
        logs.forEach(log -> {
            String[] record = log.split("\\s+");
            if (!map.containsKey(record[0])) map.put(record[0], new int[2]);
            boolean isSignIn = "sign-in".equals(record[1]);
            map.get(record[0])[isSignIn ? 0 : 1] = Integer.parseInt(record[2]);
        });

        List<String> res = map.entrySet().stream().filter(entry -> {
                    int[] value = entry.getValue();
                    return (value[0] * value[1] != 0 && value[1] - value[0] < maxTime);
                }).map(Map.Entry::getKey)
                .sorted(Comparator.comparing(Integer::parseInt))
                .collect(Collectors.toList());
        res.forEach(System.out::println);
        return res;

    }
}
