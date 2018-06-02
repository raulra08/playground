package com.zopa.core.quotes.model;

public class Lender {

    private final String name;
    private final Double rate;
    private final Double available;

    public Lender(final String name, final Double rate, final Double available) {
        this.name = name;
        this.rate = rate;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public Double getRate() {
        return rate;
    }

    public Double getAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return String.format("\"%s\", \"%s\", \"%s\"", this.name, this.rate.toString(), this.available.toString());
    }
}