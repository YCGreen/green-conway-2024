package green.conway;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RleParserTest {

    @Test
    public void parseBlinker() {
        RleParser parser = new RleParser();
        Grid grid = parser.parse("#N Blinker\n"
                + "#O John Conway\n"
                + "#C A period 2 oscillator that is the smallest and most common oscillator.\n"
                + "#C www.conwaylife.com/wiki/index.php?title=Blinker\n"
                + "x = 3, y = 1, rule = B3/S23\n"
                + "3o!");
        String actual = grid.toString();

        assertEquals("111\n", actual);
    }

    @Test
    public void parseSwitch() {
        RleParser parser = new RleParser();
        Grid grid = parser.parse("#N Switch engine\n"
                + "#O Charles Corderman\n"
                + "#C A methuselah with lifespan 3911 that can be used to make c/12 diagonal puffers and spaceships.\n"
                + "#C www.conwaylife.com/wiki/index.php?title=Switch_engine\n"
                + "x = 6, y = 4, rule = B3/S23\n"
                + "bobo2b$o5b$bo2bob$3b3o!");
        String actual = grid.toString();

        assertEquals("010100\n100000\n010010\n000111\n", actual);
    }

    @Test
    public void parseGlider() {
        RleParser parser = new RleParser();
        Grid grid = parser.parse("#C This is a glider.\n"
                + "x = 3, y = 3\n"
                + "bo$2bo$3o!");
        String actual = grid.toString();

        assertEquals("010\n001\n111\n", actual);
    }

    @Test
    public void parsePi() {
        RleParser parser = new RleParser();
        Grid grid = parser.parse("#N Pi-heptomino\n"
                + "#C One of 108 heptominoes.\n"
                + "#C www.conwaylife.com/wiki/index.php?title=Pi-heptomino\n"
                + "x = 3, y = 3, rule = B3/S23\n"
                + "3o$obo$obo!");
        String actual = grid.toString();
        assertEquals("111\n101\n101\n", actual);
    }

    @Test
    public void parseComment() {
        RleParser parser = new RleParser();
        String actual = parser.parseComment("#N Switch engine\n"
                + "#O Charles Corderman\n"
                + "#C A methuselah with lifespan 3911 that can be used to make c/12 diagonal puffers and spaceships.\n"
                + "#C www.conwaylife.com/wiki/index.php?title=Switch_engine\n"
                + "x = 6, y = 4, rule = B3/S23\n"
                + "bobo2b$o5b$bo2bob$3b3o!");

        assertEquals("Switch engine\nCharles Corderman\n"
                + "A methuselah with lifespan 3911 that can be used to "
                + "make c/12 diagonal puffers and spaceships.\n"
                + "www.conwaylife.com/wiki/index.php?title=Switch_engine\n", actual);
    }
}