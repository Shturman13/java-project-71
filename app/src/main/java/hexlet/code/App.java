package hexlet.code;

import java.io.IOException;
import picocli.CommandLine;

public class App {
    public static void main(String[] args) throws IOException {
        int exitCode = new CommandLine(new Differ()).execute(args);
        System.exit(exitCode);
    }

}
