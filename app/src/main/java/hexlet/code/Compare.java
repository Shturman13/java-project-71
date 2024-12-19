package hexlet.code;

import java.util.HashMap;
import java.util.Map;

public class Compare {
    public static Map<String, Map<String, Object>> compare(Map<String, Object> dataFromFile1,
                                                           Map<String, Object> dataFromFile2) {
        var dataMap1 = new HashMap<>(dataFromFile1);
        var dataMap2 = new HashMap<>(dataFromFile2);

        Map<String, Map<String, Object>> allParametersMap = new HashMap<>();
        allParametersMap.put("sameValue", new HashMap<>());
        allParametersMap.put("keyExistIn2NotIn1", new HashMap<>());
        allParametersMap.put("differentParameter", new HashMap<>());
        allParametersMap.put("keyExistIn1NotIn2", new HashMap<>());

        var sameValue = allParametersMap.get("sameValue");
        var keyExistIn2NotIn1 = allParametersMap.get("keyExistIn2NotIn1");
        var differentParameter = allParametersMap.get("differentParameter");
        var keyExistIn1NotIn2 = allParametersMap.get("keyExistIn1NotIn2");

        dataMap2.forEach((key, value) -> {
            Object valueFromMap1 = dataMap1.get(key);
            Object valueFromMap2 = dataMap2.get(key);

            if (dataMap1.containsKey(key)) {
                if (valueFromMap1 != null && value != null && valueFromMap1.equals(valueFromMap2)) {
                    sameValue.put(key, value);
                } else {
                    differentParameter.put(key, (dataMap1.get(key) + " /changedTo/ " + value));
                }
            } else {
                keyExistIn2NotIn1.put(key, value);
            }
            dataMap1.remove(key);
        });

        if (!dataMap1.isEmpty()) {
            keyExistIn1NotIn2.putAll(dataMap1);
        }

        return allParametersMap;
    }

}
