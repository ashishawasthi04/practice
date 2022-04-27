package com.dbx;

import java.util.*;

/*
    For streaming:
    - The problem here is we cannot increase/ decrease value of priority queue, to remove it and re-add, the remove is O(k).
    - What we can do better is to implement the min heap ourselves, and do the keyAdjust heap, which will be O(logk)
    - **Check data structure heap
*/
class PhotoView {
    int id, freq;

    public PhotoView(int id, int freq) {
        this.id = id;
        this.freq = freq;
    }
}

class MaxPhotoID {
    private final Queue<PhotoView> kMostViewPhotos;
    private final Map<Integer, PhotoView> photoViewFreqMap;
    private final int k;

    public MaxPhotoID(int k) {
        this.k = k;
        photoViewFreqMap = new HashMap<>();
        Comparator<PhotoView> comp = Comparator.comparing(view -> view.freq);
        comp = comp.thenComparing(view -> -view.id);
        kMostViewPhotos = new PriorityQueue<>(comp);
    }

    public void addPhoto(int id) {
        photoViewFreqMap.putIfAbsent(id, new PhotoView(id, 0));
        PhotoView view = photoViewFreqMap.get(id);
        view.freq++;

        if (!kMostViewPhotos.isEmpty() && view.freq >= kMostViewPhotos.peek().freq) {
            kMostViewPhotos.remove(view);
            kMostViewPhotos.offer(view);
            if (kMostViewPhotos.size() > k)
                kMostViewPhotos.poll();
        }else kMostViewPhotos.offer(view);
    }

    public List<Integer> getTopKViewPhoto() {
        PhotoView[] topK = kMostViewPhotos.toArray(PhotoView[]::new);
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
        solver.addPhoto(1);
        solver.addPhoto(2);
        solver.addPhoto(1);
        System.out.println(solver.getTopKViewPhoto());
        solver.addPhoto(3);

        System.out.println(solver.getTopKViewPhoto());
        solver.addPhoto(2);
        solver.addPhoto(12);
        solver.addPhoto(31);
        solver.addPhoto(101);
        solver.addPhoto(11);
        solver.addPhoto(3);
        System.out.println(solver.getTopKViewPhoto());

        solver.addPhoto(31);
        solver.addPhoto(101);
        solver.addPhoto(31);
        solver.addPhoto(101);
        System.out.println(solver.getTopKViewPhoto());
    }
}