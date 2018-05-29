package com.zopa.core;

import java.util.*;

public class RateCalculator {

    private static final int LOAN_PERIOD = 36;
    private static final int RATE_PERIOD = 12;

    public Optional calculateBestRate(Map<String, Map<String, Double>> data) {
        Map<String, Map<String, Double>> ratesData = new HashMap<>();

        data.forEach((s, m) -> m.put("repayment", compoundInterest(1000.0, m.get("rate"))));

        return Optional.of(ratesData);
    }

    private Double compoundInterest(Double principal, Double rate) {
        Double percent = 1 + (rate/ RATE_PERIOD);
        Double compound = Math.pow(percent, (LOAN_PERIOD/12));
        Double amount = principal * compound;

        return amount;
    }
}
