package kr.theater.data;

import kr.theater.data.DiscountCondition;
import kr.theater.data.domain.Movie;
import kr.theater.responsibility.domain.Money;

import java.time.Duration;

public class AmountDiscountMovie extends Movie {

    private Money discountAmount;

    public AmountDiscountMovie(String title, Duration runningTime,
                               Money fee, Money discountAmount,
                               DiscountCondition... discountConditions) {
        super(title, runningTime, fee, discountConditions);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money calculateDiscountAmount() {
        return discountAmount;
    }
}
