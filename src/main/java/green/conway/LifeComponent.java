package green.conway;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LifeComponent extends JComponent {
   private final Grid grid;
   private Timer timer;
   private int cellSize = 10;

   public LifeComponent(Grid grid) {
       this.grid = grid;

       timer = new Timer(400, evt -> {
           grid.nextGen();
           repaint();
       });


   }

   @Override
    protected void paintComponent(Graphics g) {
       int[][] lifeGrid = grid.getGrid();

       super.paintComponent(g) ;
       for(int y = 0; y < lifeGrid.length; y++) {
           for(int x = 0; x < lifeGrid[y].length; x++) {
               if(grid.isAlive(y, x)) {
                   g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
               }
           }
       }
       timer.start();
   }
}
