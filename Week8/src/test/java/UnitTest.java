import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.example.week8.CSVCompare;
import org.junit.Test;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class UnitTest {
    Path path = Paths.get("TestFiles/UnitTests/");
    String globalPath = path.toAbsolutePath().toString();

    @Test
    public void testGetParser(){
        System.out.println("Testing");
        String path = globalPath + "/testGetParser.csv";
        Scanner parser = CSVCompare.getParser(path);
        //Fails if exception is thrown (file does not exist)
    }

    @Test
    public void testGetWriter(){
        String path = globalPath + "/outputFile.csv";
        PrintWriter writer = CSVCompare.getWriter(path);
        //Fails if exception is thrown
    }

    @Test
    public void testCheckOrderedAccounts(){
        String path = globalPath + "/testOrderedAccounts.csv";
        Scanner parser = CSVCompare.getParser(path);
        Boolean out = CSVCompare.AccountsOrdered(parser);
        assertTrue(out);
    }

    @Test
    public void testCheckUnorderedAccounts(){
        String path = globalPath + "/testUnorderedAccounts.csv";
        Scanner parser = CSVCompare.getParser(path);
        Boolean out = CSVCompare.AccountsOrdered(parser);
        assertFalse(out);
    }

    @Test
    public void testCheckExtraLines1(){
        String path1 = globalPath + "/test1AdditionalAccount/additionalAccount1.csv";
        String path2 = globalPath + "/test1AdditionalAccount/additionalAccount2.csv";
        Boolean out = CSVCompare.checkExtraLines(path1, path2);
        assertFalse(out);
    }

    @Test
    public void testCheckExtraLinesMany(){
        String path1 = globalPath + "/testManyAdditionalAccount/manyAdditions1.csv";
        String path2 = globalPath + "/testManyAdditionalAccount/manyAdditions2.csv";
        Boolean out = CSVCompare.checkExtraLines(path1, path2);
        assertFalse(out);
    }

    @Test
    public void testCheckNoExtraLines(){
        String path1 = globalPath + "/testNoExtraAccounts/NoExtra1.csv";
        String path2 = globalPath + "/testNoExtraAccounts/NoExtra2.csv";
        Boolean out = CSVCompare.checkExtraLines(path1, path2);
        assertTrue(out);
    }

    @Test
    public void testCountLine(){
        String path = globalPath + "/testCountLine.csv";
        int lines = CSVCompare.countLines(path);
        assertTrue(lines == 25);
    }
}
