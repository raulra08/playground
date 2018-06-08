package com.zopa.core.quotes;

import com.zopa.core.quotes.model.Lender;
import com.zopa.core.quotes.model.Loan;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

public class QuoteService {

    public static final int HUNDRED_PERCENT = 100;

    private QuoteService() { }

    public static String illustrateQuote(String data, Double requiredAmount){
        List<Lender> lendersList = DataReader.readData(data);
        Optional<Loan> loan = LoanCalculator.calculateLoan(requiredAmount, lendersList);

        return loan
                .map(l->printQuote(requiredAmount, l))
                .orElse(printError());

    }

    private static String printQuote(final Double requiredAmount, final Loan loan) {
        Double rate = loan.getRate() * HUNDRED_PERCENT;
        return format("Requested amount: £%.0f Rate: %.1f%% Monthly repayment: £%.2f Total repayment: £%.2f",
                requiredAmount, rate, loan.getMonthlyRepayment(), loan.getTotalRepayment());
    }

    private static String printError() {
        return format("We couldn't find an appropriate loan that fits your needs. Thanks for trying our service.");
    }

}
