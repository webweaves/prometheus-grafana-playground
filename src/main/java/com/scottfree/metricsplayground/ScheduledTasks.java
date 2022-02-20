package com.scottfree.metricsplayground;

import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private MeterRegistry meterRegistry;

    @Scheduled(fixedRate = 30000)
    public void reportCurrentTime() {
        System.out.println("Firing main schedule");
        Poster p = new Poster(meterRegistry, "Test1", 5, 40);
        new Thread(p).start();

        Poster p2 = new Poster(meterRegistry, "Test2", 10, 60);
        new Thread(p2).start();

        System.out.println("Ending main schedule");
    }
}
