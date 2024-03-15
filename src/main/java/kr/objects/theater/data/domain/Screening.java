package kr.theater.data.domain;

import kr.theater.responsibility.domain.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
//@Setter
@AllArgsConstructor
public class Screening {
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    public Reservation reserve(Customer customer, int audienceCount) {
        return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
    }

    public Money calculateFee(int audienceCont) {
//        switch (movie.getMovieType()) {
//            case AMOUNT_DISCOUNT:
//                if (movie.isDiscountable(whenScreened, sequence)) {
//                    return movie.calculateAmountDiscountedFee().times(audienceCont);
//                }
//                break;
//            case PERCENT_DISCOUNT:
//                if (movie.isDiscountable(whenScreened, sequence)) {
//                    return movie.calculatePercentDiscountedFee().times(audienceCont);
//                }
//                break;
//            case NONE_DISCOUNT:
//                return movie.calculateNoneDiscountedFee().times(audienceCont);
//        }
//        return movie.calculateNoneDiscountedFee().times(audienceCont);
        return movie.calculateMovieFee(this).times(audienceCont);
    }

}
