package hexlet.code;

import formatters.Json;
import formatters.Plain;
import formatters.Stylish;

import java.io.IOException;
import java.util.Map;

import static hexlet.code.Differ.PLAINFORMAT;
import static hexlet.code.Differ.STYLISHFORMAT;
import static hexlet.code.Differ.JSONFORMAT;

public class Formatter {
    public static String chooseFormat(Map<String, Map<String,
            Object>> allParametersMap, String format) throws IOException {
        String finalString = "";
        switch (format) {
            case STYLISHFORMAT:
                finalString = Stylish.stylish(allParametersMap);
                break;
            case PLAINFORMAT:
                finalString = Plain.plain(allParametersMap);
                break;
            case JSONFORMAT:
                finalString = Json.json(allParametersMap);
                break;
            default:
                throw new IllegalStateException("Unexpected value");
        }

        return finalString;
    }
}
