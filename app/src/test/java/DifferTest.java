import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

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

        var actualJson = Differ.generate(filepath1.toString(), filepath2.toString());
        assertEquals(expected, actualJson);
        var actualYaml = Differ.generate(filepath3.toString(), filepath4.toString());
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

        var actual = Differ.generate(filepath3.toString(), filepath4.toString());
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateYaml() throws IOException {
        var filepath3 = getFixturePath("file8.yml");
        var filepath4 = getFixturePath("file9.yml");

        var expected = "{\n" + "  - " + "delayTime: 30" + "\n" + "  - " + "follow: true" + "\n" + "  + "
                + "follow: false" + "\n" + "  - " + "host: not hexlet.io" + "\n" + "  + "
                + "host: hexlet.io" + "\n" + "  - " + "proxi: 123.234.34.21" + "\n" + "  + "
                + "proxy: 123.234.53.22" + "\n" + "  - " + "timeout: 50" + "\n" + "  + "
                + "timeout: 100" + "\n" + "  - " + "verbose: true" + "\n" + "  + "
                + "verbose: false" + "\n" + "}";
    }
}
