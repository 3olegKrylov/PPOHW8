package ru.krylov.clock;

import java.time.Instant;

public class InitClock implements Clock {

    private Instant now;

    public InitClock() {
        this.now = Instant.now();
    }

    public Instant now() {
        return now;
    }

    public void plusTestTime(long millis) {
        now = now.plusMillis(millis);
    }

}
