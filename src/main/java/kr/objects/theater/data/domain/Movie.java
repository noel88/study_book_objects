package kr.objects.theater.data.domain;



import kr.objects.theater.responsibility.domain.Money;
import lombok.Getter;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * NOTE:
 *  - 데이터 중심의 설계란 객체 내부에 저장되는 데이터를 기반으로 시스템을 분할하는 방법이다.
 *  책임중심의 설계가 '책임이 무엇인가'를 묻는 것으로 시작한다면 데이터 중심의 설계는 객체가 내부에 저장해야 하는 '데이터가 무엇인가'를
 *  묻는 것으로 시작한다.
 *  - 데이터 중심의 설계에서는 객체가 포함해야 하는 데이터에 집중한다. 이 객체게 포함해야 하는 데이터는 무엇인가? 객체의 책임을 결정하기 전에 이런 질문의
 *  반복에 휩쓸려 있다면 데이터 중심의 설계에 매몰돼 있을 확률이 높다.
 *  - 객체의 종류를 저장하는 인스턴스 변수 (movieType)와 인스턴스의 종류에 따라 배타적으로 사용될 인스턴스 변수 (discountAmount, discountPercent)를
 *  하나의 클래스 안에 함께 포함시키는 방식은 데이터 중심의 설계 안에서 흔히 볼 수 있는 패턴이다.
 *  - 객체를 설계할 때 "이 객체가 어떤 데이터를 포햄해야 하는가?"라는 질문은 다음과 같은 두개의 개별적인 질문으로 분리해야 한다.
 *    1. 이 객체가 어떤 데이터를 포함해야 하는가?
 *    2. 이 객체가 데이터에 대해 수행해야 하는 오퍼레이션은 무엇인가?
 *  - 데이터 중심의 설계가 변경에 취약한 이유
 *    1. 데이터 중심의 설계는 본질적으로 너무 이른 시기에 데이터에 관한 결정하도록 강요한다.
 *    2. 데이터 중심의 설계에서는 협력이라는 문맥을 고려하지 않고 객체를 고립시킨 채 오퍼레이션을 결정한다.
 */

//@Getter
//@Setter
public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
//    private List<DiscountCondition> discountConditions;
//    private List<PeriodCondition> periodConditions;
//    private List<SequenceCondition> sequenceConditions;
    private List<DiscountCondition> discountConditions;

    @Getter
    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;

    public Movie(String title,
                 Duration runningTime,
                 Money fee,
                 DiscountCondition... discountConditions) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountConditions = Arrays.asList(discountConditions);
    }

    /**
     *
     * TODO: 내부 구현을 인터페이스에 노출시키고 있다. 노출시키고 있는 것은 할인 정책의 종류!
     *  - DiscountCondition의 기간 할인 조건의 명칭이 PERIOD에서 다른 값으로 변경된다면 Movie룰 수정해야 한다.
     *  - DiscountCondition의 종류가 추기되거나 삭제된다면 Movie 안의 if-else 구문을 수정해야 한다.
     *  - 각 DiscountCondition의 만족 여부를 판단하는 데 필요한 정보가 변경된다면 Movie의 isDiscountable 메서드로 전달된 파라미터를
     *   변경해야 한다. 이로 인해 Movie의 isDiscountable 메서드 시그니처도 함께 변경될 것이고 결과적으로 이 메서드에 의존하는
     *   Screening에 대한 변경을 초래할 것이다.
     */


//    public Money calculateAmountDiscountedFee() {
//        if (this.movieType != MovieType.AMOUNT_DISCOUNT) {
//            throw new IllegalArgumentException();
//        }
//        return this.fee.minus(this.discountAmount);
//    }
//
//    public Money calculatePercentDiscountedFee() {
//        if (this.movieType != MovieType.PERCENT_DISCOUNT) {
//            throw new IllegalArgumentException();
//        }
//        return this.fee.minus(fee.times(this.discountPercent));
//    }
//
//    public Money calculateNoneDiscountedFee() {
//        if (this.movieType != MovieType.NONE_DISCOUNT) {
//            throw new IllegalArgumentException();
//        }
//        return this.fee;
//    }

//    public boolean isDiscountable(LocalDateTime whenScreened, int sequence) {
//        for (DiscountCondition condition : discountConditions) {
//            if (condition.getType() == DiscountConditionType.PERIOD) {
//                if (condition.isDiscountable(whenScreened.getDayOfWeek(), whenScreened.toLocalTime())) {
//                    return true;
//                }
//            } else {
//                if (condition.isDiscountable(sequence)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }



        public Money calculateMovieFee(Screening screening) {
        if(isDiscountable(screening)) {
            return fee.minus(calculateDiscountAmount());
        }
        return fee;
    }

//    private boolean isDiscountable(Screening screening) {
//        return discountConditions.stream().anyMatch(condition -> condition.isSatisfiedBy(screening));
//    }

    //TODO: 첫번째 방법은 클래스 안에서 SequenceCondition의 목록과 PeriodCondition의 목록을 따로 유지.
//    private boolean isDiscountable(Screening screening) {
//        return checkPeriodConditions(screening) || checkSequenceConditions(screening);
//    }
//
//    private boolean checkSequenceConditions(Screening screening) {
//        return sequenceConditions.stream().anyMatch(condition -> condition.isSatisfiedBy(screening));
//    }
//
//    private boolean checkPeriodConditions(Screening screening) {
//            return periodConditions.stream().anyMatch(condition -> condition.isSatisfiedBy(screening));
//    }

    private boolean isDiscountable(Screening screening) {
            return discountConditions.stream().anyMatch(condition -> condition.isSatisfiedBy(screening));
    }


//    private Money calculateDiscountAmount() {
//        switch (movieType) {
//            case AMOUNT_DISCOUNT:
//                return calculateAmountDiscountedFee();
//            case PERCENT_DISCOUNT:
//                return calculatePercentDiscountedFee();
//            case NONE_DISCOUNT:
//                return calculateNoneDiscountedFee();
//        }
//        throw new IllegalStateException();
//    }
//
//    private Money calculateNoneDiscountedFee() {
//        return Money.ZERO;
//    }
//
//    private Money calculatePercentDiscountedFee() {
//        return fee.times(discountPercent);
//    }
//
//    private Money calculateAmountDiscountedFee() {
//        return discountAmount;
//    }

    abstract protected Money calculateDiscountAmount();
}
