package org.siit.homework.week10;

import org.siit.homework.week10.enums.TicketType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatisticsThread implements Runnable {

    private final Gate gate;
    public static final int MAX_CAPACITY = 100;

    public StatisticsThread(Gate gate) {
        this.gate = gate;
    }

    public void run() {
        int total = 0;
        while (total < MAX_CAPACITY) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<TicketType> tickets = gate.getTickets();
            Map<TicketType, Long> ticketCounts = tickets.stream()
                    .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
            total = tickets.size();
            System.out.println(ticketCounts + " TOTAL: " + total);
        }
    }
}