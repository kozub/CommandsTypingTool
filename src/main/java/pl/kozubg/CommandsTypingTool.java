package pl.kozubg;


import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple tool created to allow automatically typing commands from file.
 *
 * Required params: path to source file and delay between typing lines (seconds).
 * (ex: "java CommandsTypingTool.class \home\marian\commands.txt 2")
 *
 */
public class CommandsTypingTool {

    private static final Logger logger = Logger.getLogger(CommandsTypingTool.class.getName());

    private SimpleTyper typer;
    private Path sourceFile;
    private int delay;

    public CommandsTypingTool(String[] args) throws AWTException {
        readParams(args);
        typer = new SimpleTyper();
    }

    public static void main( String[] args ) {
        try {
            CommandsTypingTool typer = new CommandsTypingTool(args);
            Thread.sleep(2000);
            typer.start();
        } catch (Exception e) {
            logger.log(Level.SEVERE, ":-( \nError:");
            e.printStackTrace();
        }
    }

    private void readParams(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Incorrect number of input params");
        }

        sourceFile = Paths.get(args[0]);
        delay = Integer.valueOf(args[1]);
    }

    private void start() throws IOException, InterruptedException {
        typer.typeCommandsFromFileWithDelay(sourceFile, delay);
    }
}
