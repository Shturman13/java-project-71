package hexlet.code;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class Format {
    public static String stylish(Map<String, Map<String, Object>> allParametersMap) {
        var outputList = new ArrayList<String>();
        allParametersMap.forEach((key, value) -> {
            value.forEach((key1, value1) -> {
                String parameter;
                switch (key) {
                    case("sameValue"):
                        parameter = "    " + key1 + ": " + value1 + "\n";
                        outputList.add(parameter);
                        break;
                    case("keyExistIn2NotIn1"):
                        parameter = "  + " + key1 + ": " + value1 + "\n";
                        outputList.add(parameter);
                        break;
                    case("differentParameter"):
                        String[] splitValue = value1.toString().split(" /changedTo/ ");
                        parameter = "  - " + key1 + ": " + (key1 != null ? splitValue[0] : "null") + "\n" + "  + "
                                + key1 + ": " + splitValue[1] + "\n";
                        outputList.add(parameter);
                        break;
                    case("keyExistIn1NotIn2"):
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
