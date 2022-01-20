package ru.krylov.clock;

import java.time.Instant;

public class NormalClock implements Clock {

    public Instant now() {
        return Instant.now();
    }

}
