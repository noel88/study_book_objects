package kr.objects.theater.responsibility;

import kr.objects.theater.responsibility.domain.Money;
import kr.objects.theater.responsibility.domain.Screening;

public interface DiscountPolicy {
    Money calculateDiscountAmount(Screening screening);
}
