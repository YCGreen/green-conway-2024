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

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        lifePanel.add(sidePanel, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        sidePanel.add(buttonPanel);

        Grid grid = new Grid(getHeight(), getWidth());

        LifeComponent lifeComponent = new LifeComponent(grid);
        lifePanel.add(lifeComponent, BorderLayout.CENTER);

        JButton blinkerButton = new JButton("Blinker");
        buttonPanel.add(blinkerButton);
        blinkerButton.addActionListener(evt -> blinker(grid));

        JButton GosperButton = new JButton("Gosper Glider Gun");
        buttonPanel.add(GosperButton);
        GosperButton.addActionListener(evt -> Gosper(grid));

        JButton beaconButton = new JButton("Beacon");
        buttonPanel.add(beaconButton);
        beaconButton.addActionListener(evt -> beacon(grid));

        JButton clearButton = new JButton("Clear");
        buttonPanel.add(clearButton);
        clearButton.addActionListener(evt -> clearGrid(grid));

        JPanel textPanel = new JPanel();
        JTextArea textInput = new JTextArea(20, 20);
        textInput.setLineWrap(true);
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(evt -> resetGridViaParser(grid, textInput.getText()));

        textPanel.add(textInput);
        textPanel.add(enterButton);
        sidePanel.add(textPanel);

        JPanel playPanel = new JPanel();
        playPanel.setLayout(new BoxLayout(playPanel, BoxLayout.LINE_AXIS));
        lifePanel.add(playPanel, BorderLayout.SOUTH);

        JButton pauseButton = new JButton("Pause");
        playPanel.add(pauseButton);
        pauseButton.addActionListener(evt -> lifeComponent.timer.stop());

        JButton playButton = new JButton("Play");
        playPanel.add(playButton);
        playButton.addActionListener(evt -> lifeComponent.timer.start());


    }

    private void blinker(Grid grid) {
        String str = "#C this is a blinker\n" +
                "x = 3, y = 3\n" +
                "3b$3o$3b";
        resetGridViaParser(grid, str);
    }

    private void Gosper(Grid grid) {
        String str = "#N Gosper glider gun\n" +
                        "#C This was the first gun discovered.\n" +
                        "#C As its name suggests, it was discovered by Bill Gosper.\n" +
                        "x = 36, y = 9, rule = B3/S23\n" +
                        "24bo$22bobo$12b2o6b2o12b2o$11bo3bo4b2o12b2o$2o8bo5bo3b2o$2o8bo3bob2o4b" +
                        "obo$10bo5bo7bo$11bo3bo$12b2o!";
        resetGridViaParser(grid, str);
    }

    private void beacon(Grid grid) {
        String str = "#N Beacon\n" +
                "#O John Conway\n" +
                "#C A common period 2 oscillator.\n" +
                "#C www.conwaylife.com/wiki/index.php?title=Beacon\n" +
                "x = 4, y = 4, rule = B3/S23\n" +
                "2o2b$o3b$3bo$2b2o!";
        resetGridViaParser(grid, str);
    }

    private static void resetGridViaParser(Grid grid, String rle) {
        RLEParser parser = new RLEParser();
        Grid newGrid = parser.parse(rle);

        //TODO: maybe create iterator for grid so don't have to convert to int[][]
        int[][] gridAsArr = newGrid.getGrid();
        for (int i = 0; i < gridAsArr.length; i++) {
            for (int j = 0; j < gridAsArr[i].length; j++) {
                if(gridAsArr[i][j] == 1) {
                    grid.setAlive(i,j);
                }
            }
        }
    }

    private void clearGrid(Grid grid) {
        grid.clearGrid();
    }

    public static void main(String[] args) {
        new LifeFrame().setVisible(true);
    }
}


