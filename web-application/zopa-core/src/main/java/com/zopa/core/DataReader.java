package com.zopa.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.lang.String.format;

public class DataReader {

    private final String fileName;
    private Map<String, Map<String, Double>> data;

    DataReader(String fileName) {
        this.fileName = fileName;
        data = new HashMap<>();
    }

    public Optional readData() {

        String line = "";
        String splitByComma = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // skip head line
            br.readLine();
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] country = line.split(splitByComma);

                Map lender = new HashMap<String, Double>();
                lender.put("rate", Double.valueOf(country[1]));
                lender.put("available", Double.valueOf(Integer.valueOf(country[2])));
                data.put(country[0], lender);
            }

        } catch (NullPointerException | IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println(format("Unable to read file: %s", fileName) + e.getMessage());
            return Optional.empty();
        }

        return Optional.of(data);
    }
}
