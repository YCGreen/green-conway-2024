package green.conway;

public class Grid {
    private int[][] grid;
    private int height;
    private int width;

    public Grid(int height, int width) {
        grid = new int[height][width];
        this.height = height;
        this.width = width;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setAlive(int y, int x) {
        if (inBounds(y, x)) {
            grid[y][x] = 1;
        }
    }

    public void nextGen() {
        int[][] copy = copyGrid(grid);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int liveAdj = checkAdj(y, x);
                copy[y][x] = turnAlive(y, x, liveAdj) ? 1 : 0;
            }
        }

        grid = copy;
    }

    public boolean isAlive(int y, int x) {
        return inBounds(y, x) && grid[y][x] == 1;
    }

    private int checkAdj(int tgtY, int tgtX) {
        int liveCells = -grid[tgtY][tgtX];

        for (int y = tgtY - 1; y <= tgtY + 1; y++) {
            for (int x = tgtX - 1; x <= tgtX + 1; x++) {
                if (inBounds(y, x) && isAlive(y, x)) {
                    liveCells++;
                }
            }
        }

        return liveCells;
    }

    private boolean inBounds(int y, int x) {
        return y >= 0 && y < height && x >= 0 && x < width;
    }

    private boolean turnAlive(int y, int x, int adj) {
        return adj == 3 || (isAlive(y, x) && adj == 2);
    }

    private int[][] copyGrid(int[][] grid) {
        int[][] copy = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                copy[y][x] = grid[y][x];
            }
        }

        return copy;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                builder.append(grid[i][j]);
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
