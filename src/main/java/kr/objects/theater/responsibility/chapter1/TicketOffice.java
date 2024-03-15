package kr.theater.responsibility.chapter1;

import java.util.ArrayList;
import java.util.List;

public class TicketOffice {
    private Long amount;
    private List<Ticket> tickets = new ArrayList<>();

    public void sellTicketTo(Audience audience) {
        this.plusAmount(audience.buy(getTicket()));
    }


//    public TicketOffice(Long amount, Ticket ... tickets) {
//        this.amount = amount;
//        this.tickets.addAll(Arrays.asList(tickets));
//    }

    private Ticket getTicket() {
        return tickets.remove(0);
    }

//    private void minusAmount(Long amount) {
//        this.amount -= amount;
//    }

    private void plusAmount(Long amount) {
        this.amount += amount;
    }


}
