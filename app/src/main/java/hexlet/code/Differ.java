package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.Map;
import java.util.concurrent.Callable;

import static hexlet.code.ReadFile.getData;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference")
class Differ implements Callable<Integer> {

    @Parameters(paramLabel = "filepath1", index = "0", description = "path to first file")
    private String filepath1;

    @Parameters(paramLabel = "filepath2", index = "1", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    private String format = "stylish";

    @Override
    public Integer call() throws Exception { // your business logic goes here...
        var parsedFile1 = getData(filepath1);
        var parsedFile2 = getData(filepath2);
//        System.out.println(parsedFile1);
//        System.out.println(parsedFile2);
        Compare.compare(parsedFile1, parsedFile2);
//        byte[] fileContents = Files.readAllBytes(file.toPath());
//        byte[] digest = MessageDigest.getInstance(algorithm).digest(fileContents);
//        System.out.printf("%0" + (digest.length*2) + "x%n", new BigInteger(1, digest));
        return 0;
    }

    // this example implements Callable, so parsing, error handling and handling user
    // requests for usage help or version help can be done with one line of code.
//    public static void main(String... args) {
//        int exitCode = new CommandLine(new Differ()).execute(args);
//        System.exit(exitCode);
//    }
}

