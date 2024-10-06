package green.conway;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextProcessorTest {

    @Test
    public void UrlToString() {
        try {
            TextProcessor tp = new TextProcessor();
            URL url = new URL("https://conwaylife.com/patterns/acorn.rle");
            String actual = tp.UrlToString(url);

            actual = normalizeSeparators(actual);
            String expected = "#N Acorn\n" +
                    "#O Charles Corderman\n" +
                    "#C A methuselah with lifespan 5206.\n" +
                    "#C www.conwaylife.com/wiki/index.php?title=Acorn\n" +
                    "x = 7, y = 3, rule = B3/S23\n" +
                    "bo5b$3bo3b$2o2b3o!";
            expected = normalizeSeparators(expected);

            assertEquals(expected, actual);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void FileToString() {
        try {
            TextProcessor tp = new TextProcessor();
            File file = new File("/Users/yaelgreen/IdeaProjects/green-conway-2024/Files/rleTestFile.txt");
            String actual = tp.FileToString(file);

            actual = normalizeSeparators(actual);
            String expected = "#N Acorn\n" +
                    "#O Charles Corderman\n" +
                    "#C A methuselah with lifespan 5206.\n" +
                    "#C www.conwaylife.com/wiki/index.php?title=Acorn\n" +
                    "x = 7, y = 3, rule = B3/S23\n" +
                    "bo5b$3bo3b$2o2b3o!";
            expected = normalizeSeparators(expected);

            assertEquals(expected, actual);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isUrl() {
        TextProcessor tp = new TextProcessor();
        Object str = "https://conwaylife.com/patterns/acorn.rle";
        assert(tp.isUrl(str));
    }

    private String normalizeSeparators(String str) {
        return str.replaceAll("\\R", "\n");
    }
}
