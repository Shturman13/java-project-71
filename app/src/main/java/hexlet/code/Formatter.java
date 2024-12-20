package hexlet.code;

import formatters.Plain;
import formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String chooseFormat(Map<String, Map<String, Object>> allParametersMap, String format) {
        String finalString = "";
        if (format.equals("stylish")) {
            finalString = Stylish.stylish(allParametersMap);
        } else if (format.equals("plain")) {
            finalString = Plain.plain(allParametersMap);
        }
        return finalString;
    }
}
