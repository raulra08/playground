package com.zopa.core;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public class RateCalculatorTest {

    private RateCalculator rateCalculator;

    @Before
    public void setUp() {
        rateCalculator = new RateCalculator();
    }

    @Test
    public void shouldCalculateBestRate() {
        Map<String, Map<String, Double>> data = getMarketDataMock();

        Optional rate = rateCalculator.calculateBestRate(data);

        assertTrue(rate.isPresent());
    }

    private Map<String, Map<String, Double>> getMarketDataMock() {
        Map<String, Map<String, Double>> data = new HashMap<>();
        Map<String, Double> lender = new HashMap<>();

        lender.put("rate", 0.07);
        lender.put("available", 600.0);
        data.put("Bob", lender);
        return data;
    }

}