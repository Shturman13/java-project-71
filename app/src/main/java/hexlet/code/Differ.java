package hexlet.code;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

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
        Map<String, Object> parsedFile1 = getData(filepath1);
        Map<String, Object> parsedFile2 = getData(filepath2);
//        System.out.println(parsedFile1);
//        System.out.println(parsedFile2);
        Compare.compare(parsedFile1, parsedFile2);
        return 0;
    }
}

