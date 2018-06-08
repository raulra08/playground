package com.zopa.core.quotes;

import com.zopa.core.quotes.model.Lender;
import com.zopa.core.quotes.model.Loan;

import java.util.*;

public class LoanCalculator {

    private static final int RATE_PERIOD = 12;
    private static final int LOAN_PERIOD = 36;
    private static final int TIMES_COMPOUNDED = 12;

    private LoanCalculator() { }

    public static Optional<Loan> calculateLoan(final Double principal, final List<Lender> lenders) {
        List<Lender> lenderList = sortByRate(lenders);

        Double remainder = principal;

        for (Lender lender : lenderList) {

            remainder = remainder - lender.getAvailable();
            if( remainder <= 0.0) {
                return applyForLoan(principal, lender);
            }
        }
        return Optional.empty();
    }

    private static Optional<Loan> applyForLoan(Double principal, Lender lender) {
        Double amount = calculateCompoundInterestAmount(principal, lender);
        Double monthlyRepayment = amount / LOAN_PERIOD;
        return Optional.of(new Loan(monthlyRepayment, amount, lender.getRate()));
    }

    private static Double calculateCompoundInterestAmount(final Double principal, final Lender lender) {
        Double rate = 1 + (lender.getRate()/RATE_PERIOD);
        Double compoundedInterest = Math.pow(rate, (LOAN_PERIOD/ TIMES_COMPOUNDED));

        return principal * compoundedInterest;
    }

    private static List<Lender> sortByRate(final List<Lender> lenders) {
        List<Lender> lenderList = new ArrayList<>(lenders);
        Collections.sort(lenderList, Comparator.comparing(Lender::getRate));
        return lenderList;
    }
}
