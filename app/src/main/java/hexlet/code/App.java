package hexlet.code;

import picocli.CommandLine;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        int exitCode = new CommandLine(new Differ()).execute(args);
        System.exit(exitCode);
    }
}
