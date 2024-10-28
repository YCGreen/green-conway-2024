package green.conway;

import org.junit.jupiter.api.Test;

import java.io.File;

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
    public void paste() {
        LifeComponent view = mock();
        LifeFrame model = mock();
        LifeController controller = new LifeController(model, view);
        TextProcessor tp = new TextProcessor();

        try {
            File file = new File("/Users/yaelgreen/IdeaProjects/green-conway-2024/Files/rleTestFile.txt");
            String actual = tp.fileToString(file);
            controller.paste(file);
            verify(model).resetGridViaParser(actual);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
