package com.zopa.core;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AppTest {

    @Test
    public void shouldReturnTrueWithValidInput() {
        assertTrue(App.validateInput(1000));
    }

    @Test
    public void shouldReturnTrueWithStepOf100Input() {
        assertTrue(App.validateInput(1100));
    }

    @Test
    public void shouldReturnFalseWithInvalidStep() {
        assertFalse(App.validateInput(1101));
    }

    @Test
    public void shouldReturnFalseWithSmallInput() {
        assertFalse(App.validateInput(900));
    }

    @Test
    public void shouldReturnTrueWithMaxInput() {
        assertTrue(App.validateInput(1500));
    }

    @Test
    public void shouldReturnFalseWithBigInput() {
        assertFalse(App.validateInput(9000));
    }
}
