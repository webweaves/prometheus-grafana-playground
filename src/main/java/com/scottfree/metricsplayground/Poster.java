package com.scottfree.metricsplayground;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;

public class Poster implements Runnable {
    private String name;
    private Integer numberOfPosts;
    private Integer maxSeconds;

    @Autowired
    private MeterRegistry meterRegistry;

    public Poster(String name, Integer numberOfPosts, Integer maxSeconds) {
        this.name = name;
        this.numberOfPosts = numberOfPosts;
        this.maxSeconds = maxSeconds;
    }

    @Override
    public void run() {
        try {
            //initial random pause of upto 10 seconds
            Thread.sleep((long)(Math.random() * 10000));
            for (int idx=0; idx<=numberOfPosts; idx++) {
                startTimer(idx);
                Thread.sleep((long)(Math.random() * 20000));
                endTimer(idx);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void endTimer(Integer idx) {
        System.out.println("Starting timer " + getName() + " - " + idx + " of " + numberOfPosts);
    }

    private void startTimer(Integer idx) {
        System.out.println("Ending timer " + getName() + " - " + idx + " of " + numberOfPosts);
    }

    public String getName() {
        return name;
    }

    public Integer getNumberOfPosts() {
        return numberOfPosts;
    }

    public void setNumberOfPosts(Integer numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }

    public Integer getMaxSeconds() {
        return maxSeconds;
    }

    public void setMaxSeconds(Integer maxSeconds) {
        this.maxSeconds = maxSeconds;
    }

    public void setName(String name) {
        this.name = name;
    }
}
