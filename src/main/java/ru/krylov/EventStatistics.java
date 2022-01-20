package ru.krylov;

import java.util.Map;

public abstract class EventStatistics {
    //инкрементит число событий name;
    abstract void incEvent(String name);
//выдает rpm (request per minute) события name за последний час;
    abstract double getEventStatisticsByName(String name);
//выдает rpm всех произошедших событий за прошедший час;
    abstract Map<String, Double> getAllEventsStatistics();
//выводит в консоль rpm всех произошедших событий;
    public void printStatistics() {
        StringBuilder strBilder = new StringBuilder("Статитстика:\n");

        for (Map.Entry<String, Double> event : getAllEventsStatistics().entrySet()) {
            strBilder.append('\t').
                    append(event.getKey()).
                    append(" rpm(x) - ").
                    append(event.getValue()).append('\n');
        }

        System.out.println(strBilder);
    };


}
