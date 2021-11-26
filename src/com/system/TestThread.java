package com.system;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class TestThread {
    private static volatile int val = 0;
    private static AtomicInteger atomicVal = new AtomicInteger(0);
    String name;

    public void testRunnable() {
        System.out.println(this.name + " => " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        IntStream.rangeClosed(1, 20).forEach(i -> {
            //Runnable task = new TestThread()::testRunnable;
            Runnable task = new TestThread()::testVolatileAndAtomic;
            service.submit(task);
        });
        service.shutdown();
    }

    public void testVolatileAndAtomic() {
        val++;
        System.out.println(Thread.currentThread().getName() + " value is: " + val
                + "  atomic Val is: " + atomicVal.incrementAndGet());

    }
}
