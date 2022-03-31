package com.online.merchant.model.indicator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

@Slf4j
@Component
public class TransactionHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        if (!isReachable()) {
            return Health.down().withDetail("Error Code", HttpStatus.SERVICE_UNAVAILABLE).build();
        }
        return Health.up().build();
    }

    private boolean isReachable() {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("localhost", 8080), 10000);
            return true;
        } catch (IOException e) {
            log.error("'{}':'{}' is not reachable, possible causes are that it timed out or failed DNS lookup.", "localhost", 8080, e);
            return false;
        }
    }
}
