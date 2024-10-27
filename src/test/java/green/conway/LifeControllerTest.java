package green.conway;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class LifeControllerTest {

    @Test
    public void toggleCellOff() {
        LifeComponent view = mock();
        LifeFrame model = mock();

        LifeController controller = new LifeController(model, view);
        doReturn(10).when(view).getCellSize();
        doReturn(100).when(model).getWidth();
        doReturn(100).when(model).getHeight();

        controller.toggleCell(50, 100);

        verify(view).grid.isAlive(5, 10);
        verify(view).repaint();

    }

}
