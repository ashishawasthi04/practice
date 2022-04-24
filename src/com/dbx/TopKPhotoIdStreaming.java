package com.dbx;

import java.util.*;

/*
    For streaming:
    - The problem here is we cannot increase/ decrease value of priority queue, to remove it and re-add, the remove is O(k).
    - What we can do better is to implement the min heap ourselves, and do the keyAdjust heapify, which will be O(logk)
    - **Check data structure heap
*/
class PhotoView implements Comparable<PhotoView> {
    int id;
    int freq;

    public PhotoView(int id, int freq) {
        this.id = id;
        this.freq = freq;
    }

    @Override
    public int compareTo(PhotoView other) {
        if (this.freq == other.freq) {
            return other.id - this.id;
        }
        return this.freq - other.freq;
    }
}

class MaxPhotoID {
    private final PriorityQueue<PhotoView> kMostViewPhotos;
    private final Map<Integer, PhotoView> photoViewFreqMap;
    private final int k;

    public MaxPhotoID(int k) {
        this.k = k;
        kMostViewPhotos = new PriorityQueue<>();
        photoViewFreqMap = new HashMap<>();
    }

    public void view(int id) {
        if (!photoViewFreqMap.containsKey(id)) {
            photoViewFreqMap.put(id, new PhotoView(id, 0));
        }
        PhotoView view = photoViewFreqMap.get(id);
        view.freq++;

        if (kMostViewPhotos.size() < k ||
                view.freq >= kMostViewPhotos.peek().freq) {
            kMostViewPhotos.remove(view);
            kMostViewPhotos.offer(view);
            if (kMostViewPhotos.size() > k)
                kMostViewPhotos.poll();
        }
    }

    public List<Integer> getTopKViewPhoto() {
        PhotoView[] topK = kMostViewPhotos.toArray(new PhotoView[kMostViewPhotos.size()]);
        List<Integer> result = new ArrayList<>();
        for (PhotoView photoView : topK) {
            result.add(photoView.id);
        }
        return result;
    }
}

public class TopKPhotoIdStreaming {
    public static void main(String[] args) {
        MaxPhotoID solver = new MaxPhotoID(4);
        solver.view(1);
        solver.view(2);
        solver.view(1);
        System.out.println(solver.getTopKViewPhoto());
        solver.view(3);

        System.out.println(solver.getTopKViewPhoto());
        solver.view(2);
        solver.view(12);
        solver.view(31);
        solver.view(101);
        solver.view(11);
        solver.view(3);
        System.out.println(solver.getTopKViewPhoto());

        solver.view(31);
        solver.view(101);
        solver.view(31);
        solver.view(101);
        System.out.println(solver.getTopKViewPhoto());
    }
}