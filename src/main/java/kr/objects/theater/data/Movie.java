package kr.objects.theater.data;


import kr.objects.theater.responsibility.domain.Money;

public abstract class Movie {
    protected Money getFee() {
        return fee;
    }
}
