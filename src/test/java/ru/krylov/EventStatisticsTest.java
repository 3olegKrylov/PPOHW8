package ru.krylov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.krylov.clock.InitClock;
import ru.krylov.clock.NormalClock;

public class EventStatisticsTest {
    private void assertAndCheckTime(double d1, double d2) {
        Assertions.assertTrue(Math.abs(d1 - d2) < 1e-6);
    }

    @Test
    public void CountingTest() {
        EventStatistics statistics = new EventStatisticsImpl(new NormalClock());
        System.out.println("CountingTest:");

        for (int i = 0; i < 600; i++) {
            statistics.incEvent("10 min action");
        }
        for (int i = 0; i < 3600; i++) {
            statistics.incEvent("1 hour seconds action");
        }
        assertAndCheckTime(statistics.getEventStatisticsByName("10 min action"), 10);
        assertAndCheckTime(statistics.getEventStatisticsByName("1 hour seconds action"), 60);

        assertAndCheckTime(statistics.getEventStatisticsByName("zero-action"), 0);
        statistics.printStatistics();
    }

    @Test
    public void clockTest() {
        System.out.println("ClockTest:");
        InitClock clock = new InitClock();
        EventStatistics statistics = new EventStatisticsImpl(clock);

        clock.plusTestTime(50);
        int n = 2 * 60 * 60 * 1000 / 100;
        for (int i = 0; i < n; i++) {

            statistics.incEvent("Время");
            if (i != n - 1) {
                clock.plusTestTime(100);
            }

        }
        clock.plusTestTime(50);

        assertAndCheckTime(statistics.getEventStatisticsByName("Время"), 600);
        statistics.printStatistics();
    }


}
