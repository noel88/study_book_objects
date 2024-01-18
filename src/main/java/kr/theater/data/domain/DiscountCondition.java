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
//@Getter
//@Setter
public class DiscountCondition {

    @Getter
    private DiscountConditionType type;

    private int sequence;

    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    //TODO: 각 할인 조건을 판단할 수 있는 메서드가 필요함. 이전의 설계보다 분명히 개선되었지만 여전히 내부의 구현을 캡슐화하는 데는 실패한 것이다.
    public boolean isDiscountable(DayOfWeek dayOfWeek, LocalTime time) {
        if (this.type != DiscountConditionType.PERIOD) {
            throw new IllegalArgumentException();
        }
        return this.dayOfWeek.equals(dayOfWeek) &&
                this.startTime.compareTo(time) <= 0 &&
                this.endTime.compareTo(time) >= 0;
    }

    public boolean isDiscountable(int sequence) {
        if (this.type != DiscountConditionType.SEQUENCE) {
            throw new IllegalArgumentException();
        }
        return this.sequence == sequence;
    }
}
