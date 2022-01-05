package com.practice;
import java.util.*;

public class TopKFrequentElements {
    static class Element {
        int val;
        int freq;

        public Element(int val, int freq) {
            this.val = val;
            this.freq = freq;
        }
    }
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 1, 2, 2, 3, 3, 3, 1};
        List<Integer> list = new ArrayList<>();
        for(int num : arr) list.add(num);
        findTopKFrequentElements(list, 2 );
    }

    public static void findTopKFrequentElements(List<Integer> list, int k){
        Map<Integer, Integer> map = new HashMap<>();
        Queue<Element> queue = new PriorityQueue<>(Comparator.comparingInt(e -> -e.freq));
        for(int num : list){
            map.put(num, 1 + map.getOrDefault(num, 0));
        }
        map.forEach((key, val) -> queue.offer(new Element(key, val)));
        Set<Integer> set = new HashSet<>();
        List<Integer> res = new ArrayList<>();
        while(!queue.isEmpty()){
            Element cur = queue.poll();
            set.add(cur.freq);
            if(set.size() > k) break;
            res.add(cur.val);
        }
        res.forEach(System.out::println);
    }
}
