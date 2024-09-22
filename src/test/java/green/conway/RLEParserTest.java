package green.conway;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RLEParserTest {

    @Test
    public void parseBlinker() {
        RLEParser parser = new RLEParser();
        Grid grid = parser.parse("#N Blinker\n" +
                "#O John Conway\n" +
                "#C A period 2 oscillator that is the smallest and most common oscillator.\n" +
                "#C www.conwaylife.com/wiki/index.php?title=Blinker\n" +
                "x = 3, y = 1, rule = B3/S23\n" +
                "3o!");
        String actual = grid.toString();

        assertEquals("111\n", actual);
    }

    @Test
    public void parseSwitch() {
        RLEParser parser = new RLEParser();
        Grid grid = parser.parse("#N Switch engine\n" +
                "#O Charles Corderman\n" +
                "#C A methuselah with lifespan 3911 that can be used to make c/12 diagonal puffers and spaceships.\n" +
                "#C www.conwaylife.com/wiki/index.php?title=Switch_engine\n" +
                "x = 6, y = 4, rule = B3/S23\n" +
                "bobo2b$o5b$bo2bob$3b3o!");
        String actual = grid.toString();

        assertEquals("010100\n100000\n010010\n000111\n", actual);
    }

    @Test
    public void parseGlider() {
        RLEParser parser = new RLEParser();
        Grid grid = parser.parse("#C This is a glider.\n" +
                "x = 3, y = 3\n" +
                "bo$2bo$3o!");
        String actual = grid.toString();

        assertEquals("010\n001\n111\n", actual);
    }

    @Test
    public void parseGosper() {
        RLEParser parser = new RLEParser();
        Grid grid = parser.parse("#N Gosper glider gun\n" +
                "#C This was the first gun discovered.\n" +
                "#C As its name suggests, it was discovered by Bill Gosper.\n" +
                "x = 36, y = 9, rule = B3/S23\n" +
                "24bo$22bobo$12b2o6b2o12b2o$11bo3bo4b2o12b2o$2o8bo5bo3b2o$2o8bo3bob2o4b" +
                "obo$10bo5bo7bo$11bo3bo$12b2o!");
        String actual = grid.toString();
                actual = actual.replaceAll("0", ".");
                actual = actual.replaceAll("1", "0");

        assertEquals(
                "........................O...........\n" +
                "......................O.O...........\n" +
                "............OO......OO............OO\n" +
                "...........O...O....OO............OO\n" +
                "OO........O.....O...OO..............\n" +
                "OO........O...O.OO....O.O...........\n" +
                "..........O.....O.......O...........\n" +
                "...........O...O....................\n" +
                "............OO......................\n", actual);
    }

    @Test
    public void parseHalfmax() {
        RLEParser parser = new RLEParser();
        Grid grid = parser.parse("#N Halfmax\n" +
                "#O Jason Summers\n" +
                "#C A half spacefiller found in 2005.\n" +
                "#C www.conwaylife.com/wiki/index.php?title=Halfmax\n" +
                "x = 65, y = 80, rule = b3/s23\n" +
                "5bobo49bobo5b$4bo2bo49bo2bo4b$3b2o55b2o3b$2bo59bo2b$b4o55b4ob$o4bo53bo" +
                "4bo$o2bo24b3o3b3o24bo2bo$o2bo24bo2bobo2bo24bo2bo$bo26bo7bo26bob$2b4obo" +
                "20bo7bo20bob4o2b$3bo3bo21bobobobo21bo3bo3b$4bo24bobobobo24bo4b$4bobo" +
                "23bo3bo23bobo4b$29b3ob3o29b$3b3o26bo26b3o3b$3b2o23b9o23b2o3b$3b3o21bo" +
                "9bo21b3o3b$26b13o26b$4bobo18bo13bo18bobo4b$4bo19b17o19bo4b$3bo3bo15bo" +
                "17bo15bo3bo3b$2b4obo14b21o14bob4o2b$bo19bo21bo19bob$o2bo16b25o16bo2bo$" +
                "o2bo15bo25bo15bo2bo$o4bo12b29o12bo4bo$b4o12bo29bo12b4ob$2bo13b33o13bo" +
                "2b$3b2o10bo33bo10b2o3b$4bo2bobo4b37o4bobo2bo4b$5bobo2bo2bo37bo2bo2bobo" +
                "5b$8bo3b20ob20o3bo8b$9bo21bobo21bo9b$10b21o3b21o10b2$8b21o3bo3b21o8b$" +
                "7bo21bobobobo21bo7b$6bo3b2o2bob2ob2ob2ob5obobob5ob2ob2ob2obo2b2o3bo6b$" +
                "6bo4bobo2b2o4b2o7bobo7b2o4b2o2bobo4bo6b$6bo8bo5bo5b3obobob3o5bo5bo8bo" +
                "6b$7b3o20b2ob2o20b3o7b$9bo17b2o3bo3b2o17bo9b$6b2o2bo3b2o11b4obob4o11b" +
                "2o3bo2b2o6b$4bo5b2obo17bobo17bob2o5bo4b$3bo6bo18bobobobo18bo6bo3b$3bo" +
                "4b2obo2bo10b2o2b3ob3o2b2o10bo2bob2o4bo3b$3b5o3b2o5bo6b2o2b2o3b2o2b2o6b" +
                "o5b2o3b5o3b$13b2o4bo25bo4b2o13b$10b2o3b2ob2obo7b3ob3o7bob2ob2o3b2o10b$" +
                "13bo4bobo23bobo4bo13b$9bo4b5obo4bo3b3ob3o3bo4bob5o4bo9b$8b2o2b2o4bo3b" +
                "3o3bo7bo3b3o3bo4b2o2b2o8b$9b2o3bo4b2o7b4ob4o7b2o4bo3b2o9b$10b2o8bo10bo" +
                "bo10bo8b2o10b$30b2ob2o30b$22bo3bob2obobob2obo3bo22b$21bobobobob2o3b2ob" +
                "obobobo21b$21bobobobo9bobobobo21b$22bobob3o7b3obobo22b$24bobo3b5o3bobo" +
                "24b$24b2o6bo6b2o24b2$27bo2bobobo2bo27b$27b11o27b2$29bo5bo29b$28bobo3bo" +
                "bo28b$28bob2ob2obo28b$26bobob2ob2obob2o25b$25bobo9bobo25b$25bo2b3o3b3o" +
                "28b$26b2o37b$29bo2bo2b2o28b$26b3obobobo2bo27b$26bo3bobobob2o27b$27bobo" +
                "4bo30b$28b2obo2bob2o27b$30bobobobo28b$30bobobobo28b$31b2ob2o!");
        String actual = grid.toString();
        actual = actual.replaceAll("0", ".");
        actual = actual.replaceAll("1", "0");

        String[] arr = actual.split("\n");

        for (int i = 0; i < arr.length; i++) {
            int last0Ix = arr[i].lastIndexOf('0');

            if (last0Ix != -1) {
               arr[i] = arr[i].substring(0, last0Ix + 1);
            }
            else {
                arr[i] = "";
            }
        }

        actual = String.join("\n", arr);



        assertEquals(".....O.O.................................................O.O\n" +
                "....O..O.................................................O..O\n" +
                "...OO.......................................................OO\n" +
                "..O...........................................................O\n" +
                ".OOOO.......................................................OOOO\n" +
                "O....O.....................................................O....O\n" +
                "O..O........................OOO...OOO........................O..O\n" +
                "O..O........................O..O.O..O........................O..O\n" +
                ".O..........................O.......O..........................O\n" +
                "..OOOO.O....................O.......O....................O.OOOO\n" +
                "...O...O.....................O.O.O.O.....................O...O\n" +
                "....O........................O.O.O.O........................O\n" +
                "....O.O.......................O...O.......................O.O\n" +
                ".............................OOO.OOO\n" +
                "...OOO..........................O..........................OOO\n" +
                "...OO.......................OOOOOOOOO.......................OO\n" +
                "...OOO.....................O.........O.....................OOO\n" +
                "..........................OOOOOOOOOOOOO\n" +
                "....O.O..................O.............O..................O.O\n" +
                "....O...................OOOOOOOOOOOOOOOOO...................O\n" +
                "...O...O...............O.................O...............O...O\n" +
                "..OOOO.O..............OOOOOOOOOOOOOOOOOOOOO..............O.OOOO\n" +
                ".O...................O.....................O...................O\n" +
                "O..O................OOOOOOOOOOOOOOOOOOOOOOOOO................O..O\n" +
                "O..O...............O.........................O...............O..O\n" +
                "O....O............OOOOOOOOOOOOOOOOOOOOOOOOOOOOO............O....O\n" +
                ".OOOO............O.............................O............OOOO\n" +
                "..O.............OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO.............O\n" +
                "...OO..........O.................................O..........OO\n" +
                "....O..O.O....OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO....O.O..O\n" +
                ".....O.O..O..O.....................................O..O..O.O\n" +
                "........O...OOOOOOOOOOOOOOOOOOOO.OOOOOOOOOOOOOOOOOOOO...O\n" +
                ".........O.....................O.O.....................O\n" +
                "..........OOOOOOOOOOOOOOOOOOOOO...OOOOOOOOOOOOOOOOOOOOO\n" +
                "........OOOOOOOOOOOOOOOOOOOOO...O...OOOOOOOOOOOOOOOOOOOOO\n" +
                ".......O.....................O.O.O.O.....................O\n" +
                "......O...OO..O.OO.OO.OO.OOOOO.O.O.OOOOO.OO.OO.OO.O..OO...O\n" +
                "......O....O.O..OO....OO.......O.O.......OO....OO..O.O....O\n" +
                "......O........O.....O.....OOO.O.O.OOO.....O.....O........O\n" +
                ".......OOO....................OO.OO....................OOO\n" +
                ".........O.................OO...O...OO.................O\n" +
                "......OO..O...OO...........OOOO.O.OOOO...........OO...O..OO\n" +
                "....O.....OO.O.................O.O.................O.OO.....O\n" +
                "...O......O..................O.O.O.O..................O......O\n" +
                "...O....OO.O..O..........OO..OOO.OOO..OO..........O..O.OO....O\n" +
                "...OOOOO...OO.....O......OO..OO...OO..OO......O.....OO...OOOOO\n" +
                ".............OO....O.........................O....OO\n" +
                "..........OO...OO.OO.O.......OOO.OOO.......O.OO.OO...OO\n" +
                ".............O....O.O.......................O.O....O\n" +
                ".........O....OOOOO.O....O...OOO.OOO...O....O.OOOOO....O\n" +
                "........OO..OO....O...OOO...O.......O...OOO...O....OO..OO\n" +
                ".........OO...O....OO.......OOOO.OOOO.......OO....O...OO\n" +
                "..........OO........O..........O.O..........O........OO\n" +
                "..............................OO.OO\n" +
                "......................O...O.OO.O.O.OO.O...O\n" +
                ".....................O.O.O.O.OO...OO.O.O.O.O\n" +
                ".....................O.O.O.O.........O.O.O.O\n" +
                "......................O.O.OOO.......OOO.O.O\n" +
                "........................O.O...OOOOO...O.O\n" +
                "........................OO......O......OO\n" +
                "...........................O..O.O.O..O\n" +
                "...........................OOOOOOOOOOO\n" +
                ".............................O.....O\n" +
                "............................O.O...O.O\n" +
                "............................O.OO.OO.O\n" +
                "..........................O.O.OO.OO.O.OO\n" +
                ".........................O.O.........O.O\n" +
                ".........................O..OOO...OOO\n" +
                "..........................OO\n" +
                ".............................O..O..OO\n" +
                "..........................OOO.O.O.O..O\n" +
                "..........................O...O.O.O.OO\n" +
                "...........................O.O....O\n" +
                "............................OO.O..O.OO\n" +
                "..............................O.O.O.O\n" +
                "..............................O.O.O.O\n" +
                "...............................OO.OO\n" +
                "\n" +
                "\n", actual);
    }

}
