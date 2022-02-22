package com.scottfree.metricsplayground;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.Arrays;
import java.util.List;

public class Poster implements Runnable {
    private String username;
    private Integer numberOfPosts;
    private Integer maxSeconds;

    final private MeterRegistry meterRegistry;

    List<String> qTypes = Arrays.asList("SmokingX","Vital SignsX","DiabetesX","SleepingX","Blood pressureX");

    public Poster(MeterRegistry meterRegistry, String username, Integer numberOfPosts, Integer maxSeconds) {
        this.meterRegistry = meterRegistry;
        this.username = username;
        this.numberOfPosts = numberOfPosts;
        this.maxSeconds = maxSeconds;
    }

    @Override
    public void run() {
            for (int idx=0; idx < getNumberOfPosts(); idx++) {
                DistributionSummary distributionSummary = DistributionSummary
                        .builder("questionnaire_duration")
                        .baseUnit("seconds")
                        .tag("subject_code", getUsername())
                        .tag("questionnaire_code", qTypes.get(idx))
                        .description("Recording the duration of questionnaire submissions (questionnaire_code & subject_code")
                        .register(meterRegistry);

                long duration = (long)(Math.random() * getMaxSeconds());

                System.out.println("SC:"+getUsername()+", QC:" + qTypes.get(idx) + ", Duration: " + duration);

                distributionSummary.record(duration);
            }
    }

    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }
}
