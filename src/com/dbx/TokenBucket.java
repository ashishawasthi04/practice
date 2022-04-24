package com.dbx;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// https://konghq.com/blog/how-to-design-a-scalable-rate-limiting-algorithm/
public class TokenBucket {
    private final int MAX_CAPACITY;
    private final int FILL_RATE;

    private final List<Integer> bucket;
    private long lastFillTimeStamp;

    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    public TokenBucket(int maxCapacity, int fillRate) {
        this.MAX_CAPACITY = maxCapacity;
        this.FILL_RATE = fillRate;
        lastFillTimeStamp = System.currentTimeMillis();

        bucket = new ArrayList<>();
    }

    public void fill() throws InterruptedException {
        lock.lock();
        while (bucket.size() == MAX_CAPACITY) {
            System.out.println("Bucket is filled now.");
            notFull.await();
        }
        long now = System.currentTimeMillis();
        //System.out.println((now - lastFillTimeStamp)/1000 * FILL_RATE);
        long numTokensToAdd = Math.min(MAX_CAPACITY - bucket.size(), (now - lastFillTimeStamp) / 1000 * FILL_RATE);
        lastFillTimeStamp = now;
        //System.out.println(numTokensToAdd);
        for (int i = 0; i < numTokensToAdd; i++) { //add tokens
            bucket.add((int) (Math.random() * 100) + 1);
        }
        notEmpty.signalAll();
        lock.unlock();

    }

    public List<Integer> get(int n) throws InterruptedException {
        //System.out.println(n);
        if (n <= 0)
            throw new IllegalArgumentException("Cannot get zero or negative number of tokens.");
        if (n > MAX_CAPACITY)
            throw new IllegalArgumentException("Cannot get tokens more than max capacity.");
        List<Integer> result = new ArrayList<>();

        int tokenAcquired = 0;
        while (tokenAcquired < n) { //this can ensure fair competition
            lock.lock();
            while (bucket.size() < 1) {
                System.out.println("Bucket is not enough now.");
                notEmpty.await();
            }
            result.add(bucket.get(bucket.size() - 1));
            bucket.remove(bucket.size() - 1);
            tokenAcquired++;
            notFull.signalAll();
            lock.unlock();

        }

        return result;
    }

    public static void main(String[] args) {
        TokenBucket bucket = new TokenBucket(100, 8);
        Runnable filler = () -> {
            while (true) {
                try {
                    bucket.fill();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable consumer = () -> {
            while (true) {
                try {
                    List<Integer> result = bucket.get((int) (Math.random() * 5) + 1);
                    System.out.println(result.toString());
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(filler).start();
        new Thread(consumer).start();
    }
}

