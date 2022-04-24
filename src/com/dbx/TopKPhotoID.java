package com.dbx;

import java.util.*;

public class TopKPhotoID {
    /*
        - Counting the freq is O(n)
        - Use selection algorithm to select top k element is O(n) time,
        e.g. quick select; but it is not sorted, can be sorted in O(K log K) if needed.
        this approach runs in O(N) time, the constants hidden in the O-notation can be large.
        worst case of quick select is O(n^2)
        - Using min heap of size k (nlogk)
        - Using max heap of size n (n + klogn)

        For non-streaming
        approach 1:
        Count freq + bucket sort
        O(n), cons is the bucket is sparse, if there is one extreme freq, inefficient space usage
    */
    public List<Integer> topKViewPhoto1(int[] photoIds, int k) {
        List<Integer> result = new ArrayList<>();
        if (photoIds == null || photoIds.length < 1) return result;

        Map<Integer, Integer> freqMap = new HashMap<>();
        int maxFreq = 0;
        for (int id : photoIds) {
            int freq = freqMap.getOrDefault(id, 0) + 1;
            maxFreq = Math.max(maxFreq, freq);
            freqMap.put(id, freq);
        }

        List<Integer>[] freqBuckets = new List[maxFreq + 1];
        for (Map.Entry<Integer, Integer> freqEntry : freqMap.entrySet()) {
            if (freqBuckets[freqEntry.getValue()] == null) {
                freqBuckets[freqEntry.getValue()] = new ArrayList<>();
            }
            freqBuckets[freqEntry.getValue()].add(freqEntry.getKey());
        }

        for (int i = freqBuckets.length - 1; i >= 0; i--) {
            if (freqBuckets[i] != null) {
                List<Integer> ids = freqBuckets[i];
                if (k >= freqBuckets[i].size()) {
                    result.addAll(ids);
                    k -= freqBuckets[i].size();
                } else {
                    for (int j = 0; j < k; j++) {
                        result.add(ids.get(j));
                    }
                    break;
                }
            }
        }
        return result;
    }

    // Approach 2: Using k-min heap to maintain k most viewed. nlogk
    public List<Integer> topKViewPhoto2(int[] photoIds, int k) {
        List<Integer> result = new ArrayList<>();
        if (photoIds == null || photoIds.length < 1) return result;

        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int id : photoIds) {
            int freq = freqMap.getOrDefault(id, 0) + 1;
            freqMap.put(id, freq);
        }

        PriorityQueue<View> topKView = new PriorityQueue<>((a, b) -> a.freq - b.freq);
        for (Map.Entry<Integer, Integer> freqEntry : freqMap.entrySet()) {
            View view = new View(freqEntry.getKey(), freqEntry.getValue());
            if (topKView.size() < k) {
                topKView.add(view);
            } else {
                assert topKView.peek() != null;
                if (freqEntry.getValue() > topKView.peek().freq) {
                    topKView.poll();
                    topKView.offer(view);
                }
            }
        }

        while (!topKView.isEmpty()) {
            result.add(topKView.poll().id);
        }
        return result;
    }

    static class View {
        int id;
        int freq;

        public View(int id, int freq) {
            this.id = id;
            this.freq = freq;
        }
    }
}