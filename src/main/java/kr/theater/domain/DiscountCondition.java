package kr.theater.domain;

public interface  DiscountCondition {

    boolean isSatisfiedBy(Screening screening);
}
