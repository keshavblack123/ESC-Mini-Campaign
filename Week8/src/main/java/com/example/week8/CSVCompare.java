package com.example.week8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVCompare {

    public static void main(String[] args) {
        Path path = Paths.get("Week8");
        String globalPath = path.toAbsolutePath().toString();
        String file1Path = globalPath + "/sample_file_1.csv";
        String file3Path = globalPath + "/sample_file_3.csv";
        String outputFile = globalPath + "/output_file.csv";

        System.out.println(file1Path);
        if (cleanFiles(file1Path, file3Path)){
            PrintWriter writer = getWriter(outputFile);
            compareLines(file1Path, file3Path, writer);
            System.out.println("Passed!!\nOutput file produced at this address:\n" + outputFile);
        }
        else{
            System.out.println("Failed!\nOutput File not produced!");
        }
    }

    //Gets the reader for the CSV files
    public static Scanner getParser (String filePath){
        Scanner parser = null;
        try {
            parser = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return parser;
    }
    /*
    Checks if a file's list of accounts are ordered by their ID numbers
    If their ID numbers are not in a consecutive order,
    it will notify the user which accounts are not in order
     */
    public static boolean AccountsOrdered (Scanner parser){
        Integer i = 1;
        ArrayList<String> mismatch = new ArrayList();
        parser.nextLine();

        while (parser.hasNextLine()){
            String line = parser.nextLine();
            String lineArr[] = line.split(",");
            String idNum = lineArr[0].substring(3, lineArr[0].length() - 1);
            String index = i.toString();
            if (!idNum.equals(index)){
                mismatch.add(idNum);
            }
            i++;
        }
        if (!mismatch.isEmpty()){
            System.out.println("The IDs are not in order! \nIDs out of order are as follows:\n"+ mismatch);
            return false;
        }
        return true;
    }
    /*
    Checks if one file has more lines than another file
    If one file has more lines, the user will be notified which file has extra accounts
     */
    public static Boolean checkExtraLines (String file1, String file2){
        int difference = countLines(file1) - countLines(file2);
        if (difference == 0){
            return true;
        }
        else if (difference > 0){
            System.out.println("File 1 contains extra accounts");
            return false;
        }
        else{
            System.out.println("File 2 contains extra accounts");
            return false;
        }
    }
    /*
    So that only 1 function needs to be called in the main function
    checkExtraLines is called after AccountsOrdered as missing accounts and the wrong order of accounts should be addressed beforehand
    Shows the order in which the files need to be checked:
    Make sure accounts are in consecutive order --> Check that they have the same number of accounts in each file
     */
    public static Boolean cleanFiles (String filePath1, String filePath2){
        Scanner parser1 = getParser(filePath1);
        Scanner parser2 = getParser(filePath2);
        if (!AccountsOrdered(parser1)){ //Addresses unordered list or missing accounts
            System.out.println("\nAddress error in File 1");
            return false;
        }
        else if (!AccountsOrdered(parser2)){ //Addresses unordered list or missing accounts
            System.out.println("\nAddress error in File 2");
            return false;
        }
        else if (!checkExtraLines(filePath1, filePath2)){ //Addresses Additional Accounts in 1 file
            System.out.println("\nAddress error in respective File");
            return false;
        }
        return true;
    }

    //Helper function for checkExtraLines
    public static int countLines(String file){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int count = 0;
            while (reader.readLine() != null) count++;
            return count;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Get the writer to write to the output file
    public static PrintWriter getWriter (String outputPath){
        File file = null;
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new FileWriter(outputPath));
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return writer;
    }
    /*
    Called after all checks are complete
    And all input files pass the tests
    Compares the 2 files for mismatch in accounts
     */
    public static void compareLines (String filePath1, String filePath2, PrintWriter writer){
        Scanner parser1 = getParser(filePath1);
        Scanner parser2 = getParser(filePath2);
        while (parser1.hasNextLine()){
            String line1 = parser1.nextLine();
            String line3 = parser2.nextLine();
            if (!line1.equals(line3)){
                writer.println(line1);
                writer.println(line3);
                writer.flush();
            }
        }
    }
}

/*
First CSV Pathname: /Users/keshavnatarajan/Documents/SUTD/Term 5/Software Construction/ESC Mini Campaign/Week8/sample_file_1.csv
Second CSV Pathname: /Users/keshavnatarajan/Documents/SUTD/Term 5/Software Construction/ESC Mini Campaign/Week8/sample_file_3.csv
Output CSV Pathname: /Users/keshavnatarajan/Documents/SUTD/Term 5/Software Construction/ESC Mini Campaign/Week8/output_file.csv
 */