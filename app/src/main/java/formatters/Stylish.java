package formatters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stylish {
    public static String stylish(Map<String, Map<String, Object>> allParametersMap) {
        var outputList = new ArrayList<String>();
        allParametersMap.forEach((key, value) -> {
            value.forEach((key1, value1) -> {
                String parameter;
                switch (key) {
                    case("SameKey"):
                        parameter = "    " + key1 + ": " + value1 + "\n";
                        outputList.add(parameter);
                        break;
                    case("KeyAdded"):
                        parameter = "  + " + key1 + ": " + value1 + "\n";
                        outputList.add(parameter);
                        break;
                    case("KeyChanged"):
                        Object initialValue;
                        Object finalValue;

                        if (value1 instanceof List<?> && ((List<?>) value1).size() == 2) {
                            List<?> arrayList = (List<?>) value1;

                            initialValue = arrayList.get(0);
                            finalValue = arrayList.get(1);
                            parameter = "  - " + key1 + ": " + (initialValue) + "\n" + "  + "
                                    + key1 + ": " + finalValue + "\n";
                            outputList.add(parameter);
                        }
                        break;
                    case("KeyRemoved"):
                        parameter = "  - " + key1 + ": " + value1 + "\n";
                        outputList.add(parameter);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value");
                }
            });

        });

        outputList.sort((v1, v2) -> CharSequence.compare(v1.substring(4), v2.substring(4)));
        var comparedParameters = outputList.stream().collect(Collectors.joining("", "{\n", "}"));
        return comparedParameters;
    }
}
