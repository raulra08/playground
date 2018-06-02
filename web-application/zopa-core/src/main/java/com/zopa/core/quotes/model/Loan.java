package com.zopa.core.quotes.model;

public class Loan {

    private final Double monthlyRepayment;
    private final Double totalRepayment;
    private final Double rate;

    public Loan(Double monthlyRepayment, Double totalRepayment, Double rate) {
        this.monthlyRepayment = monthlyRepayment;
        this.totalRepayment = totalRepayment;
        this.rate = rate;
    }

    public Double getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public Double getTotalRepayment() {
        return totalRepayment;
    }

    public Double getRate() {
        return rate;
    }
}
