package com.zopa.core.quotes;

import com.zopa.core.quotes.model.Lender;
import com.zopa.core.quotes.model.Loan;

import java.util.List;
import java.util.Optional;

public class LoanCalculator {

    private static final int RATE_PERIOD = 12;
    private static final int LOAN_PERIOD = 36;
    private static final int TIMES_COMPOUNDED = 12;

    private LoanCalculator() {
    }

    public static Optional<Loan> calculateLoan(final Double principal, final List<Lender> lenderList) {
        Double remainder = principal;
        for (Lender lender : lenderList) {

            remainder -= lender.getAvailable();
            if (remainder <= 0.0) {
                Double amount = calculateCompoundInterestAmount(principal, lender.getRate());
                return setupLoan(amount, lender.getRate());
            }
        }
        return Optional.empty();
    }

    private static Double calculateCompoundInterestAmount(final Double principal, Double lenderRate) {
        Double rate = 1 + (lenderRate / RATE_PERIOD);
        Double compoundedInterest = Math.pow(rate, (LOAN_PERIOD / TIMES_COMPOUNDED));
        return principal * compoundedInterest;
    }

    private static Optional<Loan> setupLoan(final Double amount, Double lenderRate) {
        Double monthlyRepayment = amount / LOAN_PERIOD;
        return Optional.of(new Loan(monthlyRepayment, amount, lenderRate));
    }
}
