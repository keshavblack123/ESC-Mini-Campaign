package com.example.week8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVCompare {

    public static void main(String[] args) {
        String file1Path = "/Users/keshavnatarajan/Documents/GitHub/ESC-Mini-Campaign/Week8/sample_file_1.csv"; //<Pathname to first CSV File>
        String file3Path = "/Users/keshavnatarajan/Documents/GitHub/ESC-Mini-Campaign/Week8/sample_file_3.csv"; //<Pathname to second CSV File>
        String outputFile = "/Users/keshavnatarajan/Documents/GitHub/ESC-Mini-Campaign/Week8/output_file.csv"; //<Pathname to output CSV File>
        String unOrdered = "/Users/keshavnatarajan/Documents/GitHub/ESC-Mini-Campaign/TestFiles/testUnorderedAccounts.csv";

        Scanner parser1 = getParser(file1Path);
        Scanner parser3 = getParser(file3Path);

        if (cleanFiles(parser1, parser3)){
            PrintWriter writer = getWriter(outputFile);
            compareLines(parser1, parser3, writer);
            System.out.println("Passed!!\nOutput file produced at this address:\n" + outputFile);
        }
        else{
            System.out.println("Failed!\nOutput File not produced!");
        }
    }

    public static Scanner getParser (String filePath){
        Scanner parser = null;
        try {
            parser = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return parser;
    }

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

    public static Boolean checkExtraLines (Scanner parser1, Scanner parser2){
        ArrayList<String> mismatch = new ArrayList();
        int difference = countLines(parser1) - countLines(parser2);
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

    public static Boolean cleanFiles (Scanner parser1, Scanner parser2){
        if (!AccountsOrdered(parser1)){ //Addresses unordered list or missing accounts
            System.out.println("\nAddress error in File 1");
            return false;
        }
        else if (!AccountsOrdered(parser2)){ //Addresses unordered list or missing accounts
            System.out.println("\nAddress error in File 2");
            return false;
        }
        else if (!checkExtraLines(parser1, parser2)){ //Addresses Additional Accounts in 1 file
            System.out.println("\nAddress error in respective File");
            return false;
        }
        return true;
    }

    public static int countLines(Scanner parser){
        int count = 0;
        while(parser.hasNextLine()){
            count++;
        }
        return count;
    }

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

    public static void compareLines (Scanner parser1, Scanner parser2, PrintWriter writer){
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