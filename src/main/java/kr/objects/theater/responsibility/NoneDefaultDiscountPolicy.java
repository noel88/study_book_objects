package kr.theater.responsibility;

import kr.theater.responsibility.domain.Money;
import kr.theater.responsibility.domain.Screening;

public class NoneDefaultDiscountPolicy implements DiscountPolicy {

    @Override
    public Money calculateDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
