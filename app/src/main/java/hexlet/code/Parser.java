package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Path getPath(String pathToFile) {
        return Paths.get(pathToFile).toAbsolutePath().normalize();
    }

    public static String readFile(Path path) throws IOException {
        return Files.readString(path);
    }

    public static Map<String, Object> parse(Path pathToFile) throws IOException {
        var pathToFileString = pathToFile.toString();
        String[] split = pathToFileString.split("\\.");
        Map<String, Object> map;
        ObjectMapper mapper = null;
        switch (split[1]) {
            case("json"):
                mapper = new ObjectMapper();
                break;
            case("yml"):
                mapper = new YAMLMapper();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + split[1]);
        }
        map = mapper.readValue(readFile(getPath(pathToFile.toString())), new TypeReference<>() { });
        return map;
    }
}

