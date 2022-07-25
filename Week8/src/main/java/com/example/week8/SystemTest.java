package com.example.week8;
import org.junit.Test;
import java.io.PrintWriter;
import java.util.Scanner;
import static org.junit.Assert.*;

public class SystemTest {

    String globalPath = "/Users/keshavnatarajan/Documents/GitHub/ESC-Mini-Campaign/TestFiles/SystemTests/";



    @Test
    public void testSingleMismatch(){
        String path1 = globalPath + "testSingleMismatch/singleMismatch1.csv";
        String path2 = globalPath + "testSingleMismatch/singleMismatch2.csv";
        String outPath = globalPath + "testSingleMismatch/singleMismatchOutput.csv";

        Scanner parser1 = CSVCompare.getParser(path1);
        Scanner parser2 = CSVCompare.getParser(path2);
        PrintWriter writer = CSVCompare.getWriter(outPath);

        assertTrue(CSVCompare.cleanFiles(parser1, parser2)); //Expected output to pass

        CSVCompare.compareLines(parser1, parser2, writer); //Expected output "Passed"
    }

    @Test
    public void testAllMismatch(){
        String path1 = globalPath + "testAllMismatch/allMismatch1.csv";
        String path2 = globalPath + "testAllMismatch/allMismatch2.csv";
        String outPath = globalPath + "testAllMismatch/allMismatchOutput.csv";

        Scanner parser1 = CSVCompare.getParser(path1);
        Scanner parser2 = CSVCompare.getParser(path2);
        PrintWriter writer = CSVCompare.getWriter(outPath);

        assertTrue(CSVCompare.cleanFiles(parser1, parser2)); //Expected output to pass

        CSVCompare.compareLines(parser1, parser2, writer); //Expected output "Passed"
    }

    @Test
    public void testIdentical(){
        String path1 = globalPath + "testIdentical/identical1.csv";
        String path2 = globalPath + "testIdentical/identical2.csv";
        String outPath = globalPath + "testIdentical/identicalOutput.csv";

        Scanner parser1 = CSVCompare.getParser(path1);
        Scanner parser2 = CSVCompare.getParser(path2);
        PrintWriter writer = CSVCompare.getWriter(outPath);

        assertTrue(CSVCompare.cleanFiles(parser1, parser2)); //Expected output to pass

        CSVCompare.compareLines(parser1, parser2, writer); //Expected output "Passed"
        //Empty output file will be produced
    }

    @Test
    public void testOneOutOfOrder(){
        String path1 = globalPath + "testOneOutOfOrder/oneOutOfOrder1.csv";
        String path2 = globalPath + "testOneOutOfOrder/oneOutOfOrder1.csv";
        String outPath = globalPath + "testOneOutOfOrder/oneOutOfOrderOutput.csv";

        Scanner parser1 = CSVCompare.getParser(path1);
        Scanner parser2 = CSVCompare.getParser(path2);
        PrintWriter writer = CSVCompare.getWriter(outPath);

        assertFalse(CSVCompare.cleanFiles(parser1, parser2)); //Expected output to fail

        CSVCompare.compareLines(parser1, parser2, writer); //Expected output "Failed"
        //Error in file 1, No output CSV created
    }

    @Test
    public void testAllOutOfOrder(){
        String path1 = globalPath + "testAllOutOfOrder/allOutOfOrder1.csv";
        String path2 = globalPath + "testAllOutOfOrder/allOutOfOrder1.csv";
        String outPath = globalPath + "testAllOutOfOrder/allOutOfOrderOutput.csv";

        Scanner parser1 = CSVCompare.getParser(path1);
        Scanner parser2 = CSVCompare.getParser(path2);
        PrintWriter writer = CSVCompare.getWriter(outPath);

        assertFalse(CSVCompare.cleanFiles(parser1, parser2)); //Expected output to fail

        CSVCompare.compareLines(parser1, parser2, writer); //Expected output "Failed"
        //Error in file 2, No output CSV created
    }

    @Test
    public void testMissingAccount(){
        String path1 = globalPath + "testMissingAccounts/MissingAccount1.csv";
        String path2 = globalPath + "testMissingAccounts/MissingAccount1.csv";
        String outPath = globalPath + "testMissingAccounts/MissingAccountOutput.csv";

        Scanner parser1 = CSVCompare.getParser(path1);
        Scanner parser2 = CSVCompare.getParser(path2);
        PrintWriter writer = CSVCompare.getWriter(outPath);

        assertFalse(CSVCompare.cleanFiles(parser1, parser2)); //Expected output to fail

        CSVCompare.compareLines(parser1, parser2, writer); //Expected output "Failed"
        //Error in file 1, No output CSV created
    }

    @Test
    public void testAdditionalAccount(){
        String path1 = globalPath + "testAdditionalAccounts/AdditionalAccounts1.csv";
        String path2 = globalPath + "testAdditionalAccounts/AdditionalAccounts2.csv";
        String outPath = globalPath + "testAdditionalAccounts/AdditionalAccountsOutput.csv";

        Scanner parser1 = CSVCompare.getParser(path1);
        Scanner parser2 = CSVCompare.getParser(path2);
        PrintWriter writer = CSVCompare.getWriter(outPath);

        assertFalse(CSVCompare.cleanFiles(parser1, parser2)); //Expected output to fail

        CSVCompare.compareLines(parser1, parser2, writer); //Expected output "Failed"
        //Error in file 2, No output CSV created
    }


}
