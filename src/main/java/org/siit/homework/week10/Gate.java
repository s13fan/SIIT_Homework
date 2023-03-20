package org.siit.homework.week10;

import org.siit.homework.week10.enums.TicketType;

import java.util.ArrayList;
import java.util.List;

public class Gate {

    private final List<TicketType> tickets = new ArrayList<>();

    public synchronized void validateTicket(TicketType ticketType) {
        tickets.add(ticketType);
    }

    public synchronized List<TicketType> getTickets() {
        return tickets;
    }


}
