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
        final int compareIndex = 10;
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
                StringBuilder output = new StringBuilder("Property ");

                switch (key) {
                    case ("SameKey"):
                        break;
                    case ("KeyAdded"):
                        if (checkForComplexValue(value1)) {
                            value1 = "[complex value]";
                        }
                        parameter = output.append(key1).append(" was added with value: ").append(value1)
                                .append("\n").toString();
                        outputList.add(parameter);
                        break;
                    case ("KeyChanged"):
                        Object initialValue;
                        Object finalValue;
                        if (value1 instanceof List) {
                            List<?> arrayList = (List<?>) value1;

                            initialValue = arrayList.get(0);
                            finalValue = arrayList.get(1);

                            StringBuilder singleQuoteInitialValue = new StringBuilder("'");
                            StringBuilder singleQuoteFinalValue = new StringBuilder("'");
                            if (initialValue instanceof String) {
                                initialValue = singleQuoteInitialValue.append(initialValue).append("'").toString();
                            }
                            if (finalValue instanceof String) {
                                finalValue = singleQuoteFinalValue.append(finalValue).append("'").toString();
                            }

                            if (checkForComplexValue(initialValue)) {
                                initialValue = "[complex value]";
                            }

                            if (checkForComplexValue(finalValue)) {
                                finalValue = "[complex value]";
                            }

                            parameter = output.append(key1).append(" was updated. From ").append(initialValue)
                                    .append(" to ").append(finalValue).append("\n").toString();
                            outputList.add(parameter);
                        }
                        break;
                    case ("KeyRemoved"):
                        parameter = output.append(key1).append(" was removed").append("\n").toString();
                        outputList.add(parameter);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value");
                }
            }

        });

        outputList.sort((v1, v2) -> CharSequence.compare(v1.substring(compareIndex), v2.substring(compareIndex)));
        var comparedParameters = outputList.stream().collect(Collectors.joining(""));
        return comparedParameters.substring(0, comparedParameters.length() - 1);
    }
}
