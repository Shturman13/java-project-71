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
    private static Path getPath(String pathToFile) {
        return Paths.get(pathToFile).toAbsolutePath().normalize();
    }

    private static String readFile(Path path) throws IOException {
        return Files.readString(path);
    }

    private static ObjectMapper defineFileType(String fileType) {
        ObjectMapper mapper = null;
        switch (fileType) {
            case("json"):
                mapper = new ObjectMapper();
                break;
            case("yml"):
                mapper = new YAMLMapper();
                break;
            case("yaml"):
                mapper = new YAMLMapper();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + fileType);
        }

        return mapper;
    }

    public static Map<String, Object> parse(String pathToFile) throws IOException {
        String[] split = pathToFile.split("\\.");
        Map<String, Object> map = defineFileType(split[1])
                .readValue(readFile(getPath(pathToFile.toString())), new TypeReference<>() { });
        return map;
    }
}

