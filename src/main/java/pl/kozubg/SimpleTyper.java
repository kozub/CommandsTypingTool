package pl.kozubg;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

public class SimpleTyper {

    private static final Logger logger = Logger.getLogger(SimpleTyper.class.toString());

    private Robot robo;

    public SimpleTyper() throws AWTException {
        robo = new Robot();
    }

    public void typeCommandsFromFileWithDelay(Path sourceFile, int delay) throws IOException, InterruptedException {
        logger.info("SimpleTyper.typeCommandsFromFileWithDelay() - invoked");
        for (String line : Files.readAllLines(sourceFile)) {
            typeText(line);
            typeEnter();
            Thread.sleep(1000 * delay);
        }
        logger.info("SimpleTyper.typeCommandsFromFileWithDelay() - finished");
    }

    private void typeText(String text) {
        for (char letter : text.toCharArray()) {
            typeLetter(letter);
        }
    }

    private void typeLetter(char letter) {
        type(KeyEvent.getExtendedKeyCodeForChar(letter));
    }

    private void typeEnter() {
        type(KeyEvent.VK_ENTER);
    }

    private void type(int key) {
        robo.keyPress(key);
        robo.keyRelease(key);
    }
}
