package com.zopa.core;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AppTest {

    @Test
    public void shouldPrintQuote() {
        String[] args = null;

        App.main(new String[]{"Market Data for Exercise - csv.csv", "1000.0"});
    }

    @Test
    public void shouldComputeCompoundInterest(){
        App app = new App(new DataReader(""), new RateCalculator());

        Double gains = app.compoundInterest(1000.0, 0.07);

        assertTrue(gains.equals(1108.10));
    }

    @Test
    public void shouldQuote(){
        App app = new App(new DataReader("Market Data for Exercise - csv.csv"), new RateCalculator());

        app.quoteLoan();

        assertTrue(true);
    }
}
