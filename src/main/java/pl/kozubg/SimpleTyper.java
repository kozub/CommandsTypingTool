package pl.kozubg;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class SimpleTyper {

    private static final Logger logger = Logger.getLogger(SimpleTyper.class.toString());
    private static final Path RESOURCE_DIR = Paths.get("/tmp/images");

    private Robot robo;
    private int screenshotsCounter = 0;

    public SimpleTyper() throws AWTException {
        robo = new Robot();
    }

    public void typeCommandsFromFileWithDelay(Path sourceFile, int delay) throws IOException, InterruptedException {
        logger.info("SimpleTyper.typeCommandsFromFileWithDelay() - invoked");
        for (String line : Files.readAllLines(sourceFile)) {
            SpecialTag tag = SpecialTag.findByValue(line);
            if (tag == null) {
                typeText(line);
                typeEnter();
            } else {
                takeScreenShot();
            }

            Thread.sleep(1000 * delay);
        }
        logger.info("SimpleTyper.typeCommandsFromFileWithDelay() - finished");
    }

    private void takeScreenShot() throws IOException {
        logger.info("SimpleTyper.takeScreenShot() - invoked");
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        System.out.println(screenRect.toString());
        BufferedImage capture = robo.createScreenCapture(screenRect);
        if (!Files.exists(RESOURCE_DIR))
            Files.createDirectories(RESOURCE_DIR);
        Path screenShot = RESOURCE_DIR.resolve(String.valueOf(++screenshotsCounter) + ".jpg");
        Files.createFile(screenShot);
        ImageIO.write(capture, "jpg", screenShot.toFile());
        logger.info("SimpleTyper.takeScreenShot() - finished");
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
