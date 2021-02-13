package com.balarawool.virtualthreads;

public class VirtualThreads {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[5_000];
        for (int i = 0; i < threads.length; i++) {
            long data = i;
            threads[i] = Thread.builder().virtual().factory().newThread(() -> processData(data));
            threads[i].start();
        }
        waitForAllThreads(threads);
        long endTime = System.currentTimeMillis();
        double t = (endTime - startTime) / 1000d;
        System.out.println("Total time: " + t + " seconds");
    }

    private static void waitForAllThreads(Thread[] threads) {
        for (Thread thread: threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void processData(long data) {
        System.out.println("Thread started with: " + data);
        for (int i = 0; i < 500; i++) {
            sleep();
            work(data);
        }
        System.out.println("Thread finished with: " + data);
    }

    private static void sleep() {
        try {
            Thread.sleep(10);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void work(long data) {
        long temp = 1;
        for (int i = 0; i < 10; i++) {
            temp *= data;
        }
    }

}
