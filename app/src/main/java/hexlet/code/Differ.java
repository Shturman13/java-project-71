package hexlet.code;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;

import static hexlet.code.Format.stylish;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference")
public class Differ implements Callable<Integer> {

    @Parameters(paramLabel = "filepath1", index = "0", description = "path to first file")
    private String filepath1;

    @Parameters(paramLabel = "filepath2", index = "1", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish",
            paramLabel = "format", description = "output format [default: stylish]")
    private String format;

    @Override
    public Integer call() throws Exception {
        if (format.equals("stylish")) {
            System.out.println(stylish(generate(filepath1, filepath2)));
        }
        return 0;
    }

    public static Map<String, Map<String, Object>> generate(String filepath1, String filepath2) throws IOException {
        Map<String, Object> parsedFile1 = Parser.parse(filepath1);
        Map<String, Object> parsedFile2 = Parser.parse(filepath2);
        Map<String, Map<String, Object>> result = Compare.compare(parsedFile1, parsedFile2);
//        System.out.println(parsedFile1);
//        System.out.println(parsedFile2);
//        System.out.println(result);
        return result;
    }
}
