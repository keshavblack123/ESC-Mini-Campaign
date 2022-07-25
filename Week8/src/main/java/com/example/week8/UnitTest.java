package com.example.week8;

import org.junit.Test;
import java.io.PrintWriter;
import java.util.Scanner;
import static org.junit.Assert.*;

public class UnitTest {

    String globalPath = "/Users/keshavnatarajan/Documents/GitHub/ESC-Mini-Campaign/TestFiles/UnitTests/";

    @Test
    public void testGetParser(){
        String path = globalPath + "testGetParser.csv";
        Scanner parser = CSVCompare.getParser(path);
        //Fails if exception is thrown (file does not exist)
    }

    @Test
    public void testGetWriter(){
        String path = globalPath + "outputFile.csv";
        PrintWriter writer = CSVCompare.getWriter(path);
        //Fails if exception is thrown
    }

    @Test
    public void testCheckOrderedAccounts(){
        String path = globalPath + "testOrderedAccounts.csv";
        Scanner parser = CSVCompare.getParser(path);
        Boolean out = CSVCompare.AccountsOrdered(parser);
        assertTrue(out);
    }

    @Test
    public void testCheckUnorderedAccounts(){
        String path = globalPath + "testUnorderedAccounts.csv";
        Scanner parser = CSVCompare.getParser(path);
        Boolean out = CSVCompare.AccountsOrdered(parser);
        assertFalse(out);
    }

    @Test
    public void testCheckExtraLines1(){
        String path1 = globalPath + "test1AdditionalAccount/additionalAccount1.csv";
        String path2 = globalPath + "test1AdditionalAccount/additionalAccount2.csv";
        Scanner parser1 = CSVCompare.getParser(path1);
        Scanner parser2 = CSVCompare.getParser(path2);
        Boolean out = CSVCompare.checkExtraLines(parser1, parser2);
        assertFalse(out);
    }

    @Test
    public void testCheckExtraLinesMany(){
        String path1 = globalPath + "testManyAdditionalAccount/manyAdditions1.csv";
        String path2 = globalPath + "testManyAdditionalAccount/manyAdditions2.csv";
        Scanner parser1 = CSVCompare.getParser(path1);
        Scanner parser2 = CSVCompare.getParser(path2);
        Boolean out = CSVCompare.checkExtraLines(parser1, parser2);
        assertFalse(out);
    }

    @Test
    public void testCheckNoExtraLines(){
        String path1 = globalPath + "testNoExtraAccounts/NoExtra1.csv";
        String path2 = globalPath + "testNoExtraAccounts/NoExtra2.csv";
        Scanner parser1 = CSVCompare.getParser(path1);
        Scanner parser2 = CSVCompare.getParser(path2);
        Boolean out = CSVCompare.checkExtraLines(parser1, parser2);
        assertTrue(out);
    }

    @Test
    public void testCountLine(){
        String path = globalPath + "testCountLine.csv";
        Scanner parser = CSVCompare.getParser(path);
        int lines = CSVCompare.countLines(parser);
        assertTrue(lines == 321);
    }
}
