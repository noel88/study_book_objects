package kr.theater.responsibility.chapter1;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Theater {
    private TicketSeller ticketSeller;


    public void enter(Audience audience) {

        /**
         * NOTE:
         *  소극장은 관람객의 가방을 열어 그 안에 초대장이 들어 있는지 살펴본다. 가방 안에 초대장이 들어 있으면
         *  판매원은 매표소에 보관되어 있는 티켓을 관람객의 가방 안으로 옮긴다. 가방 안에 초대장이 들어 있지 않다면
         *  관람객의 가방에서 티켓 금액만큼의 현금을 꺼내 매표소에 적립한 후에 매표소에 보관되어 있는 티켓을 관람객의 가방 안으로 옮긴다.
         *  - 이 코드의 문제는
         *  1. 관람객과 판매원이 소극장의 통제를 받는 수동적인 존재라는 점이다.
         *  2. 하나의 클래스나 메서드에서 너무 많은 세부사항을 다루기 때문에 코드를 작성하는 사람뿐만 아니라 코드를 읽고 이해해야 하는 사람 모두에게 큰 부담을 준다.
         *  3. Audience와 TicketSeller를 변경할 경우 Theater도 함꼐 변경해야 한다. (변경에 취약하다)
         *  - 해결방법
         *  Theater가 Audience와 TicketSeller에 관해 너무 세세한 부분까지 알지 못하도록 정보를 차단한다.
         *  (관람객과 판매원을 자율적인 존재로 만들면 된다)
         *
         */
//        if (audience.getBag().hasInvitation()) {
//            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
//            audience.getBag().setTicket(ticket);
//        } else {
//            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
//            audience.getBag().minusAmount(ticket.getFee());
//            ticketSeller.getTicketOffice().plusAmount(ticket.getFee());
//            audience.getBag().setTicket(ticket);
//        }

        ticketSeller.sellTo(audience);
    }
}
