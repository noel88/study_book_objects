package kr.theater.data.type;

import kr.theater.data.DiscountCondition;
import kr.theater.data.domain.Screening;
import lombok.AllArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@AllArgsConstructor
public class PeriodCondition implements DiscountCondition {
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    @Override
    public boolean isSatisfiedBy(Screening screening) {
        return dayOfWeek.equals(screening.getWhenScreened().getDayOfWeek()) &&
            startTime.compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
            endTime.compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
    }

//    public boolean isSatisfiedBy(Screening screening) {
//        return dayOfWeek.equals(screening.getWhenScreened().getDayOfWeek()) &&
//                startTime.compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
//                endTime.compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
//    }
}
