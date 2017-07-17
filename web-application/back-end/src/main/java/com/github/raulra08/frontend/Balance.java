package com.github.raulra08.frontend;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class Balance {

    private long amount;

    @Length(max = 3)
    private String currency;

    public Balance() {}

    public Balance(long amount) {
        this.amount = amount;
        this.currency = "GBP";
    }

    @JsonProperty
    public long getAmount() {
        return amount;
    }

    @JsonProperty
    public String getCurrency() {
        return currency;
    }

}
