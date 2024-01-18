package kr.theater.responsibility.chapter1;


import lombok.AllArgsConstructor;


/**
 * NOTE:
 *  외부에서 Audience가 Bag을 소유하고 있다는 사실을 알 필요가 없다.
 */
//@Getter
@AllArgsConstructor
public class Audience {
    private Bag bag;

    /**
     * NOTE:
     *  자신의 가방 안에 초대장이 들어있는지를 스스로 확인한다.
     *  Bag은 스스로 자기 자신을 책임지지 않고 Audience에 의해 끌려다니는 수동적인 존재이다. (Bag을 자율적인 존재로 변경해야 함)
     *
     */


    public Long buy(Ticket ticket) {
//        if (bag.hasInvitation()) {
//            bag.setTicket(ticket);
//            return 0L;
//        } else {
//            bag.minusAmount(ticket.getFee());
//            bag.setTicket(ticket);
//            return ticket.getFee();
//        }
        return bag.hold(ticket);
    }
}
