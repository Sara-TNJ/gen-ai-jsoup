package com.att.poc.jsoup;

public class RateLimiter {

    private static final long DELAY_MS = 1000; // 1 request per second
    private static long lastRequestTime = 0;

    public synchronized static void acquire() {
        long now = System.currentTimeMillis();
        long waitTime = DELAY_MS - (now - lastRequestTime);

        if (waitTime > 0) {
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException ignored) {
            }
        }
        lastRequestTime = System.currentTimeMillis();
    }
}
