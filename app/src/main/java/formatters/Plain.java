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

                if (value1 instanceof String) {
                    value1 = "'" + value1 + "'";
                }
                key1 = "'" + key1 + "'";

                String parameter;
                switch (key) {
                    case ("SameKey"):
                        break;
                    case ("KeyAdded"):
                        if (checkForComplexValue(value1)) {
                            value1 = "[complex value]";
                        }
                        parameter = "Property " + key1 + " was added with value: " + value1 + "\n";
                        outputList.add(parameter);
                        break;
                    case ("KeyChanged"):
                        Object initialValue;
                        Object finalValue;

                        if (value1 instanceof List<?> && ((List<?>) value1).size() == 2) {
                            List<?> arrayList = (List<?>) value1;

                            initialValue = arrayList.get(0);
                            finalValue = arrayList.get(1);

                            if (initialValue instanceof String) {
                                initialValue = "'" + initialValue + "'";
                            }
                            if (finalValue instanceof String) {
                                finalValue = "'" + finalValue + "'";
                            }

                            if (checkForComplexValue(initialValue)) {
                                initialValue = "[complex value]";
                            }

                            if (checkForComplexValue(finalValue)) {
                                finalValue = "[complex value]";
                            }

                            parameter = "Property " + key1 + " was updated. From " + initialValue
                                    + " to " + finalValue + "\n";
                            outputList.add(parameter);
                        }
                        break;
                    case ("KeyRemoved"):
                        parameter = "Property " + key1 + " was removed" + "\n";
                        outputList.add(parameter);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value");
                }
            }

        });

        outputList.sort((v1, v2) -> CharSequence.compare(v1.substring(10), v2.substring(10)));
        var comparedParameters = outputList.stream().collect(Collectors.joining(""));
        return comparedParameters.substring(0, comparedParameters.length() - 1);
    }
}
