package com.zopa.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class App {

    private final DataReader dataReader;
    private final RateCalculator rateCalculator;

    public static void main(String[] args) {

        // check arguments
        if (args == null || args.length != 2) {
            System.out.println("Expected input: <market_data_file> <loan_amount>");
            return;
        }

        // load dependencies
        DataReader dataReader = new DataReader(args[0]);
        RateCalculator rateCalculator = new RateCalculator();

        // initialise application
        App app = new App(dataReader, rateCalculator);

        // execute
        app.quoteLoan();
    }

    public App(DataReader dataReader, RateCalculator rateCalculator) {
        this.dataReader = dataReader;
        this.rateCalculator = rateCalculator;
    }

    public void quoteLoan() {
        Optional<Map<String, Map<String, Double>>> data = this.dataReader.readData();
        data;
        Optional rate = this.rateCalculator.calculateBestRate(data.);

    }
}
