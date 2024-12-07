package hexlet.code;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static hexlet.code.ReadFile.getData;

public class App {
    public static void main(String[] args) throws IOException {
//        System.out.println("Hello, World!");
        int exitCode = new CommandLine(new Differ()).execute(args);
        System.exit(exitCode);
//        Path filepath1 = Paths.get("/app/file1.json").toAbsolutePath().normalize();
//        Path filepath2 = Paths.get("/app/file2.json").toAbsolutePath().normalize();

//        String file1 = Files.readString(filepath1);
//        String file2 = Files.readString(filepath2);
//
//        String pathToFile1 = "file1.json";
//        String pathToFile2 = "file2.json";


//        System.out.println(parsedFile1);

    }


}
