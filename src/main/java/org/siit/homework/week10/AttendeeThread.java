package org.siit.homework.week10;

import org.siit.homework.week10.enums.TicketType;

import java.util.Random;

public class AttendeeThread implements Runnable {

    private final TicketType ticketType;
    private final Gate gate;

    private static final Random random = new Random();
    private static final TicketType[] ticketTypes = TicketType.values();


    public AttendeeThread(Gate gate) {
        this.ticketType = ticketTypes[random.nextInt(ticketTypes.length)];
        this.gate = gate;
    }

    public void run() {
        gate.validateTicket(ticketType);
    }

}
