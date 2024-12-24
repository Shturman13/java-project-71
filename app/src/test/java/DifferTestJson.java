import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTestJson {
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

        var expected = "{\"SameKey\":{\"chars1\":[\"a\",\"b\",\"c\"],\"numbers1\":[1,2,3,4]},"
            + "\"KeyChanged\":{\"setting2\":[200,300],\"setting3\":[true,\"none\"],"
            + "\"chars2\":[[\"d\",\"e\",\"f\"],false],"
            + "\"default\":[null,[\"value1\",\"value2\"]],\"setting1\":[\"Some value\",\"Another value\"],"
            + "\"numbers2\":[[2,3,4,5],[22,33,44,55]],"
            + "\"checked\":[false,true],\"id\":[45,null]},\"KeyAdded\":{\"key2\":\"value2\",\"numbers4\":[4,5,6],"
            + "\"obj1\":{\"nestedKey\":\"value\",\"isNested\":true}},"
            + "\"KeyRemoved\":{\"key1\":\"value1\",\"numbers3\":[3,4,5]}}";

        var actualJson = Differ.generate(filepath6.toString(), filepath7.toString(), "json");
        assertEquals(expected, actualJson);

        var actualYaml = Differ.generate(filepath61.toString(), filepath71.toString(), "json");
        assertEquals(expected, actualYaml);
    }
}
