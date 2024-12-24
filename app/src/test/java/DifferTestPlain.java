import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTestPlain {
    public static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    @Test
    public void testGenerateComplexFilesJsonYaml() throws IOException {
        var filepath6 = getFixturePath("file6.json");
        var filepath7 = getFixturePath("file7.json");

        var filepath61 = getFixturePath("file6.yml");
        var filepath71 = getFixturePath("file7.yml");

        var expected = "Property 'chars2' was updated. From [complex value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to 'none'\n";

        var actualJson = Differ.generate(filepath6.toString(), filepath7.toString(), "plain");
        assertEquals(expected, actualJson);

        var actualYaml = Differ.generate(filepath61.toString(), filepath71.toString(), "plain");
        assertEquals(expected, actualYaml);
    }
}
