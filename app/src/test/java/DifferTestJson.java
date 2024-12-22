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

        var expected = "{\"sameValue\":{\"chars1\":[\"a\",\"b\",\"c\"],\"numbers1\":[1,2,3,4]},"
               + "\"keyExistIn2NotIn1\":{\"key2\":\"value2\","
                + "\"numbers4\":[4,5,6],\"obj1\":{\"nestedKey\":\"value\","
                + "\"isNested\":true}},\"keyExistIn1NotIn2\":{\"key1\":\"value1\","
                + "\"numbers3\":[3,4,5]},\"differentParameter\":{\"setting2\":\"200 /changedTo/ 300\","
                + "\"setting3\":\"true /changedTo/ none\",\"chars2\":\"[d, e, f] /changedTo/ false\","
                + "\"default\":\"null /changedTo/ [value1, value2]\","
                + "\"setting1\":\"Some value /changedTo/ Another value\","
                + "\"numbers2\":\"[2, 3, 4, 5] /changedTo/ [22, 33, 44, 55]\",\"checked\":\"false /changedTo/ true\","
                + "\"id\":\"45 /changedTo/ null\"}}";
        var actualJson = Differ.generate(filepath6, filepath7, "json");
        assertEquals(expected, actualJson);

        var actualYaml = Differ.generate(filepath61, filepath71, "json");
        assertEquals(expected, actualYaml);
    }
}
