package green.conway;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LifeControllerTest {

    @Test
    public void toggleCellOn() {
        LifeComponent view = mock();
        LifeFrame model = mock();
        Grid grid = mock();

        doReturn(grid).when(view).getGrid();
        doReturn(10).when(view).getCellSize();
        doReturn(100).when(model).getWidth();
        doReturn(100).when(model).getHeight();

        LifeController controller = new LifeController(model, view);
        controller.toggleCell(50, 100);

        verify(grid).isAlive(5, 10);
        verify(view).repaint();
    }

    @Test
    public void toggleCellOff() {
        LifeComponent view = mock();
        LifeFrame model = mock();
        Grid grid = mock();

        doReturn(grid).when(view).getGrid();
        doReturn(10).when(view).getCellSize();
        doReturn(100).when(model).getWidth();
        doReturn(100).when(model).getHeight();

        doReturn(true).when(grid).isAlive(5, 10);

        LifeController controller = new LifeController(model, view);
        controller.toggleCell(50, 100);

        verify(grid).kill(5, 10);
        verify(view).repaint();
    }

    @Test
    public void paste() throws IOException {
        LifeComponent view = mock();
        LifeFrame model = mock();
        LifeController controller = new LifeController(model, view);
        TextProcessor tp = new TextProcessor();

        URL url = new URL("https://conwaylife.com/patterns/acorn.rle");
        String actual = tp.urlToString(url);
        controller.paste("https://conwaylife.com/patterns/acorn.rle");
        verify(model).resetGridViaParser(actual);


    }

}
