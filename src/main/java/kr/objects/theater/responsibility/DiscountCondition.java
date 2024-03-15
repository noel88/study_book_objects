package kr.objects.theater.responsibility;


import kr.objects.theater.responsibility.domain.Screening;

public interface  DiscountCondition {

    boolean isSatisfiedBy(Screening screening);
}
