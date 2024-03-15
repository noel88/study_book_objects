package kr.theater.data;

import kr.theater.responsibility.domain.Money;

public abstract class Movie {
    protected Money getFee() {
        return fee;
    }
}
