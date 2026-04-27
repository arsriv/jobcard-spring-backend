package com.jobcard.management.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RequestIdGenerator {

    private static final AtomicInteger counter = new AtomicInteger(100);

    public String generateRequestId() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        int seq = counter.getAndIncrement();
        if (seq > 999) {
            counter.set(100);
        }
        return String.format("REQ-%s-%03d", timestamp, seq);
    }
}
