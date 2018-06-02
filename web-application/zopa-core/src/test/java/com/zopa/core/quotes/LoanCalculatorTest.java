package com.zopa.core.quotes;

import com.zopa.core.quotes.model.Lender;
import com.zopa.core.quotes.model.Loan;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LoanCalculatorTest {

    private LoanCalculator loanCalculator;
    private List<Lender> lenders;

    @Before
    public void setUp() {
        loanCalculator = new LoanCalculator();
        lenders = lendersMock();
    }

    @Test
     public void shouldComputeLoanQuote() {
        Loan loan = loanCalculator.calculateLoan(1000.0, lenders);

        Double repaymentAmount = Double.valueOf(1017.8552279577544);

        assertEquals(repaymentAmount, loan.getTotalRepayment());
    }

    private List<Lender>  lendersMock() {
        List<Lender> lenders = new ArrayList<>();
        lenders.add(new Lender("Bob", Double.valueOf("0.075"), Double.valueOf("640")));
        lenders.add(new Lender("Jane", Double.valueOf("0.069"), Double.valueOf("480")));
        lenders.add(new Lender("Fred", Double.valueOf("0.071"), Double.valueOf("520")));
        lenders.add(new Lender("Mary", Double.valueOf("0.104"), Double.valueOf("170")));
        return lenders;
    }

}