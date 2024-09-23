package green.conway;

public class RleParser {

    private final int xpos = 2;
    private final int ypos = 5;
    private final int beginComment = 3;

    public RleParser() {

    }

    public Grid parse(String rle) {
        String[] lines = rle.split("\n");

        int currParseIx = findParseIx(lines);

        int[] dimensions = extractDim(lines[currParseIx]);

        Grid grid = new Grid(dimensions[0], dimensions[1]);

        String[] cellLinesByX = lines[++currParseIx].split("\\$");

        parseRow(cellLinesByX, grid);

        return grid;
    }

    private void parseRow(String[] cellLines, Grid grid) {
        for (int y = 0; y < cellLines.length; y++) {
            parseCell(cellLines[y], y, grid);
        }
    }

    private void parseCell(String cellLine, int y, Grid grid) {
            int x = 0;

            for (int charIx = 0; charIx < cellLine.length(); charIx++) {
                char cell = cellLine.charAt(charIx);

                if (cell == 'o') {
                    grid.setAlive(y, x);
                    x++;
                } else if (cell == 'b') {
                    x++;
                } else if (Character.isDigit(cell)) {
                    int num = parseNum(cellLine, charIx);

                    int nextCharIx = charIx + String.valueOf(num).length();
                    if (nextCharIx < cellLine.length()) {
                        charIx = nextCharIx;
                    }

                    if (cellLine.charAt(charIx) == 'o') {
                        for (int j = 0; j < num; j++) {
                            grid.setAlive(y, x++);
                        }
                    } else {
                        x += num;
                    }
                }
            }
        }

    private int parseNum(String cellLine, int startIx) {
        int endIx = startIx;
        while (endIx < cellLine.length() && Character.isDigit(cellLine.charAt(endIx))) {
            endIx++;
        }
        return Integer.parseInt(cellLine.substring(startIx, endIx));
    }

    private int[] extractDim(String line) {
        line = line.replaceAll(",", "");
        String[] xyCoords = line.split(" ");
        int width = Integer.parseInt(xyCoords[xpos]);
        int height = Integer.parseInt(xyCoords[ypos]);

        return new int[]{height, width};
    }

    private int findParseIx(String[] lines) {
        int i = 0;

        while (lines[i].startsWith("#")) {
            i++;
        }

        return i;
    }

    public String parseComment(String str) {
        StringBuilder ret = new StringBuilder();

        String[] lines = str.split("\n");

        int i = 0;

        while (lines[i].startsWith("#")) {
            ret.append(lines[i++].substring(beginComment) + "\n");
        }

        return ret.toString();
    }

}
