package com.zopa.core.quotes;

import com.zopa.core.quotes.model.Lender;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataReaderTest {

    @Test
    public void shouldReadMarketDataFile() {
        int numberOfEntries = 7;
        List<Lender> lenders = DataReader.readData("mockData.csv");
        assertEquals(numberOfEntries, lenders.size());
    }

    @Test()
    public void shouldReturnEmptyOptionalIfFileNotFound() {
        List<Lender> lenders = DataReader.readData(null);
        assertTrue(lenders.isEmpty());
    }

    @Test()
    public void shouldReturnEmptyOptionalWhenDataIsCorrupted() {
        List<Lender> lenders = DataReader.readData("mockDataCorrupted.csv");
        assertTrue(lenders.isEmpty());
    }

}