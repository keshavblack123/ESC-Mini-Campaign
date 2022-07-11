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
        String file1Path = "/Users/keshavnatarajan/Documents/SUTD/Term 5/Software Construction/ESC Mini Campaign/Week8/sample_file_1.csv";
        String file3Path = "/Users/keshavnatarajan/Documents/SUTD/Term 5/Software Construction/ESC Mini Campaign/Week8/sample_file_3.csv";
        String outputFile = "/Users/keshavnatarajan/Documents/SUTD/Term 5/Software Construction/ESC Mini Campaign/Week8/output_file.csv";

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