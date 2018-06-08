package com.zopa.core;

import com.zopa.core.quotes.QuoteIllustrator;

import static java.lang.String.format;

public class App {

    private static final int MINIMUM = 1000;
    private static final int MAXIMUM = 1500;
    private static final int STEP = 100;

    public static void main(String[] args) {

        String illustration;

        if (invalidInput(args) || !validAmountValue(Integer.parseInt(args[1]))) {
            illustration = getRunErrorMessage();
        } else {
            illustration = QuoteIllustrator.illustrateQuote(args[0], Double.valueOf(args[1]));
        }

        System.out.print(illustration);
    }

    public static boolean invalidInput(String[] args) {
        if( args == null
                || args.length != 2
                || args[0] == null
                || args[0] == ""
                || args[1] == null
                || args[1] == "") {
            return true;
        }
        return false;
    }

    public static boolean validAmountValue(Integer loanAmount) {
        if (loanAmount < MINIMUM)
            return false;
        if (loanAmount > MAXIMUM)
            return false;
        return (loanAmount % STEP == 0);
    }

    private static String getRunErrorMessage() {
        return format("Expected input: <market_data_file> <loan_amount>\n" +
                "verify <loan_amount> is withing £1000 and £1500\n");
    }
}
