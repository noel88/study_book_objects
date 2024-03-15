package kr.objects.theater.data.type;


import kr.objects.theater.data.DiscountCondition;
import kr.objects.theater.data.domain.Screening;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SequenceCondition implements DiscountCondition {
    private int sequence;

    @Override
    public boolean isSatisfiedBy(Screening screening) {
        return sequence == screening.getSequence();
    }

//    public boolean isSatisfiedBy(Screening screening) {
//        return sequence == screening.getSequence();
//    }
}
