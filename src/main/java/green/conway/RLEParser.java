package green.conway;

public class RLEParser {

    public RLEParser() {

    }

    public Grid parse(String rle) {
        String[] lines = rle.split("\n");

        //move past comments to xy
        String xyLine = lines[0];
        int i = 0;
        while(xyLine.startsWith("#")) {
            xyLine = lines[++i];
        }

        //extract width and height
        xyLine = xyLine.replaceAll(",", "");
        String[] xyCoords = xyLine.split(" ");
        int width = Integer.parseInt(xyCoords[2]);
        int height = Integer.parseInt(xyCoords[5]);

        //create grid
        Grid grid = new Grid(height, width);

        //extract each cell
        String[] linesByX = lines[++i].split("\\$");

        //parse cells lines
        for (int y = 0; y < linesByX.length; y++) {
            int x = 0;
            //parse each line across
            for (int charAtK = 0; charAtK < linesByX[y].length(); charAtK++) {
                char c = linesByX[y].charAt(charAtK);
                if(c == 'o') {
                    grid.setAlive(y, x);
                    x++;
                }
                else if(c == 'b') {
                    x++;
                }
                else {
                    int kk = charAtK;
                    //if number
                    while(Character.isDigit(c) && kk+1 < linesByX[y].length()) {
                        c = linesByX[y].charAt(++kk);

                    }
                    if(charAtK < kk) {
                        int num = Integer.parseInt(linesByX[y].substring(charAtK, kk));
                        char cc = linesByX[y].charAt(kk);
                        if(cc == 'o') {
                            for (int j = 0; j < num; j++) {
                                grid.setAlive(y, x++);
                            }
                        }
                        else {
                            x += num;
                        }
                        charAtK = kk;
                    }



                }


            }
        }


        return grid;
    }

  /*  private String parseComment(String str) {
        if(str.startsWith("#C")) {}
    }*/

}
