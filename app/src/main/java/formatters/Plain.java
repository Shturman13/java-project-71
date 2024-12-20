package formatters;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Plain {
    private static boolean checkForComplexValue(Object value) {
        return value instanceof List || value instanceof Set || value instanceof Map || value instanceof Array;
    }

    public static String plain(Map<String, Map<String, Object>> allParametersMap) {
        var outputList = new ArrayList<String>();
        allParametersMap.forEach((key, value) -> {
            for (Map.Entry<String, Object> entry : value.entrySet()) {
                String key1 = entry.getKey();
                Object value1 = entry.getValue();
                if (checkForComplexValue(value1)) {
                    value1 = "[complex value]";
                }
                String parameter;
                switch (key) {
                    case ("sameValue"):
                        break;
                    case ("keyExistIn2NotIn1"):
                        parameter = "Property '" + key1 + "' was added with value: " + value1 + "\n";
                        outputList.add(parameter);
                        break;
                    case ("differentParameter"):
                        String[] splitValue = value1.toString().split(" /changedTo/ ");
                        var splitValue0 = (splitValue[0].contains("{")
                                || splitValue[0].contains("[") ? "[complex value]" : splitValue[0]);
                        var splitValue1 = (splitValue[1].contains("{")
                                || splitValue[1].contains("[") ? "[complex value]" : splitValue[1]);
                        parameter = "Property '" + key1 + "' was updated. From " + (key1 != null ? splitValue0 : "null")
                                + " to " + splitValue1 + "\n";
                        outputList.add(parameter);
                        break;
                    case ("keyExistIn1NotIn2"):
                        parameter = "Property '" + key1 + "' removed" + "\n";
                        outputList.add(parameter);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value");
                }
            }

        });

        outputList.sort((v1, v2) -> CharSequence.compare(v1.substring(10), v2.substring(10)));
        var comparedParameters = outputList.stream().collect(Collectors.joining(""));
        return comparedParameters;
    }
}
