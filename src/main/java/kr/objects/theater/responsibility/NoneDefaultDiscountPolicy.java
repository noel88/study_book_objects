package kr.objects.theater.responsibility;

import kr.objects.theater.responsibility.domain.Money;
import kr.objects.theater.responsibility.domain.Screening;

public class NoneDefaultDiscountPolicy implements DiscountPolicy {

    @Override
    public Money calculateDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
