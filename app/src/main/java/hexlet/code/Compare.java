package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Compare {
    public static String compare(Map<String, Object> dataFromFile1, Map<String, Object> dataFromFile2) {
        var outputList = new ArrayList<String>();

        var dataMap1 = new HashMap<>(dataFromFile1);
        var dataMap2 = new HashMap<>(dataFromFile2);

        dataFromFile2.forEach((key, value) -> {
            String sameParameter = "    " + key + ": " + value + "\n";
            String differentParameter = "  - " + key + ": " + dataMap1.get(key) + "\n" + "  + "
                    + key + ": " + value + "\n";

            String keyExistIn1NotIn2 = "  + " + key + ": " + value + "\n";

            var resultOfKeyExist = dataMap1.containsKey(key)
                    ? (dataMap1.get(key).equals(dataMap2.get(key)) ? sameParameter : differentParameter)
                    : (keyExistIn1NotIn2);
            dataMap1.remove(key);
            outputList.add(resultOfKeyExist);
        });
        if (!dataMap1.isEmpty()) {
            dataMap1.forEach((key, value) -> {
                String keyExistIn2NotIn1 = "  - " + key + ": " + dataMap1.get(key) + "\n";
                outputList.add(keyExistIn2NotIn1);
            });
        }
        outputList.sort((v1, v2) -> CharSequence.compare(v1.substring(4), v2.substring(4)));
        var comparedParameters = outputList.stream().collect(Collectors.joining("", "{\n", "}"));
        System.out.println(comparedParameters);
        return comparedParameters;
    }
}
