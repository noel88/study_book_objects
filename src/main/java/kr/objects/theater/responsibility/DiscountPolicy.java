package kr.theater.responsibility;

import kr.theater.responsibility.domain.Money;
import kr.theater.responsibility.domain.Screening;

public interface DiscountPolicy {
    Money calculateDiscountAmount(Screening screening);
}
