package com.zopa.core;

import com.zopa.core.quotes.QuoteService;

public class App {

    private static final int MINIMUM = 1000;
    private static final int MAXIMUM = 1500;
    private static final int STEP = 100;

    public static void main(String[] args) {
        String illustration = "";
        if (args == null || args.length != 2 || invalidInput(args[0], args[1])) {
            illustration = "Expected input: <market_data_file> <loan_amount>";
        } else {
            if (validAmountValue(Integer.parseInt(args[1]))) {
                illustration = QuoteService.illustrateQuote(args[0], Double.valueOf(args[1]));
            }
        }
        System.out.print(illustration);
    }

    public static boolean invalidInput(String fileName, String loanAmount) {
        if (fileName == null || fileName == "") {
            return true;
        }
        if (loanAmount == null || loanAmount == "") {
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
}
