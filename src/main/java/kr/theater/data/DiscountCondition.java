package kr.theater.data;

import kr.theater.data.domain.Screening;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
