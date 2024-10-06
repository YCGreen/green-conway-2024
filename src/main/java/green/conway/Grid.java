package green.conway;

import java.util.Arrays;

public class Grid {
    private int[][] grid;

    public Grid(int height, int width) {
        grid = new int[height][width];
    }

    public Grid(int[][] arr) {
        grid = arr;
    }

    public int[][] getGridAsArr() {
        return grid;
    }

    public int getHeight() {
        return grid.length > 0 ? grid.length : 0;
    }

    public int getWidth() {
        return (grid.length > 0 && grid[0].length > 0) ? grid[0].length : 0;

    }

    public void setAlive(int y, int x) {
        if (inBounds(y, x)) {
            grid[y][x] = 1;
        }
    }

    public void nextGen() {
        int[][] copy = copyGrid(grid);

        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
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
        return y >= 0 && y < getHeight() && x >= 0 && x < getWidth();
    }

    private boolean turnAlive(int y, int x, int adj) {
        return adj == 3 || (isAlive(y, x) && adj == 2);
    }

    private int[][] copyGrid(int[][] grid) {
        int[][] copy = new int[getHeight()][getWidth()];
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                copy[y][x] = grid[y][x];
            }
        }

        return copy;
    }

    public void centerGrid(Grid newGrid) {
        int startX = (this.getWidth() - newGrid.getWidth()) / 2;
        int startY = (this.getHeight() - newGrid.getHeight()) / 2;

        copyGrid(newGrid, startY, startX);
    }

    private void copyGrid(Grid orig, int startY, int startX) {
        int endHeight = Math.min(orig.getHeight(), getHeight() - startY);
        int endWidth = Math.min(orig.getWidth(), getWidth() - startX);

        for (int y = 0; y < endHeight; y++) {
            for (int x = 0; x < endWidth; x++) {
                if(orig.isAlive(y, x)) {
                    setAlive(y + startY, x + startX);
                }
            }
        }
}


    public void clearGrid() {
        for (int y = 0; y < grid.length; y++) {
            Arrays.fill(grid[y], 0);
        }
    }

    public void kill(int y, int x) {
        if (inBounds(y, x)) {
            grid[y][x] = 0;
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                builder.append(grid[i][j]);
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
