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

    public void paste(Object cbContents) {
        TextProcessor tp = new TextProcessor();
        try {
            String contentStr = cbContents.toString();
            if(tp.isUrl(cbContents)) {
                model.resetGridViaParser(tp.urlToString(new URL(contentStr)));
            } else if((new File(contentStr).isFile())) {
                model.resetGridViaParser(tp.fileToString(new File(contentStr)));
            } else {
                model.resetGridViaParser(contentStr);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

 /*   public void startTimer() {
        view.timer = new Timer(400, evt -> {
            view.grid.nextGen();
            view.repaint();
        });
    } */

    public void stopTimer() {

    }

    public void toggleCell(int screenY, int screenX) {
        int yPos = screenY / view.getCellSize();
        int xPos = screenX / view.getCellSize();
        if (view.grid.isAlive(yPos, xPos)) {
            view.grid.kill(yPos, xPos);
        } else {
            view.grid.setAlive(yPos, xPos);
        }
        view.repaint();

    }
}
