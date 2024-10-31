package green.conway;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class LifeController {

    private final LifeFrame model;
    private final LifeComponent view;

    public LifeController(LifeFrame model, LifeComponent view) {
        this.model = model;
        this.view = view;
    }

    public void paste(String cbContents) {
        TextProcessor tp = new TextProcessor();
        try {
            if (tp.isUrl(cbContents)) {
                model.resetGridViaParser(tp.urlToString(new URL(cbContents)));
            } else if ((new File(cbContents).isFile())) {
                model.resetGridViaParser(tp.fileToString(new File(cbContents)));
            } else {
                model.resetGridViaParser(cbContents);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void startTimer(Grid grid) {
        view.timer = new Timer(400, evt -> {
            grid.nextGen();
            view.repaint();
        });
    }

    public void stopTimer() {
        view.timer.stop();
    }

    public void toggleCell(int screenY, int screenX) {
        int yPos = screenY / view.getCellSize();
        int xPos = screenX / view.getCellSize();
        if (view.getGrid().isAlive(yPos, xPos)) {
            view.getGrid().kill(yPos, xPos);
        } else {
            view.getGrid().setAlive(yPos, xPos);
        }
        view.repaint();

    }
}
