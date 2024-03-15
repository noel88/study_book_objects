package kr.theater.responsibility;

import kr.theater.responsibility.domain.Screening;

public interface  DiscountCondition {

    boolean isSatisfiedBy(Screening screening);
}
