package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class ReadFile {
    public static Path getPath(String pathToFile) {
        return Paths.get(pathToFile).toAbsolutePath().normalize();
    }

    public static String readFile(Path path) throws IOException {
        return Files.readString(path);
    }

    public static Map<String, Object> getData(String pathToFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(readFile(getPath(pathToFile)), new TypeReference<>() { });
        return map;
    }
}
