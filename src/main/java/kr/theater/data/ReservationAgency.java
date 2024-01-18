package kr.theater.data;

import kr.theater.data.domain.*;
import kr.theater.responsibility.domain.Money;

/**
 * NOTE: ReservationAgency를 수정해야 할 경우
 *  - 할인 정책이 추가될 경우
 *  - 할인 정책별로 할인 요금을 계산하는 방법이 변경될 경우
 *  - 할인 조건이 추가되는 경우
 *  - 할인 조건별로 할인 여부를 판단하는 방법이 변경된 경우
 *  - 예매 요금을 계산하는 방벙이 변경될 경우
 *  낮은 응집도는 두가지 측면에서 설계에 문제를 일으킴.
 *  1. 변경의 이유가 서로 다른 코드들을 하나의 모듈안에 뭉처놓았기 때문에 변경과 아무 상관이 없는 코드들이 영향을 받게 된다.
 *  어떤 코드를 수정한 후에 아무런 상관도 없던 코드에 문제가 발생하는 것은 모듈의 응집도가 낮을 때 발생하는 대표적인 증상.
 *  2. 하나의 요구사항 변경을 반영하기 뒤해 동시에 여러 모듈을 수정해야 한다.
 *  응집도가 낮을 경우 다른 모듈에 위치해야 할 책임의 일부가 엉뚱한 곳에 위치하게 되기 때문이다.
 */
public class ReservationAgency {
    public Reservation reservation(Screening screening, Customer customer, int audienceCount) {

        //TODO: 변경 전.
//        Movie movie = screening.getMovie();
//
//        boolean discountable = false;
//        for (DiscountCondition condition : movie.getDiscountConditions()) {
//            if (condition.getType() == DiscountConditionType.PERIOD) {
//                discountable = screening.getWhenScreened().getDayOfWeek().equals(condition.getDayOfWeek()) &&
//                        condition.getStartTime().compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
//                        condition.getEndTime().compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
//
//            } else {
//                discountable = condition.getSequence() == screening.getSequence();
//            }
//
//            if (discountable) { break; }
//        }
//        Money fee;
//        if (discountable) {
//            Money discountAmount = Money.ZERO;
//            switch (movie.getMovieType()) {
//                case AMOUNT_DISCOUNT:
//                    discountAmount = movie.getDiscountAmount();
//                    break;
//                case PERCENT_DISCOUNT:
//                    discountAmount = movie.getFee().times(movie.getDiscountPercent());
//                    break;
//                case NONE_DISCOUNT:
//                    discountAmount = Money.ZERO;
//                    break;
//            }
//            fee = movie.getFee().minus(discountAmount);
//        } else {
//            fee = movie.getFee();
//        }
//        return new Reservation(customer, screening, fee, audienceCount);



        //TODO: 변경 후
        Money fee = screening.calculateFee(audienceCount);
        return new Reservation(customer, screening, fee, audienceCount);
    }

}
