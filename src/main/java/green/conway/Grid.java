package green.conway;

public class Grid {
    private int[][] grid;
    private int height;
    private int width;

    public Grid(int height, int width) {
        grid = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = 0;
            }
        }
        this.height = height;
        this.width = width;
    }

    public void setAlive(int cellHeight, int cellWidth) {
        grid[cellHeight][cellWidth] = 1;
    }

    public void nextGen() {
        int[][] copy = copyGrid(grid);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int liveAdj = checkAdj(i, j);
                copy[i][j] = turnAlive(grid[i][j], liveAdj) ? 1 : 0;
            }
        }

        grid = copy;
    }

    private boolean isAlive(int cell) {
        return cell == 1;
    }

    private int checkAdj(int cellHeight, int cellWidth) {
        int liveCells = -grid[cellHeight][cellWidth];

        for (int i = cellHeight - 1; i <= cellHeight + 1; i++) {
            for (int j = cellWidth - 1; j <= cellWidth + 1; j++) {
                if (i >= 0 && i < height && j >= 0 && j < width) {
                    if (isAlive(grid[i][j])) {
                        liveCells++;
                    }
                }
            }
        }

        return liveCells;
    }

    private boolean turnAlive(int cell, int adj) {
        return (isAlive(cell) && (adj == 2 || adj == 3)) || (!isAlive(cell) && adj == 3);
    }

    private int[][] copyGrid(int[][] grid) {
        int[][] copy = new int[height][width];
        for (int i = 0; i< height; i++) {
            for (int j = 0; j < width; j++) {
                copy[i][j] = grid[i][j];
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
