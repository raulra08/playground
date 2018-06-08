package com.zopa.core.quotes;

import com.zopa.core.quotes.model.Lender;
import com.zopa.core.quotes.model.Loan;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class QuoteIllustrator {

    private static final int HUNDRED_PERCENT = 100;

    private QuoteIllustrator() { }

    public static String illustrateQuote(String data, Double requiredAmount){
        List<Lender> lendersList = DataReader.readData(data)
                .stream()
                .sorted(Comparator.comparing(Lender::getRate))
                .collect(Collectors.toList());

        return LoanCalculator
                .calculateLoan(requiredAmount, lendersList)
                .map(l->printQuote(requiredAmount, l))
                .orElse(printError());
    }

    private static String printQuote(final Double requiredAmount, final Loan loan) {
        Double rate = loan.getRate() * HUNDRED_PERCENT;
        return format("Requested amount: £%.0f " +
                        "\nRate: %.1f%% " +
                        "\nMonthly repayment: £%.2f " +
                        "\nTotal repayment: £%.2f",
                requiredAmount,
                rate,
                loan.getMonthlyRepayment(),
                loan.getTotalRepayment());
    }

    private static String printError() {
        return format("We couldn't find an appropriate loan that fits your needs. Thanks for trying our service.");
    }
}
