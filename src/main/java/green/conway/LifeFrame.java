package green.conway;

import javax.swing.*;
import java.awt.*;

public class LifeFrame extends JFrame {

    public LifeFrame() {
        setSize(800, 600);
        setTitle("Conway's Game of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel lifePanel = new JPanel();
        lifePanel.setLayout(new BorderLayout());
        setContentPane(lifePanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        lifePanel.add(buttonPanel, BorderLayout.EAST);

        Grid grid = new Grid(20, 20);

        LifeComponent lifeComponent = new LifeComponent(grid);
        lifePanel.add(lifeComponent, BorderLayout.CENTER);

        JButton blinkerButton = new JButton("Blinker");
        buttonPanel.add(blinkerButton);
        blinkerButton.addActionListener(evt -> blinker(grid));

        JButton toadButton = new JButton("Toad");
        buttonPanel.add(toadButton);
        toadButton.addActionListener(evt -> toad(grid));

        JButton beaconButton = new JButton("Beacon");
        buttonPanel.add(beaconButton);
        beaconButton.addActionListener(evt -> beacon(grid));



    }

    //need to clear grid before these
    //automate based on size of grid (no hardcoded numbers)
    //potential: draw own squares then press start, pause nextgen until button clicked

    private void blinker(Grid grid) {
        grid.setAlive(9, 10);
        grid.setAlive(10, 10);
        grid.setAlive(11, 10);
    }

    private void toad(Grid grid) {

    }

    private void beacon(Grid grid) {
        grid.setAlive(9, 10);
        grid.setAlive(9, 11);
        grid.setAlive(10, 10);
        grid.setAlive(10, 11);
        grid.setAlive(8, 12);
        grid.setAlive(8, 13);
        grid.setAlive(7, 12);
        grid.setAlive(7, 13);
    }

    public static void main(String[] args) {
        new LifeFrame().setVisible(true);
    }
}


