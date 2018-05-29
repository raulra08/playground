package com.zopa.core;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Optional;

import static org.junit.Assert.*;

public class DataReaderTest {

    private DataReader dataReader;

    @Before
    public void setUp() {
        String fileName = "Market Data for Exercise - csv.csv";
        dataReader = new DataReader(fileName);
    }

    @Test
    public void shouldReadMarketDataFile() throws FileNotFoundException {
        Optional optionalData = dataReader.readData();
        assertTrue(optionalData.isPresent());
    }

    @Test()
    public void shouldReturnEmptyOptionalIfFileNotFound() throws FileNotFoundException {

        DataReader underTest = new DataReader(null);
        Optional optionalData = underTest.readData();

        assertTrue(!optionalData.isPresent());
    }

}