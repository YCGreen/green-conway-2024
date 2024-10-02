package green.conway;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class LifeFrame extends JFrame {

    public Grid grid;
    LifeComponent lifeComponent;
    RleParser parser = new RleParser();
    JTextArea descriptor = new JTextArea();
    private final double SCALE = 1.2;
    private int CELLSIZE;

    public LifeFrame() {
        setSize(800, 600);
        setTitle("Conway's Game of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                //TODO: handle resizing smaller
                centerGrid();
            }
        });

        JPanel lifePanel = new JPanel();
        lifePanel.setLayout(new BorderLayout());
        setContentPane(lifePanel);

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        lifePanel.add(sidePanel, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1, 5, 5));
        sidePanel.add(buttonPanel);

        grid = new Grid(getHeight(), getWidth());

        lifeComponent = new LifeComponent(grid);
        lifePanel.add(lifeComponent, BorderLayout.CENTER);
        CELLSIZE = lifeComponent.getCellSize();

        JButton blinkerButton = new JButton("Blinker");
        buttonPanel.add(blinkerButton);
        blinkerButton.addActionListener(evt -> blinker());

        JButton gosperButton = new JButton("Gosper Glider Gun");
        buttonPanel.add(gosperButton);
        gosperButton.addActionListener(evt -> gosper());

        JButton beaconButton = new JButton("Beacon");
        buttonPanel.add(beaconButton);
        beaconButton.addActionListener(evt -> beacon());

        JButton clearButton = new JButton("Clear");
        buttonPanel.add(clearButton);
        clearButton.addActionListener(evt -> lifeComponent.clearGrid());

        JTextArea textInput = new JTextArea(20, 20);
        textInput.setLineWrap(true);

        JPanel ebPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(evt -> resetGridViaParser(textInput.getText()));
        ebPanel.add(enterButton);

        JPanel textPanel = new JPanel();
        textPanel.add(textInput);
        sidePanel.add(textPanel);
        sidePanel.add(ebPanel);

        JPanel playPanel = new JPanel();
        playPanel.setLayout(new BoxLayout(playPanel, BoxLayout.LINE_AXIS));
        lifePanel.add(playPanel, BorderLayout.SOUTH);

        JButton pauseButton = new JButton("Pause");
        playPanel.add(pauseButton);
        pauseButton.addActionListener(evt -> lifeComponent.timer.stop());

        JButton playButton = new JButton("Play");
        playPanel.add(playButton);
        playButton.addActionListener(evt -> lifeComponent.timer.start());

        descriptor.setLineWrap(true);
        descriptor.setEditable(false);
        descriptor.setText(parser.parseComment(textInput.getText()));
        playPanel.add(descriptor);

    }

    private void blinker() {
        String str = "#C this is a blinker\n"
                + "x = 3, y = 3\n"
                + "3b$3o$3b";
        resetGridViaParser(str);
    }

    private void gosper() {
        String str = "#N Gosper glider gun\n"
                + "#C This was the first gun discovered.\n"
                + "#C As its name suggests, it was discovered by Bill Gosper.\n"
                + "x = 36, y = 9, rule = B3/S23\n"
                + "24bo$22bobo$12b2o6b2o12b2o$11bo3bo4b2o12b2o$2o8bo5bo3b2o$2o8bo3bob2o4b"
                + "obo$10bo5bo7bo$11bo3bo$12b2o!";
        resetGridViaParser(str);
    }

    private void beacon() {
        String str = "#N Beacon\n"
                + "#O John Conway\n"
                + "#C A common period 2 oscillator.\n"
                + "#C www.conwaylife.com/wiki/index.php?title=Beacon\n"
                + "x = 4, y = 4, rule = B3/S23\n"
                + "2o2b$o3b$3bo$2b2o!";
        resetGridViaParser(str);
    }

    private void resetGridViaParser(String rle) {
        //int[][] newGrid = parser.parse(rle);
        this.grid = new Grid(parser.parse(rle));
        descriptor.setText(parser.parseComment(rle));

        centerGrid();
    }

    private void centerGrid() {
        Grid newGrid = new Grid((int)(lifeComponent.getHeight() / CELLSIZE * SCALE),
                (int)(lifeComponent.getWidth() / CELLSIZE * SCALE));
        grid.centerGrid(newGrid);
        this.grid = newGrid;
        lifeComponent.resetGrid(grid);
    }

    public static void main(String[] args) {
        new LifeFrame().setVisible(true);
    }
}


