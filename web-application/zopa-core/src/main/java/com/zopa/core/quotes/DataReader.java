package com.zopa.core.quotes;

import com.zopa.core.quotes.model.Lender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import static java.lang.String.format;

public class DataReader {

    private DataReader() {
    }

    public static List<Lender> readData(String fileName) {

        String line;
        String splitByComma = ",";
        List<Lender> lenderList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // skip head line
            br.readLine();
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] country = line.split(splitByComma);

                String name = country[0];
                Double rate = Double.valueOf(country[1]);
                Double available = Double.valueOf(country[2]);

                lenderList.add(new Lender(name, rate, available));
            }

        } catch (Exception e) {
            System.err.println(format("Unable to read file: '%s'", fileName));
            return new ArrayList<>();
        }

        return lenderList;
    }
}
