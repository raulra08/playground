package com.zopa.core.quotes;

import org.junit.Test;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

public class QuoteIllustratorTest {

    @Test
    public void shouldPrintQuoteMessage() {
        String quoteMessage = QuoteIllustrator.illustrateQuote("mockData.csv", 1000.0);
        String expectedQuote = format("Requested amount: £1000" +
                "\nRate: 7.1%%" +
                "\nMonthly repayment: £28.27" +
                "\nTotal repayment: £1017.86");

        assertEquals(expectedQuote, quoteMessage);
    }




}