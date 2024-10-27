package green.conway;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LifeComponent extends JComponent {
   public Grid grid;
   public Timer timer;
   private static final int cellSize = 10;

   public LifeComponent(Grid grid) {
       this.grid = grid;
  }

  public int getCellSize() {
       return cellSize;
  }

   public void clearGrid() {
       grid.clearGrid();
   }

   public void resetGrid(Grid grid) {
       this.grid = grid;
   }

   @Override
    protected void paintComponent(Graphics g) {
       super.paintComponent(g);

       for (int y = 0; y < grid.getHeight(); y++) {
           for (int x = 0; x < grid.getWidth(); x++) {
               if (grid.isAlive(y, x)) {
                   g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
               }
           }
       }
   }
}
