package kr.theater.data.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * NOTE: DiscountCondition은 순번 조건일 경우에는 seqeunce를 이용하여 할인 여부를 결정하고,
 *  기간 조건일 경우에는 dayOfWeek, startTime, endTime을 이용해 할인 여부를 결정한다.
 *  - 내부 구현의 변경이 외부로 퍼저나가는 파급 효과는 캡슐화가 부족하다는 명백한 증거이다.
 */
@Getter
//@Setter
public class DiscountCondition {
    private DiscountConditionType type;
    private int sequence;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    //TODO: 각 할인 조건을 판단할 수 있는 메서드가 필요함. 이전의 설계보다 분명히 개선되었지만 여전히 내부의 구현을 캡슐화하는 데는 실패한 것이다.
//    public boolean isDiscountable(DayOfWeek dayOfWeek, LocalTime time) {
//        if (this.type != DiscountConditionType.PERIOD) {
//            throw new IllegalArgumentException();
//        }
//        return this.dayOfWeek.equals(dayOfWeek) &&
//                this.startTime.compareTo(time) <= 0 &&
//                this.endTime.compareTo(time) >= 0;
//    }
//
//    public boolean isDiscountable(int sequence) {
//        if (this.type != DiscountConditionType.SEQUENCE) {
//            throw new IllegalArgumentException();
//        }
//        return this.sequence == sequence;
//    }

    /**
     * NOTE:
     *  DiscountCondition은 하나 이상의 변경 이유를 가지기 때문에 응집도가 낮다.
     *  응집도가 낮다는 것은 서로 연관성이 없는 기능이나 데이터가 하나의클래스 안에 뭉쳐져 있다는 것을 의미한다. 따라서 낮은 응집도가 초래하는 문제를 해결하기 위해서 변경의 이유에 따라 클래스를 분리해야 한다.
     *  - 새로운 할인조건 추가 :
     *   isSatisfiedBy 메서더의 안에 if-else 구문을 수정해야 한다. 물론 새로운 할인 조건이 새로운 데이터를 요구한다면 DiscountCondition에서 속성을 추가하는 작업도 필요하다.
     *  - 순번 조건을 판단하는 로직 변경 :
     *   isSatisfiedBySequence 메서드의 내부 구현을 수정해야 한다. 물론 순번 조건을 판단하는 데 필요한 데이터가 변경된다면 DiscountCondition의 sequence 속성 역시 변경해야 한다.
     *  - 기간 조건을 판단하는 로직이 변경되는 경우 :
     *   isSatisfiedByPeriod 메서드의 내부 구현을 수정해야 한다. 물론 기간 조건을 판단하는 데 필요한 데이터가 변경된다면 DiscountCondition의 dayOfWeek, startTime, endTime 속성 역시 변경해야 할 것이다.
     *
     *
     *
     */
    //


    //TODO: 한쪽에 메서드를 몰아두지 않고 각각 다른 쪽으로 이동한다.
//    public boolean isSatisfiedBy(Screening screening) {
//        if (type == DiscountConditionType.PERIOD) {
//            return isSatisfiedByPeriod(screening);
//        }
//        return isSatisfiedBySequence(screening);
//    }

//    private boolean isSatisfiedBySequence(Screening screening) {
//        return sequence == screening.getSequence();
//    }
//
//    private boolean isSatisfiedByPeriod(Screening screening) {
//        return dayOfWeek.equals(screening.getWhenScreened().getDayOfWeek()) &&
//                startTime.compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
//                endTime.compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
//    }

    /**
     * NOTE: 코드를 통해 변경의 이유를 파악할 수 있는 첫번째 방법은 인스턴스 변수가 초기화되는 시점을 살펴보는 것이다.
     *  응집도가 높은 클래스는 인스턴스를 생성할 때 모든 속성을 함께 초기화 한다.
     *  두번때 방법은 메서드들이 인스턴스 변수를 사용하는 방식을 살펴보는 것이다. 모든 메서드가 객체의 모든 속성을 사용한다면
     *  클래서의 응집도는 높다고 볼 수 있다.
     */


    public boolean isDiscountable(DiscountCondition condition, Screening screening) {
        if(condition.getType() == DiscountConditionType.PERIOD) {
            return isSatisfiedByPeriod(condition, screening);
        }
        return isSatisfiedBySequence(condition, screening);
    }

    private boolean isSatisfiedBySequence(DiscountCondition condition, Screening screening) {
        return condition.getSequence() == screening.getSequence();
    }

    private boolean isSatisfiedByPeriod(DiscountCondition condition, Screening screening) {
        return screening.getWhenScreened().getDayOfWeek().equals(condition.getDayOfWeek()) &&
                condition.getStartTime().compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
                condition.getEndTime().compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
    }}
