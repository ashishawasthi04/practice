package com.dbx;

import java.util.LinkedList;
import java.util.Queue;
//Things to consider:
//        If a lot of hits within given time window / per sec
//        If it keeps hit() for a long time only then call getHit(), memory problem (clear outdated also when hit() is called)
//        Multi-threading, if multiple threads report hit() // if a webpages, multiple clients click the hit
//
//        Simple version using queue:
//        Space: varies, depends on number of hits in given time window
//        Time: varies, depends on number of outdated hits
public class HitCounter1 {
    private static final int WINDOW = 5*60; // 5 minutes
    private Queue<Integer> timeStamps;

    public HitCounter1() {
        timeStamps =  new LinkedList<>();
    }

    public void hit() {
        int currTime = getCurrTimeSec();
        timeStamps.offer(currTime);
    }

    public int getHits() {
        int currTime = getCurrTimeSec();
        while(!timeStamps.isEmpty() && timeStamps.peek() <= currTime - WINDOW) {
            timeStamps.poll();
        }
        return timeStamps.size();
    }

    public int getCurrTimeSec() {
        return (int)System.currentTimeMillis()/1000;
    }
}