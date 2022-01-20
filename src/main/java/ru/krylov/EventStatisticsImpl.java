package ru.krylov;

import ru.krylov.clock.Clock;

import java.time.Instant;
import java.util.*;

public class EventStatisticsImpl extends EventStatistics {
    private final Map<String, List<Instant>> EventList;
    private final Clock clock;
    private Instant lastClearTime;


    @Override
    void incEvent(String eventName) {
        clearStatistic();
        if (!EventList.containsKey(eventName)) {
            if (!Objects.equals(eventName, "")) {
                EventList.put(eventName, new ArrayList<>());
            }
        }

        EventList.get(eventName).add(clock.now());
    }

    public EventStatisticsImpl(Clock clock) {
        this.clock = clock;
        lastClearTime = clock.now();

        EventList = new HashMap<>();
    }


    @Override
    double getEventStatisticsByName(String name) {
        int count = 0;
        clearStatistic();
        Instant nowTime = clock.now();

        List<Instant> exitEvent = EventList.get(name);
        if (exitEvent == null){
            return 0;
        }
        for (Instant time : exitEvent) {
            //1 час
            if (nowTime.isBefore(time.plusSeconds(3600))) {
                count++;
            }
        }
        return (double) count / 60;
    }

    @Override
    Map<String, Double> getAllEventsStatistics() {
        Map<String, Double> allStatictic = new HashMap<>();

        for (String event : EventList.keySet()) {
            allStatictic.put(event, getEventStatisticsByName(event));
        }
        return allStatictic;
    }

    private void clearStatistic() {
        if (clock.now().isAfter(lastClearTime.plusSeconds(500))) {
            lastClearTime = clock.now();

            Set<String> removeE = new HashSet<>();
            for (String event : EventList.keySet()) {
                for (int j = 0; j < EventList.get(event).size(); j++) {Instant eventTime = EventList.get(event).get(j);
                    if (clock.now().isAfter(eventTime.plusSeconds(3600))) {
                        EventList.get(event).remove(j--);
                    }
                }
                if (EventList.get(event).isEmpty()) {
                    removeE.add(event);
                }
            }

            for (String event : removeE) {
                EventList.remove(event);
            }
        }
    }

    public static void main(String[] args) {

    }

}
