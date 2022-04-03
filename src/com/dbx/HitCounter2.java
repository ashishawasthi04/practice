package com.dbx;

//  Circular Array approach:
//  think about it as assigning the hits to buckets, 300 buckets, next time you put hits into the bucket,
//  the previous hits are already outdated, you can safely overwrite it.
//  Space: O(WINDOW_LENGTH)
//  Time: O(WINDOW_LENGTH)
//circular array
public class HitCounter2 {
    private final int WINDOW_LENGTH; // in sec
    private Hit[] hitRecords;
    private int lastHitTime;

    public HitCounter2(int windowLength) {
        this.WINDOW_LENGTH = windowLength;
        hitRecords = new Hit[windowLength];
    }

    public void hit() {
        int currTime = getCurrTimeSec();
        int index = currTime % WINDOW_LENGTH;
        if (hitRecords[index] != null && hitRecords[index].timeStamp == currTime) {
            hitRecords[index].count++;
        } else {
            hitRecords[index] = new Hit(currTime, 1);
        }
    }

    public int getHits() {
        int currTime = getCurrTimeSec();
        int count = 0;
        for (Hit hit : hitRecords) {
            if (hit != null && hit.timeStamp > currTime - WINDOW_LENGTH)
                count += hit.count;
        }
        return count;
    }

    public int getCurrTimeSec() {
        return (int) System.currentTimeMillis() / 1000;
    }

    private class Hit {
        int timeStamp;
        int count;

        public Hit(int timeStamp, int count) {
            this.timeStamp = timeStamp;
            this.count = count;
        }
    }

    // We can avoid storing the timestamp attribute by storing the lastHitTime, and clear those
    // since lastHitTime till currentHitTime / getHitTime to avoid invalid counts.
    public void clearBucketFromLastHit(int currTime) {
        if (lastHitTime != -1) {
            for (int i = lastHitTime + 1; i <= currTime; i++) {
                hitRecords[i % WINDOW_LENGTH].count = 0;
            }
        }
    }
}
/*
Multi-threading:
use BlockingQueue, LinkedBlockingQueue is not bounded
synchronised the modification and reading from queue
Use ConcurrentHashMap, it is not blocking on the whole collection when write,
so can have multiple writes at the same time, read is non-blocking, using volatile.
This is achieved by partitioning Map into different parts based on concurrency level and locking only
a portion of Map during updates.
*/

