package com.zopa.core;

import com.zopa.core.quotes.DataReader;
import com.zopa.core.quotes.LoanCalculator;
import com.zopa.core.quotes.model.Lender;

import java.util.List;
import java.util.Optional;

public class App {

    private static final int MINIMUM = 1000;
    private static final int MAXIMUM = 1500;
    private static final int STEP = 100;

    public static void main(String[] args) {

        // check arguments
        if (args == null || args.length != 2) {
            System.out.println("Expected input: <market_data_file> <loan_amount>");
            return;
        }

        Integer loanAmount = Integer.parseInt(args[1]);
        if (App.validateInput(loanAmount)) {
            List<Lender> lendersList = DataReader.readData(args[0]);
            LoanCalculator loanCalculator = new LoanCalculator();
        }
    }

    public static boolean validateInput(Integer loanAmount) {
        if (loanAmount < MINIMUM)
            return false;
        if (loanAmount > MAXIMUM)
            return false;
        return (loanAmount % STEP == 0);
    }
}
