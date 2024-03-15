package kr.objects.theater.data;


import kr.objects.theater.data.domain.Screening;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
