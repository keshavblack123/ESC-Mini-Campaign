package com.example.week8;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CSVCompare {

    public static void main(String[] args) {
        String file1Path = "<Pathname to first CSV File>";
        String file3Path = "<Pathname to second CSV File>";
        String outputFile = "<Pathname to output CSV File>";

        Scanner parser1 = null;
        try {
            parser1 = new Scanner(new File(file1Path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner parser3 = null;
        try {
            parser3 = new Scanner(new File(file3Path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        File file = null;
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new FileWriter(outputFile));
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (parser1.hasNextLine()){
            String line1 = parser1.nextLine();
            String line3 = parser3.nextLine();
            if (!line1.equals(line3)){
                writer.println(line1);
                writer.println(line3);
                writer.flush();

            }
        }
    }
}