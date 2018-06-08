package com.zopa.core;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AppTest {

    @Test
    public void shouldPrintQuoteIllustration(){
        String[] args = {"mockData.csv", "0"};
        App.main(args);
    }

    @Test
    public void shouldReturnTrueWithValidAmountValue() {
        assertTrue(App.validAmountValue(1000));
    }

    @Test
    public void shouldReturnTrueWithStepOf100AmountValue() {
        assertTrue(App.validAmountValue(1100));
    }

    @Test
    public void shouldReturnFalseWithInvalidStep() {
        assertFalse(App.validAmountValue(1101));
    }

    @Test
    public void shouldReturnFalseWithSmallAmountValue() {
        assertFalse(App.validAmountValue(900));
    }

    @Test
    public void shouldReturnTrueWithMaxAmountValue() {
        assertTrue(App.validAmountValue(1500));
    }

    @Test
    public void shouldReturnFalseWithBigAmountValue() {
        assertFalse(App.validAmountValue(9000));
    }

    @Test
    public void shouldReturnFalseWhenInputIsValid() {
        assertFalse(App.invalidInput(new String[]{"fileName", "0"}));
    }

    @Test
    public void shouldReturnTrueWithInvalidFileNameInput() {
        assertTrue(App.invalidInput(new String[]{"", "0"}));
    }

    @Test
    public void shouldReturnTrueWithInvalidAmountInput() {
        assertTrue(App.invalidInput(new String[]{"fileName", null}));
    }
}
