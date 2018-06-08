package com.zopa.core.quotes;

import com.zopa.core.quotes.model.Lender;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;

public class DataReader {

    private DataReader() {
    }

    public static List<Lender> readData(String fileName) {

        String splitByComma = ",";
        List<Lender> lenderList;

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            lenderList = stream
                    .filter(line -> !line.startsWith("Lender"))
                    .map(line -> {
                        String[] country = line.split(splitByComma);

                        String name = country[0];
                        Double rate = Double.valueOf(country[1]);
                        Double available = Double.valueOf(country[2]);

                        return new Lender(name, rate, available);
                    })
                    .collect(Collectors.toList());

        } catch (Exception e) {
            System.err.println(format("Unable to read file: '%s'", fileName));
            System.err.println(format("Exception caught: %s, message: %s", e.getClass().toString(), e.getMessage()));
            return new ArrayList<>();
        }

        return lenderList;
    }
}
