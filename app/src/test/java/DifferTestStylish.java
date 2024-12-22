import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTestStylish {

    public static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    @Test
    public void testGenerateJsonYaml() throws IOException {
        var filepath1 = getFixturePath("file1.json");
        var filepath2 = getFixturePath("file2.json");

        var filepath3 = getFixturePath("file1.yml");
        var filepath4 = getFixturePath("file2.yml");


        var expected = "{\n" + "  - " + "follow: false" + "\n" + "    " + "host: hexlet.io" + "\n"
                + "  - " + "proxy: 123.234.53.22" + "\n"
                + "  - " + "timeout: 50" + "\n" + "  + "
                + "timeout: 20" + "\n" + "  + " + "verbose: true" + "\n" + "}";

        var actualJson = Differ.generate(filepath1.toString(), filepath2.toString(), "stylish");
        assertEquals(expected, actualJson);
        var actualYaml = Differ.generate(filepath3.toString(), filepath4.toString(), "stylish");
        assertEquals(expected, actualYaml);
    }

    @Test
    public void testGenerateJson() throws IOException {
        var filepath3 = getFixturePath("file3.json");
        var filepath4 = getFixturePath("file4.json");

        var expected = "{\n" + "  + " + "delayTime: 30" + "\n" + "  - " + "follow: false" + "\n" + "  + "
                + "follow: true" + "\n" + "  - " + "host: hexlet.io" + "\n" + "  + "
                + "host: not hexlet.io" + "\n" + "  - " + "proxy: 123.234.53.22" + "\n" + "  + "
                + "proxy: 123.234.34.21" + "\n" + "    " + "timeout: 50" + "\n" + "  + " + "verbose: true" + "\n" + "}";

        var actual = Differ.generate(filepath3.toString(), filepath4.toString(), "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateYaml() throws IOException {
        var filepath8 = getFixturePath("file8.yml");
        var filepath9 = getFixturePath("file9.yml");

        var expected = "{\n" + "  - " + "delayTime: 30" + "\n" + "  - " + "follow: true" + "\n" + "  + "
                + "follow: false" + "\n" + "  - " + "host: not hexlet.io" + "\n" + "  + "
                + "host: hexlet.io" + "\n" + "  - " + "proxi: 123.234.34.21" + "\n" + "  + "
                + "proxy: 123.234.53.22" + "\n" + "  - " + "timeout: 50" + "\n" + "  + "
                + "timeout: 100" + "\n" + "  - " + "verbose: true" + "\n" + "  + "
                + "verbose: false" + "\n" + "}";

        var actual = Differ.generate(filepath8.toString(), filepath9.toString(), "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateComplexFilesJsonYaml() throws IOException {
        var filepath6 = getFixturePath("file6.json");
        var filepath7 = getFixturePath("file7.json");

        var filepath61 = getFixturePath("file6.yml");
        var filepath71 = getFixturePath("file7.yml");

        var expected = "{\n" + "    chars1: [a, b, c]" + "\n" + "  - chars2: [d, e, f]" + "\n"
                + "  + chars2: false" + "\n" + "  - checked: false" +  "\n" + "  + checked: true" + "\n"
                + "  - default: null" + "\n" + "  + default: [value1, value2]" + "\n"
                + "  - id: 45" + "\n" + "  + id: null" + "\n" + "  - key1: value1" + "\n"
                + "  + key2: value2" + "\n" + "    numbers1: [1, 2, 3, 4]" + "\n"
                + "  - numbers2: [2, 3, 4, 5]" + "\n" + "  + numbers2: [22, 33, 44, 55]" + "\n"
                + "  - numbers3: [3, 4, 5]" + "\n" + "  + numbers4: [4, 5, 6]" + "\n"
                + "  + obj1: {nestedKey=value, isNested=true}" + "\n" + "  - setting1: Some value"
                + "\n" + "  + setting1: Another value" + "\n" + "  - setting2: 200" + "\n"
                + "  + setting2: 300" + "\n" + "  - setting3: true" + "\n"
                + "  + setting3: none" + "\n" + "}";

        var actualJson = Differ.generate(filepath6.toString(), filepath7.toString(), "stylish");
        assertEquals(expected, actualJson);

        var actualYaml = Differ.generate(filepath61.toString(), filepath71.toString(), "stylish");
        assertEquals(expected, actualYaml);

    }
}
