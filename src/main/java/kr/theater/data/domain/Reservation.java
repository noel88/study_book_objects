package kr.theater.data.domain;

import kr.theater.responsibility.domain.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Reservation {
    private Customer customer;
    private Screening screening;
    private Money fee;
    private int audienceCount;
}
