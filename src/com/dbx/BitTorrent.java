package com.dbx;

import java.util.*;

public class BitTorrent {

    class Chunk {
        int start;
        int end;
    }

    //If all chunks are given:
    //just do something like interval merge
    public boolean isFileDone(List<Chunk> chunks, int size) {
        if (chunks == null || chunks.size() == 0) return false;

        chunks.sort((a, b) -> a.start - b.start);
        if (chunks.get(0).start != 0) return false;

        int end = chunks.get(0).end;
        for (int i = 1; i < chunks.size(); i++) {
            Chunk chunk = chunks.get(i);
            if (chunk.start > end)
                return false;
            else
                end = Math.max(end, chunk.end);
        }

        return end == size;
    }

    //If chunks are coming in stream:
    //Using Priority Queue, addBlock is O(klog(m)) depending on the chunk arrivals and merging, but
    // amortised worst case should be no more than O(logn).
    class Downloader {
        private PriorityQueue<Chunk> chunks;
        int size;

        public Downloader(int size) {
            this.size = size;
            chunks = new PriorityQueue<>((a, b) -> a.start - b.start);
        }

        public void addBlock(Chunk chunk) {
            chunks.offer(chunk);
            if (chunks.size() > 1) {
                Chunk smallest = chunks.poll();
                //keep merging if there are continuous chunks
                while (!chunks.isEmpty() && chunks.peek().start <= smallest.end) {
                    Chunk c = chunks.poll();
                    smallest.end = Math.max(smallest.end, c.end);
                }
                chunks.offer(smallest);
            }
        }

        public boolean isDone() {
            if (!chunks.isEmpty() &&
                    chunks.peek().start == 0 && chunks.peek().end == size) return true;
            return false;
        }
    }

    //Using BitSet
    class DownloaderBitSet {
        private BitSet chunksBitSet;
        int size;

        public DownloaderBitSet(int size) {
            this.size = size;
            chunksBitSet = new BitSet(size);
        }

        public void addBlock(Chunk chunk) {
            chunksBitSet.set(chunk.start, chunk.end);
        }

        public boolean isDone() {
            return chunksBitSet.cardinality() == size;
        }
    }
}
