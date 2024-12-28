package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Compare {
    public static final String SAMEKEY = "SameKey";
    public static final String KEYADDED = "KeyAdded";
    public static final String KEYCHANGED = "KeyChanged";
    public static final String KEYREMOVED = "KeyRemoved";

    public static Map<String, Map<String, Object>> compare(Map<String, Object> dataFromFile1,
                                                           Map<String, Object> dataFromFile2) {
        Map<String, Map<String, Object>> allParametersMap = new HashMap<>();
        allParametersMap.put(SAMEKEY, new HashMap<>());
        allParametersMap.put(KEYADDED, new HashMap<>());
        allParametersMap.put(KEYCHANGED, new HashMap<>());
        allParametersMap.put(KEYREMOVED, new HashMap<>());

        var sameValue = allParametersMap.get(SAMEKEY);
        var keyExistIn2NotIn1 = allParametersMap.get(KEYADDED);
        var differentParameter = allParametersMap.get(KEYCHANGED);
        var keyExistIn1NotIn2 = allParametersMap.get(KEYREMOVED);

        dataFromFile2.forEach((key, value) -> {
            Object valueFromMap1 = dataFromFile1.get(key);
            Object valueFromMap2 = dataFromFile2.get(key);

            if (dataFromFile1.containsKey(key)) {
                if (valueFromMap1 != null && value != null && valueFromMap1.equals(valueFromMap2)) {
                    sameValue.put(key, value);
                } else {
                    var changedValue = new ArrayList<>();
                    changedValue.add(dataFromFile1.get(key));
                    changedValue.add(value);
                    differentParameter.put(key, changedValue);
                }
            } else {
                keyExistIn2NotIn1.put(key, value);
            }
            dataFromFile1.remove(key);
        });

        if (!dataFromFile1.isEmpty()) {
            keyExistIn1NotIn2.putAll(dataFromFile1);
        }
        return allParametersMap;
    }
}
