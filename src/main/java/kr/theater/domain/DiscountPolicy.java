package kr.theater.domain;

public interface DiscountPolicy {
    Money calculateDiscountAmount(Screening screening);
}
