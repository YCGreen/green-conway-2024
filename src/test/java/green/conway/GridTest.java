package green.conway;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GridTest {

    @Test
    public void nextGen() {
        Grid grid = new Grid(3, 3);
        grid.setAlive(0, 1);
        grid.setAlive(1, 1);
        grid.setAlive(2, 1);

        grid.nextGen();

        String actual = grid.toString();

        assertEquals("000\n111\n000\n", actual);
    }

}
