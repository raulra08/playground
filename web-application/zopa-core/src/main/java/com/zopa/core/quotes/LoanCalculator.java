package com.zopa.core.quotes;

import com.zopa.core.quotes.model.Lender;
import com.zopa.core.quotes.model.Loan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LoanCalculator {

    private static final int LOAN_PERIOD = 36;
    private static final int RATE_PERIOD = 12;
    private static final int TIMES_COMPOUNDED = 12;
    private Loan loan;

    public LoanCalculator() {
    }

    public Loan calculateLoan(final Double principal, final List<Lender> lenders) {
        List<Lender> lenderList = sortByRate(lenders);

        int i = 0;
        Double remainder = principal;
        for (Lender lender : lenderList) {

            remainder = remainder - lender.getAvailable();
            if( remainder <= 0.0) {
                setLoan(principal, lender);
                break;
            }
        }
        return getLoan();
    }

    private void setLoan(Double principal, Lender lender) {
        Double amount = calculateCompoundInterestAmount(principal, lender);
        this.loan = new Loan(amount/LOAN_PERIOD, amount, lender.getRate());
    }

    private Loan getLoan() {
        return this.loan;
    }

    private Double calculateCompoundInterestAmount(final Double principal, final Lender lender) {
        Double rate = 1 + (lender.getRate()/RATE_PERIOD);
        Double compoundedInterest = Math.pow(rate, (LOAN_PERIOD/ TIMES_COMPOUNDED));

        return principal * compoundedInterest;
    }

    private List<Lender> sortByRate(final List<Lender> lenders) {
        List<Lender> lenderList = new ArrayList<>(lenders);
        Collections.sort(lenderList, Comparator.comparing(Lender::getRate));
        return lenderList;
    }
}
