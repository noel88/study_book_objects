package kr.theater.responsibility.chapter1;

import lombok.Setter;

/**
 * NOTE: Getter 제거, 외부에서 Getter로 접근할 수 없도록 차단한다 (캡슐화)
 *  TicketSeller는 ticketOffice에서 티켓을 꺼내거나 판매 요금을 적립하는 일을 스스로 수행할 수 밖에 없다.
 */


//@Getter
@Setter
public class TicketSeller {
    private TicketOffice ticketOffice;

    /**
     * NOTE:
     *  TicketSeller는 Audience의 getBag 메서드를 호출해서 Audience 내부의 Bag 인스턴스에 직접 접근한다.
     *  Bag 인스턴스에 접근하는 객체가 Theater에서 TicketSeller로 바뀌었을 뿐, Audience는 여전히 자율적인 존재가 아니다.
     *  해결방법: TicketSeller가 Audience의 인터페이스에만 의존하도록 수정.
     */
    public void sellTo(Audience audience) {
//        if (audience.getBag().hasInvitation()) {
//            Ticket ticket = ticketOffice.getTicket();
//            audience.getBag().setTicket(ticket);
//        } else {
//            Ticket ticket = ticketOffice.getTicket();
//            audience.getBag().minusAmount(ticket.getFee());
//            ticketOffice.plusAmount(ticket.getFee());
//            audience.getBag().setTicket(ticket);
//        }

        /**
         * NOTE:
         *  변경 전은 TicketSeller가 Audience의 인터페이스에 의존했지만,
         *  변경 후에는 TicketOffice가 Audience에게 직접 티켓을 판매하기 때문에 Audience에 관해 알고 있어야 한다.
         */

//        ticketOffice.plusAmount(audience.buy(ticketOffice.getTicket()));
        ticketOffice.sellTicketTo(audience);

    }
}
