package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Compare {
    public static Map<String, Map<String, Object>> compare(Map<String, Object> dataFromFile1,
                                                           Map<String, Object> dataFromFile2) {
        var dataMap1 = new HashMap<>(dataFromFile1);
        var dataMap2 = new HashMap<>(dataFromFile2);

        Map<String, Map<String, Object>> allParametersMap = new HashMap<>();
        allParametersMap.put("SameKey", new HashMap<>());
        allParametersMap.put("KeyAdded", new HashMap<>());
        allParametersMap.put("KeyChanged", new HashMap<>());
        allParametersMap.put("KeyRemoved", new HashMap<>());

        var sameValue = allParametersMap.get("SameKey");
        var keyExistIn2NotIn1 = allParametersMap.get("KeyAdded");
        var differentParameter = allParametersMap.get("KeyChanged");
        var keyExistIn1NotIn2 = allParametersMap.get("KeyRemoved");

        dataMap2.forEach((key, value) -> {
            Object valueFromMap1 = dataMap1.get(key);
            Object valueFromMap2 = dataMap2.get(key);

            if (dataMap1.containsKey(key)) {
                if (valueFromMap1 != null && value != null && valueFromMap1.equals(valueFromMap2)) {
                    sameValue.put(key, value);
                } else {
                    var changedValue = new ArrayList<>();
                    changedValue.add(dataMap1.get(key));
                    changedValue.add(value);
                    differentParameter.put(key, changedValue);
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
