package com.scottfree.metricsplayground;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.LongTaskTimer;
import io.micrometer.core.instrument.MeterRegistry;
import io.prometheus.client.Summary;

public class Poster implements Runnable {
    private String username;
    private Integer numberOfPosts;
    private Integer maxSeconds;

    final private MeterRegistry meterRegistry;

    public Poster(MeterRegistry meterRegistry, String username, Integer numberOfPosts, Integer maxSeconds) {
        this.meterRegistry = meterRegistry;
        this.username = username;
        this.numberOfPosts = numberOfPosts;
        this.maxSeconds = maxSeconds;


    }

    @Override
    public void run() {
        try {
            Thread.sleep((long)(Math.random() * 3000));

            for (int idx=0; idx < getNumberOfPosts(); idx++) {
                DistributionSummary distributionSummary = DistributionSummary
                        .builder("questionnaire_duration")
                        .baseUnit("seconds")
                        .tag("subject_code", getUsername())
                        .tag("questionnaire_code", getUsername() + "-" + idx)
                        .description("Recording the duration of questionnaire submissions (questionnaire_code & subject_code")
                        .register(meterRegistry);

                long duration = (long)(Math.random() * getMaxSeconds());

                System.out.println("SC:"+getUsername()+", QC:" + getUsername() + "-" + idx + ", Duration: " + duration);

                distributionSummary.record(duration);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
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
