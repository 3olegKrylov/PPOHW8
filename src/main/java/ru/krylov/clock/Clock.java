package ru.krylov.clock;

import java.time.Instant;

public interface Clock {
    Instant now();
}
