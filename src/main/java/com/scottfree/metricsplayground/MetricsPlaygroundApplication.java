package com.scottfree.metricsplayground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MetricsPlaygroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetricsPlaygroundApplication.class, args);
    }

}
