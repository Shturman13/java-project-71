package hexlet.code;

import formatters.Json;
import formatters.Plain;
import formatters.Stylish;

import java.io.IOException;
import java.util.Map;

public class Formatter {
    public static String chooseFormat(Map<String, Map<String,
            Object>> allParametersMap, String format) throws IOException {
        String finalString = "";
        switch (format) {
            case("stylish"):
                finalString = Stylish.stylish(allParametersMap);
                break;
            case("plain"):
                finalString = Plain.plain(allParametersMap);
                break;
            case("json"):
                finalString = Json.json(allParametersMap);
                break;
            default:
                throw new IllegalStateException("Unexpected value");
        }

        return finalString;
    }
}
