package green.conway;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LifeComponent extends JComponent {
   public Grid grid;
   public Timer timer;
   private static final int cellSize = 10;

   public LifeComponent(Grid grid) {
       this.grid = grid;
       startTimer();
       cellsClickable();
  }

   public void clearGrid() {
       grid.clearGrid();
   }

   public void resetGrid(Grid grid) {
       timer.stop();
       this.grid = grid;
       startTimer();
       cellsClickable();
   }

   private void startTimer() {
       timer = new Timer(400, evt -> {
           grid.nextGen();
           repaint();
       });
   }

   private void cellsClickable() {
       addMouseListener(new MouseListener() {
           @Override
           public void mouseClicked(MouseEvent e) {
               int yPos = e.getY() / cellSize;
               int xPos = e.getX() / cellSize;
               if (grid.isAlive(yPos, xPos)) {
                   grid.kill(yPos, xPos);
               } else {
                   grid.setAlive(yPos, xPos);
               }
               repaint();
           }

           @Override
           public void mousePressed(MouseEvent e) {

           }

           @Override
           public void mouseReleased(MouseEvent e) {

           }

           @Override
           public void mouseEntered(MouseEvent e) {

           }

           @Override
           public void mouseExited(MouseEvent e) {

           }
       });
   }

   @Override
    protected void paintComponent(Graphics g) {
       super.paintComponent(g);
       int[][] lifeGrid = grid.getGrid();

       for (int y = 0; y < lifeGrid.length; y++) {
           for (int x = 0; x < lifeGrid[y].length; x++) {
               if (grid.isAlive(y, x)) {
                   g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
               }
           }
       }
   }
}
