package kr.theater.responsibility.domain;

import kr.theater.responsibility.DefaultDiscountPolicy;
import kr.theater.responsibility.DiscountPolicy;

import java.time.Duration;

public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    private DefaultDiscountPolicy defaultDiscountPolicy;
    private DiscountPolicy discountPolicy;


    public Movie(String title, Duration runningTime, Money fee, DefaultDiscountPolicy defaultDiscountPolicy) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.defaultDiscountPolicy = defaultDiscountPolicy;
    }

    public void changeDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public Money getFee() {
        return fee;
    }

    public Money calculateMovieFee(Screening screening) {

//        if (discountPolicy == null) {
//            return fee;
//        }
        return fee.minus(defaultDiscountPolicy.calculateDiscountAmount(screening));
    }
}
