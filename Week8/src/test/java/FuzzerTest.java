import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.example.week8.CSVCompare;
import org.junit.Test;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class FuzzerTest {
    Path path = Paths.get("TestFiles/Fuzzing/");
    String globalPath = path.toAbsolutePath().toString();

    /*
    currency -> AUD, USD, INR, CAD, SGD, CHF, GBP, HKD, SEK, GBP
    type -> SAVINGS, CURRENT
    balance -> 6 digit number
     */

    String balance;

    @Test
    public void testFlippingBit(){
        String path1 = globalPath + "/flippingBit/sample_file_1.csv";
        String path2 = globalPath + "/flippingBit/sample_file_3.csv";
        String fuzzedFile = globalPath + "/flippingBit/fuzzed.csv";
        String outPath = globalPath + "/flippingBit/outputFile.csv";

        Scanner parser = CSVCompare.getParser(path1);
        PrintWriter writer = CSVCompare.getWriter(fuzzedFile);
        int n = CSVCompare.countLines(path1);
        int lineNum = new Random().nextInt(n);
        int count = 0;
        // Preparing the fuzzed file, generating a random replacement token
        while (parser.hasNextLine()){
            String line = parser.nextLine();
            if (count == lineNum) {
                String[] lineArr = line.split(",");
                String replace = "";
                int cat = new Random().nextInt(4);
                if (cat == 0) {replace = getRandomAccount();}
                else if (cat == 1){replace = getRandomCurrency();}
                else if (cat == 2){replace = getRandomType();}
                else {replace = getRandomBalance();}
                replace = '"' + replace + '"';
                lineArr[cat + 1] = replace;
                line = String.join(",", lineArr);
            }
            writer.println(line);
            writer.flush();
            count ++;
        }
        PrintWriter outWriter = CSVCompare.getWriter(outPath);
        assertTrue(CSVCompare.cleanFiles(fuzzedFile, path2)); //Expected output to pass
        CSVCompare.compareLines(fuzzedFile, path2, outWriter);
        //Expected output to have 2 lines of 1 mismatched account where you can observe the random replacement generated
    }

    @Test
    public void testTrim(){
        String path1 = globalPath + "/trimming/sample_file_1.csv";
        String path2 = globalPath + "/trimming/sample_file_3.csv";
        String fuzzedFile = globalPath + "/trimming/fuzzed.csv";
        String outPath = globalPath + "/trimming/outputFile.csv";

        Scanner parser = CSVCompare.getParser(path1);
        PrintWriter writer = CSVCompare.getWriter(fuzzedFile);
        int n = CSVCompare.countLines(path1);
        int lineNum = new Random().nextInt(n);
        int i = 0;
        while (parser.hasNextLine()){
            String line = parser.nextLine();
            writer.println(line);
            writer.flush();
            if (i == lineNum){
                break;
            }
            i++;
        }
        PrintWriter outWriter = CSVCompare.getWriter(outPath);
        assertFalse(CSVCompare.cleanFiles(fuzzedFile, path2)); //Expected output to fail
    }

    @Test
    public void testInsert() {
        String path1 = globalPath + "/insertChar/sample_file_1.csv";
        String path2 = globalPath + "/insertChar/sample_file_3.csv";
        String fuzzedFile = globalPath + "/insertChar/fuzzed.csv";
        String outPath = globalPath + "/insertChar/outputFile.csv";

        Scanner parser = CSVCompare.getParser(path1);
        PrintWriter writer = CSVCompare.getWriter(fuzzedFile);
        Integer n = CSVCompare.countLines(path1);
        String id = '"' + "ID" + n + '"';
        String newAccount = '"' + getRandomAccount() + '"';
        String newCurrency = '"' + getRandomCurrency() + '"';
        String newType = '"' + getRandomType() + '"';
        String newBalance = '"' + getRandomBalance() + '"';
        String[] newLineArr = {id, newAccount, newCurrency, newType, newBalance};
        String newLine = String.join(",", newLineArr);
        while (parser.hasNextLine()){
            String line = parser.nextLine();
            writer.println(line);
            writer.flush();
        }
        writer.println(newLine);
        writer.flush();

        PrintWriter outWriter = CSVCompare.getWriter(outPath);
        assertFalse(CSVCompare.cleanFiles(fuzzedFile, path2)); //Expected output to fail
    }

    @Test
    public void testSwap() {
        String path1 = globalPath + "/swapChar/sample_file_1.csv";
        String path2 = globalPath + "/swapChar/sample_file_3.csv";
        String fuzzedFile = globalPath + "/swapChar/fuzzed.csv";
        String outPath = globalPath + "/swapChar/outputFile.csv";

        Scanner parser = CSVCompare.getParser(path1);
        PrintWriter writer = CSVCompare.getWriter(fuzzedFile);
        int n = CSVCompare.countLines(path1);
        Random r = new Random();
        int lineNum1 = r.nextInt(n);
        int lineNum2 = r.nextInt(n);
        int smaller = 1;
        int larger = 2;
        System.out.println(lineNum1);
        System.out.println(lineNum2);
        if (lineNum1 < lineNum2){
            smaller = lineNum1;
            larger = lineNum2;
        }
        else{
            smaller = lineNum2;
            larger = lineNum1;
        }
        String temp = "";
        int count = 0;
        while (parser.hasNextLine()) {
            if (count == smaller) {
                temp = parser.nextLine();
            }
            else if (count == larger){
                String line = parser.nextLine();
                writer.println(line);
                writer.println(temp);
                writer.flush();
            }
            else {
                String line = parser.nextLine();
                writer.println(line);
                writer.flush();
            }
            count ++;
        }

        PrintWriter outWriter = CSVCompare.getWriter(outPath);
        assertFalse(CSVCompare.cleanFiles(fuzzedFile, path2)); //Expected output to fail
    }



    public String getRandomCurrency(){
        String[] currency = {"AUD", "USD", "INR", "CAD", "SGD", "CHF", "GBP", "HKD", "SEK", "GBP"};
        int rand = new Random().nextInt(currency.length);
        return currency[rand];
    }

    public String getRandomType(){
        String[] type = {"SAVINGS", "CURRENT"};
        int rand = new Random().nextInt(type.length);
        return type[rand];
    }

    public String getRandomBalance(){
        Random r = new Random();
        String out = "";
        for (int i  = 0; i < 6; i++){
            Integer rand = new Random().nextInt(10);
            out = out + rand;
        }
        return out;
    }

    public String getRandomAccount(){
        return "BOS" + getRandomBalance();
    }



}
