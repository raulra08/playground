package com.zopa.core.quotes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuoteServiceTest {

    @Test
    public void shouldPrintQuoteMessage() {

        String quoteMessage = QuoteService.illustrateQuote("mockData.csv", 1000.0);
        String expectedQuote = "Requested amount: £1000 Rate: 7.1% Monthly repayment: £28.27 Total repayment: £1017.86";

        assertEquals(expectedQuote, quoteMessage);
    }




}