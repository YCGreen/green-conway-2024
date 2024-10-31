package green.conway;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TextProcessorTest {

    @Test
    public void urlToString() throws IOException {
        TextProcessor tp = new TextProcessor();
        URL url = new URL("https://conwaylife.com/patterns/acorn.rle");
        String actual = tp.urlToString(url);

        actual = normalizeSeparators(actual);
        String expected = "#N Acorn\n"
                + "#O Charles Corderman\n"
                + "#C A methuselah with lifespan 5206.\n"
                + "#C www.conwaylife.com/wiki/index.php?title=Acorn\n"
                + "x = 7, y = 3, rule = B3/S23\n"
                + "bo5b$3bo3b$2o2b3o!";
        expected = normalizeSeparators(expected);

        assertEquals(expected, actual);

    }

    @Test
    public void fileToString() throws IOException {
        TextProcessor tp = new TextProcessor();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("rleTestFile.txt").getFile());
        String actual = tp.fileToString(file);

        actual = normalizeSeparators(actual);
        String expected = "#N Acorn\n"
                + "#O Charles Corderman\n"
                + "#C A methuselah with lifespan 5206.\n"
                + "#C www.conwaylife.com/wiki/index.php?title=Acorn\n"
                + "x = 7, y = 3, rule = B3/S23\n"
                + "bo5b$3bo3b$2o2b3o!";
        expected = normalizeSeparators(expected);

        assertEquals(expected, actual);

    }

    @Test
    public void isUrl() throws MalformedURLException {
        TextProcessor tp = new TextProcessor();
        String str = "https://conwaylife.com/patterns/acorn.rle";
        assertTrue(tp.isUrl(str));
    }

    private String normalizeSeparators(String str) {
        return str.replaceAll("\\R", "\n");
    }
}
